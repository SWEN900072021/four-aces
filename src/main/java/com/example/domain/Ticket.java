package com.example.domain;

public class Ticket extends DomainObject{
    private double price;
    private int flightId;
    private String seatClass;
    private String seatNumber;
    private Boolean isAvailable;
    private Integer passengerId;

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passengerId = null;
        UnitOfWork.getInstance().registerNew(this);
    }

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber, Integer passengerId) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.passengerId = passengerId;
        UnitOfWork.getInstance().registerNew(this);
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

    public void setAvailability(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public Integer getPassengerId() {
        return this.passengerId;
    }
}
