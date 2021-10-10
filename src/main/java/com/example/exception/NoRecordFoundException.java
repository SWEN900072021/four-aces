package com.example.exception;

public class NoRecordFoundException extends Exception{

    public NoRecordFoundException(String table, String condition){
        super(String.format("No record found in table %s with condition %s", table, condition));
    }

    public NoRecordFoundException(String table, int id){
        super(String.format("No record found in table %s with id %d", table, id));
    }
}
