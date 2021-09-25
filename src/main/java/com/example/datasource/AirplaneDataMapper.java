package com.example.datasource;

import com.example.domain.Airplane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneDataMapper extends AbstractDataMapper<Airplane> {

    private static AirplaneDataMapper _instance;

    private AirplaneDataMapper() {
        this.table = "airplane";
        this.fields = new String[] {"airplane_id", "airplane_type", "seats"};
        this.pkey = "airplane_id";
    }

    public static AirplaneDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new AirplaneDataMapper();
        }
        return _instance;
    }

    @Override
    public Airplane newDomainObject(ResultSet rs) throws SQLException {
        int airplaneId = Integer.parseInt(rs.getString("airplane_id"));
        String airplaneType = rs.getString("airplane_type");
        String seats = rs.getString("seats");
        Airplane airplane = new Airplane(airplaneId, airplaneType, seats);
        return airplane;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Airplane airplane) throws Exception {
        ps.setString(1, airplane.getType());
        ps.setString(2, airplane.getSeatsString());

    }
}