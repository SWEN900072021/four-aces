package com.example.dataMpper;

import com.example.domain.Airline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
