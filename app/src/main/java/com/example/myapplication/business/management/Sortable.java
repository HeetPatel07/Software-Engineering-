package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.util.List;

public interface Sortable {

    List<Book> sortByPrice(List<Book> originalist);

    List<Book> sortByRating(List<Book> originalist);

}
