package com.example.datasource;

import com.example.domain.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FlightDataMapper extends AbstractDataMapper<Flight> {

    private static FlightDataMapper _instance;

    private FlightDataMapper() {
        this.table = "flight";
        this.fields = new String[]{
                "flight_code",
                "date",
                "time",
//                "airline_id",
//                "airplane_id",
//                "source",
//                "destination",
//                "stopovers"
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
        String flightDate = rs.getString("date");
        String flightTime = rs.getString("time");
        Flight flight = new Flight(flightId, flightCode, flightDate, flightTime);
        return flight;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Flight flight) throws Exception {
        ps.setString(1, flight.getCode());
        ps.setString(2, flight.getDate());
        ps.setString(3, flight.getTime());
    }
}
