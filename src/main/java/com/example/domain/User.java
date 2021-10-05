package com.example.domain;

import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.HashMap;

public class User extends DomainObject {

    protected String username;
    protected String password;
    protected String email;

    public User(Integer id) {
        super(id);
    }

    public void login(String password) throws Exception {
        if (!this.password.equals(password)) {
            throw new TRSException("Wrong Password");
        }
    }

    public void setUsername(String username) {
        this.username = username;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setPassword(String password) {
        this.password = password;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setEmail(String email) {
        this.email = email;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setId(Integer id) {
        this.id = id;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
