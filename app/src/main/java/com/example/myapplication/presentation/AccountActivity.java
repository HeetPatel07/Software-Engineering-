package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.business.AccountManagement;
import com.example.myapplication.business.AuthenticatedUser;
import com.example.myapplication.persistence.DummyDatabase;

public class AccountActivity extends GlobalActivity {

    private EditText enterUsernameField, enterAddressField, enterPasswordField;
    private AccountManagement accountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile_activity);
        initializeViews();
        initializeAccountManagement();
        setupListeners();
    }

    private void initializeViews() {
        enterUsernameField = findViewById(R.id.enter_username_field);
        enterAddressField = findViewById(R.id.enter_address_field);
        enterPasswordField = findViewById(R.id.enter_password_field);
    }

    private void initializeAccountManagement() {
        accountManagement = new AccountManagement((DummyDatabase) DummyDatabase.getInstance());
    }

    private void setupListeners() {
        setupBackToAccountButton();
        setupCreateAccountButton();
    }

    private void setupBackToAccountButton() {
        Button backToAccountButton = findViewById(R.id.button_back_account_management);
        backToAccountButton.setOnClickListener(v -> navigateToLoginActivity());
    }

    private void setupCreateAccountButton() {
        Button createAccountButton = findViewById(R.id.account_confirm);
        createAccountButton.setOnClickListener(v -> createAccount());
    }

    private void navigateToLoginActivity() {

        Intent intent = new Intent();

        if(AuthenticatedUser.getInstance().getUser()==null) {
             intent = new Intent(this, LoginActivity.class);

        }else{
            intent = new Intent(this, ChangeAccount.class);
        }

        startActivity(intent);
    }

    private void createAccount() {
        String username = enterUsernameField.getText().toString();
        String address = enterAddressField.getText().toString();
        String password = enterPasswordField.getText().toString();

        if (username.isEmpty() || address.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean userCreated = accountManagement.createNewUser(username, password, address);
            if (userCreated) {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                navigateToLoginActivity(); // Optionally navigate to login activity upon successful account creation
            } else {
                Toast.makeText(this, "Failed to create account, something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
