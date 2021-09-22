package com.example.domain;

import com.example.controller.AuthenticationController;
import com.example.datasource.AirlineDataMapper;

import java.util.HashMap;

public class Airline extends User{

    public String name;

    private boolean pending;

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Flight createFlight(HashMap<String, String> params){
        return null;
    }

    public void editFlight(Flight flight, HashMap<String, String> params){
        
    }
    
    public void deleteFlight(Flight flight){

    }

    public void viewCustomers(Flight flight){

    }

    @Override
    public int register(HashMap<String, String> params) {
        params.put("pending", "true");
        params.put("name", params.get("username"));
        Airline airline = (Airline) new AirlineDataMapper().create(params);
        return airline.id;
    }

    @Override
    public int login(HashMap<String, String> params) {
        String inputPassword = params.remove("password");
        Airline airline = new AirlineDataMapper().find(params);
        // login logic
        if( airline.password.equals(inputPassword) ){
            return airline.id;
        }
        return AuthenticationController.LOGIN_FAIL_NO_USER_FOUND;
    }
}
