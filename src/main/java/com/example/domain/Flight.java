package com.example.domain;

import java.util.List;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private int source;
    private int destination;
    private String stopovers;
    private Integer airlineId;
    private Integer airplaneId;

    public Flight(Integer id, String code, String date, String time, int source, int destination, Integer airlineId) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.airlineId = airlineId;
        UnitOfWork.getInstance().registerNew(this);
    }

    public String getCode() {
        return this.code;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public int getSource() { return this.source; }

    public int getDestination() { return this.destination; }

    public String getSrcRefCode(List<Airport> airports) {
        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            if (airport.getId() == this.source) {
                return airport.getReferenceCode();
            }
        }
        return "Airport Not Found";
    }

    public String getDesRefCode(List<Airport> airports) {
        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            if (airport.getId() == this.destination) {
                return airport.getReferenceCode();
            }
        }
        return "Airport Not Found";
    }

    public int getAirlineId() {
        return this.airlineId;
    }

    public void setCode(String code) {
        this.code = code;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setDate(String date) {
        this.date = date;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setTime(String time) {
        this.time = time;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setSource(int source) {
        this.source = source;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public void setDestination(int destination) {
        this.destination = Flight.this.destination;
        UnitOfWork.getInstance().registerDirty(this);
    }
}

