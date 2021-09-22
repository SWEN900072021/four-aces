package com.example.datasource;

import com.example.domain.Airline;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirlineDataMapper extends UserDataMapper<Airline>{

    public AirlineDataMapper() {
        super("airline", "airline_", new Airline());
    }

    @Override
    public void setAttrs(ResultSet resultSet) throws SQLException {
        super.setAttrs(resultSet);
        this.user.name = resultSet.getString(prefix+"name");
        this.user.setPending(resultSet.getBoolean(prefix+"pending"));
    }
}