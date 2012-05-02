package com.hons.honspace.solarsystem;

import java.util.ArrayList;
import java.util.List;

import com.hons.honspace.solarsystem.places.FogOfWar;
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
    
    public Place[][] getSubGrid(int centerX, int centerY, int width) {
        int maxVal = (2*width)+1;
        Place[][] window = new Place[maxVal][maxVal];;
        Place temp = null;
        Place fog = new FogOfWar();
        for(int i = 0; i < maxVal; i++){
            for(int j = 0; j < maxVal; j++){
                temp = places[convertCoordToGrid(i-width)][convertCoordToGrid(j-width)];
                if(temp == null) {
                    temp = fog;
                }
                window[i][j] = temp;
            }
        }
        
        return window;
        
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
