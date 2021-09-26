package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Flight;
import com.example.domain.Ticket;
import com.example.exception.TRSException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightDataMapper extends AbstractDataMapper<Flight> {

    private static FlightDataMapper _instance;

    private FlightDataMapper() {
        this.table = "flight";
        this.fields = new String[] {
                "flight_code",
                "flight_date",
                "flight_time",
                "origin",
                "destination",
                "airline_id",
                "airplane_id",
        };
        this.pkey = "flight_id";
    }

    public static FlightDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new FlightDataMapper();
        }
        return _instance;
    }

    @Override
    public Flight newDomainObject(ResultSet rs) throws SQLException {
        int flightId = Integer.parseInt(rs.getString("flight_id"));
        String flightCode = rs.getString("flight_code");
        String flightDate = rs.getString("flight_date");
        String flightTime = rs.getString("flight_time");
        int source = Integer.parseInt(rs.getString("origin"));
        int destination = Integer.parseInt(rs.getString("destination"));
        int airlineId = Integer.parseInt(rs.getString("airline_id"));
        int airplaneId = Integer.parseInt(rs.getString("airplane_id"));
        Flight flight = new Flight(flightId, flightCode, flightDate, flightTime, source, destination, airlineId, airplaneId);
        return flight;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Flight flight) throws Exception {
        ps.setString(1, flight.getCode());
        ps.setString(2, flight.getDate());
        ps.setString(3, flight.getTime());
        ps.setInt(4, flight.getSourceAirportId());
        ps.setInt(5, flight.getDestinationAirportId());
        ps.setInt(6, flight.getAirlineId());
        ps.setInt(7, flight.getAirplaneId());
    }

    public ArrayList<Flight> getAll(int airlineId) throws Exception{
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = String.format(SQLSelect, "*", this.table, "WHERE airline_id = ?");

        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, airlineId);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            Flight flight = newDomainObject(rs);
            flights.add(flight);
        }
        rs.close();
        ps.close();
        conn.close();
        if (flights.isEmpty()) {
            throw new TRSException("No value found in the table "+ this.table);
        }
        return flights;
    }
}
