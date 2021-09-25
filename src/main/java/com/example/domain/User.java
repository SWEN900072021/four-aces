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

    public User() {
        super();
    }

    public User login(String password) throws Exception {
        if (!this.password.equals(password)) {
            throw new TRSException("Wrong Password");
        }
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setPassword(String password) {
        this.password = password;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setEmail(String email) {
        this.email = email;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setId(Integer id) {
        this.id = id;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public String getUsername() {
        if (this.username == null) load();
        return this.username;
    }

    public String getEmail() {
        if (this.email == null) load();
        return this.email;
    }

    public String getPassword(){
        if (this.password == null) load();
        return this.password;
    }

    public void load() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
