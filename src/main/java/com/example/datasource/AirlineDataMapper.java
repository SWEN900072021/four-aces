package com.example.datasource;

import com.example.domain.Airline;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirlineDataMapper extends UserDataMapper<Airline>{
    private static AirlineDataMapper _instance = null;
    private static final String url = "jdbc:postgresql://localhost:5432/MyDB";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static AirlineDataMapper getInstance() {
        if (_instance == null) {
            _instance = new AirlineDataMapper();
        }
        return _instance;
    }

    private AirlineDataMapper() {
        super("airline", "airline_");
        this.fields = new String[]{this.prefix + "username",
                this.prefix + "password",
                this.prefix + "email",
                this.prefix + "name",
                this.prefix + "pending"};
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

    public static Connection connection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

//    public Airline findById(int id) {
//        Airline airline = null;
//        String sql = "SELECT * FROM airline WHERE airline_id = ?;";
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Connection conn = null;
//
//        try {
//            conn = connection();
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, id);
//            stmt.execute();
//            rs = stmt.getResultSet();
//            if (rs.next()) {
//                int airlineId = Integer.parseInt(rs.getString("airline_id"));
//                String airlineName = rs.getString("airline_name");
//                airline = new Airline(airlineId, airlineName);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return airline;
//    }

    @Override
    public Airline createUser(int id, String username, String email, String password) {
        return new Airline(id, username, email, password);
    }

}
