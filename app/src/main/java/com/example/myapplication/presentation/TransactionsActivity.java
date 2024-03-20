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
import com.example.myapplication.Models.Transaction;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.FavouriteBookManagement;
import com.example.myapplication.customException.CheckoutException;

import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private CheckoutManagement purchaseHistory;
    private List<Transaction> history;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);

        FooterUtility.initFooterButtons(this);
        findViewById(R.id.button_back_library).setVisibility(View.GONE);
        findViewById(R.id.messageCheckout1).setVisibility(View.GONE);
        findViewById(R.id.finishBuying).setVisibility(View.GONE);

        TextView heading = findViewById(R.id.booksSaleHeading);

        heading.setText("Past Purchases");

        booksContainer = findViewById(R.id.saleOfBooksContainer);

        setupBookList();
        refreshBookList();

    }


    private void setupBookList() {
        //the database connection is established
        purchaseHistory = new CheckoutManagement(Services.getTransactionDatabase());
        try {
            history = purchaseHistory.pastPurchases();  //getting the past purchases list
        }catch (CheckoutException e) {
            Toast.makeText(TransactionsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void refreshBookList() {
        booksContainer.removeAllViews();

        for( Transaction transaction : history){
            View bookView = createBookView();
            configureBookView(bookView, transaction);
            booksContainer.addView(bookView);
        }
    }

    private View createBookView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        return inflater.inflate(R.layout.book_item_activity, booksContainer, false);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void configureBookView(View bookView, Transaction transaction) {

        Book book= transaction.getBook();
        TextView bookName = bookView.findViewById(R.id.bookName);
        TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);
        TextView bookPrice = bookView.findViewById(R.id.bookPrice);
        TextView bookcondition = bookView.findViewById(R.id.bookCondition);
        Button buyBook = bookView.findViewById(R.id.bookAction);
        ImageView deleteFavBook = bookView.findViewById(R.id.bookDelete);

        bookName.setText(String.format("Book Name: %s", book.getBookName()));
        bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
        bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));
        bookcondition.setText(String.format("Delivered to: %s",transaction.getDeliveredTo()));


        deleteFavBook.setVisibility(View.GONE);
        buyBook.setVisibility(View.GONE);

    }
}
