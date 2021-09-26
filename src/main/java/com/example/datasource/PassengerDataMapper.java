package com.example.datasource;

import com.example.domain.Passenger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PassengerDataMapper extends AbstractDataMapper<Passenger> {

    private static PassengerDataMapper _instance;

    private PassengerDataMapper() {
        this.table = "passenger";
        this.pkey = "passenger_id";
        this.fields = new String[]{
                "passenger_firstname",
                "passenger_lastname",
                "identificationtype",
                "identificationnumber"
        };
    }

    public static PassengerDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new PassengerDataMapper();
        }
        return _instance;
    }

    @Override
    public Passenger newDomainObject(ResultSet resultSet) throws Exception {
        String firstname = resultSet.getString("passenger_firstname");
        String lastname = resultSet.getString("passenger_lastname");
        String idType = resultSet.getString("identificationtype");
        String idNumber = resultSet.getString("identificationnumber");
        int id = resultSet.getInt("passenger_id");
        return new Passenger(id, firstname,lastname,idType,idNumber);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Passenger obj) throws Exception {
        ps.setString(1,obj.getfirstName());
        ps.setString(2,obj.getlastName());
        ps.setString(3,obj.getIdentificationType());
        ps.setString(4,obj.getIdentificationNumber());
    }
}
