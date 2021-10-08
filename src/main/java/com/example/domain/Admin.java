package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.CustomerDataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Admin extends User{

    public Admin(Integer id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public ArrayList<Airline> viewAirlines() throws Exception {
        return AirlineDataMapper.getInstance().getAll();
    }

    public ArrayList<Customer> viewCustomers() throws Exception {
        return CustomerDataMapper.getInstance().getAll();
    }
}
