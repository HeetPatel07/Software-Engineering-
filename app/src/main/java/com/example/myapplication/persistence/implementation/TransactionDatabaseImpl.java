package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.subinterfaces.TransactionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDatabaseImpl implements TransactionDatabase {

    private final String dbpath;
    public TransactionDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }

    @Override
    public synchronized List<Book> getPurchaseHistory(User user) {
        List<Book> bookList = new ArrayList<>();

        String sql;

        sql="SELECT b.id, b.bookname, b.author_name, BS.price, b.edition, b.description, BS.book_condition FROM BOOKS b INNER JOIN FAVOURITEBOOK BF on b.id = BF.book_id INNER JOIN BOOKFORSALE BS ON b.id = BS.book_id WHERE BF.user_id = ?;";

        try{
            Connection connection = TransactionDatabase.super.getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user.getUserID());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String bookCondition = rs.getString("book_condition");
                bookList.add(new Book(id, bookName, price, description, edition, authorName, bookCondition));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public synchronized boolean deleteBookForSale(User user, Book book) {
        return false;
    }

    @Override
    public synchronized boolean addSaleBook(int userId, int bookId, String bookCondition, double price) {
        return false;
    }
}

