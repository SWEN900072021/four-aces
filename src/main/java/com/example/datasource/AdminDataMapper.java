package com.example.datasource;

import com.example.domain.Admin;
import com.example.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminDataMapper extends UserDataMapper<Admin> {

    public AdminDataMapper() {
        super("administrator", "administrator_");
    }

    @Override
    public void setAttrs(Admin user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(prefix+"id"));
        user.setUsername(resultSet.getString(prefix+"username"));
        user.setPassword(resultSet.getString(prefix+"password"));
    }

    @Override
    public Admin newInstance() {
        return Admin.getAdmin();
    }
}
