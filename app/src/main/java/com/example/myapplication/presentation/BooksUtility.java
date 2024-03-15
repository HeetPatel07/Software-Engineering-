package com.example.myapplication.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.business.authentication.AuthenticatedUser;

import java.util.List;

public class BooksUtility {

    private static LinearLayout booksContainer;
    private static View activity;


    // Constructor
    public BooksUtility(LinearLayout cont, List<Book> show){
        this.booksContainer = cont;
        refreshBookList(show);
       // this.activity = act;
    }

    private static void refreshBookList(List<Book> list) {
        booksContainer.removeAllViews();
        for (Book book : list) {
            View bookView = createBookView();
            configureBookView(bookView, book);
            booksContainer.addView(bookView);
        }
    }

    private static View createBookView() {
        LayoutInflater inflater = LayoutInflater.from(booksContainer.getContext());
        return inflater.inflate(R.layout.book_item_activity, booksContainer, false);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private static void configureBookView(View bookView, Book book) {
        TextView bookName = bookView.findViewById(R.id.bookName);
        TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);
        TextView bookCondition = bookView.findViewById(R.id.bookCondition);
        TextView bookPrice = bookView.findViewById(R.id.bookPrice);
        ImageView button = bookView.findViewById(R.id.bookDelete);

        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookCondition.setText(String.format("Book Condition: %s", book.getCondition()));
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));
        button.setVisibility(View.GONE);
    }
}
