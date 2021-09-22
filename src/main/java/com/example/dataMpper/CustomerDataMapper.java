package com.example.dataMpper;

import com.example.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerDataMapper extends UserDataMapper<Customer> {

    public CustomerDataMapper() {
        super("customer", "cus_");
    }

    @Override
    public void setAttrs(Customer user, ResultSet resultSet) throws SQLException {
        super.setAttrs(user, resultSet);
        user.setFirstName(resultSet.getString(prefix+"firstname"));
        user.setLastName(resultSet.getString(prefix+"lastname"));
    }

    @Override
    public Customer newInstance() {
        return new Customer();
    }
}
