package com.example.datasource;

import com.example.exception.TRSException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataMapper<E> {

    String SQLInsert = "INSERT INTO %s(%s) VALUES(%s)";

    String SQLSelect = "SELECT %s FROM %s %s";

    String SQLUpdate = "UPDATE %s SET %s WHERE %s";

    E create(HashMap<String, String> params) throws SQLException;

    int update(HashMap<String, String> params) throws TRSException, SQLException;

    void delete(HashMap<String, String> params);

    ArrayList<E> find(HashMap<String, String> params) throws SQLException;
}
