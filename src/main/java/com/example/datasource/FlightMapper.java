package com.example.datasource;

import com.example.domain.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightMapper {
    private static FlightMapper _instance = null;
    private static final String url = "jdbc:postgresql://localhost:5432/myDB";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static FlightMapper getInstance() {
        if (_instance == null) {
            _instance = new FlightMapper();
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

    public static List<Flight> getAll() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                int flightId = Integer.parseInt(rs.getString("flight_id"));
                String flightCode = rs.getString("flight_code");
                String flightDate = rs.getString("flight_date");
                String flightTime = rs.getString("flight_time");
                Flight flight = new Flight(flightId, flightCode, flightDate, flightTime);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (findStatement != null) {
                    findStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flights;
    }

    public static void insert(Flight flight) {
        String sql = "INSERT INTO flight (flight_code, flight_date, flight_time) VALUES (?, ?, ?);";
        PreparedStatement insertStatement = null;
        Connection conn = null;

        try {
            conn = connection();
            insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, flight.getCode());
            insertStatement.setString(2, flight.getDate());
            insertStatement.setString(3, flight.getTime());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static Flight findById(int id) {
        Flight flight = null;
        String sql = "SELECT * FROM flight WHERE flight_id = ?;";
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
                int flightId = Integer.parseInt(rs.getString("flight_id"));
                String flightCode = rs.getString("flight_code");
                String flightDate = rs.getString("flight_date");
                String flightTime = rs.getString("flight_time");
                flight = new Flight(flightId, flightCode, flightDate, flightTime);
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
        return flight;

    }

    public static void update(Flight flight) {
        String sql = "UPDATE flight SET flight_code = ?, flight_date = ?, flight_time = ? WHERE flight_id = ?;";
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = connection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, flight.getCode());
            stmt.setString(2, flight.getDate());
            stmt.setString(3, flight.getTime());
            stmt.setInt(4, flight.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
    }

    public static void deleteById(int id) {
        String sql = "DELETE FROM flight WHERE flight_id = ?;";
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = connection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
    }

    public static void main(String[] args) {
        FlightMapper flightMapper = new FlightMapper();
    }
}

