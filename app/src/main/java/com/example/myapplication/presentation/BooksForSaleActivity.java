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


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.Models.User;

import java.util.List;

public class BooksForSaleActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private SellBooksManagement manager;

    private User authenticatedUser;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);
        initializeViews();
        authenticatedUser=AuthenticatedUser.getInstance().getUser();
        manager= new SellBooksManagement(Services.getBookDatabase(), Services.getSellBooksDatabase(), authenticatedUser);
        FooterUtility.initFooterButtons(this);
        setupBookList();

        Button backToLibrary = findViewById(R.id.button_back_library);

        backToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the SellBooksActivity
                Intent backToLibrary = new Intent(BooksForSaleActivity.this, LibraryActivity.class);
                startActivity(backToLibrary);
                // Finish the current activity if needed
                finish();
            }
        });


    }

    private void initializeViews() {
        booksContainer = findViewById(R.id.saleOfBooksContainer);
    }

    private void setupBookList() {
        int id = AuthenticatedUser.getInstance().getUser().getUserID();
        List<Book>userList= manager.getUsedBooksForSale(id);
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
        buyBook.setVisibility(View.GONE);
        deleteFavBook.setVisibility(View.GONE);

    }

}
