package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Airline;
import com.example.exception.TRSException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirlineDataMapper extends UserDataMapper<Airline>{

    public AirlineDataMapper() {
        super("airline", "airline_");
    }

    @Override
    public void setAttrs(Airline user, ResultSet resultSet) throws SQLException {
        super.setAttrs(user, resultSet);
        user.name = resultSet.getString(prefix+"name");
        user.setPending(resultSet.getBoolean(prefix+"pending"));
    }

    @Override
    public Airline newInstance() {
        return new Airline();
    }

}
