package com.example.concurrency;

import com.example.exception.ConcurrencyException;

import java.util.HashMap;

public class LockManager {

    private HashMap<String, String> lockList;

    private static LockManager _instance;

    private LockManager() {
        lockList = new HashMap<>();
    }

    public static synchronized LockManager getInstance(){
        if( _instance == null ){
            _instance = new LockManager();
        }
        return _instance;
    }

    public HashMap<String, String> getLockList(){
        return this.lockList;
    }

    /**
     * Acquire the lock by adding the lock into the lock list
     * @param lockable, the name and id of the lockable item, e.g., flight-1
     * @param owner, http session id that can be obtained by request.getSession(true).getId()
     * @throws ConcurrencyException, when the id of the lockable item is in the list
     */
    public void acquireLock(String lockable, String owner) throws ConcurrencyException {
        if (this.lockList.containsKey(lockable)) {
            throw new ConcurrencyException("Unable to lock "+ lockable);
        }
        lockList.put(lockable, owner);
        System.out.println(lockList);
    }

    /**
     * Release the lock by removing the lock from the lock list
     * @param lockable, the name and id of the lockable item, e.g., flight-1
     * @param user, http session id that can be obtained by request.getSession(true).getId()
     * @throws ConcurrencyException, when the user is asking to release the lock is not the owner of the lock
     */
    public void releaseLock(String lockable, String user) throws ConcurrencyException{
        if (!lockList.get(lockable).equals(user)) {
            throw new ConcurrencyException(String.format("Unable to release lock %d because the owner of this lock is someone else", lockable));
        }
        System.out.println(lockList);
        lockList.remove(lockable);
    }

    public boolean isAvailable(String lockable) {
        return (!this.lockList.containsKey(lockable));
    }
}
