package com.example.datasource;

import com.example.domain.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDataMapper extends UserDataMapper<Admin> {

    public AdminDataMapper() {
        super("administrator", "administrator_", Admin.getAdmin());
    }

    @Override
    public void setAttrs(ResultSet resultSet) throws SQLException {
        this.user.setId(resultSet.getInt(prefix+"id"));
        this.user.setUsername(resultSet.getString(prefix+"username"));
        this.user.setPassword(resultSet.getString(prefix+"password"));
    }
}
