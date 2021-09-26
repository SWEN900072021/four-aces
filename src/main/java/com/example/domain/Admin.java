package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.CustomerDataMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User{

    public Admin(Integer id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
        UnitOfWork.getInstance().registerNew(this);
    }

    public ArrayList<Airline> viewAirlines() throws Exception {
        return AirlineDataMapper.getInstance().getAll();
    }

    public ArrayList<Customer> viewCustomers() throws Exception {
        return CustomerDataMapper.getInstance().getAll();
    }

    public void viewCustomer(Customer customer){

    }

    public void updateAirport(Airport airport, HashMap<String, String> params){

    }

    public void deleteAirport(Airport airport){

    }

}
