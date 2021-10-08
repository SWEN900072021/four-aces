package com.example.domain;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Reservation extends DomainObject {

    private Customer customer;
    private Flight goFlight;
    private Flight returnFlight;
    private boolean submitted;

    public Reservation(Integer reservationId, Customer customer) {
        super(reservationId);
        this.customer = customer;
        this.goFlight = null;
        this.returnFlight = null;
        this.submitted = false;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public Reservation(Integer reservationId, Customer customer, Flight goFlight, Flight returnFlight, boolean submitted) {
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
        this.submitted = submitted;
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

    public boolean isSubmitted() {
        return this.submitted;
    }

    public void submitBooking() {
        this.submitted = true;
        UnitOfWork.getCurrent().registerDirty(this);
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

}
