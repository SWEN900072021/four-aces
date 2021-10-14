package com.example.domain;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.exception.NoRecordFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Reservation extends DomainObject {

    private Customer customer;
    private Flight goFlight;
    private Flight returnFlight;
    private ArrayList<Ticket> tickets;
    private HashMap<Passenger, ArrayList<Ticket>> passengerTickets;

    public Reservation(Integer reservationId, Customer customer) {
        super(reservationId);
        this.customer = customer;
        this.goFlight = null;
        this.returnFlight = null;
        this.tickets = null;
        this.passengerTickets = null;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public Reservation(Integer reservationId, Customer customer, Flight goFlight, Flight returnFlight) {
        super(reservationId);
        this.customer = customer;
        if (goFlight != null) {
            this.goFlight = goFlight;
        } else {
            this.goFlight = null;
        }
        if (returnFlight != null) {
            this.returnFlight = returnFlight;
        } else {
            this.returnFlight = null;
        }
        this.tickets = null;
        this.passengerTickets = null;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public void bookGoFlight(Flight goFlight) {
        this.goFlight = goFlight;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void bookReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void bookTicket(Integer ticket) {
    }

    public Flight getReturnFlight() {
        return this.returnFlight;
    }

    public Flight getGoFlight() {
        return this.goFlight;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Flight> getFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        if (this.goFlight != null) {
            flights.add(goFlight);
        }
        if (this.returnFlight != null) {
            flights.add(returnFlight);
        }

        return flights;

    }

    public ArrayList<Ticket> getTickets() throws SQLException {
        if (this.tickets == null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("reservation_id", Integer.toString(this.id));
            try {
                ArrayList<Ticket> tickets = TicketDataMapper.getInstance().find(params);
                return tickets;
            } catch (NoRecordFoundException e) {
                this.tickets = new ArrayList<>();
            }
        }
        return this.tickets;
    }

    public boolean isReturning() {
        return (this.returnFlight != null);
    }

    public void bookTicket(Ticket ticket) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
    }

    public HashMap<Passenger, ArrayList<Ticket>> getPassengerTickets() {
        if (this.passengerTickets == null) {
            this.passengerTickets = new HashMap<Passenger, ArrayList<Ticket>>();
            if (this.tickets == null || this.tickets.size() == 0) {
                return this.passengerTickets;
            } else {
                for (Ticket ticket : tickets) {
                    if (passengerTickets.containsKey(ticket.getPassenger())) {
                        passengerTickets.get(ticket.getPassenger()).add(ticket);
                    } else {
                        passengerTickets.put(ticket.getPassenger(), new ArrayList<>());
                        passengerTickets.get(ticket.getPassenger()).add(ticket);
                    }
                }
                return this.passengerTickets;
            }
        } else {
            return this.passengerTickets;
        }
    }

    public void addPassenger(Passenger passenger) {
        getPassengerTickets();
        if (!passengerTickets.containsKey(passenger)) {
            passengerTickets.put(passenger, new ArrayList<>());
        }
    }

    public void bookTicket(Passenger passenger, Ticket ticket) {
        this.bookTicket(ticket);
        if (!passengerTickets.containsKey(passenger)) {
            passengerTickets.put(passenger, new ArrayList<>());
        }
        passengerTickets.get(passenger).add(ticket);
    }


}
