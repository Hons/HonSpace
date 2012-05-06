package com.hons.honspace.solarsystem;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.solarsystem.places.FogOfWar;
import com.hons.honspace.solarsystem.places.Place;

public class Grid {
    public static final Logger logger = Logger.getLogger(Grid.class);
    public static final int MAX_DISTANCE = 50;
    
    int id;
    private String name = "";
    private Place[][] places = new Place[MAX_DISTANCE*2+1][MAX_DISTANCE*2+1];
    //This is kept as basically an index so you can directly reference a cell.
    private HashMap<String,Place> allPlaces = new HashMap<String,Place>();
    
    public Grid(){
        
    }
    
    public Place getPlace(int x,int y) {
        return places[convertCoordToGrid(x)][convertCoordToGrid(y)];
    }
    
    public void setPlace(int x, int y, Place place){
        
        place.setX(x);
        place.setY(y);
        places[convertCoordToGrid(x)][convertCoordToGrid(y)] = place;
        allPlaces.put(coordsIndex(x,y), place);
    }
    
    public Place[][] getPlaces() {
        return places;
    }

    public Collection<Place> getAllPlaces() {
        return allPlaces.values();
    }
    
    public GridCommand getSubGrid(int centerX, int centerY, int width) {
        //logger.error(centerX + "::" + centerY + "::" + width);
        int maxVal = (2*width)+1;
        Place[][] window = new Place[maxVal][maxVal];;
        Place temp = null;
        Place fog = new FogOfWar();
        
        for(int i = 0; i < maxVal; i++){
            for(int j = 0; j < maxVal; j++){
                //logger.error("Setting: " + i + "\t" + j + "\t" + convertCoordToGrid(i-width) + "\t" + convertCoordToGrid(j-width));
                temp = places[convertCoordToGrid((centerX-width)+i)][convertCoordToGrid((centerY-width)+j)];
                if(temp == null) {
                    temp = fog;
                }
                window[maxVal-1-j][i] = temp;
            }
        }
        GridCommand command = new GridCommand();
        command.setId(id);
        command.setName(name);
        command.setPlaces(window);
        
        return command;
    }

    /*
     * Grids will be re-sizable (dynamically? who knows)
     * Either way arrays are +ve indexed so we'll need to translate every 
     * given coordinate to the center of the array to find the right cell. 
     * 
     */
    private int convertCoordToGrid(int coord){
       return coord + MAX_DISTANCE; 
    }
    
    private String coordsIndex(int x, int y){
        return x + "-" + y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
