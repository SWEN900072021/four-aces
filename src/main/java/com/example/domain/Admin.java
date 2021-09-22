package com.example.domain;

import com.example.controller.AuthenticationController;
import com.example.datasource.AdminDataMapper;

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

    public Airline createAirline(HashMap<String, String> params){
        return null;
    }

    public void viewCustomer(Customer customer){

    }

    public void updateAirport(Airport airport, HashMap<String, String> params){

    }

    public void deleteAirport(Airport airport){

    }

    @Override
    public int register(HashMap<String, String> params) {
        params.remove("email");
        admin = (new AdminDataMapper()).create(params);
        return admin.id;
    }

    @Override
    public int login(HashMap<String, String> params) {

        String inputPassword = params.remove("password");
        admin = new AdminDataMapper().find(params);

        if( admin.password.equals(inputPassword) ){
            return admin.id;
        }
        return AuthenticationController.LOGIN_FAIL_NO_USER_FOUND;
    }
}
