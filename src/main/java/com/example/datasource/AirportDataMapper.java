package com.example.datasource;

import com.example.domain.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirportDataMapper extends AbstractDataMapper<Airport> {

    private static AirportDataMapper _instance;
    private AirportDataMapper(){
        this.table = "airport";
        this.pkey = "referencecode";
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
    public Airport newDomainObject(ResultSet resultSet) throws Exception {
        Integer airportId = Integer.parseInt(resultSet.getString("airport_id"));
        String code = resultSet.getString("referencecode");
        String address = resultSet.getString("address");
        return new Airport(airportId, code, address);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Airport obj) throws Exception {
        ps.setString(1,obj.getReferenceCode());
        ps.setString(2,obj.getAddress());
    }
}
