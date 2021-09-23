package com.example.domain;

import java.util.ArrayList;

public class Airplane extends DomainObject {
    private int id;
    private ArrayList<Seat> seats;
    private String type;

    public Airplane(int id, String type) {
        super(id);
        this.type = type;
        this.seats = null;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getType() {
        return type;

    }

    public ArrayList<Seat> getAvailableSeats(){
        return null;
    }
}
