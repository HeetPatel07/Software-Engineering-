package com.example.myapplication.Models;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class UserTest {
    User testUser1;
    User testUser2;

    @Before
    public void setUpTest(){
        testUser1 = new User("testUser1", 1,"password1", "type1",
                "Address1");
        testUser2 = new User("testUser2", 2,"password2", "type2",
                "Address2");
    }

    @Test
    public void testEqual(){
        assertFalse(testUser1.equalTo(null));
        assertFalse(testUser1.equalTo(testUser2));
        assertTrue(testUser1.equalTo(testUser1));
    }
    @Test
    public void testPassword(){
        assertTrue(testUser1.checkPassword("password1"));
        assertFalse(testUser1.checkPassword(""));
        assertFalse(testUser1.checkPassword(null));
        assertFalse(testUser1.checkPassword("password2"));
    }
    @Test
    public void testChangePassword(){
        assertFalse(testUser1.setPassword("", "aaaa"));
        assertFalse(testUser1.setPassword(null, "aaaa"));
        assertFalse(testUser1.setPassword("aaaaaa", "aaaaa"));
        assertTrue((testUser1.setPassword("password1", "aaaaa")));
        assertTrue(testUser1.checkPassword("aaaaa"));
    }

    @Test
    public void testGetterAndSetter(){

        assertEquals("testUser1", testUser1.getUsername());
        assertEquals(1 , testUser1.getUserID());
        assertEquals("type1", testUser1.getType());
        assertEquals("Address1", testUser1.getAddress());
        testUser1.setType("newType1");
        assertEquals("newType1", testUser1.getType());
        testUser1.setAddress("newAddress1");
        assertEquals("newAddress1", testUser1.getAddress());
    }

}

