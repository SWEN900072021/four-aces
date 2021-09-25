package com.example.controller;

import java.io.File;
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

    public DBController(){
        try {
            String propertyFile = "";
            // Use config.properties in local development and use system.properties in deployed app
            File f = new File("src/config.properties");
            if (f.exists()) {
                propertyFile = "config.properties";
            } else {
                propertyFile = "system.properties";
            }

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

            System.out.println(this.url);
            System.out.println(this.name);
            System.out.println(this.password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        Connection connection;
        DriverManager.registerDriver(new org.postgresql.Driver());
        connection = DriverManager.getConnection(url, name, password);
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        DBController dbController = new DBController();
        dbController.connect();

        String DB_CONNECTION = System.getenv().get("url");
        System.out.println(DB_CONNECTION);
    }
}

