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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Book;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.FavouriteBookManagement;

import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private FavouriteBookManagement favBooksDB;
    private List<Book>userList;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);

        // button_back_library;
        //messageCheckout1
        //booksSaleHeading
        //finishBuying
        //saleOfBooksContainer
        FooterUtility.initFooterButtons(this);
        findViewById(R.id.button_back_library).setVisibility(View.GONE);
        findViewById(R.id.messageCheckout1).setVisibility(View.GONE);
        findViewById(R.id.finishBuying).setVisibility(View.GONE);

       // findViewById(R.id.)

        TextView heading= findViewById(R.id.booksSaleHeading);

        heading.setText("Past Purchases");

        booksContainer= findViewById(R.id.saleOfBooksContainer);


        favBooksDB = new FavouriteBookManagement(Services.getFavBooksDatabase());

       // setupBookList();
    }


    private void setupBookList() {
        userList= favBooksDB.getFavBooks(AuthenticatedUser.getInstance().getUser().getUserID());
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
                Intent requiredBook = new Intent(TransactionsActivity.this, FavouriteBooksActivity.class);
                startActivity(requiredBook);
            }
        });

        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is logged in before allowing to buy
                // Show under construction alert for buy button

            }
        });
    }
}
