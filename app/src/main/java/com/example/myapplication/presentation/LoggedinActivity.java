package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.business.AuthenticatedUser;

public class LoggedinActivity extends GlobalActivity{

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_activity);
        setupUI();

        ImageView viewAccount = findViewById(R.id.viewAccountInfo);

        viewAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                Intent signUp = new Intent(LoggedinActivity.this, ChangeAccount.class);
                startActivity(signUp);
            }

        });
    }
}
