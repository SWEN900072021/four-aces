package com.example.domain;

import com.example.datasource.TicketDataMapper;

import java.util.ArrayList;

public class Booking {
    private Customer customer;
    private Flight flight;
    private Flight returnFlight;
    private ArrayList<Passenger> passengers;
    private ArrayList<Ticket> goTickets;
    private ArrayList<Ticket> returnTickets;

    public Booking(Customer customer) {
        this.customer = customer;
        this.flight = null;
        this.returnFlight = null;
        this.passengers = new ArrayList<>();
        this.goTickets = new ArrayList<>();
        this.returnTickets = new ArrayList<>();
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


    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public void bookSeats(String[] selectedTicketIds) throws Exception {
        for (String id : selectedTicketIds) {
            int ticketId = Integer.parseInt(id);
            this.goTickets.add(TicketDataMapper.getInstance().findById(ticketId));
        }
    }

}
