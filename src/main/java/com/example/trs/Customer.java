package com.example.trs;

import com.example.util.Param;

import java.util.ArrayList;

public class Customer extends User{

    private ArrayList<Flight> upcoming;
    private ArrayList<Flight> previous;

    public Passenger createPassengers(ArrayList<Param> params){
        return null;
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void login(String username, String password) {

    }
}
