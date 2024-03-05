package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.business.AuthenticatedUser;

public class LoggedinActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_activity);
        initFooterButtons();
        ImageView viewAccount = findViewById(R.id.viewAccountInfo);

        viewAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                Intent signUp = new Intent(LoggedinActivity.this, ChangeAccount.class);
                startActivity(signUp);
            }
        });


        ImageView viewCourseBooks = findViewById(R.id.viewRequiredCourseBook);
        viewCourseBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requiredBook = new Intent(LoggedinActivity.this, CoursesBooksActivity.class);
                startActivity(requiredBook);
            }
        });
    }

    private void initFooterButtons(){
        ImageView profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedinActivity.this, LoggedinActivity.class));
            }
        });

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedinActivity.this,HomePageActivity.class));
            }
        });

        ImageView libraryButton = findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedinActivity.this, LibraryActivity.class));
            }
        });

    }

}
