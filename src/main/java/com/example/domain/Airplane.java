package com.example.domain;

import java.util.ArrayList;

public class Airplane extends DomainObject {

    private String type;
    private String seatsString;
    private ArrayList<Seat> seats;

    public Airplane(Integer id, String type, String seatsString) {
        super(id);
        this.type = type;
        this.seatsString = seatsString;
    }

    public String getType() {
        return this.type;
    }

    public String getSeatsString() {
        return this.seatsString;
    }

    public ArrayList<Seat> getSeats() {
        return null;
    }
}
