package com.example.myapplication.persistence;

import android.os.Build;
import android.util.Log;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.utlis.RandomGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookEaseDatabase implements Database {

    private final String dbPath;

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public BookEaseDatabase(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true","SA", "");
    }


    public void loadUsers() {
        String usersSql = "SELECT * FROM PUBLIC.USERS";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(usersSql)) {

            System.out.println("Users:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String type = rs.getString("type");

                System.out.println("User ID: " + id + ", Username: " + username + ", Password: " + password + ", Address: " + address + ", Type: " + type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadBooks() {
        String booksSql = "SELECT * FROM PUBLIC.BOOKS";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(booksSql)) {

            System.out.println("Books:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");

                System.out.println("Book ID: " + id + ", Book Name: " + bookname + ", Author Name: " + authorName + ", Price: " + price + ", Edition: " + edition + ", Description: " + description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return books.stream().filter(book -> book.getBookName().equals(bookName)).toList();
        }
        return null;
    }

    public Optional<Book> findBookWithID(int id){
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public boolean addUser(String userName, String nPassword, String nType, String nAddress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (findUserWithUsername(userName).isEmpty()) {
                User user = new User(userName, users.size(), nPassword, nType, nAddress);
                users.add(user);
                return true;
            }
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
