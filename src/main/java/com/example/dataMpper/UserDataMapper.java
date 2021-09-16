package com.example.dataMpper;

import com.example.controller.DBController;
import com.example.trs.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDataMapper<T extends User> implements DataMapper<User> {

    protected final String table;
    protected final String prefix;
    protected T user;

    public UserDataMapper(String table, String prefix, T user) {
        this.table = table;
        this.prefix = prefix;
        this.user = user;
    }

    @Override
    public T create(HashMap<String, String> params) {
        Connection conn = null;
        try {
            conn = new DBController().connect();
            StringBuilder attrs = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (String key : params.keySet()) {
                attrs.append(prefix).append(key).append(",");
                values.append("'").append(params.get(key)).append("',");
            }
            attrs = new StringBuilder(attrs.substring(0, attrs.length() - 1));
            values = new StringBuilder(values.substring(0, values.length() - 1));
            String sql = String.format(SQLInsert, table, attrs.toString(), values.toString());
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    this.user.setId(resultSet.getInt(1));
                } else
                    this.user.setId(-1);
                resultSet.close();
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.user;
    }

    @Override
    public void update(HashMap<String, String> params) {

    }

    @Override
    public void delete(HashMap<String, String> params) {

    }

    @Override
    public T find(HashMap<String, String> params) {
        StringBuilder condition = new StringBuilder();
        for( String key : params.keySet() ){
            condition.append(" ").append(key).append(" = '").append(params.get(key)).append("' ,");
        }
        if (condition.length() > 0){
            condition = new StringBuilder(condition.substring(0, condition.length() - 2));
            condition = new StringBuilder(condition.toString().replaceAll(condition.toString(), "AND"));
            condition.insert(0, " WHERE");
        }
        return find("*", condition.toString());
    }

    public T find(String selectAttrs, String conditions){
        try {
            Connection conn = new DBController().connect();
            String sql = String.format(SQLSelect, selectAttrs, table, conditions);
            PreparedStatement findStatement = conn.prepareStatement(sql);
            findStatement.execute();
            ResultSet resultSet = findStatement.getResultSet();
            if( resultSet.next() ) this.setAttrs(resultSet);
            else this.user.setId(-1);
            resultSet.close();
            findStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.user;
    }

    public void setAttrs(ResultSet resultSet) throws SQLException {
        this.user.setId(resultSet.getInt(prefix+"id"));
        this.user.setUsername(resultSet.getString(prefix+"username"));
        this.user.setEmail(resultSet.getString(prefix+"email"));
        this.user.setPassword(resultSet.getString(prefix+"password"));
    }
}
