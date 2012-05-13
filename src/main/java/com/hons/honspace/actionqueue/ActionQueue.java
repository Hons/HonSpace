package com.hons.honspace.actionqueue;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;


public class ActionQueue {
    public static final Logger logger = Logger.getLogger(ActionQueue.class);
    
    public static ActionQueue singleton;
    private LinkedBlockingQueue<Action> actionQueue;
    
    private ActionQueue(){
        actionQueue = new LinkedBlockingQueue<Action>();
    }
    
    public static synchronized ActionQueue getInstance(){
        if(singleton == null){
            singleton = new ActionQueue();
        }
        return singleton;
    }
    
    public void queueItem(Action action){
        actionQueue.add(action);
    }
    
    public void tick(){
        logger.warn("Ticking: " + actionQueue.size() + " actions");
        LinkedBlockingQueue<Action> oldQueue = actionQueue;
        actionQueue = new LinkedBlockingQueue<Action>();
        while(!oldQueue.isEmpty()){
            Action action = oldQueue.poll();
            action.preRun();
            action.run();
            action.postRun();
        }
    }

}
