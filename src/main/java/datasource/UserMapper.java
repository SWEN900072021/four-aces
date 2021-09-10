package datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
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

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = connection();
            findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            rs = findStatement.getResultSet();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(username + "-" + password);
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
        return users;
    }

    public static void main(String[] args) {
        UserMapper dbConnection = new UserMapper();
        List <String> users = dbConnection.getAllUsers();
        for (int i = 0; i < users.size(); i ++) {
            System.out.println(users.get(i));
        }
    }
}

