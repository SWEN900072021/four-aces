package com.example.datasource;

import com.example.domain.DomainObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("EmptyMethod")
public interface DataMapper<E extends DomainObject> {

    String SQLInsert = "INSERT INTO %s(%s) VALUES(%s);";

    String SQLSelect = "SELECT %s FROM %s %s;";

    String SQLUpdate = "UPDATE %s SET %s WHERE %s;";

    String SQLDelete = "DELETE FROM %s WHERE %s;";

    void insert(E obj) throws Exception;

    void update(E obj) throws Exception;

    void delete(E obj) throws Exception;

    E findById(int id) throws Exception;

    ArrayList<E> getAll() throws Exception;

    ArrayList<E> find(HashMap<String, String> params) throws SQLException, Exception;

    static DataMapper<?> getDataMapper(String domainObjectName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String dataMapperName = "com.example.datasource." + domainObjectName + "DataMapper";
        Class<?> dataMapperClass = Class.forName(dataMapperName);
        Method m = dataMapperClass.getMethod("getInstance");
        return (DataMapper<?>) m.invoke(null);
    }

}
