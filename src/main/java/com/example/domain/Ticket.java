package com.example.domain;


import com.example.datasource.PassengerDataMapper;
import java.util.Objects;

public class Ticket extends DomainObject{
    private double price;
    private int flightId;
    private String seatClass;
    private String seatNumber;
    private Passenger passenger;
    private Reservation reservation;

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passenger = null;
        this.reservation = null;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber, Passenger passenger, Reservation reservation) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passenger = passenger;
        this.reservation = reservation;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public double getPrice() {
        return this.price;
    }

    public int getFlightId() {
        return this.flightId;
    }

    public String getSeatClass() {
        return this.seatClass;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public Passenger getPassenger() {
        return this.passenger;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

}
