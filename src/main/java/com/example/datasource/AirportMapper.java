package com.example.datasource;





import com.example.domain.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportMapper {
    private static final String url = "jdbc:postgresql://localhost:5432/myDB";
    private static final String user = "postgres";
    private static final String password = "xiangyuwuwyx1112";

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

    public static List<Airport> getAllAirport() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airport;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                String referenceCode = rs.getString("referenceCode");
                String address = rs.getString("address");
                Airport airport = new Airport(referenceCode, address);
                airports.add(airport);
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
        return airports;
    }

    public static void addAirport(String referenceCode, String address ) {
        String sql = "INSERT INTO airport (referenceCode,address) VALUES (?, ?);";
        PreparedStatement insertStatement = null;
        Connection conn = null;

        try {
            conn = connection();
            insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, referenceCode);
            insertStatement.setString(2, address);
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
        AirportMapper dbConnection = new AirportMapper();
//        List <Flight> flights = dbConnection.getAllFlights();
//        for (int i = 0; i < flights.size(); i ++) {
//            Flight flight = flights.get(i);
//            System.out.println(flight.getId() + "-" + flight.getDate() + "-" + flight.getTime());
//        }
        dbConnection.addAirport("AP002","MEL");
    }
}

