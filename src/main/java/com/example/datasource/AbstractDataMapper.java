package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.DomainObject;
import com.example.exception.NoRecordFoundException;
import com.example.exception.TRSException;

import java.sql.*;
import java.util.*;

/**
 * To use this class, please remember to add a type parameter when extending it, see the created data mappers
 * Do not forget to add constructor for each data mapper, and put the constructor as private, otherwise it is not
 * singleton pattern.
 * @param <T>
 */
public abstract class AbstractDataMapper<T extends DomainObject> implements DataMapper<T>{

    protected String[] fields; // store all fields in an array form
    protected String table; // store the table name
    protected String pkey; // store the primary key of a table

    /**
     * get the fields in the database
     * @return formatted String: "field1, field2, field3, ..."
     */
    public String getFields(){
        return String.join(",", fields);
    }

    /**
     * get the set in the database, only useful for updating,
     * @return formatted String: "field1 = ?, field2 = ?, ...."
     */
    public String getSets(){
        ArrayList<String> sets = new ArrayList<>();
        for( String field : fields ){
            sets.add(field + " = ?");
        }
        return String.join(",", sets);
    }

    /**
     * Use result set to create a new domain object instance
     * set the instance attributes with result set
     * @param resultSet
     * @return
     * @throws Exception
     */
    public abstract T newDomainObject(ResultSet resultSet) throws SQLException, NoRecordFoundException;

    /**
     * set the PreparedStatement with object
     * @param ps
     * @param obj
     * @throws Exception
     */
    public abstract void setPreparedStatement(PreparedStatement ps, T obj) throws SQLException, NoRecordFoundException;

    /**
     * insert with a concrete object
     * @param obj
     * @throws Exception
     */
    @Override
    public void insert(T obj) throws SQLException, TRSException, NoRecordFoundException {
        Connection conn = new DBController().connect();
        String[] valuesFormat = new String[fields.length];
        Arrays.fill(valuesFormat, "?");
        String sql = String.format(SQLInsert, table, getFields(), String.join(",", valuesFormat));
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        this.setPreparedStatement(ps, obj);
        int rowAffected = ps.executeUpdate();
        if (rowAffected == 0) throw new TRSException("Record Insertion Failed");
        if (rowAffected > 0) {
            ResultSet resultSet = ps.getGeneratedKeys();
            if (!resultSet.next()) throw new TRSException("Record Insertion Failed");
            resultSet.close();
        }
        ps.close();
        conn.close();
    }

    /**
     * update database with a concrete object
     * @param obj
     */
    @Override
    public void update(T obj) throws SQLException, NoRecordFoundException {
        Connection conn = new DBController().connect();
        String sql = String.format(SQLUpdate, table, getSets(), String.format("%s = '%s'", pkey, obj.getId()));
        PreparedStatement ps = conn.prepareStatement(sql);
        this.setPreparedStatement(ps, obj);
        int rowAffected = ps.executeUpdate();
        ps.close();
        conn.close();
        if (rowAffected == 0) {
            throw new NoRecordFoundException(table, obj.getId());
        }
    }

    /**
     * delete a record with a concrete object
     * @param obj
     */
    @Override
    public void delete(T obj) throws SQLException {
        deleteById(obj.getId());
    }

    /**
     * delete record with id
     * @param id
     */
    public void deleteById(int id) throws SQLException{
        String sql = String.format(SQLDelete, table, String.format("%s='%d'", pkey, id));
        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ps.close();
        conn.close();
    }

    /**
     * get all records in the table
     * @return ArrayList of the concrete instances
     */
    @Override
    public ArrayList<T> getAll() throws SQLException, NoRecordFoundException {
        ArrayList<T> objs = new ArrayList<>();
        String sql = String.format(SQLSelect, "*", this.table, "");

        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        conn.close();
        while(rs.next()){
            T obj = newDomainObject(rs);
            objs.add(obj);
        }
        rs.close();
        ps.close();
        return objs;
    }

    /**
     * retrieve one record with id, may not applicable to some table such as airport
     * @param id
     * @return a concrete instance of the result
     */
    @Override
    public T findById(int id) throws SQLException, NoRecordFoundException  {
        Connection conn = new DBController().connect();
        String sql = String.format(SQLSelect, "*", table, "WHERE "+pkey+" = " + id);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        if (!resultSet.next()) {
            throw new NoRecordFoundException(table, id);
        }
        conn.close();
        T obj = this.newDomainObject(resultSet);
        resultSet.close();
        ps.close();
        return obj;
    }

    /**
     * retrieve record from the table by condition created with params, now only works for equal relationship,
     * no inequalities
     * @param params: HashMap(key, value): for example: searching customer with email, then ("email", email)
     * @return ArrayList of concrete instance that satisfies the condition
     * @throws SQLException
     * @throws NoRecordFoundException
     */
    @Override
    public ArrayList<T> find(HashMap<String, String> params) throws SQLException, NoRecordFoundException {
        ArrayList<String> conditions = new ArrayList<>();
        for (String key : params.keySet()) {
            // check if the key provided in the params is a column name in the database
            String columnName = key;
            // if not pick the nearest one
            if(!Arrays.asList(fields).contains(key)){
                for (String field : fields){
                    if (field.indexOf(key) > 0){
                        columnName = field;
                    }
                }
            }
            conditions.add(String.format("%s='%s'",columnName,params.get(key)));
        }
        return find("WHERE " + String.join(" AND ", conditions));
    }

    /**
     * retrieve the records from the table with given condition, this can be used for inequalities
     * @param conditions, make sure it is String type,
     * @return ArrayList of concrete instances
     * @throws SQLException, for sql errors
     * @throws NoRecordFoundException, for no record found in the table
     */
    public ArrayList<T> find(String conditions) throws SQLException, NoRecordFoundException {
        ArrayList<T> objs = new ArrayList<>();
        Connection conn = new DBController().connect();
        String sql = String.format(SQLSelect, "*", table, conditions);
        PreparedStatement findStatement = conn.prepareStatement(sql);
        findStatement.execute();
        ResultSet resultSet = findStatement.getResultSet();
        conn.close();
        while (resultSet.next()) {
            T obj = newDomainObject(resultSet);
            objs.add(obj);
        }
        if (objs.isEmpty()) {
            throw new NoRecordFoundException(table, conditions);
        }
        resultSet.close();
        findStatement.close();
        return objs;
    }

}
