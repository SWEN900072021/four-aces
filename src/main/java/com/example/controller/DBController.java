package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

    private final String url = "jdbc:postgresql://localhost:5432/MyDB";
    private final String name = "postgres";
    private final String password = "admin";

    public Connection connect() throws SQLException {
        Connection connection = null;
        DriverManager.registerDriver(new org.postgresql.Driver());
        connection = DriverManager.getConnection(url, name, password);
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.close();
    }
}
