package com.hons.honspace.actionqueue;

import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.objects.movable.MovableObjectInSpace;

public class MoveObjectAction extends Action{
    
    MovableObjectInSpace ois;
    Grid grid;
    int x;
    int y;
    
    public MoveObjectAction(MovableObjectInSpace ois, Grid grid, int x, int y){
        this.ois = ois;
        this.grid = grid;
        this.x = x;
        this.y = y;
    }
    
    public void preRun() {
        
    }

    public void run() {
        int locX = grid.getPlaceWithId(ois.getPlaceId()).getX();
        int locY = grid.getPlaceWithId(ois.getPlaceId()).getY();
        for(int i = 0; i < ois.getMoveDistance(); i ++){
            if(locX == x && locY == y){
                break;
            }
            if(Math.abs(locY - y) > Math.abs(locX - x)){
                if(locY > y){
                    locY--;
                } else {
                    locY++;
                }
            } else {
                if(locX > x){
                    locX--;
                } else {
                    locX++;
                }
            }
            
            grid.enterPlace(locX,locY,ois.getViewDistance());
        }
        ois.setPlaceId(grid.getPlace(x, y).getId());
        ois.setMoved(true);
    }

    public void postRun() {
        ois.setMoved(false);
    }

}
