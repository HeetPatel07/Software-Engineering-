package com.example.myapplication.presentation;
import android.content.Intent;

import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AuthenticationManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.business.utlis.FooterUtility;
import com.example.myapplication.persistence.stub.DummyDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameEntered;
    private EditText passwordEntered;
    private AuthenticationManager authenticationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        FooterUtility.initFooterButtons(this);
        initializeViews();
        initializeAuthenticationManager();
        setupListeners();
    }

    private void initializeViews() {
        userNameEntered = findViewById(R.id.userNameTextInput);
        passwordEntered = findViewById(R.id.passwordTextInput);
    }

    private void initializeAuthenticationManager() {
        authenticationManager = new AuthenticationManager(Services.getUserDatabase());
    }

    private void setupListeners() {
        setupBackToHomepageButton();
        setupSignupButton();
        setupLoginButton();
    }

    private void setupBackToHomepageButton() {
        Button backToHomepageButton = findViewById(R.id.backToHomeButton);
        backToHomepageButton.setOnClickListener(v -> navigateToHomePage());
    }

    private void setupSignupButton() {
        Button signupButton = findViewById(R.id.create_account_button);
        signupButton.setOnClickListener(v -> navigateToSignupPage());
    }

    private void setupLoginButton() {
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void navigateToHomePage() {
        startActivity(new Intent(this, HomePageActivity.class));
    }

    private void navigateToSignupPage() {
        startActivity(new Intent(this, AccountActivity.class));
    }

    private void attemptLogin() {
        String userName = userNameEntered.getText().toString();
        String password = passwordEntered.getText().toString();

        if (authenticationManager.authenticateUser(userName, password) && !userName.isEmpty() && !password.isEmpty()) {
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            navigateToHomePage(); // Assuming successful login navigates to the HomePage
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
