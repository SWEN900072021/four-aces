package com.example.domain;

import java.util.HashMap;

public class User {
    
    protected String username;
    protected String password;
    protected String email;
    protected int id;

    public int register(HashMap<String, String> params){
        return -1;
    }
    
    public int login(HashMap<String, String> params){
        return -1;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(int id){ this.id = id; }

    public String getUsername(){
        if( this.username == null ) load();
        return this.username;
    }

    public String getEmail(){
        if( this.email == null ) load();
        return this.email;
    }

    public int getId(){
        if( this.id == 0 ) load();
        return this.id;
    }

    public void load(){}
}
