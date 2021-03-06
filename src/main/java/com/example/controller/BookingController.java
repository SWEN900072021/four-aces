package com.example.controller;

import com.example.concurrency.LockManager;
import com.example.datasource.FlightDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;
import com.example.exception.NoRecordFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingController {
    private static BookingController _instance = null;

    public static BookingController getInstance() {
        if (_instance == null) {
            _instance = new BookingController();
        }
        return _instance;
    }

    public List<Flight> getReturnFlights(Flight flight) throws Exception {
        int origin = flight.getDestination().getId();
        int destination = flight.getSource().getId();
        int airlineId = flight.getAirline().getId();

        HashMap<String, String> params = new HashMap<>();
        params.put("origin", Integer.toString(origin));
        params.put("destination", Integer.toString(destination));
        List<Flight> returnFlights;
        try{
            returnFlights = FlightDataMapper.getInstance().find(params);
        }catch (NoRecordFoundException e){
            returnFlights = new ArrayList<>();
        }
        ArrayList<Flight> result = new ArrayList<>();
        for (Flight f : returnFlights) {
            if (f.getAirline().getId() == airlineId) {
                result.add(f);
            }
        }
        return returnFlights;
    }


    public List<Ticket> getAvailableTickets(Flight flight) throws Exception {
        int flightId = flight.getId();
        List<Ticket> tickets = TicketDataMapper.getInstance().getAll(flightId, true);
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (LockManager.getInstance().isAvailable("ticket-" + ticket.getId())) {
                System.out.println("ticket-" + ticket.getId());
                result.add(ticket);
            }
        }
        System.out.println(tickets);
        return result;
    }

    public List<Ticket> getReservedTickets(int reservationId, int flightId) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("reservation_id", Integer.toString(reservationId));
        params.put("flight_id", Integer.toString(flightId));
        return TicketDataMapper.getInstance().find(params);
    }
}
