package com.example.myapplication.presentation;

import android.annotation.SuppressLint;
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
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.FavouriteBookManagement;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.presentation.utils.FooterUtility;

import java.util.List;

public class FavouriteBooksActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private FavouriteBookManagement favBooksDB;
    private CheckoutManagement shoppingCart = new CheckoutManagement(Services.getTransactionDatabase());
    private List<Book> userList;

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
        try {
            userList = favBooksDB.getFavBooks(AuthenticatedUser.getInstance().getUser().getUserID());
        } catch (BookNotFoundException e) {
            Toast.makeText(FavouriteBooksActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
        TextView bookcondition = bookView.findViewById(R.id.bookCondition);
        Button buyBook = bookView.findViewById(R.id.bookAction);
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
                int usrId = AuthenticatedUser.getInstance().getUser().getUserID();
                favBooksDB.removeFavBook(usrId, bookId);
                Intent requiredBook = new Intent(FavouriteBooksActivity.this, FavouriteBooksActivity.class);
                startActivity(requiredBook);
            }
        });

        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add this to the shopping cart
                try {
                    shoppingCart.buyBook(book);
                } catch (CheckoutException mssg) {
                    Toast.makeText(FavouriteBooksActivity.this, mssg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
