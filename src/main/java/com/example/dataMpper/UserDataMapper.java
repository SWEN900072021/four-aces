package com.example.dataMpper;

import com.example.controller.DBController;
import com.example.domain.User;
import com.example.exception.TRSException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDataMapper<T extends User> implements DataMapper<T> {

    protected final String table;
    protected final String prefix;

    public UserDataMapper(String table, String prefix) {
        this.table = table;
        this.prefix = prefix;
    }

    @Override
    public T create(HashMap<String, String> params) throws SQLException {
        Connection conn = new DBController().connect();
        T user = this.newInstance();
        StringBuilder attrs = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String key : params.keySet()) {
            attrs.append(prefix).append(key).append(",");
            values.append("'").append(params.get(key)).append("',");
        }
        attrs = new StringBuilder(attrs.substring(0, attrs.length() - 1));
        values = new StringBuilder(values.substring(0, values.length() - 1));
        String sql = String.format(SQLInsert, table, attrs, values);
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            } else
                user.setId(-1);
            resultSet.close();
        }
        preparedStatement.close();
        conn.close();
        return user;
    }

    @Override
    public int update(HashMap<String, String> params) throws TRSException, SQLException {
        Connection conn = new DBController().connect();
        String[] condition_columns = params.get("condition").split(",");
        StringBuilder condition = new StringBuilder();
        for( String column : condition_columns ){
            condition.append(String.format("%s=%s AND ", prefix + column, params.get(column)));
        }
        condition = new StringBuilder(condition.substring(0, condition.length() - " AND ".length()));

        String[] update_columns = params.get("update").split(",");
        StringBuilder update = new StringBuilder();
        for (String column: update_columns){
            update.append(String.format("%s='%s', ", prefix + column, params.get(column)));
        }
        update = new StringBuilder(update.substring(0, update.length() - ", ".length()));
        String sql = String.format(SQLUpdate, table, update, condition);
        PreparedStatement ps = conn.prepareStatement(sql);
        int rowAffected = ps.executeUpdate();
        ps.close();
        conn.close();
        return rowAffected;
    }

    @Override
    public void delete(HashMap<String, String> params) {

    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) throws SQLException {
        StringBuilder condition = new StringBuilder();
        for (String key : params.keySet()) {
            condition.append(" ").append(prefix).append(key).append(" = '").append(params.get(key)).append("' ,");
        }
        if (condition.length() > 0) {
            condition = new StringBuilder(condition.substring(0, condition.length() - 2));
            condition = new StringBuilder(condition.toString().replaceAll(",", "AND"));
            condition.insert(0, " WHERE");
        }
        return find("*", condition.toString());
    }

    public ArrayList<T> find(String selectAttrs, String conditions) throws SQLException {
        ArrayList<T> users = new ArrayList<>();
        Connection conn = new DBController().connect();
        String sql = String.format(SQLSelect, selectAttrs, table, conditions);
        PreparedStatement findStatement = conn.prepareStatement(sql);
        findStatement.execute();
        ResultSet resultSet = findStatement.getResultSet();
        while (resultSet.next()) {
            T user = newInstance();
            setAttrs(user, resultSet);
            users.add(user);
        }
        if (users.isEmpty()) {
            T user = newInstance();
            user.setId(-1);
            users.add(user);
        }
        resultSet.close();
        findStatement.close();
        conn.close();
        return users;
    }

    public void setAttrs(T user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(prefix + "id"));
        user.setUsername(resultSet.getString(prefix + "username"));
        user.setEmail(resultSet.getString(prefix + "email"));
        user.setPassword(resultSet.getString(prefix + "password"));
    }

    public T newInstance() {
        return null;
    }
}
