package com.example.myapplication.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import com.example.myapplication.customException.UserNotFoundException;
import com.example.myapplication.persistence.implementation.UserDatabaseImpl;
import com.example.myapplication.presentation.utils.FooterUtility;


public class SellBooksActivity extends AppCompatActivity {
    private Spinner BookID;
    private EditText price;
    private Spinner condition;
    private SellBooksManagement sellBooks;
    String[] bookOptions;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_books_page);
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();
        FooterUtility.initFooterButtons(this);
        initializeViews();
        sellBooks = new SellBooksManagement(Services.getBookDatabase(), Services.getSellBooksDatabase(), authenticatedUser);
    }


    private void initializeViews() {
        BookID = findViewById(R.id.bookSpinner);
        price = findViewById(R.id.enter_price_field);
        condition = findViewById(R.id.spinnerCondition);

        bookOptions = getResources().getStringArray(R.array.booklist);

        // Create an ArrayAdapter using the book options array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookOptions);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        BookID.setAdapter(adapter);

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

        try {
            int bookID = BookID.getSelectedItemPosition()+1;
            String priceText = price.getText().toString();
            String bookCondition = (String) condition.getSelectedItem();

            if (priceText.isEmpty()) {
                throw new NumberFormatException("Please enter a selling valid price");
            }
            if (AuthenticatedUser.getInstance() == null) {
                throw new UserNotFoundException("Cannot proceed, please login first");
            }

            float bookPrice = Float.parseFloat(price.getText().toString());

            //extreme edge cases
            if (bookID < 0)
                throw new NumberFormatException("Please enter a selling valid price");

            if (Float.isNaN(bookPrice) || Float.isInfinite(bookPrice) || bookPrice < 0)
                throw new NumberFormatException("Please enter a selling valid price");


            String result = null;
            result = sellBooks.bookExists(bookID, bookPrice, bookCondition);

            if (result != null) {
                Toast.makeText(this, "Book " + result + " added for sale", Toast.LENGTH_SHORT).show();
                navigateToLibraryPage();
            } else {
                Toast.makeText(this, "No such book can be added to sell", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (UserNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

