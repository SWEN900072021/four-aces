package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Ticket;
import com.example.exception.TRSException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDataMapper extends AbstractDataMapper<Ticket> {

    private static TicketDataMapper _instance;

    private TicketDataMapper() {
        this.table = "ticket";
        this.fields = new String[] {"ticket_price", "flight_id", "seat_class", "seat_number", "is_available"};
        this.pkey = "ticket_id";
    }

    public static TicketDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new TicketDataMapper();
        }
        return _instance;
    }

    @Override
    public Ticket newDomainObject(ResultSet rs) throws SQLException {
        int ticketId = Integer.parseInt(rs.getString("ticket_id"));
        double price = Double.parseDouble(rs.getString("ticket_price"));
        int flightId = Integer.parseInt(rs.getString("flight_id"));
        String seatClass = rs.getString("seat_class");
        String seatNumber = rs.getString("seat_number");
        Boolean isAvailable = Boolean.parseBoolean(rs.getString("is_available"));
        Ticket ticket = new Ticket(ticketId, price, flightId, seatClass, seatNumber, isAvailable);
        return ticket;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Ticket ticket) throws Exception {
        ps.setDouble(1, ticket.getPrice());
        ps.setInt(2, ticket.getFlightId());
        ps.setString(3, ticket.getSeatClass());
        ps.setString(4, ticket.getSeatNumber());
        ps.setBoolean(5, ticket.getIsAvailable());
    }

    public List<Ticket> getAll(int flightId) throws Exception{
        List<Ticket> tickets = new ArrayList<>();
        String sql = String.format(SQLSelect, "*", this.table, "WHERE flight_id = ?");
        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flightId);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            Ticket ticket = newDomainObject(rs);
            tickets.add(ticket);
        }
        rs.close();
        ps.close();
        conn.close();
        if (tickets.isEmpty()){
            throw new TRSException("No value found in the table "+ this.table);
        }
        return tickets;
    }

    public List<Ticket> getAll(int flightId, Boolean isAvailable) throws Exception{
        List<Ticket> tickets = new ArrayList<>();
        String sql = String.format(SQLSelect, "*", this.table, "WHERE flight_id = ? AND is_available = " + isAvailable);
        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flightId);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            Ticket ticket = newDomainObject(rs);
            tickets.add(ticket);
        }
        rs.close();
        ps.close();
        conn.close();
        if (tickets.isEmpty()){
            throw new TRSException("No value found in the table "+ this.table);
        }
        return tickets;
    }
}

