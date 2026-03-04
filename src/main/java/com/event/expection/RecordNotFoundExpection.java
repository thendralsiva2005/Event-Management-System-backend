package com.event.expection;

public class RecordNotFoundExpection extends RuntimeException{

    public RecordNotFoundExpection(String message){
        super(message);
    }
}