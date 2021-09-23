package com.example.datasource;

import com.example.domain.Airplane;
import com.example.domain.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirplaneDataMapper extends AbstractDataMapper<Airplane> {

    private static AirplaneDataMapper _instance;
    private AirplaneDataMapper(){
        this.table = "airport";
        this.pkey = "referencecode";
        this.fields = new String[]{
                "referencecode",
                "address"
        };
    }

    public static AirplaneDataMapper getInstance(){
        if ( _instance == null ){
            return _instance = new AirplaneDataMapper();
        }
        return _instance;
    }

    @Override
    public Airplane newDomainObject(ResultSet resultSet) throws Exception {
        Integer airplaneId = Integer.parseInt(resultSet.getString("airplane_id"));
        String type = resultSet.getString("airplane_type");
        return new Airplane(airplaneId, type);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Airplane obj) throws Exception {
        ps.setString(1,obj.getType());
    }
}
