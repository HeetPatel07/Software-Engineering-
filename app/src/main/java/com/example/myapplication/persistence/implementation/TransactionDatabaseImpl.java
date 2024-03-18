package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Transaction;
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
    public synchronized List<Transaction> getPurchaseHistory(User user) {

        List<Transaction> purchaseHistory = new ArrayList<>();

        String sql;
        Book bookSold;

        sql="SELECT t.book_id, t.amount, t.address,b.bookname, b.author_name FROM TRANSACTIONS t JOIN BOOKS b ON b.id = t.book_id WHERE t.user_id = ?";

        try{
            Connection connection = TransactionDatabase.super.getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user.getUserID());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("book_id");
                String deliveredTo= rs.getString("address");
                String bookName = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("amount").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();

                bookSold= new Book(id,bookName,0,null,edition,authorName,null);
                purchaseHistory.add(new Transaction(deliveredTo,price,bookSold));
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return purchaseHistory;
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

