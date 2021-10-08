package com.example.domain;

import java.util.List;
import java.util.Objects;

public class Customer extends User {

    private String firstName;
    private String lastName;
    private String email;
    private List<Flight> selectedFlights;

    public Customer(Integer id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        UnitOfWork uow = UnitOfWork.getCurrent();
        if (!Objects.isNull(uow)) {
            uow.registerNew(this);
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail(){ return this.email;}
    public void setFirstName(String firstName){
        this.firstName = firstName;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setEmail(String email){
        this.email = email;
        UnitOfWork.getCurrent().registerDirty(this);
    }
}

