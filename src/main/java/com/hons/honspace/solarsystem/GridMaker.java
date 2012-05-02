package com.hons.honspace.solarsystem;

import com.hons.honspace.solarsystem.places.EmptySpace;
import com.hons.honspace.solarsystem.places.Planet;
import com.hons.honspace.solarsystem.places.Sun;

public class GridMaker {
    private static GridMaker singleton = null;
    
    public static synchronized GridMaker getIntance(){
        if(singleton == null){
            singleton = new GridMaker();
        }
        return singleton;
    }
    
    public Grid makeGrid() {
        Grid grid = new Grid();
        
        for(int i = -Grid.MAX_DISTANCE; i <= Grid.MAX_DISTANCE; i++){
            for(int j = -Grid.MAX_DISTANCE; j <= Grid.MAX_DISTANCE; j++){
                grid.setPlace(i, j, new EmptySpace());
            }
        }
        
        grid.setPlace(0, 0, new Sun());
        grid.setPlace(2, 1, new Planet());
        grid.setPlace(4, 0, new Planet());
        grid.setPlace(4, 1, new Planet());
        grid.setPlace(4, 2, new Planet());
        grid.setPlace(4, 3, new Planet());
        grid.setPlace(4, 4, new Planet());
        grid.setPlace(4, 5, new Planet());
        grid.setPlace(4, 6, new Planet());
        grid.setPlace(4, 7, new Planet());
        grid.setPlace(4, 8, new Planet());
        grid.setPlace(4, 9, new Planet());
        grid.setPlace(-1, -4, new Planet());
        grid.setPlace(-2, 4, new Planet());
        
        return grid;
    }
    
}
