package com.example.domain;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.exception.TRSException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        UnitOfWork.getCurrent().registerNew(this);
    }

    public ArrayList<Passenger> viewPassengers() throws Exception {
        return PassengerDataMapper.getInstance().getAll();
    }

    public void setPending(boolean pending) {
        this.pending = pending;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setName(String name) {
        this.name = name;
        UnitOfWork.getCurrent().registerDirty(this);
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

    @Override
    public void login(String password) throws Exception {
        if (!this.password.equals(password)) {
            throw new TRSException("Wrong Password");
        }
        if (isPending()) {
            throw new TRSException("Waiting for the administrator to approve it.");
        }
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
