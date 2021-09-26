package com.example.datasource;

import com.example.domain.User;

import java.sql.*;

public abstract class UserDataMapper<T extends User> extends AbstractDataMapper<T> implements DataMapper<T> {

    protected final String prefix;

    protected UserDataMapper(String table, String prefix) {
        this.table = table;
        this.prefix = prefix;
        this.pkey = this.prefix+"id";
    }

    public void setPreparedStatement(PreparedStatement ps, T user) throws Exception {
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
    }


    @Override
    public T newDomainObject(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt(prefix + "id");
        String username = resultSet.getString(prefix + "username");
        String email = resultSet.getString(prefix + "email");
        String password = resultSet.getString(prefix + "password");
        return createUser(id, username, email, password);
    }

    public abstract T createUser(int id, String username, String email, String password);

}
