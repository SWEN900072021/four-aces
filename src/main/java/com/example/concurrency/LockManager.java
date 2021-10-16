package com.example.concurrency;

import com.example.exception.ConcurrencyException;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LockManager {

    private HashMap<String, String> lockList;
    private HashMap<String, Long> lockTime;

    public static final long TIME_OUT_LIMIT = 30*60*1000; // 30 minutes

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
        if (this.lockList.containsKey(lockable) ) {
            long lastAccessTime = this.lockTime.get(lockable);
            if( System.currentTimeMillis() - lastAccessTime < TIME_OUT_LIMIT )
                throw new ConcurrencyException("Unable to lock "+ lockable);
        }
        lockList.put(lockable, owner);
        lockTime.put(lockable, System.currentTimeMillis());
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
        lockTime.remove(lockable);
    }

    public boolean isAvailable(String lockable) {
        return (!this.lockList.containsKey(lockable));
    }
}
