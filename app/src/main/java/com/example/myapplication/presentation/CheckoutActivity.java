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
import com.example.myapplication.R;
import com.example.myapplication.customException.CheckoutException;
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

    private CheckoutManagement shoppingCart;

    @SuppressLint("SuspiciousIndentation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_for_sale_view_activity);
        FooterUtility.initFooterButtons(this);
        shoppingCart = new CheckoutManagement(Services.getTransactionDatabase());    //getting the shopping cart to show information

        TextView heading = findViewById(R.id.booksSaleHeading);
        TextView message = findViewById(R.id.messageCheckout1);
        Button finishBuy = findViewById(R.id.finishBuying);

        findViewById(R.id.button_back_library).setVisibility(View.GONE);


        heading.setText("Checkout Page");
        message.setText("Your cart so far");
         booksContainer = findViewById(R.id.saleOfBooksContainer);
        new BooksUtility(booksContainer, shoppingCart.getCheckoutBooks());

        if (shoppingCart.isEmpty())
            finishBuy.setVisibility(View.GONE);
        else
            finishBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showOrderConfirmationDialog(AuthenticatedUser.getInstance().getUser());
                }
            });
    }


    private void showOrderConfirmationDialog(User user) {
        // Create AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);

        // Set dialog title
        builder.setTitle("Order Confirmation");

        // Set dialog message with user's address
        builder.setMessage("Do you want to deliver to the following address?\n\n" + user.getAddress());

        // Set positive button and its click listener
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    shoppingCart.finishTransaction();
                    new BooksUtility(booksContainer, shoppingCart.getCheckoutBooks());
                } catch (CheckoutException e) {
                    Toast.makeText(CheckoutActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set negative button and its click listener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle cancel button click
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
