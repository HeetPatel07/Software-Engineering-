package com.example.myapplication.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Course;
import com.example.myapplication.R;
import com.example.myapplication.business.BookManagement;
import com.example.myapplication.business.CourseManagement;
import com.example.myapplication.persistence.DummyDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ProfessorCoursesBooksActivity extends GlobalActivity {

    private HashMap<String, Button> courseButtonsMap = new HashMap<>();

    //initialize courseManagement
    private static CourseManagement courseManagement;
    private Map<String, Course> courses = new HashMap<>();
    private BookManagement bookManagement;


    protected void onResume() {
        super.onResume();
        // Refresh the courses and books display
        courses = courseManagement.getCourses(); // Ensure you're getting the updated courses map
        displayCoursesAndRequiredBooks();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_course_page_activity);
        setupUI();

        courseManagement = CourseManagement.getInstance();
        bookManagement = new BookManagement(DummyDatabase.getInstance());

        //button to add the course
        Button addcourse = findViewById(R.id.addCourseButton);
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the course name
                EditText courseInput = findViewById(R.id.courseInput);
                String courseName = courseInput.getText().toString();

                if(courseManagement.addCourse(courseName)){
                    Toast.makeText(ProfessorCoursesBooksActivity.this,"Course Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProfessorCoursesBooksActivity.this,"Failed to add a course", Toast.LENGTH_SHORT).show();
                }

                courses = courseManagement.getCourses();
                displayCoursesAndRequiredBooks();

            }
        });

        displayCoursesAndRequiredBooks();
    }

    private void displayCoursesAndRequiredBooks(){
        LinearLayout courseBookContainer = findViewById(R.id.courseBookContainer);
        courseBookContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Map.Entry<String, Course> course : courses.entrySet()) {
            View courseBookView = inflater.inflate(R.layout.course_books_activity, courseBookContainer, false);
            //set course name
            TextView courseNameView = courseBookView.findViewById(R.id.courseName);
            String courseName = course.getValue().getCourseName();
            courseNameView.setText(courseName);



            //add book button operations
            Button addBookButton = courseBookView.findViewById(R.id.addBookButton);
            addBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfessorCoursesBooksActivity.this, AddBookPopupActivity.class);
                    intent.putExtra("courseName", courseName);
                    startActivity(intent);
                }
            });

            LinearLayout courseInfoContainer = courseBookView.findViewById(R.id.courseBookInfoLayout);
            Set<Integer> requredBookIDsSet =  courseManagement.getCourseRequiredBookIDs(courseName);

            for(Integer bookId : requredBookIDsSet){

                Book book = bookManagement.findBookWithID(bookId);

                View bookView = inflater.inflate(R.layout.book_item_activity, courseInfoContainer, false);

                TextView bookName = bookView.findViewById(R.id.bookName);
                TextView bookAuthor = bookView.findViewById(R.id.bookAuthor);
                TextView bookTags = bookView.findViewById(R.id.bookTags);
                TextView bookPrice = bookView.findViewById(R.id.bookPrice);
                ImageView button = bookView.findViewById(R.id.bookDelete);

                bookName.setText(String.format("Book Name: %s", book.getBookName()));
                bookAuthor.setText(String.format("Book Author: %s", book.getAuthorName()));
                bookTags.setVisibility(View.GONE);
                bookPrice.setText(String.format("Book Price: $%.2f", book.getPrice()));

                Button bookButton = bookView.findViewById(R.id.bookAction);
                String buttonName = "Add to Cart";
                bookButton.setText(buttonName);
                courseButtonsMap.put(courseName, bookButton);
                bookButton.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ProfessorCoursesBooksActivity.this, "Book added to the cart", Toast.LENGTH_SHORT).show();
                        // Change button text to indicate the book is added to the cart
                        bookButton.setText("Added");
                        bookButton.setEnabled(false);
                        bookButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

                    }
                });

                ImageView deleteButton = bookView.findViewById(R.id.bookDelete); // Assuming this is called within an Activity
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfessorCoursesBooksActivity.this);
                        builder.setMessage("Are you sure to delete it")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //confirmation of delete
                                        if(courseManagement.deleteRequiredBookInCourse(courseName, bookId)){
                                            // Show a Toast message or log for successful deletion
                                            Toast.makeText(ProfessorCoursesBooksActivity.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    displayCoursesAndRequiredBooks();
                                                }
                                            });
                                        }
                                    }
                                })
                                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //cancel the dialog
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