package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.BookManagement;
import com.example.myapplication.business.CourseManagement;
import com.example.myapplication.persistence.DummyDatabase;

import java.util.List;

public class AddBookPopupActivity extends AppCompatActivity {

    private BookManagement bookManagement;
    private LinearLayout booksContainer;
    private static CourseManagement courseManagement;
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_popup_activity);

        courseName = getIntent().getStringExtra("courseName");
        bookManagement = new BookManagement(Services.getBookEaseDatabase());
        courseManagement = CourseManagement.getInstance();
        // button to back to course management page
        Button backButton = findViewById(R.id.backToCourse);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBookPopupActivity.this, CoursesBooksActivity.class));
            }
        });

        //search books
        Button searchButton = findViewById(R.id.searchbook);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText bookView = findViewById(R.id.bookName);
                String bookname = bookView.getText().toString();


            }
        });

        showBookList();

    }

    private void showBookList(){
        //book list
        booksContainer = findViewById(R.id.bookContainer);
        List<Book> books = bookManagement.viewBooks();

        for(Book book: books){
            LayoutInflater inflater = LayoutInflater.from(this);
            View bookView = inflater.inflate(R.layout.book_item_activity, booksContainer, false);

            TextView bookName = bookView.findViewById(R.id.bookName);
            TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);
            TextView bookTags = bookView.findViewById(R.id.bookTags);
            TextView bookPrice = bookView.findViewById(R.id.bookPrice);
            ImageView button = bookView.findViewById(R.id.bookDelete);

            bookName.setText(String.format("Book Name: %s", book.getBookName()));
            bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
            bookTags.setVisibility(View.GONE);
            bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));
            button.setVisibility(View.GONE);

            Button bookActionButton = bookView.findViewById(R.id.bookAction);
            bookActionButton.setText("Add the book");
            bookActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    courseManagement.addRequiredBookToCourse(courseName, book.getId());
                    Intent intent = new Intent(AddBookPopupActivity.this, CoursesBooksActivity.class);
                    startActivity(intent);
                }
            });

            booksContainer.addView(bookView);
        }

    }
}
