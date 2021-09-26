package com.example.exception;

public class AccessDeniedException extends Exception{

    public AccessDeniedException(){
        super("Access Denied");
    }
}
