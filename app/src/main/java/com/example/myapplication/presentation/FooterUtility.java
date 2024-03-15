package com.example.myapplication.presentation;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.business.authentication.AuthenticatedUser;

public class FooterUtility {

    static User user= null;

    public static void initFooterButtons(AppCompatActivity activity) {

        user= AuthenticatedUser.getInstance().getUser();

        ImageView profileButton = activity.findViewById(R.id.profileButton);
        profileButton.setOnClickListener(v -> {
            setIntent(activity,0);
        });

        ImageView homeButton = activity.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> activity.startActivity(new Intent(activity, HomePageActivity.class)));

        ImageView libraryButton = activity.findViewById(R.id.libraryButton);
        libraryButton.setOnClickListener(v -> {
            setIntent(activity,1);
        });

        ImageView checkButton = activity.findViewById(R.id.cartButton);
        checkButton.setOnClickListener(v -> {
            setIntent(activity,2);
        });
    }
    private static void setIntent(AppCompatActivity activity, int index){
        Class<?>[] activityClasses = {LoggedinActivity.class, LibraryActivity.class, CheckoutActivity.class};

        if(user!=null){
            activity.startActivity(new Intent(activity, activityClasses[index]));
        }else{
            Toast.makeText(activity, "Login Firstly", Toast.LENGTH_SHORT).show();
            activity.startActivity(new Intent(activity, LoginActivity.class));
        }
    }
}
