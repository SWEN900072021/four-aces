package com.example.datasource;

import com.example.domain.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDataMapper extends AbstractDataMapper<Ticket> {

    private static TicketDataMapper _instance;

    private TicketDataMapper() {
        this.table = "ticket";
        this.fields = new String[] {"ticket_price", "flight_id", "seat_number", "seat_class"};
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
        Ticket ticket = new Ticket(ticketId, price, flightId, seatClass, seatNumber);
        return ticket;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Ticket ticket) throws Exception {
        ps.setDouble(1, ticket.getPrice());
        ps.setInt(2, ticket.getFlightId());
        ps.setString(3, ticket.getSeatClass());
        ps.setString(4, ticket.getSeatNumber());
    }
}

