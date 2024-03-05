package com.example.myapplication.presentation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Models.Book;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.BookManagement;

public class BookInfoActivity extends AppCompatActivity {

    BookManagement bookList = new BookManagement(Services.getBookDatabase());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_activity);
        initFooterButtons();

        Book book = getBookFromIntent();
        if (book != null) {
            displayBookInfo(book);
        } else {
            Toast.makeText(this, "Error loading book details.", Toast.LENGTH_SHORT).show();
        }

        Button buyButton = findViewById(R.id.buyBookButton);
        Button saveButton= findViewById(R.id.saveBookButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderConstructionAlert();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderConstructionAlert();
            }
        });

    }

    private void initFooterButtons(){
        ImageView profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AuthenticatedUser.getInstance().getUser() != null) {
                    startActivity(new Intent(BookInfoActivity.this,LoggedinActivity.class));
                } else {
                    startActivity(new Intent(BookInfoActivity.this, LoginActivity.class));
                }
            }
        });

        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookInfoActivity.this,HomePageActivity.class));
            }
        });

        ImageView libraryButton = findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AuthenticatedUser.getInstance().getUser() != null) {
                    startActivity(new Intent(BookInfoActivity.this,LibraryActivity.class));
                } else {
                    startActivity(new Intent(BookInfoActivity.this, LoginActivity.class));
                }
            }
        });

    }

    // Function to create and show the "Under Construction" alert
    private void showUnderConstructionAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Under construction")
                .setMessage("More to come in next iterations")

                // Set a single "OK" button to close the alert
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @SuppressLint("ObsoleteSdkInt")
    private Book getBookFromIntent() {
        int bookId = getIntent().getIntExtra("bookId", -1);
        if (bookId != -1) {
            // Use BookManager to get book by ID

            return bookList.findBookWithID(bookId);

        } else {
            // Handle the case where book ID is not provided
            return null;
        }
    }


    @SuppressLint("SetTextI18n")
    private void displayBookInfo(Book book) {
        setTextWithFormat(R.id.bookName, "Name: %s", book.getBookName());
        setTextWithFormat(R.id.bookAuthor, "Author: %s", book.getAuthorName());
        setTextWithFormat(R.id.bookPrice, "Price: $%.2f", book.getPrice());


        setRating(R.id.bookRating, book.getOverallBookRating());
        setText(R.id.bookDescription, book.getDescription());
    }

    private void setText(int textViewId, String text) {
        TextView textView = findViewById(textViewId);
        textView.setText(text);
    }

    private void setTextWithFormat(int textViewId, String format, Object... args) {
        TextView textView = findViewById(textViewId);
        textView.setText(String.format(format, args));
    }

    private void setRating(int ratingBarId, float rating) {
        RatingBar ratingBar = findViewById(ratingBarId);
        ratingBar.setRating(rating);
    }
}
