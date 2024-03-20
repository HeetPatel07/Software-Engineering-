package com.example.myapplication.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.presentation.utils.FooterUtility;


public class SellBooksActivity extends AppCompatActivity {
    private EditText BookID;
    private EditText price;
    private Spinner condition;

    private SellBooksManagement sellBooks;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_books_page);
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();
        FooterUtility.initFooterButtons(this);
        initializeViews();
        sellBooks = new SellBooksManagement(Services.getBookDatabase(), Services.getSellBooksDatabase(), authenticatedUser);
    }


    private void initializeViews(){
        BookID= findViewById(R.id.enter_Book_ID);
        price= findViewById(R.id.enter_price_field);
        condition= findViewById(R.id.spinnerCondition);

        setUpDoneButton();
    }

    private void setUpDoneButton() {
        Button signupButton = findViewById(R.id.sell_book_1);
        signupButton.setOnClickListener(v -> attemptSale());
    }

    private void navigateToLibraryPage() {
        startActivity(new Intent(this, LibraryActivity.class));
    }

    private void attemptSale() {

        try{
            String bookIDText= BookID.getText().toString();
            String priceText= price.getText().toString();
            String bookCondition= (String)condition.getSelectedItem();

            if (BookID.getText().toString().isEmpty()) {
                throw new NumberFormatException("Please enter a valid ISBN number");
            }
            if(priceText.isEmpty()){
                throw new NumberFormatException("Please enter a selling valid price");
            }
            if (AuthenticatedUser.getInstance() == null) {
                throw new IllegalStateException("Cannot proceed, please login first");
            }

            float bookPrice = Float.parseFloat(price.getText().toString());
            int bookID = Integer.parseInt(bookIDText);
            //extreme edge cases
            if(bookID < 0)
                throw new NumberFormatException("Please enter a selling valid price");

            if(Float.isNaN(bookPrice) || Float.isInfinite(bookPrice) || bookPrice<0)
                throw new NumberFormatException("Please enter a selling valid price");



            String result=null;
            result = sellBooks.bookExists(bookID, bookPrice,bookCondition);

            if(result!=null) {
                Toast.makeText(this, "Book " +result+ " added for sale", Toast.LENGTH_SHORT).show();
                navigateToLibraryPage();
            }else{
                Toast.makeText(this, "No such book can be added to sell", Toast.LENGTH_SHORT).show();
            }

        }catch( NumberFormatException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }catch(IllegalStateException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

