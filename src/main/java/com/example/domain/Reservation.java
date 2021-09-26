package com.example.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Reservation extends DomainObject {

    private String customerId;
    private Flight goFlight;
    private Flight returnFlight;
    private ArrayList<Passenger> passengers;
    private HashMap<Integer, Integer> goTickets;
    private HashMap<Integer, Integer> returnTickets;

    public Reservation() {
        super(null);

    }



}
