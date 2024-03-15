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
import com.example.myapplication.business.management.BookManagement;
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.utils.DBHelper;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private SellBooksManagement manager;

    private User authenticatedUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);

        TextView heading= findViewById(R.id.booksSaleHeading);

        TextView message= findViewById(R.id.messageCheckout1);

        findViewById(R.id.button_back_library).setVisibility(View.GONE);

        LinearLayout box= findViewById(R.id.saleOfBooksContainer);

        heading.setText("Checkout Page");
        message.setText("Your cart so far");

        FooterUtility.initFooterButtons(this);
    }

}
