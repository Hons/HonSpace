package com.hons.honspace.solarsystem;

import java.util.ArrayList;
import java.util.List;

import com.hons.honspace.solarsystem.places.Place;

public class Grid {
    public static final int MAX_DISTANCE = 50;
    
    private String name = "";
    private Place[][] places = new Place[MAX_DISTANCE*2+1][MAX_DISTANCE*2+1];
    private List<Place> allPlaces = new ArrayList<Place>();
    
    public Grid(){
        
    }
    
    public Place getPlace(int x,int y) {
        return places[convertCoordToGrid(x)][convertCoordToGrid(y)];
    }
    
    protected void setPlace(int x, int y, Place place){
        place.setX(x);
        place.setY(y);
        places[convertCoordToGrid(x)][convertCoordToGrid(y)] = place;
        allPlaces.add(place);
    }
    
    public Place[][] getPlaces() {
        return places;
    }

    public List<Place> getAllPlaces() {
        return allPlaces;
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

}
