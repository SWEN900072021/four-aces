package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.AirplaneDataMapper;
import com.example.datasource.TicketDataMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private Integer source;
    private Integer destination;
    private Integer airlineId;
    private Integer airplaneId;
    private List<Integer> stopovers;

    public Flight(Integer id, String code, String date, String time, int source, int destination,
                  Integer airlineId, Integer airplaneId, List<Integer> stopovers) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.airlineId = airlineId;
        this.airplaneId = airplaneId;
        this.stopovers = stopovers;
        UnitOfWork.getCurrent().registerNew(this);
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

    public LocalDateTime getDateTime() {
        LocalDateTime dateTime = null;
        // Parse date and time string in this format: 2021/09/09, 16:00
        String[] dateArray = this.date.split("-");
        String[] timeArray = this.time.split(":");
        if (dateArray.length == 3 && timeArray.length == 2) {
            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int day = Integer.parseInt(dateArray[2]);
            int hour = Integer.parseInt(timeArray[0]);
            int minute = Integer.parseInt(timeArray[1]);
            dateTime = LocalDateTime.of(year, month, day, hour, minute);
        } else {
            System.out.println("Wrong date and time format");
        }
        return dateTime;
    }

    public Integer getSourceAirportId() { return this.source; }

    public Integer getDestinationAirportId() { return this.destination; }

    public Airport getSourceAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(source);
    }

    public Airport getDestinationAirport() throws Exception {
        return AirportDataMapper.getInstance().findById(destination);
    }

    public List<Airport> getStopoverAirports() throws Exception {
        List<Airport> stopovers = new ArrayList<>();
        AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
        for (int airportId : this.stopovers) {
            Airport airport = airportDataMapper.findById(airportId);
            stopovers.add(airport);
        }
        return stopovers;
    }

    public String getStopoverAirportsString() throws Exception {
        String stopovers = "";
        AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
        for (int airportId : this.stopovers) {
            Airport airport = airportDataMapper.findById(airportId);
            stopovers += airport.getReferenceCode() + " ";
        }
        return stopovers;
    }

    public Integer getAirlineId() {
        return this.airlineId;
    }

    public Airline getAirline() throws Exception {
        return AirlineDataMapper.getInstance().findById(this.airlineId);
    }

    public Integer getAirplaneId() {
        return this.airplaneId;
    }

    public Airplane getAirplane () throws Exception {
        return AirplaneDataMapper.getInstance().findById(this.airplaneId);
    }

    public List<Ticket> getAvailableTickets() {
        HashMap<String, String> params = new HashMap<>();
        params.put("flight_id", Integer.toString(this.id));

        List<Ticket> tickets = null;
        try {
            tickets = TicketDataMapper.getInstance().find(params);
            return tickets;
        } catch (Exception e) {
            return new ArrayList<Ticket>();
        }
    }

    public void setCode(String code) {
        this.code = code;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setDate(String date) {
        this.date = date;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setTime(String time) {
        this.time = time;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setSource(int source) {
        this.source = source;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setDestination(int destination) {
        this.destination = destination;
        UnitOfWork.getCurrent().registerDirty(this);
    }
}

