package com.example.controller;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingController {
    private static BookingController _instance = null;
    private HashMap<Integer, Reservation> map;

    private BookingController(){
        this.map = new HashMap<Integer, Reservation>();
    }

    public static BookingController getInstance() {
        if (_instance == null) {
            _instance = new BookingController();
        }
        return _instance;
    }
    public Integer getReturnFlightId(int customerId) throws Exception { return map.get(customerId).getReturnFlightId(); }

    public void bookGoFlight(int customerId, int flightId) throws Exception {
        if (map.get(customerId) == null) {
            UnitOfWork.newCurrent();
            Reservation reservation = new Reservation(null, customerId);
            UnitOfWork.getCurrent().commit();
            HashMap<String, String> params = new HashMap<>();
            params.put("customer_id", Integer.toString(customerId));
            params.put("submitted", Boolean.toString(false));
            Reservation savedReservation = ReservationDataMapper.getInstance().find(params).get(0);
            System.out.println(savedReservation.getReturnFlightId());
            map.put(customerId, savedReservation);
        }
        map.get(customerId).bookGoFlight(flightId);
    }

    public List<Flight> getReturnFlights(int flightId) throws Exception {
        Flight flight = FlightDataMapper.getInstance().findById(flightId);
        int origin = flight.getDestinationAirportId();
        int destination = flight.getSourceAirportId();
        HashMap<String, String> params = new HashMap<>();
        params.put("origin", Integer.toString(origin));
        params.put("destination", Integer.toString(destination));
        List<Flight> returnFlights = FlightDataMapper.getInstance().find(params);
        return returnFlights;
    }

    public void bookReturnFlight(int customerId, int flightId) throws Exception {
        if (map.get(customerId) == null) {
            UnitOfWork.newCurrent();
            Reservation reservation = new Reservation(null, customerId);
            UnitOfWork.getCurrent().commit();
            HashMap<String, String> params = new HashMap<>();
            params.put("customer_id", Integer.toString(customerId));
            params.put("submitted", Boolean.toString(false));
            Reservation savedReservation = ReservationDataMapper.getInstance().find(params).get(0);
            map.put(customerId, savedReservation);

        }
        map.get(customerId).bookReturnFlight(flightId);
    }

    public List<Ticket> getAvailableGoTickets(int customerId) throws Exception {
        int flightId = map.get(customerId).getGoFlightId();
        List<Ticket> tickets = TicketDataMapper.getInstance().getAll(flightId, true);
        return tickets;
    }

    public List<Ticket> getAvailableReturnTickets(int customerId) throws Exception {
        int flightId = map.get(customerId).getReturnFlightId();
        List<Ticket> tickets = TicketDataMapper.getInstance().getAll(flightId, true);
        return tickets;
    }

    public void bookTicket(int customerId, int passengerId, int ticketId, String type) throws Exception {
        UnitOfWork.newCurrent();
        Ticket ticket = TicketDataMapper.getInstance().findById(ticketId);
        ticket.setPassengerId(passengerId);
        ticket.setReservationId(map.get(customerId).getId());
        UnitOfWork.getCurrent().commit();
    }

    public boolean isReturning(int customerId) {
        return (map.get(customerId).getReturnFlightId() != null);
    }

    public void submitBooking(int customerId) throws Exception {
        UnitOfWork.newCurrent();
        map.get(customerId).submitBooking();
        UnitOfWork.getCurrent().commit();
        map.remove(customerId);
    }

    public List<Ticket> getReservedTickets(int reservationId, int flightId) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("reservation_id", Integer.toString(reservationId));
        params.put("flight_id", Integer.toString(flightId));
        List<Ticket> tickets = TicketDataMapper.getInstance().find(params);
        return tickets;
    }
}
