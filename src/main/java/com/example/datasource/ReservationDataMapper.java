package com.example.datasource;

import com.example.domain.Passenger;
import com.example.domain.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ReservationDataMapper extends AbstractDataMapper<Reservation> {
    private static ReservationDataMapper _instance;

    private ReservationDataMapper() {
        this.table = "reservation";
        this.pkey = "reservation_id";
        this.fields = new String[]{
                "customer_id",
                "go_flight",
                "return_flight",
                "submitted"
        };
    }

    public static ReservationDataMapper getInstance(){
        if( _instance == null ){
            return _instance = new ReservationDataMapper();
        }
        return _instance;
    }

    @Override
    public String getSets(){
        ArrayList<String> sets = new ArrayList<>();
        for( String field : fields ) {
            sets.add(field + " = ?");
        }
        return String.join(",", sets);
    }

    @Override
    public Reservation newDomainObject(ResultSet resultSet) throws Exception {
        int reservationId = resultSet.getInt("reservation_id");
        int customerId = resultSet.getInt("customer_id");
        int goFlightId = resultSet.getInt("go_flight");
        int returnFlightId = resultSet.getInt("return_flight");
        boolean submitted  = resultSet.getBoolean("submitted");
        return new Reservation(reservationId, customerId, goFlightId, returnFlightId, submitted);
    }

    @Override
    public void setPreparedStatement(PreparedStatement ps, Reservation obj) throws Exception {
        ps.setInt(1,obj.getCustomerId());
        if (obj.getGoFlightId() != null) {
            ps.setInt(2, obj.getGoFlightId());
        } else {
            ps.setNull(2, Types.NULL);
        }
        if (obj.getReturnFlightId() != null) {
            ps.setInt(3,obj.getReturnFlightId());
        } else {
            ps.setNull(3, Types.NULL);
        }
        ps.setBoolean(4, obj.isSubmitted());
    }
}
