package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import com.example.myapplication.R;
import com.example.myapplication.business.AuthenticatedUser;
import com.example.myapplication.Models.User;



public class ChangeAccount extends GlobalActivity{


    protected void onCreate(Bundle savedInstanceState) {

        //make sure the use is created and then only we come to this code

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile_activity);


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

                if(newName.length()<=3 || newPass.length()<=4){

                    Toast.makeText(ChangeAccount.this, "Please make sure you entered the username and the password correctly", Toast.LENGTH_SHORT).show();

                }else {

                    userObj.setName(newName);
                    userObj.setAddress(newAdd);
                    userObj.setPassword(enteredPassword, newPass);

                    Intent signUp = new Intent(ChangeAccount.this, LoggedinActivity.class);
                    startActivity(signUp);
                    Toast.makeText(ChangeAccount.this, "Changes saved", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
