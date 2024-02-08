package com.example.myapplication.Models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class BookPropertiesTest {
    BookProperties test;
    @Before
    public void setTest(){
        test = new BookProperties("description", 1.0);
    }
    @Test
    public void showOverallRatingTest(){
        assertEquals(0, test.showOverallRating());
        test.addRating(new Rating(0, "0rating", 1));
        test.addRating(new Rating(0, "0rating", 1));
        assertEquals(0, test.showOverallRating());
        test.addRating(new Rating(2, "2rating",2));
        test.addRating(new Rating (2, "2rating", 2));
        assertEquals(1, test.showOverallRating());
    }

    @Test
    public void setterAndGetterTest(){
        assertEquals("description", test.getDescription());
        test.setDescription("description2");
        assertEquals("description2", test.getDescription());
        assertEquals(1.0, test.getEdition(), 0.1);
        test.setEdition(2.5);
        assertEquals(2.5, test.getEdition(),0.1);


    }
}
