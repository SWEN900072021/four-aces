package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Airline;
import com.example.domain.Airplane;
import com.example.domain.Airport;
import com.example.domain.Flight;
import com.example.exception.NoRecordFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.util.ArrayList;
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
                "stopovers"
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
    public Flight newDomainObject(ResultSet rs) throws SQLException, NoRecordFoundException {
        AirportDataMapper airportDataMapper = AirportDataMapper.getInstance();
        AirplaneDataMapper airplaneDataMapper = AirplaneDataMapper.getInstance();
        AirlineDataMapper airlineDataMapper = AirlineDataMapper.getInstance();

        int flightId = Integer.parseInt(rs.getString("flight_id"));
        String flightCode = rs.getString("flight_code");
        String flightDate = rs.getString("flight_date");
        String flightTime = rs.getString("flight_time");
        Airport source = airportDataMapper.findById(Integer.parseInt(rs.getString("origin")));
        Airport destination = airportDataMapper.findById(Integer.parseInt(rs.getString("destination")));
        Airline airline = airlineDataMapper.findById(Integer.parseInt(rs.getString("airline_id")));
        Airplane airplane = airplaneDataMapper.findById(Integer.parseInt(rs.getString("airplane_id")));
        String stopoversString = rs.getString("stopovers");
        Flight flight = new Flight(flightId, flightCode, flightDate, flightTime, source, destination,
                                   airline, airplane, toList(stopoversString));
        return flight;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Flight flight) throws SQLException, NoRecordFoundException {
        ps.setString(1, flight.getCode());
        ps.setString(2, flight.getDate());
        ps.setString(3, flight.getTime());
        ps.setInt(4, flight.getSource().getId());
        ps.setInt(5, flight.getDestination().getId());
        ps.setInt(6, flight.getAirline().getId());
        ps.setInt(7, flight.getAirplane().getId());
        ps.setObject(8, toPGObject(flight.getStopoverAirports()));
    }

    public ArrayList<Flight> getAll(int airlineId) throws Exception {
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
        return flights;
    }

    // Covert List to PGObject for inserting in database
    private PGobject toPGObject(List<Airport> stopovers) throws SQLException {
        JSONObject stopoversObj = new JSONObject();
        JSONArray stopoversArray = new JSONArray();
        for (Airport airport : stopovers) {
            JSONObject airportObj = new JSONObject();
            airportObj.put("airport_id", airport.getId());
            airportObj.put("referenceCode", airport.getReferenceCode());
            airportObj.put("address", airport.getAddress());
            stopoversArray.put(airportObj);
        }
        stopoversObj.put("stopovers", stopoversArray);

        // Wrap the JSONObject in a PGobject, otherwise it cannot be inserted into database
        PGobject pGobject = new PGobject();
        pGobject.setType("jsonb");
        pGobject.setValue(stopoversObj.toString());

        return pGobject;
    }

    // Covert JSON string to List
    private List<Airport> toList(String stopoversString) {
        List<Airport> stopovers = new ArrayList<>();
        JSONObject stopoversObj = new JSONObject(stopoversString);
        JSONArray stopoversArr = stopoversObj.getJSONArray("stopovers");
        for (int i = 0; i < stopoversArr.length(); i++) {
            int airportId = stopoversArr.getJSONObject(i).getInt("airport_id");
            String referenceCode = stopoversArr.getJSONObject(i).getString("referenceCode");
            String address = stopoversArr.getJSONObject(i).getString("address");
            Airport airport = new Airport(airportId, referenceCode, address);
            stopovers.add(airport);
        }
        return stopovers;
    }
}
