package com.example.datasource;

import com.example.controller.DBController;
import com.example.domain.Customer;
import com.example.domain.Flight;
import com.example.domain.Passenger;
import com.example.domain.Reservation;
import com.example.exception.NoRecordFoundException;
import com.example.exception.TRSException;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDataMapper extends AbstractDataMapper<Reservation> {
    private static ReservationDataMapper _instance;

    private ReservationDataMapper() {
        this.table = "reservation";
        this.pkey = "reservation_id";
        this.fields = new String[]{
                "customer_id",
                "go_flight",
                "return_flight"
        };
    }

    public static ReservationDataMapper getInstance() {
        if (_instance == null) {
            return _instance = new ReservationDataMapper();
        }
        return _instance;
    }

    @Override
    public String getSets() {
        ArrayList<String> sets = new ArrayList<>();
        for (String field : fields) {
            sets.add(field + " = ?");
        }
        return String.join(",", sets);
    }

    @Override
    public Reservation newDomainObject(ResultSet resultSet) throws SQLException, NoRecordFoundException {
        int reservationId = resultSet.getInt("reservation_id");
        int customerId = resultSet.getInt("customer_id");
        Customer customer = CustomerDataMapper.getInstance().findById(customerId);
        Flight goFlight;
        if (resultSet.getString("go_flight") != null) {
            int goFlightId = resultSet.getInt("return_flight");
            goFlight = FlightDataMapper.getInstance().findById(goFlightId);
        } else {
            goFlight = null;
        }
        Flight returnFlight;
        if (resultSet.getString("return_flight") != null) {
            int returnFlightId = resultSet.getInt("return_flight");
            returnFlight = FlightDataMapper.getInstance().findById(returnFlightId);
        } else {
            returnFlight = null;
        }

        return new Reservation(reservationId, customer, goFlight, returnFlight);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Reservation obj) throws SQLException {
        ps.setInt(1, obj.getCustomer().getId());
        if (obj.getGoFlight().getId() != null) {
            ps.setInt(2, obj.getGoFlight().getId());
        } else {
            ps.setNull(2, Types.NULL);
        }
        if (obj.getReturnFlight().getId() != null) {
            ps.setInt(3, obj.getReturnFlight().getId());
        } else {
            ps.setNull(3, Types.NULL);
        }
    }

    public ArrayList<Integer> getPassengersIdByReservations(ArrayList<Reservation> reservations) throws Exception {
        ArrayList<Integer> passengersId = new ArrayList<>();
        Connection conn = new DBController().connect();
        for (Reservation reservation : reservations) {
            String sql = String.format(SQLSelect, "*", "passengerreservation", "WHERE " + pkey + " = " + reservation.getId());
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            while (resultSet.next()) {
                int passenger_id = resultSet.getInt("passenger_id");
                passengersId.add(passenger_id);
            }
            resultSet.close();
            ps.close();
        }
        conn.close();
        return passengersId;
    }

    public Reservation find(Reservation reservation) throws SQLException, NoRecordFoundException {
        Reservation result = find("WHERE customer_id='" + reservation.getCustomer().getId() +
                "' AND go_flight= '" + reservation.getGoFlight().getId() +
                "' AND return_flight= '" + reservation.getReturnFlight().getId() +
                "'").get(0);
        return result;
    }
}
