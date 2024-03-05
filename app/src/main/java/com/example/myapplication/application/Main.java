package com.example.myapplication.application;

import org.hsqldb.jdbcDriver;
public class Main {
    private static String dbName = "BookEase";

    public static void setDBPathName(final String name) {
        new jdbcDriver();
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}
