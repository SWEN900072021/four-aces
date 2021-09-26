package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.AirplaneDataMapper;

import java.util.List;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private Integer source;
    private Integer destination;
    private Integer airlineId;
    private Integer airplaneId;

    public Flight(Integer id, String code, String date, String time, int source, int destination, Integer airlineId, Integer airplaneId) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.airlineId = airlineId;
        this.airplaneId = airplaneId;
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

    public Integer getSourceAirportId() { return this.source; }


    public Integer getDestinationAirportId() { return this.destination; }

    public Airport getSourceAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(source);
    }

    public Airport getDestinationAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(destination);
    }

    public Integer getAirlineId() {
        return this.airlineId;
    }

    public Integer getAirplaneId() {
        return this.airplaneId;
    }

    public Airplane getAirplane() throws Exception {
        AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
        Airplane airplane = airplaneDataMapper.findById(this.airplaneId);
        return airplane;
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

