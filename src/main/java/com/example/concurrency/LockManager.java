package com.example.concurrency;

import com.example.exception.ConcurrencyException;

import java.util.HashMap;

public class LockManager {

    private HashMap<Integer, String> lockList;

    private static LockManager _instance;

    private LockManager(){}

    public static synchronized LockManager getInstance(){
        if( _instance == null ){
            _instance = new LockManager();
        }
        return _instance;
    }

    public HashMap<Integer, String> getLockList(){
        if (lockList == null){
            lockList= new HashMap<>();
        }
        return lockList;
    }

    /**
     * Acquire the lock by adding the lock into the lock list
     * @param lockable, the id of the lockable item
     * @param owner, the user requesting the lock for the lockable item
     * @throws ConcurrencyException, when the id of the lockable item is in the list
     */
    public void acquireLock(int lockable, String owner) throws ConcurrencyException {
        if( this.lockList.containsKey(lockable) ) throw new ConcurrencyException("Unable to lock "+ lockable);
        lockList.put(lockable, owner);
    }

    /**
     * Release the lock by removing the lock from the lock list
     * @param lockable, the id of the lockable item
     * @param user, the user releasing the lock
     * @throws ConcurrencyException, when the user is asking to release the lock is not the owner of the lock
     */
    public void releaseLock(int lockable, String user) throws ConcurrencyException{
        if (!lockList.get(lockable).equals(user))
            throw new ConcurrencyException(String.format("Unable to release lock %d because the owner of this lock is someone else", lockable));
        lockList.remove(lockable);
    }
}
