package main.java.com.example.four_aces.unitofworks;

import main.java.com.example.four_aces.datasource.FlightMapper;
import main.java.com.example.four_aces.domain.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightUnitOfWork {
    private static ThreadLocal current = new ThreadLocal();

    private List<Flight> newObjects = new ArrayList<>();
    private List<Flight> dirtyObjects = new ArrayList<>();
    private List<Flight> deleteObjects = new ArrayList<>();

    public static void newCurrent() {
        setCurrent(new FlightUnitOfWork());
    }

    public static void setCurrent(FlightUnitOfWork uow) {
        current.set(uow);
    }

    public static FlightUnitOfWork getCurrent() {
        return (FlightUnitOfWork) current.get();
    }

    public void registerNew(Flight obj) {
        if (!Objects.isNull(obj.getFlightId()) && !dirtyObjects.contains(obj) &&
            !deleteObjects.contains(obj) && !newObjects.contains(obj)) {
            newObjects.add(obj);
        }
    }

    public void registerDirty(Flight obj) {
        if (!Objects.isNull(obj.getFlightId()) && !deleteObjects.contains(obj) &&
            !dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
            dirtyObjects.add(obj);
        }
    }

    public void registerDeleted(Flight obj) {
        if (!Objects.isNull(obj.getFlightId())) {
            if (newObjects.contains(obj)) {
                newObjects.remove(obj);
            }
            if (!deleteObjects.contains(obj)) {
                deleteObjects.add(obj);
            }
        }
    }

    public void registerClean(Flight obj) {
        if(!Objects.isNull(obj.getFlightId())) {

        }
    }

    public void commit() {
        for (Flight obj : newObjects) {
            FlightMapper flightMapper = new FlightMapper();
        }
        for (Flight obj : dirtyObjects) {

        }
        for (Flight obj : deleteObjects) {

        }
    }
}
