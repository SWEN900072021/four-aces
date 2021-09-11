package com.example.trs;

import com.example.util.Param;

import java.util.ArrayList;

public class Admin extends User{

    private static Admin admin;

    public static Admin getAdmin(){
        if (admin == null){
            return createAdmin();
        }
        return admin;
    }

    private static Admin createAdmin(){
        return null;
    }

    public Airport createAirport(String code, String address){
        return null;
    }

    public Airline createAirline(ArrayList<Param> params){
        return null;
    }

    public void viewCustomer(Customer customer){

    }

    public void updateAirport(Airport airport, ArrayList<Param> params){

    }

    public void deleteAirport(Airport airport){

    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void login(String username, String password) {

    }

}
