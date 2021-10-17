package com.example.concurrency;

import com.example.exception.ConcurrencyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockManager {

    private HashMap<String, String> lockList;
    private HashMap<String, Long> lockTime;
    private HashMap<String, ArrayList<LockObserver>> observers;

    public static final long TIME_OUT_LIMIT = 30*60*1000; // 30 minutes

    private static LockManager _instance;

    private LockManager() {
        lockList = new HashMap<>();
        lockTime = new HashMap<>();
        observers = new HashMap<>();
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
        if ( !lockList.containsKey(lockable) ) return;
        if (!lockList.get(lockable).equals(user)) {
            throw new ConcurrencyException(String.format("Unable to release lock %s because the owner of this lock is someone else", lockable));
        }
        System.out.println(lockList);
        lockList.remove(lockable);
        lockTime.remove(lockable);
    }

    public boolean isAvailable(String lockable) {
        return (!this.lockList.containsKey(lockable));
    }

    public void acquireLock(String lockable, LockObserver observer) throws ConcurrencyException {
        acquireLock(lockable, observer.getOwner());
        addObserver(lockable, observer);
    }

    public void releaseLock(String lockable, LockObserver observer) throws ConcurrencyException {
        releaseLock(lockable, observer.getOwner());
        removeObserver(lockable, observer);
    }

    public void hardAcquireLock(String lockable, String owner) throws ConcurrencyException {
        if( lockList.containsKey(lockable)){
            if (lockList.get(lockable).equals(owner)){
                throw new ConcurrencyException(String.format("The flight %s is being editing or deleting by the same account", lockable));
            }
        }
        lockList.put(lockable, owner);
        notifyAllObserver(lockable);
    }

    public void addObserver(String lockable, LockObserver observer){
        if( !observers.containsKey(lockable) ){
            observers.put(lockable, new ArrayList<>());
        }
        System.out.println(observer.getOwner());
        observers.get(lockable).add(observer);
    }

    public void removeObserver(String lockable, LockObserver observer){
        if ( !observers.containsKey(lockable) ) return;
        observers.get(lockable).removeIf(o -> o.getOwner().equals(observer.getOwner()));
    }

    public void notifyAllObserver(String lockable){
        if ( !observers.containsKey(lockable) ) return;
        for (LockObserver o: observers.get(lockable)){
            System.out.println(o.getOwner());
            o.update();
        }
    }

    public abstract static class LockObserver{

        HttpSession session;
        HttpServletRequest req;
        HttpServletResponse resp;

        public LockObserver(HttpSession session){
            this.session = session;
        }

        public LockObserver(HttpServletRequest request, HttpServletResponse response){
            this.req = request;
            this.resp = response;
            this.session = req.getSession();
        }

        public abstract void update();

        public String getOwner(){
            return session.getId();
        }

        public HttpServletRequest getRequest() {
            return req;
        }

        public HttpServletResponse getResp() {
            return resp;
        }

        public HttpSession getSession() {
            return session;
        }
    }
}
