package com.example.domain;

import java.util.ArrayList;

public class Airplane extends DomainObject {

    private ArrayList<Seat> seats;
    private String type;

    public Airplane() {
        super(null);
    }

    public ArrayList<Seat> getAvailableSeats(){
        return null;
    }
}
