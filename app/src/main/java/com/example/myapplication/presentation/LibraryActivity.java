package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.business.utlis.FooterUtility;

public class LibraryActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_library_activity);
        FooterUtility.initFooterButtons(this);
        ImageView sellBooks = findViewById(R.id.SellBooks);

        sellBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the SellBooksActivity
                Intent sellBooksIntent = new Intent(LibraryActivity.this, SellBooksActivity.class);
                startActivity(sellBooksIntent);
                // Finish the current activity if needed
                finish();
            }
        });


        ImageView viewBooksForSale = findViewById(R.id.viewBooksForSale);
        viewBooksForSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the SellBooksActivity
                Intent getBooksIntent = new Intent(LibraryActivity.this, BooksForSaleActivity.class);
                startActivity(getBooksIntent);
                // Finish the current activity if needed
                finish();
            }
        });


    }

}
