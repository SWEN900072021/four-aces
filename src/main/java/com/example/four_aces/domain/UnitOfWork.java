package com.example.four_aces.domain;
import com.example.four_aces.datasource.CustomerMapper;

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
        for (DomainObject obj : deleteObjects) {

        }
    }
}
