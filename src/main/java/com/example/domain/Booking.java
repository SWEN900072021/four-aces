package com.example.domain;

import com.example.datasource.TicketDataMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class Booking {
    private int customerId;
    private Flight flight;
    private Flight returnFlight;
    private ArrayList<Passenger> passengers;
    private HashMap<Integer, Integer> goTickets;
    private HashMap<Integer, Integer> returnTickets;

    public Booking(int customerId) {
        this.customerId = customerId;
        this.flight = null;
        this.returnFlight = null;
        this.passengers = new ArrayList<>();
        this.goTickets = new HashMap<>();
        this.returnTickets = new HashMap<>();
    }

    public void bookGoTicket(int passengerId, int ticketId) {
        goTickets.put(passengerId, ticketId);
    }

    public void bookReturnTicket(int passengerId, int ticketId) {
        returnTickets.put(passengerId, ticketId);
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

    public void submitBooking() {
//set passenger id to tickets

//create reservation
        //customer
        //ticketIds
        //flightIds

    }

//    public void bookSeats(String[] selectedTicketIds) throws Exception {
//        for (String id : selectedTicketIds) {
//            int ticketId = Integer.parseInt(id);
//            this.goTickets.add(TicketDataMapper.getInstance().findById(ticketId));
//        }
//    }


}
