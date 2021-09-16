package com.example.trs;

import java.util.ArrayList;

public class Flight {

    private String date;
    private String time;
    private ArrayList<Seat> availableSeats;

    public String code;
    public Airport origin;
    public Airport destination;
    public ArrayList<Airport> stopovers;
    public Airplane type;
}
