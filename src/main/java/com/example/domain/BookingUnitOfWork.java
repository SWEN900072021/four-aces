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

    private Passenger currentPassenger = null;

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

    public void registerTicket(Ticket ticket) {
        passengerTickets.get(currentPassenger).add(ticket);
    }

    public void commit() throws Exception {
        ReservationDataMapper.getInstance().insert(reservation);
        Reservation savedReservation = ReservationDataMapper.getInstance().find(reservation);
        for (Passenger passenger : passengerTickets.keySet()) {
            Passenger savedPassenger;
            try {
                if (passenger == null) {
                    System.out.println("PASSENGER IS NULL");
                }
                savedPassenger = PassengerDataMapper.getInstance().find(passenger);
            } catch (NoRecordFoundException e) {
                PassengerDataMapper.getInstance().insert(passenger);
                savedPassenger = PassengerDataMapper.getInstance().find(passenger);
            }
            for (Ticket ticket : passengerTickets.get(passenger)) {
                ticket.setPassenger(savedPassenger);
                ticket.setReservation(savedReservation);
                TicketDataMapper.getInstance().update(ticket);
            }
        }
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void rollback() {
        this.reservation = null;
        this.passengerTickets.clear();
        this.currentPassenger = null;
    }

    public Passenger getCurrentPassenger() {
        return currentPassenger;
    }

    public void setCurrentPassenger(Passenger currentPassenger) {
        this.currentPassenger = currentPassenger;
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> result = new ArrayList<>();
        for (ArrayList<Ticket> tickets : passengerTickets.values()) {
            for (Ticket ticket : tickets) {
                result.add(ticket);
            }
        }
        return result;
    }

    public ArrayList<Passenger> getPassengers(){
        return new ArrayList<>(passengerTickets.keySet());
    }
}

