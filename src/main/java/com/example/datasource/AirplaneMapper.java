package com.example.datasource;

import com.example.domain.Airplane;

import java.sql.*;

public class AirplaneMapper {
    private static AirplaneMapper _instance = null;
    private static final String url = "jdbc:postgresql://localhost:5432/MyDB";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static AirplaneMapper getInstance() {
        if (_instance == null) {
            _instance = new AirplaneMapper();
        }
        return _instance;
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

    public Airplane findById(int id) {
        Airplane airplane = null;
        String sql = "SELECT * FROM airplane WHERE airplane_id = ?;";
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
                int airplaneId = Integer.parseInt(rs.getString("airplane_id"));
                String airplaneType = rs.getString("airplane_type");
                airplane = new Airplane(airplaneId, airplaneType);
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
        return airplane;
    }

}
