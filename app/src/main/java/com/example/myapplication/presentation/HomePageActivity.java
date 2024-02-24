package com.example.myapplication.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.business.BookManagement;
import com.example.myapplication.persistence.DummyDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends GlobalActivity {

    private LinearLayout booksContainer;
    private EditText searchContentView;

    private BookManagement books = new BookManagement(DummyDatabase.getInstance());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);
        setupUI();
        initializeViews();
        setupBookList();
        setupSearchFunctionality();
    }

    private void initializeViews() {
        booksContainer = findViewById(R.id.bookContainer);
        searchContentView = findViewById(R.id.searchInput);
    }

    private void setupBookList() {
        refreshBookList(books.viewBooks());
    }

    private void setupSearchFunctionality() {
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        //getting the input in the search bar
        String searchQuery = searchContentView.getText().toString().toLowerCase();


        if (!searchQuery.isEmpty()) {
            List<Book> filteredBooks = books.findBooksWithBookName(searchQuery);
            if (!filteredBooks.isEmpty()) {
                refreshBookList(filteredBooks);
            } else {
                Toast.makeText(HomePageActivity.this, "No books found.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // If search query is empty, show all books
            refreshBookList(books.viewBooks());
        }
    }
    private List<Book> filterBooksByQuery(String query) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books.findBooksWithBookName(query)) {
            if (book.getBookName().toLowerCase().contains(query)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
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
        TextView bookTags = bookView.findViewById(R.id.bookTags);
        TextView bookPrice = bookView.findViewById(R.id.bookPrice);

        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookTags.setVisibility(View.GONE);
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));

        setupViewBookInfoButton((ImageView) bookView.findViewById(R.id.viewBookInfoButton), book);
    }

    private void setupViewBookInfoButton(ImageView button, Book book) {
        button.setOnClickListener(v -> {

            // Use BookManager to get book ID
            int bookId = book.getId();
            // Assuming you have a getBookId method in your Book class
            // Pass book ID instead of BookSerialization
            Intent intent = new Intent(HomePageActivity.this, BookInfoActivity.class);
            intent.putExtra("bookId", bookId);
            startActivity(intent);

        });
    }
}
