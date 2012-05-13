package com.hons.honspace.util;

public class IdGenerator {
    private static IdGenerator singleton = null;
    private int id ;
    
    private IdGenerator(int id) {
        this.id =id;
    }
    
    public synchronized static IdGenerator getInstance(){
        //0-9999 reserved for testing/temporary grids
        return getInstance(10000);
    }
    
    public synchronized static IdGenerator getInstance(int startId){
        if(singleton == null){
            singleton = new IdGenerator(startId);
        }
        return singleton;
    }
    
    public synchronized int getNextId() {
        return id++;
    }
    
}
