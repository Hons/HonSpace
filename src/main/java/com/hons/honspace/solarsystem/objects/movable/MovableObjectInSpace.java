package com.hons.honspace.solarsystem.objects.movable;

import com.hons.honspace.solarsystem.objects.ObjectInSpace;

public abstract class MovableObjectInSpace extends ObjectInSpace{
    
    protected int moveDistance;
    protected int viewDistance;
    protected boolean moved = false;

    public MovableObjectInSpace() {
        super();
    }

    public int getMoveDistance() {
        return moveDistance;
    }

    public void setMoveDistance(int moveDistance) {
        this.moveDistance = moveDistance;
    }
    
    public int getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public boolean getMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
    
}
