package com.hons.honspace.actionqueue;

public abstract class Action implements Runnable{

    public abstract void preRun();
    
    public abstract void run();
    
    public abstract void postRun();
    
}
