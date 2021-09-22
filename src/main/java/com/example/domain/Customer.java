package com.example.domain;

import com.example.controller.AuthenticationController;
import com.example.datasource.CustomerDataMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User{

    private ArrayList<Flight> upcoming;
    private ArrayList<Flight> previous;

    private String firstName;
    private String lastName;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Passenger createPassengers(HashMap<String, String> params){
        return null;
    }

    @Override
    public int register(HashMap<String, String> params) {
        Customer customer = new CustomerDataMapper().create(params);
        return customer.id;
    }

    @Override
    public int login(HashMap<String, String> params) {
        // processing params
        String inputPassword = params.remove("password");
        Customer customer = new CustomerDataMapper().find(params);
        // login logic
        if( customer.password.equals(inputPassword) ){
            return customer.id;
        }
        return AuthenticationController.LOGIN_FAIL_NO_USER_FOUND;
    }
}
