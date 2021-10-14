package com.example.domain;

import com.example.datasource.DataMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnitOfWork {
    private static ThreadLocal current = new ThreadLocal();

    private List<DomainObject> newObjects = new ArrayList<>();
    private List<DomainObject> dirtyObjects = new ArrayList<>();
    private List<DomainObject> deleteObjects = new ArrayList<>();

    public static void newCurrent() {
        System.out.println("SETTING NEW CURRENT");
        setCurrent(new UnitOfWork());
    }

    public static void setCurrent(UnitOfWork uow) {
        current.set(uow);
    }

    public static UnitOfWork getCurrent() {
        if (Objects.isNull(current.get())) {
            System.out.println("NEW UNITOFWORK CURRENT IS CREATED AGAIN");
            UnitOfWork uow = new UnitOfWork();
            setCurrent(uow);
            return uow;
        }
        return (UnitOfWork) current.get();
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
            Class<?> objClass = obj.getClass();
            DataMapper.getDataMapper(objClass.getSimpleName()).insert(obj.cast(objClass));
        }
        for (DomainObject obj : dirtyObjects) {
            Class<?> objClass = obj.getClass();
            DataMapper.getDataMapper((objClass).getSimpleName()).update(obj.cast(objClass));
        }
        for (DomainObject obj : deleteObjects) {
            java.lang.Class<?> objClass = obj.getClass();
            DataMapper.getDataMapper((objClass).getSimpleName()).delete(obj.cast(objClass));
        }
        newObjects.clear();
        dirtyObjects.clear();
        deleteObjects.clear();
    }

    public void abortAll() {
        newObjects.clear();
        dirtyObjects.clear();
        deleteObjects.clear();
    }

    public DomainObject getNewObjectOf(String className) throws ClassNotFoundException {
        Class<?> objClass = Class.forName("com.example.domain."+className);
        for (DomainObject obj : newObjects) {
            if (objClass.isInstance(obj)) {
                System.out.println("FOUND IT");
                return obj;
            }
        }

        return null;
    }
}
