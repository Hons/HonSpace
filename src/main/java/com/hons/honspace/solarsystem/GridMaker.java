package com.hons.honspace.solarsystem;

import java.util.Random;

import com.hons.honspace.solarsystem.objects.movable.Spaceship;
import com.hons.honspace.solarsystem.objects.unmovable.Asteroid;
import com.hons.honspace.solarsystem.objects.unmovable.Gate;
import com.hons.honspace.solarsystem.objects.unmovable.SpaceStation;
import com.hons.honspace.solarsystem.places.EmptySpace;
import com.hons.honspace.solarsystem.places.FogOfWar;
import com.hons.honspace.solarsystem.places.HomePlanet;
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
                
                grid.setPlace(i, j, new FogOfWar());
                
                /*
                random = rand.nextDouble();
                if(random > 0.95){
                    Planet p = new Planet();
                    grid.setPlace(i, j, p);
                    
                    random = rand.nextDouble();
                    if(random > 0.5){
                        grid.addObjectInSpace(i,j,new SpaceStation());
                    }
                } else {
                    Place p = new EmptySpace();
                    grid.setPlace(i, j, p);
                    random = rand.nextDouble();
                    if(random > 0.95){
                        grid.addObjectInSpace(i,j,new Asteroid());
                    }
                    random = rand.nextDouble();
                    if(random > 0.975){
                        grid.addObjectInSpace(i,j,new SpaceStation());
                    }
                }*/
            }
        }
        

        for(int i = -7; i <= 7; i++){
            for(int j = -7; j <= 7; j++){
                grid.setPlace(i, j, new EmptySpace());
                random = rand.nextDouble();
                if(random > 0.9){
                    grid.addObjectInSpace(i, j, new Gate());
                }
            }
        }

        random = rand.nextDouble();
        if(random >= 0.975){
            grid.setPlace(1, 1, new Sun());
            grid.setPlace(-1, 1, new Sun());
            grid.setPlace(0, -1, new Sun());
        } else if(random >= 0.95 && random < 0.975){
            grid.setPlace(1, -1, new Sun());
            grid.setPlace(-1, -1, new Sun());
            grid.setPlace(0, 1, new Sun());
        } else if(random >= 0.85 && random < 0.95){
            grid.setPlace(1, 0, new Sun());
            grid.setPlace(-1, 0, new Sun());
        } else if(random >= 0.75 && random < 0.85){
            grid.setPlace(0, 1, new Sun());
            grid.setPlace(0, -1, new Sun());
        } else {
            grid.setPlace(0, 0, new Sun());
        }

        for(int i = 0; i < rand.nextInt(5) + 20; i++){
            int x = rand.nextInt(5) + 2;
            int y = rand.nextInt(5) + 2;
            if(rand.nextDouble() > 0.5) x *= -1;
            if(rand.nextDouble() > 0.5) y *= -1;
            if(rand.nextDouble() > 0.6){
                grid.setPlace(x, y, new Planet());
            } else {
                grid.addObjectInSpace(x, y, new Asteroid());
            }
        }

        int val = rand.nextInt(3) ;
        int x = 3;
        int y = 3;
        if(rand.nextDouble() > 0.5) val *= -1;
        
        if(rand.nextDouble() > 0.5){
            x=val;
        } else {
            y=val;
        }
            
        grid.setPlace(x, y, new HomePlanet());
        grid.addObjectInSpace(x, y, new Spaceship());
        
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
