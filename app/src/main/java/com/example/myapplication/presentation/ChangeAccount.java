package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.Models.User;
import com.example.myapplication.customException.UserCreationException;

public class ChangeAccount extends AppCompatActivity {

    private AccountManagement accountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile_activity);

        // Initialize AccountManagement
        accountManagement = new AccountManagement(Services.getUserDatabase());

        // RadioGroup and RadioButtons initialization
        RadioGroup group = findViewById(R.id.radio_userType);
        RadioButton radioStu = findViewById(R.id.radio_student);
        RadioButton radioProf = findViewById(R.id.radio_professor);

        // Disable RadioButtons once one is selected
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioStu.setEnabled(false);
                radioProf.setEnabled(false);
            }
        });

        // Button initialization
        Button confirm = findViewById(R.id.account_confirm);
        Button back = findViewById(R.id.button_back_account_management);

        // Back button click listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the previous activity
                Intent signUp = new Intent(ChangeAccount.this, LoggedinActivity.class);
                startActivity(signUp);
                // Display a toast message
                Toast.makeText(ChangeAccount.this, "Changes unsaved", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the text for the confirm button
        confirm.setText("Update");

        // EditText initialization
        EditText username = findViewById(R.id.enter_username_field);
        EditText address = findViewById(R.id.enter_address_field);
        EditText password = findViewById(R.id.enter_password_field);

        // Get the current user information
        User userObj = AuthenticatedUser.getInstance().getUser();
        String enteredUsername = userObj.getUsername();
        String enteredPassword = userObj.getPassword();
        String enteredAddress = userObj.getAddress();
        String type = userObj.getType();

        // Pre-select the RadioButton based on the userType
        if ("Student".equals(type)) {
            group.check(R.id.radio_student);
        } else if ("Professor".equals(type)) {
            group.check(R.id.radio_professor);
        }

        // Set EditText fields with the current user information
        username.setText(enteredUsername);
        password.setText(enteredPassword);
        address.setText(enteredAddress);

        // Confirm button click listener
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated user information from EditText fields
                EditText newUsername = findViewById(R.id.enter_username_field);
                EditText newAddress = findViewById(R.id.enter_address_field);
                EditText newPassword = findViewById(R.id.enter_password_field);

                String newName = newUsername.getText().toString();
                String newAdd = newAddress.getText().toString();
                String newPass = newPassword.getText().toString();

                try {
                    // Validate input fields
                    if (newAdd.isEmpty())
                        throw new IllegalArgumentException("Please enter the address correctly. No empty address allowed.");
                    if (type.isEmpty())
                        throw new IllegalArgumentException("Please select your role.");

                    // Update user information
                    boolean newUsernameSetup = accountManagement.setNewUserName(newName);
                    boolean newUserAdd = accountManagement.setNewUserAddress(newAdd);
                    boolean newUserPassword = accountManagement.setNewPassword(newPass);

                    // Display appropriate message based on update status
                    if (newUsernameSetup && newUserAdd && newUserPassword) {
                        Toast.makeText(ChangeAccount.this, "User info set up successfully", Toast.LENGTH_SHORT).show();
                        // Navigate back to the previous activity
                        Intent signUp = new Intent(ChangeAccount.this, LoggedinActivity.class);
                        startActivity(signUp);
                    } else {
                        if (!newUsernameSetup) {
                            Toast.makeText(ChangeAccount.this, "Username update failed", Toast.LENGTH_SHORT).show();
                        }
                        if (!newUserAdd) {
                            Toast.makeText(ChangeAccount.this, "Address update failed", Toast.LENGTH_SHORT).show();
                        }
                        if (!newUserPassword) {
                            Toast.makeText(ChangeAccount.this, "Password update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (UserCreationException e) {
                    // Catch IllegalArgumentException to show specific error messages
                    Toast.makeText(ChangeAccount.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
