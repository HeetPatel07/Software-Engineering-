package com.example.myapplication.presentation;

import com.example.myapplication.business.management.CheckoutManagement;

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
import com.example.myapplication.Models.Course;
import com.example.myapplication.R;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.CourseManagement;
import com.example.myapplication.presentation.utils.FooterUtility;

import java.util.List;
import java.util.Set;


public class CoursesBooksActivity extends AppCompatActivity {


    private static CourseManagement courseManagement;
    private CheckoutManagement shoppingCart = new CheckoutManagement(Services.getTransactionDatabase());
    List<Course> courseList;
    AuthenticatedUser authUser;

    protected void onResume() {
        super.onResume();
        // Refresh the courses and books display
        courseList = courseManagement.getCourse(); // Ensure you're getting the updated courses map
        displayCoursesAndRequiredBooks();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_page_activity);
        FooterUtility.initFooterButtons(this);
        courseManagement = new CourseManagement(Services.getCourseRequiredBookDatabase());
        authUser = AuthenticatedUser.getInstance();

        courseList = courseManagement.getCourse();
        displayCoursesAndRequiredBooks();
    }

    private void displayCoursesAndRequiredBooks() {
        LinearLayout courseBookContainer = findViewById(R.id.courseBookContainer);
        courseBookContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        String userType = authUser.getUser().getType();

        for (Course course : courseList) {
            View courseBookView = inflater.inflate(R.layout.course_books_activity, courseBookContainer, false);
            //set course name
            TextView courseNameView = courseBookView.findViewById(R.id.courseName);
            String courseName = course.getCourseName();
            courseNameView.setText(courseName);

            //add book button operations
            Button addBookButton = courseBookView.findViewById(R.id.addBookButton);
            if ("Student".equalsIgnoreCase(userType)) {
                addBookButton.setVisibility(View.GONE); // Hide the add book button for students
            }

            addBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CoursesBooksActivity.this, AddBookPopupActivity.class);
                    intent.putExtra("courseName", courseName);
                    startActivity(intent);
                }
            });

            LinearLayout courseInfoContainer = courseBookView.findViewById(R.id.courseBookInfoLayout);
            Set<Book> requredBookSet = course.getRequiredBookSet();


            for (Book book : requredBookSet) {
                View bookView = inflater.inflate(R.layout.book_item_activity, courseInfoContainer, false);

                TextView bookName = bookView.findViewById(R.id.bookName);
                TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);

                TextView bookPrice = bookView.findViewById(R.id.bookPrice);

                bookName.setText(String.format("Book Name: %s", book.getBookName()));
                bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
                bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));

                Button bookButton = bookView.findViewById(R.id.bookAction);
                String buttonName = "Add to Cart";
                bookButton.setText(buttonName);


                bookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check if the user is logged in before allowing to buy
                        if (AuthenticatedUser.getInstance().getUser() == null) {
                            Toast.makeText(CoursesBooksActivity.this, "Login Firstly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CoursesBooksActivity.this, LoginActivity.class));
                        } else {
                            // add this to the shopping cart
                            if (shoppingCart.buyBook(book)) {
                                Toast.makeText(CoursesBooksActivity.this,
                                        "Book " + book.getBookName() + " added to the cart",
                                        Toast.LENGTH_SHORT).show();
                                bookButton.setText("Added");
                                bookButton.setEnabled(false);
                                bookButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                            } else {
                                Toast.makeText(CoursesBooksActivity.this,
                                        "Book" + book.getBookName() +
                                                "was not added to the cart cause it is already in your cart",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        ;
                    }
                });


                ImageView deleteButton = bookView.findViewById(R.id.bookDelete);
                if ("Student".equalsIgnoreCase(userType)) {
                    deleteButton.setVisibility(View.GONE); // Hide the add book button for students
                }
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CoursesBooksActivity.this);
                        builder.setMessage("Are you sure to delete it")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            courseManagement.deleteRequiredBookInCourse(courseName, book.getId());
                                            courseList = courseManagement.getCourse();
                                            Toast.makeText(CoursesBooksActivity.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                                            runOnUiThread(() -> displayCoursesAndRequiredBooks());
                                        } catch (Exception e) {
                                            // Handle failure
                                            Toast.makeText(CoursesBooksActivity.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                })
                                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //cancel the dialog and do nothing
                                    }
                                });
                        builder.create().show();
                    }
                });
                courseInfoContainer.addView(bookView);
            }
            courseBookContainer.addView(courseBookView);
        }
    }
}
