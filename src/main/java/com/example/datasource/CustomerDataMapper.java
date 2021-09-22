package com.example.datasource;

import com.example.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDataMapper extends UserDataMapper<Customer> {

    private static CustomerDataMapper _instance;

    private CustomerDataMapper() {
        super("customer", "cus_");
        this.fields = new String[]{prefix + "username",
                prefix + "password",
                prefix + "email",
                prefix + "firstname",
                prefix + "lastname"};
    }

    public static CustomerDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new CustomerDataMapper();
        }
        return _instance;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Customer user) throws Exception {
        super.setPreparedStatement(ps, user);
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getFirstName());
        ps.setString(5, user.getLastName());
    }

    @Override
    public Customer newDomainObject(ResultSet resultSet) throws Exception {
        Customer customer = super.newDomainObject(resultSet);
        customer.setFirstName(resultSet.getString(prefix + "firstname"));
        customer.setLastName(resultSet.getString(prefix + "lastname"));
        return customer;
    }

    @Override
    public Customer createUser(int id, String username, String email, String password) {
        return new Customer(id, username, email, password);
    }
}
