package main.java.com.example.four_aces.datasource;

import main.java.com.example.four_aces.domain.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightMapper {
    private static final String url = "jdbc:postgresql://localhost:5432/myDB";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static Connection connection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                int flightId = Integer.parseInt(rs.getString("flight_id"));
                String flightCode = rs.getString("flight_code");
                String flightDate = rs.getString("flight_date");
                String flightTime = rs.getString("flight_time");
                Flight flight = new Flight(flightId, flightCode, flightDate, flightTime);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (findStatement != null) {
                    findStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flights;
    }

    public static void insert(Flight flight) {
        String sql = "INSERT INTO flight (flight_code, flight_date, flight_time) VALUES (?, ?, ?);";
        PreparedStatement insertStatement = null;
        Connection conn = null;

        try {
            conn = connection();
            insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, flight.getFlightCode());
            insertStatement.setString(2, flight.getFlightDate());
            insertStatement.setString(3, flight.getFlightTime());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FlightMapper dbConnection = new FlightMapper();
        List <Flight> flights = dbConnection.getAllFlights();
//        for (int i = 0; i < flights.size(); i ++) {
//            Flight flight = flights.get(i);
//            System.out.println(flight.getFlightId() + "-" + flight.getFlightDate() + "-" + flight.getFlightTime());
//        }

//        dbConnection.createFlight("QF180","21/09/09","12:30pm");
//        dbConnection.createFlight("QF170","21/09/09","12:30pm");
    }
}

