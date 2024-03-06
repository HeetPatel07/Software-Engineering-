package com.example.myapplication.business.utlis;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.presentation.LoggedinActivity;
import com.example.myapplication.presentation.LoginActivity;
import com.example.myapplication.presentation.HomePageActivity;
import com.example.myapplication.presentation.LibraryActivity;

public class FooterUtility {

    public static void initFooterButtons(AppCompatActivity activity) {
        ImageView profileButton = activity.findViewById(R.id.profileButton);
        profileButton.setOnClickListener(v -> {
            if (AuthenticatedUser.getInstance().getUser() != null) {
                activity.startActivity(new Intent(activity, LoggedinActivity.class));
            } else {
                activity.startActivity(new Intent(activity, LoginActivity.class));
            }
        });

        ImageView homeButton = activity.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> activity.startActivity(new Intent(activity, HomePageActivity.class)));

        ImageView libraryButton = activity.findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(v -> {
            if (AuthenticatedUser.getInstance().getUser() != null) {
                activity.startActivity(new Intent(activity, LibraryActivity.class));
            } else {
                Toast.makeText(activity, "Login Firstly", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity, LoginActivity.class));
            }
        });
    }
}
