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
        String sql = "SELECT * FROM flights;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String flightCode = rs.getString("flight_code");
                String date = rs.getString("flight_date");
                String time = rs.getString("flight_time");
                Flight flight = new Flight(id, flightCode, date,time);
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

    public static void addFlight(String flightCode, String flightDate, String flightTime) {
        String sql = "INSERT INTO flights (id, flight_code, flight_date, flight_time) VALUES (?, ?, ?, ?);";
        PreparedStatement insertStatement = null;
        Connection conn = null;

        try {
            conn = connection();
            insertStatement = conn.prepareStatement(sql);
            insertStatement.setInt(1, 3);
            insertStatement.setString(2, flightCode);
            insertStatement.setString(3, flightDate);
            insertStatement.setString(4, flightTime);
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
//        List <Flight> flights = dbConnection.getAllFlights();
//        for (int i = 0; i < flights.size(); i ++) {
//            Flight flight = flights.get(i);
//            System.out.println(flight.getId() + "-" + flight.getDate() + "-" + flight.getTime());
//        }

        dbConnection.addFlight("QF180","21/09/09","12:30pm");
    }
}

