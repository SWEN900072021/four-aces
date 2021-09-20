package com.example.dataMpper;

import com.example.controller.DBController;
import com.example.domain.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
