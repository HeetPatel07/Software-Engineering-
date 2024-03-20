package com.example.myapplication.presentation;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AccountManagement;

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

        ImageView viewFavBooks = findViewById(R.id.viewFavouriteBooks);
        viewFavBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requiredBook = new Intent(LoggedinActivity.this, FavouriteBooksActivity.class);
                startActivity(requiredBook);
            }
        });


        ImageView viewTransactions = findViewById(R.id.viewTransactions);
        viewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requiredBook = new Intent(LoggedinActivity.this, TransactionsActivity.class);
                startActivity(requiredBook);
            }
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoggedinActivity.this);
                builder.setTitle("Confirm Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform logout actions
                                Intent requiredBook = new Intent(LoggedinActivity.this, HomePageActivity.class);
                                startActivity(requiredBook);
                                AccountManagement logout = new AccountManagement(Services.getUserDatabase());
                                logout.logoutUser();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss the dialog
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


    }
}
