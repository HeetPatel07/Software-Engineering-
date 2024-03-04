package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.business.AuthenticatedUser;
import com.example.myapplication.business.CourseManagement;
import com.example.myapplication.business.utlis.RandomGenerator;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.persistence.DummyDatabase;

public class GlobalActivity extends AppCompatActivity {

    private Database database;
    private static boolean flag= false; //to make sure the database is populated only once

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_footer_activity);

        database = initializeDatabase();

        if(!flag)
            populateDatabase();

        setupUI();
    }

    private Database initializeDatabase() {
        return DummyDatabase.getInstance();
    }

    private void populateDatabase() {
        populateUsers();
        populateBooks();
        flag=true;  //this make the dummy data only once
    }

    private void populateUsers() {
        for (int i = 0; i < 10; i++) {
            database.addUser(
                    RandomGenerator.getRandomName(),
                    "12345", // Assuming a default password
                    "User",
                    RandomGenerator.getRandomAddress()
            );
        }
    }

    private void populateBooks() {
        for (int i = 0; i < 30; i++) {
            database.addBook(
                    i,
                    RandomGenerator.getRandomBookName(),
                    RandomGenerator.getRandomPrice(),
                    String.format("This is the book description of %s.", RandomGenerator.getRandomBookName()),
                    RandomGenerator.randomEdition(),
                    RandomGenerator.randomAuthorName()
            );
        }
    }

    protected void setupUI() {
        setupProfileImageButton();
        setupHomeButton();
        setupLibraryButton();
    }

    private void setupProfileImageButton() {
        ImageView profileImageButton = findViewById(R.id.profileButton);
        Animation buttonJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.button_jump_animation);
        profileImageButton.setOnClickListener(v -> {
            v.startAnimation(buttonJumpAnimation);
            navigateToLogin();
        });
    }


    private void setupHomeButton() {
        ImageView homeButton = findViewById(R.id.homeButton);
        Animation buttonJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.button_jump_animation);
        homeButton.setOnClickListener(v -> {
            v.startAnimation(buttonJumpAnimation);
            navigateToHome();
        });
    }

    private void setupLibraryButton(){
        ImageView libraryButton = findViewById(R.id.libraryButton);
        Animation buttonJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.button_jump_animation);
        libraryButton.setOnClickListener(v->{
            v.startAnimation(buttonJumpAnimation);
            navigateToLibrary();
        });
    }

    private void navigateToLogin() {
        if (AuthenticatedUser.getInstance().getUser() != null) {
            startActivity(new Intent(this,LoggedinActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void navigateToHome() {
        startActivity(new Intent(this, HomePageActivity.class));
    }

    private void navigateToLibrary(){
        startActivity(new Intent(this, LibraryActivity.class));
    }
}
