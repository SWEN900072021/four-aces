package com.example.dataMpper;

import com.example.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerDataMapper extends UserDataMapper<Customer> {

    public CustomerDataMapper() {
        super("customer", "cus_", new Customer());
    }

    @Override
    public void setAttrs(ResultSet resultSet) throws SQLException {
        super.setAttrs(resultSet);
        this.user.setFirstName(resultSet.getString(prefix+"firstname"));
        this.user.setLastName(resultSet.getString(prefix+"lastname"));
    }
}
