package com.hons.honspace.actionqueue;

import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.objects.movable.MovableObjectInSpace;
import com.hons.honspace.solarsystem.objects.unmovable.Gate;
import com.hons.honspace.solarsystem.places.Place;

public class JumpObjectAction extends Action{

    MovableObjectInSpace ois;
    Grid grid;
    Gate fromGate;

    public JumpObjectAction(MovableObjectInSpace ois, Grid grid, Gate fromGate) {
        this.ois = ois;
        this.grid = grid;
        this.fromGate = fromGate;

    }
    
    public void preRun() {
        
    }

    public void run() {
        //Place from = grid.getPlaceWithId(ois.getPlaceId());
        Place to;
        if(fromGate.getLinksToGateId() == -1){
            to = grid.pickUnpopulatedPlace();
            grid.enterNewPlaceThroughGate(to.getX(),to.getY(),ois.getViewDistance(),fromGate.getId());
            to = grid.getPlace(to.getX(), to.getY());
        } else {
            Gate toGate = (Gate) grid.getObjectInSpaceWithId(fromGate.getLinksToGateId());
            to = grid.getPlaceWithId(toGate.getPlaceId());
        }
        
        ois.setPlaceId(to.getId());
        ois.setMoved(true);
        
    }

    public void postRun() {
        ois.setMoved(false);
    }

}
