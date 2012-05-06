package com.hons.honspace.solarsystem;

import java.util.Random;

import com.hons.honspace.solarsystem.objects.ObjectInSpace;
import com.hons.honspace.solarsystem.objects.unmovable.Asteroid;
import com.hons.honspace.solarsystem.objects.unmovable.SpaceStation;
import com.hons.honspace.solarsystem.places.EmptySpace;
import com.hons.honspace.solarsystem.places.Place;
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
    
    public Grid makeGrid(){
        double random;
        Random rand = new Random();
        Grid grid = new Grid();
        
        for(int i = -Grid.MAX_DISTANCE; i <= Grid.MAX_DISTANCE; i++){
            for(int j = -Grid.MAX_DISTANCE; j <= Grid.MAX_DISTANCE; j++){
                random = rand.nextDouble();
                if(random > 0.95){
                    Planet p = new Planet();
                    grid.setPlace(i, j, p);
                    
                    random = rand.nextDouble();
                    if(random > 0.5){
                        SpaceStation station = new SpaceStation(p);
                        p.addObject(station);
                    }
                } else {
                    Place p = new EmptySpace();
                    grid.setPlace(i, j, p);
                    random = rand.nextDouble();
                    if(random > 0.95){
                        Asteroid asteroid = new Asteroid(p);
                        p.addObject(asteroid);
                    }
                    random = rand.nextDouble();
                    if(random > 0.975){
                        SpaceStation station = new SpaceStation(p);
                        p.addObject(station);
                    }
                }
            }
        }

        grid.setPlace(0, 0, new Sun());
        return grid;
    }
    
    public Grid makeBoringGrid() {
        Grid grid = new Grid();
        
        for(int i = -Grid.MAX_DISTANCE; i <= Grid.MAX_DISTANCE; i++){
            for(int j = -Grid.MAX_DISTANCE; j <= Grid.MAX_DISTANCE; j++){
                grid.setPlace(i, j, new EmptySpace());
            }
        }
        
        grid.setPlace(0, 0, new Sun());
        grid.setPlace(1, 3, new Planet());
        
        
        grid.setPlace(2, 1, new Planet());
        /*grid.setPlace(4, 0, new Planet());
        grid.setPlace(4, 1, new Planet());
        grid.setPlace(4, 2, new Planet());
        grid.setPlace(4, 3, new Planet());
        grid.setPlace(4, 4, new Planet());
        grid.setPlace(4, 5, new Planet());
        grid.setPlace(4, -1, new Planet());
        grid.setPlace(4, -2, new Planet());
        grid.setPlace(4, -3, new Planet());
        grid.setPlace(4, -4, new Planet());
        grid.setPlace(4, -5, new Planet());*/
        grid.setPlace(-1, -5, new Planet());
        grid.setPlace(-2, 4, new Planet());
        
        
        return grid;
    }
    
}
