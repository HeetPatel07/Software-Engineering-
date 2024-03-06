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



public class ChangeAccount extends AppCompatActivity {

    private AccountManagement accountManagement;
    protected void onCreate(Bundle savedInstanceState) {

        //make sure the use is created and then only we come to this code

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile_activity);

        RadioGroup group = findViewById(R.id.radio_userType);
        RadioButton radioStu = findViewById(R.id.radio_student);
        RadioButton radioProf = findViewById(R.id.radio_professor);

        accountManagement = new AccountManagement(Services.getUserDatabase());
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Disable both RadioButtons once one is selected
                radioStu.setEnabled(false);
                radioProf.setEnabled(false);
            }
        });


        Button confirm = findViewById(R.id.account_confirm);
        Button back = findViewById(R.id.button_back_account_management);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(ChangeAccount.this, LoggedinActivity.class);
                startActivity(signUp);
                Toast.makeText(ChangeAccount.this, "Changes unsaved", Toast.LENGTH_SHORT).show();
            }

        });


        // Set the text for the button to reuse the same layout
        confirm.setText("Update");

        EditText username,address,password;
         username = findViewById(R.id.enter_username_field);
         address = findViewById(R.id.enter_address_field);
         password = findViewById(R.id.enter_password_field);


        User userObj = AuthenticatedUser.getInstance().getUser();

        String  enteredUsername,enteredPassword,enteredAddress;

         enteredUsername = userObj.getName();
         enteredPassword = userObj.getPassword();
         enteredAddress = userObj.getAddress();

        String type = userObj.getType();
         RadioGroup radioGroup = findViewById(R.id.radio_userType);

        // Pre-select the RadioButton based on the userType
        if ("Student".equals(type)) {
            radioGroup.check(R.id.radio_student);
        } else if ("Professor".equals(type)) {
            radioGroup.check(R.id.radio_professor);
        }

         username.setText(enteredUsername);
         password.setText(enteredPassword);
         address.setText(enteredAddress);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newUsername = findViewById(R.id.enter_username_field);
                EditText newAddress = findViewById(R.id.enter_address_field);
                EditText newPassword = findViewById(R.id.enter_password_field);

                String newName, newAdd, newPass;

                newName = newUsername.getText().toString();
                newAdd = newAddress.getText().toString();
                newPass = newPassword.getText().toString();

                try {
                    if(newAdd.isEmpty()) throw new IllegalArgumentException("Please enter the address correctly. No empty address allowed.");
                    if(type.isEmpty()) throw new IllegalArgumentException("Please select your role.");

                    boolean newUsernameSetup = accountManagement.setNewUserName(newName);
                    boolean newUserAdd = accountManagement.setNewUserAddress(newAdd);
                    boolean newUserPassword = accountManagement.setNewPassword(newPass);

                    if (newUsernameSetup && newUserAdd && newUserPassword) {
                        Toast.makeText(ChangeAccount.this, "user info set up successfully", Toast.LENGTH_SHORT).show();
                        Intent signUp = new Intent(ChangeAccount.this, LoggedinActivity.class);
                        startActivity(signUp);
                    } else {
                        if(!newUsernameSetup){
                            Toast.makeText(ChangeAccount.this, "Username updated failed", Toast.LENGTH_SHORT).show();
                        }
                        if(!newUserAdd){
                            Toast.makeText(ChangeAccount.this, "Address updated failed", Toast.LENGTH_SHORT).show();
                        }
                        if(!newUserPassword){
                            Toast.makeText(ChangeAccount.this, "Password updated failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IllegalArgumentException e) {
                    // Catch IllegalArgumentException to show specific error messages
                    Toast.makeText(ChangeAccount.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
