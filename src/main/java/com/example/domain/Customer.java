package com.example.domain;

public class Customer extends User {

    private String firstName;
    private String lastName;

    public Customer(Integer id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        UnitOfWork.getInstance().registerDirty(this);
    }
}

