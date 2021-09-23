package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Airline;
import com.example.domain.Flight;
import com.example.exception.TRSException;

import java.sql.*;

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

    public AirlineDataMapper() {
        super("airline", "airline_");
    }

    @Override
    public void setAttrs(Airline user, ResultSet resultSet) throws SQLException {
        super.setAttrs(user, resultSet);
        user.name = resultSet.getString(prefix+"name");
        user.setPending(resultSet.getBoolean(prefix+"pending"));
    }

    @Override
    public Airline newInstance() {
        return new Airline();
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

    public Airline findById(int id) {
        Airline airline = null;
        String sql = "SELECT * FROM airline WHERE airline_id = ?;";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            rs = stmt.getResultSet();
            if (rs.next()) {
                int airlineId = Integer.parseInt(rs.getString("airline_id"));
                String airlineName = rs.getString("airline_name");
                airline = new Airline(airlineId, airlineName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return airline;
    }

}
