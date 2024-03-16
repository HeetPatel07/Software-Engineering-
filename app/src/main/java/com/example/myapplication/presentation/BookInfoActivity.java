package com.example.myapplication.presentation;

import android.annotation.SuppressLint;

import com.example.myapplication.business.management.CheckoutManagement;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.Models.Book;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.BookManagement;
import com.example.myapplication.Models.Rating;
import com.example.myapplication.business.management.FavouriteBookManagement;
import com.example.myapplication.customException.CheckoutException;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {
    // Declare variables
    User currUser;
    private BookManagement bookList = new BookManagement(Services.getBookDatabase());
    private CheckoutManagement shoppingCart = new CheckoutManagement();
    private FavouriteBookManagement saveBookManager = new FavouriteBookManagement(Services.getFavBooksDatabase());

    // UI elements
    private LinearLayout commentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_activity);

        // Initialize footer buttons
        FooterUtility.initFooterButtons(this);

        // Initialize UI views
        initializeViews();

        // Get current user from authentication
        currUser = AuthenticatedUser.getInstance().getUser();

        // Get book information from the intent
        Book book = getBookFromIntent();

        // Display book information or show an error message
        if (book != null) {
            displayBookInfo(book);
        } else {
            Toast.makeText(this, "Error loading book details.", Toast.LENGTH_SHORT).show();
        }

        // Set click listeners for buy and save buttons
        Button buyButton = findViewById(R.id.buyBookButton);
        Button saveButton = findViewById(R.id.saveBookButton);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is logged in before allowing to buy
                if (currUser == null) {
                    Toast.makeText(BookInfoActivity.this, "Login Firstly", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookInfoActivity.this, LoginActivity.class));
                } else {
                    // add this to the shopping cart
                    try {
                        shoppingCart.buyBook(book);
                    } catch (CheckoutException mssg) {
                        Toast.makeText(BookInfoActivity.this, mssg.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currUser == null) {
                    Toast.makeText(BookInfoActivity.this, "Login Firstly", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookInfoActivity.this, LoginActivity.class));
                } else {
                    //add this to the favourite books list
                    saveBookManager.addFavBook(currUser.getUserID(), book);
                    Toast.makeText(BookInfoActivity.this, "Book" + book.getBookName() + " is now in your favourite books", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("ObsoleteSdkInt")
    private Book getBookFromIntent() {
        int bookId = getIntent().getIntExtra("bookId", -1);
        // Use BookManager to get book by ID
        return bookList.findBookWithID(bookId);

    }

    @SuppressLint("SetTextI18n")
    private void displayBookInfo(Book book) {
        // Set book information to respective UI elements
        setTextWithFormat(R.id.bookName, "Name: %s", book.getBookName());
        setTextWithFormat(R.id.bookAuthor, "Author: %s", book.getAuthorName());
        setTextWithFormat(R.id.bookPrice, "Price: $%.2f", book.getPrice());
        setTextWithFormat(R.id.bookEdition, "%.2f", book.getBookEdition());
        setRating(R.id.bookRating, book.getOverallBookRating());
        setText(R.id.bookDescription, book.getDescription());

        // Configure and display comments
        configureComment(book);
    }

    private void setText(int textViewId, String text) {
        // Set text to the specified TextView
        TextView textView = findViewById(textViewId);
        textView.setText(text);
    }

    private void setTextWithFormat(int textViewId, String format, Object... args) {
        // Set formatted text to the specified TextView
        TextView textView = findViewById(textViewId);
        textView.setText(String.format(format, args));
    }

    private void setRating(int ratingBarId, float rating) {
        // Set rating to the specified RatingBar
        RatingBar ratingBar = findViewById(ratingBarId);
        ratingBar.setRating(rating);
    }

    private void initializeViews() {
        // Initialize the comment container view
        this.commentContainer = findViewById(R.id.commentsContainer);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void configureComment(Book book) {
        // Clear existing comments and add new comments to the comment container
        commentContainer.removeAllViews();
        ArrayList<Rating> list = book.getRatings();

        for (Rating rating : list) {
            // Create and configure comment view for each rating
            View commentView = createCommentView();
            configureCommentView(commentView, rating);
            commentContainer.addView(commentView);
        }
    }

    private View createCommentView() {
        // Inflate the comment_box_activity layout for each comment
        LayoutInflater inflater = LayoutInflater.from(this);
        return inflater.inflate(R.layout.comment_box_activity, commentContainer, false);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void configureCommentView(View commentView, Rating rating) {
        // Configure and display the rating and comment in the comment view
        TextView outUserName = commentView.findViewById(R.id.userNameComment);
        TextView outRating = commentView.findViewById(R.id.userRating);
        TextView outComment = commentView.findViewById(R.id.userComment);
        outRating.setText("Rating: " + rating.getRating() + " / 5");
        outComment.setText("Comment: " + rating.getComment());
        outUserName.setText("Username: " + rating.getAuthorName());
    }
}
