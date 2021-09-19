package main.java.com.example.four_aces.domain;

import main.java.com.example.four_aces.datasource.FlightMapper;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private static UnitOfWork _instance = null;
    private List<DomainObject> newObjects = new ArrayList<>();
    private List<DomainObject> dirtyObjects = new ArrayList<>();
    private List<DomainObject> deleteObjects = new ArrayList<>();

    public static UnitOfWork getInstance() {
        if (_instance == null) {
            _instance = new UnitOfWork();
        }
        return _instance;
    }

    public void registerNew(DomainObject obj) {
        if (!dirtyObjects.contains(obj) && !deleteObjects.contains(obj) && !newObjects.contains(obj)) {
            newObjects.add(obj);
        }
    }

    public void registerDirty(DomainObject obj) {
        if (!deleteObjects.contains(obj) && !dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
            dirtyObjects.add(obj);
        }
    }

    public void registerDeleted(DomainObject obj) {
        if (newObjects.remove(obj)) {
            return;
        }
        dirtyObjects.remove(obj);
        if (!deleteObjects.contains(obj)) {
            deleteObjects.add(obj);
        }
    }

    public void commit() {
        for (DomainObject obj : newObjects) {
            if (obj.getClass().equals(Flight.class)) {
                FlightMapper flightMapper = new FlightMapper();
                flightMapper.insert((Flight) obj);
            }
        }
        for (DomainObject obj : dirtyObjects) {

        }
        for (DomainObject obj : deleteObjects) {

        }
    }
}
