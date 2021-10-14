package com.example.datasource;

import com.example.domain.Passenger;
import com.example.domain.Reservation;
import com.example.exception.NoRecordFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Passenger newDomainObject(ResultSet resultSet) throws SQLException {
        String firstname = resultSet.getString("passenger_firstname");
        String lastname = resultSet.getString("passenger_lastname");
        String idType = resultSet.getString("identificationtype");
        String idNumber = resultSet.getString("identificationnumber");
        int id = resultSet.getInt("passenger_id");
        return new Passenger(id,firstname,lastname,idType,idNumber);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Passenger obj) throws SQLException {
        ps.setString(1, obj.getFirstName());
        ps.setString(2, obj.getLastName());
        ps.setString(3, obj.getIdType());
        ps.setString(4, obj.getIdNumber());
    }

    public Passenger find(Passenger passenger) throws SQLException, NoRecordFoundException {
        Passenger result = find("WHERE passenger_firstname='" + passenger.getFirstName() +
                "' AND passenger_lastname= '" + passenger.getLastName() +
                "' AND identificationType= '" + passenger.getIdType() +
                "' AND identificationNumber= '" + passenger.getIdNumber()).get(0);
        return result;
    }
}
