package com.example.myapplication.business.unitTests;

import static org.junit.Assert.*;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.RatingManger;

import org.junit.Test;

public class RatingMangerTest {

    User testUser1 = new User("user1", 1, "password1", "type1","address1");
    User testUser2 = new User("user2", 2, "password2", "type2", "address2");
    Rating rating1 = new Rating(1,"aaaaa", testUser1);
    @Test
    public void createTest(){
        RatingManger.newRating(1, "aaaa", null);
        RatingManger.newRating(1, "aaaa");
        Exception e = assertThrows(RuntimeException.class, ()->
                RatingManger.newRating (-1, "aaaa", testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                RatingManger.newRating(6, "aaaa", testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());

        e = assertThrows(RuntimeException.class, ()->
                RatingManger.newRating(-1, "aaaa", 1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                RatingManger.newRating (6, "aaaa", 1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
    }
    @Test
    public void setRatingTest(){
        Exception e = assertThrows(RuntimeException.class, ()->
                RatingManger.setRating( rating1, -1 , testUser1.getUserID()));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                RatingManger.setRating( rating1,6 , testUser1));
        assertEquals("Rating must between 0 and 5", e.getMessage());
        e = assertThrows(RuntimeException.class, ()->
                RatingManger.setRating( rating1,1 , testUser2));
        assertEquals("Current user is not the author of this rating", e.getMessage());
        RatingManger.setRating( rating1,0 , testUser1);
        assertEquals(0, rating1.getRating());
    }
    @Test
    public void setCommentTest(){
        Exception e = assertThrows(RuntimeException.class, ()->
                RatingManger.setRating(rating1, "     " , testUser2));
        assertEquals("Current user is not the author of this rating", e.getMessage());
        RatingManger.setRating(rating1, "aaaaa" , testUser1);
        assertEquals("aaaaa", rating1.getComment());
        RatingManger.setRating(rating1, 5, "aaaaa2", testUser1);
        assertEquals("aaaaa2", rating1.getComment());
        assertEquals(5, rating1.getRating());
    }


}
