package com.example.four_aces.datasource;


import com.example.four_aces.domain.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerMapper {
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

    public static List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM users;";
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
               // String username = rs.getString("username");
               // String password = rs.getString("password");
                String passenger_id = rs.getString("passenger_id");
                String passenger_firstName = rs.getString("passenger_firstName");
                String passenger_lastName = rs.getString("passenger_lastName");
                String identificationType = rs.getString("identificationType");
                String identificationNumber = rs.getString("identificationNumber");
                Passenger passenger = new Passenger(passenger_id, passenger_firstName, passenger_lastName,identificationType,identificationNumber );
                passengers.add(passenger);
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
        return passengers;
    }
    public static void main(String[] args) {
        PassengerMapper dbConnection = new PassengerMapper();
        List <Passenger> passengers = dbConnection.getAllPassengers();
        for (int i = 0; i < passengers.size(); i ++) {
            Passenger passenger = passengers.get(i);
            System.out.println(passenger.getPassenger_id() + "-" + passenger.getPassenger_firstName() + "-" + passenger.getPassenger_lastName()+ passenger.getIdentificationNumber() + "-"+ passenger.getIdentificationType());
        }
    }

    }
