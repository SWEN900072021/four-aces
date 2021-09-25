package com.example.domain;

import java.util.ArrayList;

public class Booking {
    private Customer customer;
    private Flight flight;
    private Flight returnFlight;
    private ArrayList<Passenger> passengers;

    public Booking(Customer customer) {
        this.customer = customer;
        this.flight = null;
        this.passengers = new ArrayList<>();
    }

    public void selectFlight(Flight flight) {
        this.flight = flight;
    }

    public void selectReturnFlight(Flight flight) {
        this.returnFlight = flight;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public Flight getFlight() {
        return this.flight;
    }

    public Flight getReturnFlight() {
        return this.returnFlight;
    }


}
