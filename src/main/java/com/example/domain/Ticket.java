package com.example.domain;

public class Ticket extends DomainObject{
    private double price;
    private int flightId;
    private String seatClass;
    private String seatNumber;
    private Boolean isAvailable;

    public Ticket(Integer id, Double price, int flightId, String seatClass, String seatNumber, Boolean isAvailable) {
        super(id);
        this.price = price;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
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

    public Boolean getIsAvailable() { return this.isAvailable; }
}
