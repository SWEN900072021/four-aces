package com.example.domain;

import com.example.datasource.AdminDataMapper;
import com.example.datasource.AirlineDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User{

    private static Admin admin;

    public static Admin getAdmin(){
        if (admin == null){
            return new Admin();
        }
        return admin;
    }

    public Airport createAirport(String code, String address){
        return null;
    }

    public ArrayList<Airline> viewAirlines() throws SQLException {
        return new AirlineDataMapper().find("*","");
    }

    public void approveAirlineRegistration(HashMap<String, String> params) throws SQLException, TRSException {
        if (params.get("id") == null){
            throw new TRSException("No Airline has been selected");
        }
        params.put("condition","id");
        if (params.get("pending") == null){
            throw new TRSException("Invalid Approval");
        }
        params.put("update","pending");
        if( new AirlineDataMapper().update(params) == 0 ){
            throw new TRSException("Failure in updating value");
        }
    }

    public void viewCustomer(Customer customer){

    }

    public void updateAirport(Airport airport, HashMap<String, String> params){

    }

    public void deleteAirport(Airport airport){

    }

    @Override
    public Admin register(HashMap<String, String> params) throws TRSException, SQLException {
        params.remove("email");
        admin = (new AdminDataMapper()).create(params);
        return admin;
    }

    @Override
    public Admin login(HashMap<String, String> params) throws TRSException, SQLException {
        String inputPassword = params.remove("password");
        admin = new AdminDataMapper().find(params).get(0);
        if( !admin.password.equals(inputPassword) ){
            throw new TRSException("Wrong password");
        }
        return admin;
    }
}
