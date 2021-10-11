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

    public Reservation(Integer reservationId, Customer customer) {
        super(reservationId);
        this.customer = customer;
        this.goFlight = null;
        this.returnFlight = null;
        this.tickets = null;
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

    public void bookTicket(Ticket ticket) throws SQLException {
        if (this.tickets == null) {
            getTickets();
        }
            this.tickets.add(ticket);
    }
}
