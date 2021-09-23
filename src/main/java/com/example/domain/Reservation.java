package com.example.domain;

import java.util.ArrayList;

public class Reservation extends DomainObject {

    private Customer customer;
    private ArrayList<Ticket> tickets;

    public Reservation(){
        super(null);
    }

}
