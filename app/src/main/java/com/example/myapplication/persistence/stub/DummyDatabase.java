package com.example.myapplication.persistence.stub;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.stub.utlis.RandomGenerator;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DummyDatabase implements BookDatabase, UserDatabase {

    private static final DummyDatabase dummyDatabase = new DummyDatabase();

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public static Database getInstance() {
        return dummyDatabase;
    }

    private DummyDatabase() {

    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    public Optional<User> findUserWithUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();

    }

    public List<Book> findBooksWithBookName(String bookName) {
        return books.stream().filter(book -> book.getBookName().equals(bookName)).toList();

    }

    public Book findBookWithID(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }


    public boolean addUser(String userName, String nPassword, String nType, String nAddress) {
        if (findUserWithUsername(userName).isEmpty()) {
            User user = new User(userName, users.size(), nPassword, nType, nAddress);
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {

        return false;
    }

    public void addBook(int id, String bookName,
                        double price, String description,
                        double edition, String authorName, String bookCondition) {
        Book book = new Book(id, bookName, price, description, edition, authorName, null);
        for (int i = 0; i <= 10; i++) {
            String comment = RandomGenerator.generateRandomComment().trim();
            int rating = RandomGenerator.generateRandomRating();
            book.addRating(rating, comment, 1);
        }

        books.add(book);
    }

    @Override
    public boolean updateUserPassword(int userID, String newPassword) {
        return false;
    }

    @Override
    public boolean updateUsername(int userID, String newUsername) {
        return false;
    }

    @Override
    public boolean updateUserAddress(int userID, String newAddress) {
        return false;
    }
}