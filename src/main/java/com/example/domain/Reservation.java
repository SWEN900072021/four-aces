package com.example.domain;

import com.example.datasource.FlightDataMapper;
import com.example.datasource.TicketDataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Reservation extends DomainObject {

    private Integer customerId;
    private Integer goFlightId;
    private Integer returnFlightId;
    private boolean submitted;

    public Reservation(Integer reservationId, Integer customerId) {
        super(reservationId);
        this.customerId = customerId;
        this.goFlightId = null;
        this.returnFlightId = null;
        this.submitted = false;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public Reservation(Integer reservationId, Integer customerId, Integer goFlightId, Integer returnFlightId, boolean submitted) {
        super(reservationId);
        this.customerId = customerId;
        if (goFlightId != 0) {
            this.goFlightId = goFlightId;
        } else {
            this.goFlightId = null;
        }
        if (returnFlightId != 0) {
            this.returnFlightId = returnFlightId;
        } else {
            this.returnFlightId = null;
        }
        this.submitted = submitted;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public void bookGoFlight(Integer goFlightId) {
        this.goFlightId = goFlightId;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void bookReturnFlight(Integer returnFlightId) {
        this.returnFlightId = returnFlightId;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void bookTicket(Integer ticket) {
    }

    public Integer getReturnFlightId() {
        return this.returnFlightId;
    }

    public Integer getGoFlightId() {
        return this.goFlightId;
    }

    public Integer getCustomerId() {
        return this.customerId;
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
        if (this.goFlightId != null) {
            System.out.println(goFlightId);
            flights.add(FlightDataMapper.getInstance().findById(goFlightId));
        }
        if (this.returnFlightId != null) {
            flights.add(FlightDataMapper.getInstance().findById(returnFlightId));
        }

        return flights;
    }

}
