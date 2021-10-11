package com.example.domain;

import com.example.datasource.AirlineDataMapper;
import com.example.datasource.AirportDataMapper;
import com.example.datasource.AirplaneDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.exception.NoRecordFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flight extends DomainObject {
    private String code;
    private String date;
    private String time;
    private Airport source;
    private Airport destination;
    private Airline airline;
    private Airplane airplane;
    private List<Airport> stopovers;

    public Flight(Integer id, String code, String date, String time, Airport source, Airport destination,
            Airline airline, Airplane airplane, List<Airport> stopovers) {
        super(id);
        this.code = code;
        this.date = date;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.airline = airline;
        this.airplane = airplane;
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

    public Airport getSource() {
        return this.source;
    }

    public Airport getDestination() {
        return this.destination;
    }

    public List<Airport> getStopoverAirports() {
        return stopovers;
    }

    public String getStopoverAirportsString() throws SQLException, NoRecordFoundException {
        String stopovers = "";
        // AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
        // for (int airportId : this.stopovers) {
        // Airport airport = airportDataMapper.findById(airportId);
        // stopovers += airport.getReferenceCode() + " ";
        // }
        return stopovers;
    }

    public Airline getAirline() {
        return this.airline;
    }

    public Airplane getAirplane() {
        return this.airplane;
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

    public void setSource(Airport source) {
        this.source = source;
        UnitOfWork.getCurrent().registerDirty(this);
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
        UnitOfWork.getCurrent().registerDirty(this);
    }
}
