package com.example.trs;

import com.example.dataMpper.AdminDataMapper;

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

}
