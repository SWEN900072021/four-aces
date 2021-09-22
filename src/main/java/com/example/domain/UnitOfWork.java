package com.example.domain;

import com.example.datasource.FlightMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(obj.getId()) && !dirtyObjects.contains(obj) && !deleteObjects.contains(obj) && !newObjects.contains(obj)) {
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
                FlightMapper.getInstance().insert((Flight) obj);
            }
        }
        for (DomainObject obj : dirtyObjects) {
            if (obj.getClass().equals(Flight.class)) {
                FlightMapper.getInstance().update((Flight) obj);
            }
        }
        for (DomainObject obj : deleteObjects) {

        }
        newObjects.clear();
        dirtyObjects.clear();
        deleteObjects.clear();
    }
}
