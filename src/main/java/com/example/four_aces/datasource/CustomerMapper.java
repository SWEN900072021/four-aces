package com.example.four_aces.datasource;

import com.example.four_aces.domain.Customer;
import com.example.four_aces.domain.DomainObject;

import java.awt.peer.TextAreaPeer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    private static CustomerMapper _instance = null;
    private static final String url = "jdbc:postgresql://localhost:5432/myDB";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static CustomerMapper getInstance() {
        if (_instance == null) {
            _instance = new CustomerMapper();
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

    public static List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String username = rs.getString("username");
                String password = rs.getString("password");
                Customer customer = new Customer(id, username, password);
                customers.add(customer);
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
        return customers;
    }

    public static Customer findById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id = ?;";
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
                int customerId = Integer.parseInt(rs.getString("id"));
                String username = rs.getString("username");
                String password = rs.getString("password");
                customer = new Customer(id, username, password);
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
        return customer;

    }

    public static void deleteById(int id) {
        String sql = "DELETE FROM customer WHERE id = ?;";
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
        CustomerMapper customerMapper = new CustomerMapper();
    }
}