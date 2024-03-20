package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class LoggedinActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_activity);
        FooterUtility.initFooterButtons(this);
        ImageView viewAccount = findViewById(R.id.viewAccountInfo);

        viewAccount.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(LoggedinActivity.this, CoursesBooksActivity.class);
                startActivity(intent);
            }
        });

        ImageView viewFavBooks= findViewById(R.id.viewFavouriteBooks);
        viewFavBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requiredBook = new Intent(LoggedinActivity.this, FavouriteBooksActivity.class);
                startActivity(requiredBook);
            }
        });


        ImageView viewTransactions= findViewById(R.id.viewTransactions);
        viewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requiredBook = new Intent(LoggedinActivity.this, TransactionsActivity.class);
                startActivity(requiredBook);
            }
        });
    }
}
