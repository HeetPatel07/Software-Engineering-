package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.authentication.AuthenticatedUser;


public class AccountActivity extends AppCompatActivity {

    private EditText enterUsernameField, enterAddressField, enterPasswordField;
    private RadioButton studentTypeButton, professorTypeButton;
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
        studentTypeButton = findViewById(R.id.radio_student);
        professorTypeButton = findViewById(R.id.radio_professor);
    }

    private void initializeAccountManagement() {
        accountManagement = new AccountManagement(Services.getUserDatabase());
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

        Intent intent;

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
        String type = "";
        if(studentTypeButton.isChecked()){
            type = studentTypeButton.getText().toString();
        }else if(professorTypeButton.isChecked()){
            type = professorTypeButton.getText().toString();
        }

        try {
            if(address.isEmpty()) throw new IllegalArgumentException("Please enter the address correctly.. No empty address allowed.");
            if(type.isEmpty()) throw new IllegalArgumentException("Please select your role.");

            boolean userCreated = accountManagement.createNewUser(username, password, type, address);
            if (userCreated) {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();

                navigateToLoginActivity(); // Optionally navigate to login activity upon successful account creation
            } else {
                Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalArgumentException e) {
            // Catch IllegalArgumentException to show specific error messages
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
