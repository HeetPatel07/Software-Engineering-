package com.example.myapplication.business;

import static org.junit.Assert.assertNull;

import com.example.myapplication.business.management.SellBooksLogic;
import com.example.myapplication.persistence.stub.DummyDatabase;

import org.junit.Before;
import org.junit.Test;

public class SellBooksLogicTest {
    SellBooksLogic sl;
    DummyDatabase data = (DummyDatabase) DummyDatabase.getInstance();
    @Before
    public void setUp(){
        sl = new SellBooksLogic(data);
    }

    @Test
    public void bookExistsTest(){
        assertNull(sl.bookExists(-1, 0.0f, null));
    }

}
