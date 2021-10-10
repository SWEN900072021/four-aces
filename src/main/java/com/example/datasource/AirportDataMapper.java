package com.example.datasource;

import com.example.domain.Airport;
import com.example.exception.NoRecordFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportDataMapper extends AbstractDataMapper<Airport> {

    private static AirportDataMapper _instance;
    private AirportDataMapper(){
        this.table = "airport";
        this.pkey = "airport_id";
        this.fields = new String[]{
                "referencecode",
                "address"
        };
    }

    public static AirportDataMapper getInstance(){
        if ( _instance == null ){
            return _instance = new AirportDataMapper();
        }
        return _instance;
    }

    @Override
    public Airport newDomainObject(ResultSet resultSet) throws SQLException, NoRecordFoundException {
        Integer airportId = Integer.parseInt(resultSet.getString("airport_id"));
        String code = resultSet.getString("referencecode");
        String address = resultSet.getString("address");
        return new Airport(airportId, code, address);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Airport obj) throws SQLException {
        ps.setString(1,obj.getReferenceCode());
        ps.setString(2,obj.getAddress());
    }
}
