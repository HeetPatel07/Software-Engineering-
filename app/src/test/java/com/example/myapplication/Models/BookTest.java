package com.example.myapplication.Models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book test;

    @Before
    public void setTest(){
        test = new Book(0, "bookname", 0.1,
                "bookDescription", 1.0,"authorName" , "New");
    }
    @Test
    public void getterSetter(){
        assertEquals(0, test.getId());
        assertEquals("bookname", test.getBookName());
        assertEquals("bookDescription", test.getDescription());
        assertEquals("authorName", test.getAuthorName());
    }

}
