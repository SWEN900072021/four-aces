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
import java.sql.Types;

public class TicketDataMapper extends AbstractDataMapper<Ticket> {

    private static TicketDataMapper _instance;

    private TicketDataMapper() {
        this.table = "ticket";
        this.fields = new String[] {"ticket_price", "flight_id", "seat_class", "seat_number", "passenger_id", "reservation_id"};
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
        String passengerId = rs.getString("passenger_id");
        String reservationId = rs.getString("reservation_id");
        Ticket ticket;
        if (passengerId == null || reservationId == null) {
            ticket = new Ticket(ticketId, price, flightId, seatClass, seatNumber);
        } else {
            Integer psgId = Integer.parseInt(passengerId);
            Integer rsvId = Integer.parseInt(reservationId);
            ticket = new Ticket(ticketId, price, flightId, seatClass, seatNumber, psgId, rsvId);
        }
        return ticket;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Ticket ticket) throws Exception {
        ps.setDouble(1, ticket.getPrice());
        ps.setInt(2, ticket.getFlightId());
        ps.setString(3, ticket.getSeatClass());
        ps.setString(4, ticket.getSeatNumber());
        if (ticket.getPassengerId() != null) {
            ps.setInt(5, ticket.getPassengerId());
        } else {
            ps.setNull(5, Types.NULL);
        }
        if (ticket.getReservationId() != null) {
            ps.setInt(6, ticket.getReservationId());
        } else {
            ps.setNull(6, Types.NULL);
        }

    }

    public List<Ticket> getAll(int flightId) throws SQLException {
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
        return tickets;
    }

    public List<Ticket> getAll(int flightId, Boolean isAvailable) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql;
        if (isAvailable) {
            sql = String.format(SQLSelect, "*", this.table, "WHERE flight_id = ? AND passenger_id IS NULL");
        } else {
            sql = String.format(SQLSelect, "*", this.table, "WHERE flight_id = ? AND passenger_id IS NOT NULL");
        }
        System.out.println(sql);
        Connection conn = new DBController().connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flightId);
        ps.setBoolean(2, isAvailable);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            Ticket ticket = newDomainObject(rs);
            tickets.add(ticket);
        }
        rs.close();
        ps.close();
        conn.close();
        return tickets;
    }
}

