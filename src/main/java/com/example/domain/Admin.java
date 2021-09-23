package com.example.domain;

import com.example.datasource.AdminDataMapper;
import com.example.datasource.AirlineDataMapper;
import com.example.datasource.CustomerDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User{

    private final static String DEFAULT_USERNAME = "admin";
    private final static String DEFAULT_PASSWORD = "admin";

    private static Admin admin;

    private Admin(Integer id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
        UnitOfWork.getInstance().registerNew(this);
    }

    public static Admin getAdmin(){
        if (admin == null){
            return createAdmin(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        }
        return admin;
    }

    public static Admin createAdmin(String username, String password){
        if( admin == null ) {
            return admin = new Admin(null, username, password);
        }
        return admin;
    }

    public Airport createAirport(String code, String address){
        return null;
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

    @Override
    public Admin login(String password) throws Exception {
        if( !admin.password.equals(password) ){
            throw new TRSException("Wrong password");
        }
        return admin;
    }

}
