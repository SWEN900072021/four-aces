package com.example.domain;

public class Seat {
    private String seatNumber;
    private String seatClass;

    public Seat(String seatNumber, String seatClass) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public String getSeatClass() {
        return this.seatClass;
    }
}
