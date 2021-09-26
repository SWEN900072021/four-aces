package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Airline extends User {
    private String name;
    private boolean pending;

    public Airline(Integer id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = username;
        this.pending = true; // true means is waiting for approval
        UnitOfWork.getInstance().registerNew(this);
    }

    public Airline(Integer id, String username, String email, String password, boolean pending) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = username;
        this.pending = pending;
        UnitOfWork.getInstance().registerNew(this);
    }

    public void setPending(boolean pending) {
        this.pending = pending;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setName(String name) {
        this.name = name;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public boolean isPending() {
        return this.pending;
    }

    public String getName() {
        return this.name;
    }

    public void createFlight(String flightCode, String flightDate, String flightTime, int source, int destination, int airplaneId) {
        new Flight(null, flightCode, flightDate, flightTime, source, destination, this.id, airplaneId);
    }

    public void editFlight(Flight flight, HashMap<String, String> params) {

    }

    public void deleteFlight(Flight flight) {

    }

    public void viewCustomers(Flight flight) {

    }

    @Override
    public void login(String password) throws Exception {
        if (!this.password.equals(password)) {
            throw new TRSException("Wrong Password");
        }
        if (isPending()) {
            throw new TRSException("Waiting for the administrator to approve it.");
        }
    }
}
