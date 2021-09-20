package com.example.four_aces.domain;

public class Flight {
    private int id;
    private String date;
    private String time;

    public Flight(int id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }
}
