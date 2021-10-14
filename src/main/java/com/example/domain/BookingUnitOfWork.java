package com.example.domain;

import com.example.datasource.DataMapper;
import com.example.datasource.PassengerDataMapper;
import com.example.datasource.ReservationDataMapper;
import com.example.datasource.TicketDataMapper;
import com.example.exception.NoRecordFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BookingUnitOfWork {
    private Reservation reservation = null;
    private HashMap<Passenger, ArrayList<Ticket>> passengerTickets = new HashMap<>();

    public BookingUnitOfWork(Reservation reservation) {
        this.reservation = reservation;
    }

    public BookingUnitOfWork() {
    }

    public void registerReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void registerPassenger(Passenger passenger) {
        if (!passengerTickets.containsKey(passenger)) {
            passengerTickets.put(passenger, new ArrayList<>());
        }
    }

    public void registerTicket(Passenger passenger, Ticket ticket) {
        if (!passengerTickets.containsKey(passenger)) {
            passengerTickets.put(passenger, new ArrayList<>());
        }
        passengerTickets.get(passenger).add(ticket);
    }

    public void commit() throws Exception {
        ReservationDataMapper.getInstance().insert(reservation);
        Reservation savedReservation = ReservationDataMapper.getInstance().find(reservation);
        for (Passenger passenger : passengerTickets.keySet()) {
            Passenger savedPassenger;
            try {
                savedPassenger = PassengerDataMapper.getInstance().find(passenger);
            } catch (NoRecordFoundException e) {
                PassengerDataMapper.getInstance().insert(passenger);
                savedPassenger = PassengerDataMapper.getInstance().find(passenger);
            }
            for (Ticket ticket : passengerTickets.get(passenger)) {
                ticket.setPassenger(savedPassenger);
                ticket.setReservation(savedReservation);
                TicketDataMapper.getInstance().insert(ticket);
            }
        }
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void rollback() {
        this.reservation = null;
        this.passengerTickets.clear();
    }

}

