package com.example.myapplication.presentation.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;




import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.FavouriteBookManagement;

import java.util.List;

public class BooksUtility {

    private static LinearLayout booksContainer;

    private static List<Book> books;

    private static FavouriteBookManagement favBooksDB;

    // Constructor
    public BooksUtility(LinearLayout cont, List<Book> show) {
        this.booksContainer = cont;
        refreshBookList(show);


    }


    private static void refreshBookList(List<Book> list) {
        booksContainer.removeAllViews();
        books = list;
        for (Book book : books) {
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
        ImageView deleteBook = bookView.findViewById(R.id.bookDelete);
        Button viewButton = bookView.findViewById(R.id.bookAction);


        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookCondition.setText(String.format("Book Condition: %s", book.getCondition()));
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));
        viewButton.setVisibility(View.GONE);

        deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CheckoutManagement cart= new CheckoutManagement(Services.getTransactionDatabase());
                if (!cart.removeBook(book)) {
                    Toast.makeText(booksContainer.getContext(), "Error in removing the book " + book.getBookName() + " please contact admin", Toast.LENGTH_SHORT).show();
                }
                refreshBookList(books);
            }
        });

    }

}
