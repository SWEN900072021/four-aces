package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Passenger;
import com.example.domain.Reservation;
import com.example.domain.Ticket;
import com.example.exception.NoRecordFoundException;
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
    public Ticket newDomainObject(ResultSet rs) throws SQLException, NoRecordFoundException {
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
            int psgId = Integer.parseInt(passengerId);
            int rsvId = Integer.parseInt(reservationId);
            Passenger passenger = PassengerDataMapper.getInstance().findById(psgId);
            Reservation reservation = ReservationDataMapper.getInstance().findById(rsvId);
            ticket = new Ticket(ticketId, price, flightId, seatClass, seatNumber, passenger, reservation);
        }
        return ticket;
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Ticket ticket) throws SQLException {
        ps.setDouble(1, ticket.getPrice());
        ps.setInt(2, ticket.getFlightId());
        ps.setString(3, ticket.getSeatClass());
        ps.setString(4, ticket.getSeatNumber());
        if (ticket.getPassenger() != null) {
            ps.setInt(5, ticket.getPassenger().getId());
        } else {
            ps.setNull(5, Types.NULL);
        }
        if (ticket.getReservation() != null) {
            ps.setInt(6, ticket.getReservation().getId());
        } else {
            ps.setNull(6, Types.NULL);
        }
    }

    public List<Ticket> getAll(int flightId, Boolean isAvailable) throws SQLException, NoRecordFoundException {
        String condition;
        if (isAvailable) {
            condition = String.format("WHERE flight_id = %d AND passenger_id IS NULL", flightId);
        } else {
            condition = String.format("WHERE flight_id = %d AND passenger_id IS NOT NULL", flightId);
        }
        return this.find(condition);
    }

}

