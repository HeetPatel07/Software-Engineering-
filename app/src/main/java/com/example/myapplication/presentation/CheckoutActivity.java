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
import com.example.myapplication.presentation.BooksUtility;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.utils.DBHelper;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private LinearLayout booksContainer;

    private SellBooksManagement manager;

    private User authenticatedUser;

    private CheckoutManagement shoppingCart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);
        FooterUtility.initFooterButtons(this);
        shoppingCart = CheckoutManagement.getInstance();    //getting the shopping cart to show information

        TextView heading= findViewById(R.id.booksSaleHeading);
        TextView message= findViewById(R.id.messageCheckout1);

        findViewById(R.id.button_back_library).setVisibility(View.GONE);

        heading.setText("Checkout Page");
        message.setText("Your cart so far");
        LinearLayout box= findViewById(R.id.saleOfBooksContainer);
        new BooksUtility(box, shoppingCart.getCheckoutBooks());
    }

}
