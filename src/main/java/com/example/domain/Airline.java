package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.FlightDataMapper;
import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Airline extends User {
    private String name;
    private boolean pending;
    private List<Flight> flights;

    public Airline(Integer id, String username, String email, String password) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = username;
        this.pending = true; // true means is waiting for approval
        this.flights = null;
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
    public Airline login(String password) throws Exception {
        if (!this.password.equals(password)) {
            throw new TRSException("Wrong Password");
        }
        if (isPending()) {
            throw new TRSException("Waiting for the administrator to approve it.");
        }
        return this;
    }

    public List<Flight> getFlights() throws Exception {
        if (this.flights == null) {
            this.loadFlights();
        }
        return this.flights;
    }

    public void loadFlights() throws Exception {
        this.flights = FlightDataMapper.getInstance().getAll(this.id);
    }
}
