package com.example.domain;

import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.HashMap;

public class User extends DomainObject{

    protected String username;
    protected String password;
    protected String email;

    public User(){
        super(null);
    }

    public User register(HashMap<String, String> params) throws TRSException, SQLException {
        return null;
    }
    
    public User login(HashMap<String, String> params) throws TRSException, SQLException {
        return null;
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

    @Override
    public Integer getId(){
        if( this.id == 0 ) load();
        return this.id;
    }

    public void load(){}
}
