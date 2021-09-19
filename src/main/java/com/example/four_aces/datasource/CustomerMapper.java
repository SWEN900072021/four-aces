package main.java.com.example.four_aces.datasource;

import main.java.com.example.four_aces.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    private static final String url = "jdbc:postgresql://localhost:5432/myDB";
    private static final String user = "postgres";
    private static final String password = "admin";

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

    public static List<Customer> getAllUsers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            int id = 0;
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                Customer customer = new Customer(id++, username, password);
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
}

