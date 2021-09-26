package com.example.domain;

import com.example.datasource.TicketDataMapper;

import java.util.ArrayList;
import java.util.HashMap;

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
        UnitOfWork.getInstance().registerNew(this);
    }

    public Reservation(Integer reservationId, Integer customerId, Integer goFlightId, Integer returnFlightId, boolean submitted) {
        super(reservationId);
        this.customerId = customerId;
        this.goFlightId = goFlightId;
        this.returnFlightId = returnFlightId;
        this.submitted = submitted;
        UnitOfWork.getInstance().registerNew(this);
    }

    public void bookGoFlight(Integer goFlightId) {
        this.goFlightId = goFlightId;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void bookReturnFlight(Integer returnFlightId) {
        this.returnFlightId = returnFlightId;
        UnitOfWork.getInstance().registerDirty(this);
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
        UnitOfWork.getInstance().registerDirty(this);
    }

}
