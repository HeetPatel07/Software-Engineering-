package com.example.myapplication.customException;

public class RatingException extends RuntimeException{
    //this class should not been called;
    public RatingException(){
        super();
    }
    public RatingException(String msg){
        super(msg);
    }
}
