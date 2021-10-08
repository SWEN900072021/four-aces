package com.example.domain;


import com.example.datasource.PassengerDataMapper;
import java.util.Objects;

public class Ticket extends DomainObject{
    private double price;
    private int flightId;
    private String seatClass;
    private String seatNumber;
    private Boolean isAvailable;
    private Integer passengerId;
    private Integer reservationId;

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passengerId = null;
        this.reservationId = null;
        UnitOfWork.getCurrent().registerNew(this);
    }

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber, Integer passengerId, Integer reservationId) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passengerId = passengerId;
        this.reservationId = reservationId;
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

    public Boolean isAvailable() {
        return (passengerId == null);
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public Integer getPassengerId() {
        return this.passengerId;
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public Passenger getPassenger() throws Exception {
        Passenger passenger = PassengerDataMapper.getInstance().findById(passengerId);
        return passenger;
    }
}
