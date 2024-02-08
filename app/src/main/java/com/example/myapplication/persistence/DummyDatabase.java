package com.example.myapplication.persistence;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Rating;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.utlis.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DummyDatabase implements Database {

    private static final Database dummyDatabase = new DummyDatabase();

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public static Database getInstance() {
        return dummyDatabase;
    }

    private DummyDatabase() {

    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    public Optional<User> findUserWithUsername(String username) {
        return users.stream().filter(user -> user.getName().equals(username)).findFirst();

    }
    public List<Book> findBookWithBookName(String bookName) {
        return books.stream().filter(book -> book.getBookName().equals(bookName)).toList();

    }

    public Optional<Book> findBookWithID(int id){
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public boolean addUser(String userName, String nPassword, String nType, String nAddress) {
        if (findUserWithUsername(userName).isEmpty()) {
            User user = new User(userName, users.size(), nPassword, nType, nAddress);
            users.add(user);
            return true;
        }
        return false;
    }

    public void addBook(int id, String bookName,
                        double price, String description,
                        double edition, String authorName) {
        Book book = new Book(id, bookName, price, description, edition, authorName);
        for(int i =0 ; i<=10;i++){
            String comment = RandomGenerator.generateRandomComment().trim();
            int rating = RandomGenerator.generateRandomRating();
            book.addRating(rating, comment);
        }

        books.add(book);
    }


}
