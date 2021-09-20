package com.example.four_aces.datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightMapper {
    private static final String url = "jdbc:postgresql://localhost:5432/MyDB";
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

//    public static List<com.example.four_aces.domain.Flight> getAllFlights() {
//        List<com.example.four_aces.domain.Flight> flights = new ArrayList<>();
//        String sql = "SELECT * FROM flight;";
//        PreparedStatement findStatement = null;
//        ResultSet rs = null;
//        Connection conn = null;
//
//        try {
//            conn = connection();
//            findStatement = conn.prepareStatement(sql);
//            findStatement.execute();
//            rs = findStatement.getResultSet();
//            int id = 0;
//            while (rs.next()) {
//                String code = rs.getString("flight_code");
//                com.example.four_aces.domain.Flight flight = new com.example.four_aces.domain.Flight(id++, code);
//                flights.add(flight);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (findStatement != null) {
//                    findStatement.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return flights;
//    }

    public static List<com.example.four_aces.domain.Flight> searchFlights(String flightDate, String flightTime) {
        List<com.example.four_aces.domain.Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight WHERE time=\'" + flightTime + "\' AND date=\'" + flightDate + "\';";
        System.out.println(sql);
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            int id = 0;
            while (rs.next()) {
                String date = rs.getString("date");
                String time = rs.getString("time");
                int flightId = rs.getInt("flight_id");
                com.example.four_aces.domain.Flight flight = new com.example.four_aces.domain.Flight(flightId, date, time);
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

}
