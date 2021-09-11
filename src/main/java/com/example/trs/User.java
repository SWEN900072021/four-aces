package com.example.trs;

public abstract class User {
    
    protected String username;
    protected String password;
    protected String email;
    
    public abstract void register(String username, String password);
    
    public abstract void login(String username, String password);
}
