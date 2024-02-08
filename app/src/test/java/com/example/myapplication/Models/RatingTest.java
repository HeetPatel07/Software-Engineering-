package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class RatingTest {
    Rating test1;
    User testUser1;
    User testUser2;

    @Before
    public void setUpTest(){
        testUser1 = new User("user1", 1, "password1", "type1","address1");
        testUser2 = new User("user2", 2, "password2", "type2", "address2");
        test1 = new Rating(1,"aaaaa", testUser1);
    }

    @Test
    public void createTest(){
        Exception e = assertThrows(RuntimeException.class, ()->
                new Rating (1, "aaaa", null));
        assertEquals("Rating must contain a User", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                new Rating (-1, "aaaa", testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                new Rating (6, "aaaa", testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());

        e = assertThrows(RuntimeException.class, ()->
                new Rating (-1, "aaaa", 1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                new Rating (6, "aaaa", 1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
    }

    @Test
    public void setRatingTest(){
        Exception e = assertThrows(RuntimeException.class, ()->
                test1.setRating( -1 , testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                test1.setRating( 6 , testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                test1.setRating( 1 , testUser2));
        assertEquals("Current user is not the author of this comment", e.getMessage());
        test1.setRating( 0 , testUser1);
        assertEquals(0, test1.getRating());
    }
    @Test
    public void setCommentTest(){
        Exception e = assertThrows(RuntimeException.class, ()->
                test1.setComment( "     " , testUser2));
        assertEquals("Current user is not the author of this comment", e.getMessage());
        test1.setComment("aaaaa" , testUser1);
        assertEquals("aaaaa", test1.getComment());

    }
}
