package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBController {

    private String url;
    private String name;
    private String password;

    public static final String propertyFile = "config.properties";

    public DBController(){
        try {
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);

            if (inputStream != null){
                prop.load(inputStream);
            } else{
                throw new IOException(("File not found"));
            }

            this.url = prop.getProperty("url");
            this.name = prop.getProperty("name");
            this.password = prop.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        Connection connection;
        DriverManager.registerDriver(new org.postgresql.Driver());
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            connection = DriverManager.getConnection(dbUrl);
        }
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.close();
    }
}
