package com.example.controller;

import com.example.datasource.FlightDataMapper;
import com.example.domain.*;

import java.util.HashMap;
import java.util.List;

public class BookingController {
    private static BookingController _instance = null;
    private HashMap<Customer, Booking> map;


    private BookingController(){
        this.map = new HashMap<Customer, Booking>();
    }

    public static BookingController getInstance() {
        if (_instance == null) {
            _instance = new BookingController();
        }
        return _instance;
    }

    public void bookFlight(Customer customer, Flight flight) {
        if (!map.containsKey(customer)) {
            map.put(customer, new Booking(customer));
        }
        map.get(customer).selectFlight(flight);
    }

    public Flight getFlight(Customer customer) {
        return map.get(customer).getFlight();
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

    public void bookReturnFlight(Customer customer, Flight flight) {
        if (!map.containsKey(customer)) {
            map.put(customer, new Booking(customer));
        }
        map.get(customer).selectReturnFlight(flight);
    }
}
