package com.example.controller;

import com.example.datasource.FlightDataMapper;
import com.example.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingController {
    private static BookingController _instance = null;
    private HashMap<Integer, Booking> map;

//    public Booking getCustomer(Customer customer){
//        Booking booking = null;
//        for( Customer c: map.keySet() ){
//            if( c.getId().equals(customer.getId()) ){
//                booking = map.get(c);
//            }
//        }
//        return booking;
//    }

    private BookingController(){
        this.map = new HashMap<Integer, Booking>();
    }

    public static BookingController getInstance() {
        if (_instance == null) {
            _instance = new BookingController();
        }
        return _instance;
    }

    public Flight getReturnFlight(int customerId) {
        return map.get(customerId).getFlight();
    }

    public void bookFlight(int customerId, Flight flight) {
        if (map.get(customerId) == null) {
            map.put(customerId, new Booking(customerId));
        }
        map.get(customerId).selectFlight(flight);
    }

    public Flight getFlight(int customerId) {
        return map.get(customerId).getFlight();
    }

    public List<Flight> getReturnFlights(Flight flight) throws Exception {
        int origin = flight.getDestinationAirportId();
        int destination = flight.getSourceAirportId();
        HashMap<String, String> params = new HashMap<>();
        params.put("origin", Integer.toString(origin));
        params.put("destination", Integer.toString(destination));
        List<Flight> returnFlights = FlightDataMapper.getInstance().find(params);
        return returnFlights;
    }

    public void bookReturnFlight(int customerId, Flight flight) {
        if (map.get(customerId) == null) {
            map.put(customerId, new Booking(customerId));
        }
        map.get(customerId).selectReturnFlight(flight);
    }

    public void addPassenger(int customerId, Passenger passenger) {
        if (map.get(customerId) == null) {
            map.put(customerId, new Booking(customerId));
        }
        map.get(customerId).addPassenger(passenger);
    }

    public List<Passenger> getPassengers(int customerId) {
        return map.get(customerId).getPassengers();
    }

    public void bookTicket(int customerId, int passengerId, int ticketId, String type) {
        switch (type) {
            case "go":
                map.get(customerId).bookGoTicket(passengerId, ticketId);
                break;
            case "return":
                map.get(customerId).bookReturnTicket(passengerId, ticketId);
                break;
            default:
                break;
        }
    }

    public boolean isReturning(int customerId) {
        return (map.get(customerId).getReturnFlight() != null);
    }

    public void submitBooking(int customerId) {
        map.get(customerId).submitBooking();
    }
}
