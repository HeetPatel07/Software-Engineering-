package com.example.myapplication.Models;

public class User {
    private final int userID;
    private String name;
    private String password;
    private String type;
    private String address;

    public User(String userName, int nID, String password, String nType, String nAddress){
        this.name = userName;
        this.userID = nID;
        this.password = password;
        this.type = nType;
        this.address = nAddress;
    }
    public boolean checkPassword(String input){
        return password.equals(input);
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String oldPassword, String newPassword){
        if(password.equals(oldPassword)){
            password = newPassword;
            return true;
        } else {
            return false;
        }
    }
    public String getName() {
        return name;
    }
    public int getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public boolean equalTo(User currUser) {
        return this.equals(currUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID=" + userID +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
