package com.example.myapplication.presentation;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.BookManagement;

import java.util.List;

public class BooksForSaleActivity extends AppCompatActivity {

    private LinearLayout booksContainer;
    private EditText searchContentView;

    private BookManagement books = new BookManagement(Services.getBookDatabase());


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);
        initializeViews();
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
        List<Book>userList= AuthenticatedUser.getInstance().getUser().getBooksForSale();
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
        return inflater.inflate(R.layout.sale_book_activity, booksContainer, false);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void configureBookView(View bookView, Book book) {
        TextView bookName = bookView.findViewById(R.id.salebookName);
        TextView bookAuthor = bookView.findViewById(R.id.salebookAuthor);
        TextView bookTags = bookView.findViewById(R.id.salebookTags);
        TextView bookPrice = bookView.findViewById(R.id.salebookPrice);

        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookTags.setVisibility(View.GONE);
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));

    }

}
