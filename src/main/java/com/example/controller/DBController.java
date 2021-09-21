package com.example.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        try{
            Properties prop = new Properties();
            InputStream inputStream = new FileInputStream(propertyFile);

            prop.load(inputStream);

            this.url = prop.getProperty("url");
            this.name = prop.getProperty("name");
            this.password = prop.getProperty("password");
        }catch (IOException e){
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
}
