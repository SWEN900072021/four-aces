package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirplaneMapper;
import com.example.datasource.AirportDataMapper;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private int source;
    private int destination;
    private Integer airlineId;

    public Flight(Integer id) {
        super(id);
        this.code = null;
        this.date = null;
        this.time = null;
        UnitOfWork.getInstance().registerNew(this);
    }
    public Flight(Integer id, String code, String date, String time) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.airlineId = airlineId;
        UnitOfWork.getInstance().registerNew(this);
    }

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

    public Airline getAirline() throws Exception {
        return AirlineDataMapper.getInstance().findById(airlineId);
    }

    public int getSource() { return this.source; }

    public int getSourceAirportId() { return this.source; }

    public int getDestinationAirportId() { return this.destination; }

    public Airport getSourceAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(source);
    }

    public Airport getDestinationAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(destination);
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

