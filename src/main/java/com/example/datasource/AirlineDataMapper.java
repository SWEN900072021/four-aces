package com.example.datasource;

import com.example.domain.Airline;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirlineDataMapper extends UserDataMapper<Airline>{

    private static AirlineDataMapper _instance;

    private AirlineDataMapper() {
        super("airline", "airline_");
        this.fields = new String[]{this.prefix + "username",
                this.prefix + "password",
                this.prefix + "email",
                this.prefix + "name",
                this.prefix + "pending"};
    }

    public static AirlineDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new AirlineDataMapper();
        }
        return _instance;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Airline user) throws Exception {
        super.setPreparedStatement(ps, user);
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getName());
        ps.setBoolean(5, user.isPending());
    }

    @Override
    public Airline newDomainObject(ResultSet resultSet) throws Exception {
        Airline airline = super.newDomainObject(resultSet);
        airline.setName(resultSet.getString(prefix+"name"));
        airline.setPending(resultSet.getBoolean(prefix+"pending"));
        return airline;
    }

    @Override
    public Airline createUser(int id, String username, String email, String password) {
        return new Airline(id, username, email, password);
    }
}
