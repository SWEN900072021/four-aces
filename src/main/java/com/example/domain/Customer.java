package com.example.domain;

import com.example.controller.AuthenticationController;
import com.example.dataMpper.CustomerDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User {

    private ArrayList<Flight> upcoming;
    private ArrayList<Flight> previous;

    private String firstName;
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Passenger createPassengers(HashMap<String, String> params) {
        return null;
    }

    @Override
    public Customer register(HashMap<String, String> params) throws SQLException {
        return new CustomerDataMapper().create(params);
    }

    @Override
    public Customer login(HashMap<String, String> params) throws TRSException, SQLException {
        // processing params
        String inputPassword = params.remove("password");
        ArrayList<Customer> customers = new CustomerDataMapper().find(params);
        // login logic
        if (customers.size() > 1) {
            throw new TRSException("Multiple Account Found");
        }
        if (customers.size() == 0) {
            throw new TRSException("Register First");
        }
        if (!customers.get(0).password.equals(inputPassword)) {
            throw new TRSException("Wrong Password");
        }
        return customers.get(0);
    }
}
