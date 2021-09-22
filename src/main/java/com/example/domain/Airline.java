package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Airline extends User {

    public String name;

    private boolean pending;

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isPending() {
        return this.pending;
    }

    public Flight createFlight(HashMap<String, String> params) {
        return null;
    }

    public void editFlight(Flight flight, HashMap<String, String> params) {

    }

    public void deleteFlight(Flight flight) {

    }

    public void viewCustomers(Flight flight) {

    }

    @Override
    public Airline register(HashMap<String, String> params) throws TRSException, SQLException {
        params.put("pending", "true");
        params.put("name", params.get("username"));
        return new AirlineDataMapper().create(params);
    }

    @Override
    public Airline login(HashMap<String, String> params) throws TRSException, SQLException {
        String inputPassword = params.remove("password");
        ArrayList<Airline> airlines = new AirlineDataMapper().find(params);
        // login logic
        if (airlines.size() > 1) {
            throw new TRSException("Multiple account has been found");
        }
        if (airlines.size() == 0) {
            throw new TRSException("Register First");
        }
        Airline airline = airlines.get(0);
        if (!airline.password.equals(inputPassword)) {
            throw new TRSException("Wrong password");
        }
        if (airline.isPending()){
            throw new TRSException("Waiting for the administrator to approve it.");
        }
        return airline;
    }
}
