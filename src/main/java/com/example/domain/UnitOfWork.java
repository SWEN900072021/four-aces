package com.example.domain;

import com.example.datasource.DataMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnitOfWork {
    private static UnitOfWork _instance = null;
    private List<DomainObject> newObjects = new ArrayList<>();
    private List<DomainObject> dirtyObjects = new ArrayList<>();
    private List<DomainObject> deleteObjects = new ArrayList<>();

    private UnitOfWork(){
    }

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

    public void commit() throws Exception {
        for (DomainObject obj : newObjects) {
            java.lang.Class<?> objClass = obj.getClass();
            System.out.println(objClass);
            DataMapper.getDataMapper(objClass.getSimpleName()).insert(obj.cast(objClass));
        }
        for (DomainObject obj : dirtyObjects) {
            java.lang.Class<?> objClass = obj.getClass();
            DataMapper.getDataMapper((objClass).getSimpleName()).update(obj.cast(objClass));
        }
        for (DomainObject obj : deleteObjects) {

        }
        newObjects.clear();
        dirtyObjects.clear();
        deleteObjects.clear();
    }

}
