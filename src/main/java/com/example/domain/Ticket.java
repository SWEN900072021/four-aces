package com.example.domain;

public class Ticket extends DomainObject{

    private Passenger owner;
    private Flight flight;
    private Seat seat;
    private Float price;

    public Ticket(){
        super(null);
    }
}
