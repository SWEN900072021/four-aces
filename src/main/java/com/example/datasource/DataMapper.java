package com.example.datasource;

import java.sql.SQLException;
import java.util.HashMap;

public interface DataMapper<E> {

    public final String SQLInsert = "INSERT INTO %s(%s) VALUES(%s)";

    public final String SQLSelect = "SELECT %s FROM %s %s";

    public E create(HashMap<String, String> params) throws SQLException;

    public void update(HashMap<String, String> params);

    public void delete(HashMap<String, String> params);

    public E find(HashMap<String, String> params) throws SQLException;
}
