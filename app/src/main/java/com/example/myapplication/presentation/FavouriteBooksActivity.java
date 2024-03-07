package com.example.myapplication.presentation;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.FavouriteBookManagement;

import java.util.List;

public class FavouriteBooksActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private FavouriteBookManagement favBooksDB;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_book_activity);
        favBooksDB = new FavouriteBookManagement(Services.getFavBooksDatabase());
        initializeViews();
        FooterUtility.initFooterButtons(this);
        setupBookList();

    }

    private void initializeViews() {
        booksContainer = findViewById(R.id.favBooksContainer);
    }

    private void setupBookList() {
        List<Book>userList= favBooksDB.getFavBooks(AuthenticatedUser.getInstance().getUser().getUserID());
        refreshBookList(userList);
    }


    private void refreshBookList(List<Book> books) {
        booksContainer.removeAllViews();
        for (Book book : books) {
            View bookView = createBookView();
            configureBookView(bookView, book);
            booksContainer.addView(bookView);
        }
    }

    private View createBookView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        return inflater.inflate(R.layout.book_item_activity, booksContainer, false);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void configureBookView(View bookView, Book book) {

        TextView bookName = bookView.findViewById(R.id.bookName);
        TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);
        TextView bookPrice = bookView.findViewById(R.id.bookPrice);
        TextView bookcondition= bookView.findViewById(R.id.bookCondition);
        Button buyBook= bookView.findViewById(R.id.bookAction);
      ImageView deleteFavBook = bookView.findViewById(R.id.bookDelete);

        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));
        bookcondition.setText(String.format("Book Condition: %s", book.getCondition()));
        buyBook.setText(String.format("Buy"));

        deleteFavBook.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int bookId = book.getId();
                int usrId =AuthenticatedUser.getInstance().getUser().getUserID();
                favBooksDB.removeFavBook(usrId,bookId);
                Intent requiredBook = new Intent(FavouriteBooksActivity.this, FavouriteBooksActivity.class);
                startActivity(requiredBook);
            }
        });



        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is logged in before allowing to buy
                    // Show under construction alert for buy button
                    showBuyAlert();
            }
        });
    }


    private void showBuyAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Do you want to buy this book?")
                .setMessage("This book will be sent to: " + AuthenticatedUser.getInstance().getUser().getAddress())

                // Set a "Yes" button and its listener
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Code to execute when "Yes" is pressed
                        dialog.dismiss(); // Dismiss the dialog
                    }
                })

                // Set a "No" button and its listener
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Code to execute when "No" is pressed
                        dialog.dismiss(); // Dismiss the dialog
                    }
                })

                // Set the icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
