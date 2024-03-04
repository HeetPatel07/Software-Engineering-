package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.business.AuthenticatedUser;

public class LibraryActivity extends GlobalActivity{

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_library_activity);
        setupUI();

        ImageView sellBookSection = findViewById(R.id.sellBooksButton);
        sellBookSection.setOnClickListener(v->{
            Intent intent = new Intent(LibraryActivity.this, SellBookActivity.class);
            startActivity(intent);
        });

    }
}
