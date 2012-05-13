package com.hons.honspace.solarsystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.command.PlaceCommand;
import com.hons.honspace.solarsystem.objects.ObjectInSpace;
import com.hons.honspace.solarsystem.objects.unmovable.Asteroid;
import com.hons.honspace.solarsystem.objects.unmovable.Gate;
import com.hons.honspace.solarsystem.objects.unmovable.SpaceStation;
import com.hons.honspace.solarsystem.places.EmptySpace;
import com.hons.honspace.solarsystem.places.FogOfWar;
import com.hons.honspace.solarsystem.places.Place;
import com.hons.honspace.solarsystem.places.Planet;
import com.hons.honspace.solarsystem.places.Sun;
import com.hons.honspace.util.IdGenerator;

public class Grid {
    public static final Logger logger = Logger.getLogger(Grid.class);
    public static final int MAX_DISTANCE = 50;
    
    int id;
    private String name = "";
    private Place[][] places = new Place[MAX_DISTANCE*2+1][MAX_DISTANCE*2+1];
    //This is an index of all places in this grid.
    private HashMap<Integer,Place> allPlaces = new HashMap<Integer,Place>();
    //And an index of all OIS in this grid
    private HashMap<Integer,ObjectInSpace> allObjectsInSpace = new HashMap<Integer,ObjectInSpace>();
    
    public Grid(){
        id = IdGenerator.getInstance().getNextId();
    }
    
    public Place pickUnpopulatedPlace(){
        List<Place> emptyPlaces = new ArrayList<Place>();
        for(Place p : allPlaces.values()){
            if (p instanceof FogOfWar){
                emptyPlaces.add(p);
            }
        }
        Collections.shuffle(emptyPlaces);
        return emptyPlaces.get(0);        
    }
    
    public void enterNewPlaceThroughGate(int x, int y, int viewDistance, int fromId){
        enterPlace(x, y, viewDistance);
        Gate toGate = new Gate();
        Gate fromGate = (Gate) getObjectInSpaceWithId(fromId);
        addObjectInSpace(x, y, toGate);

        toGate.setLinksToGateId(fromGate.getId());
        fromGate.setLinksToGateId(toGate.getId());
    }
            
    
    public void enterPlace(int x, int y, int viewDistance){
        double random;
        Random rand = new Random();
        
        for(int i = x-viewDistance ; i <= x+viewDistance; i++){
            for(int j = y-viewDistance ; j <= y+viewDistance; j++){
                if(convertCoordToGrid(i)<0 || convertCoordToGrid(j) <0) continue;
                
                Place place = getPlace(i, j);
                if(place instanceof FogOfWar){
                    random = rand.nextDouble();
                    if(random > 0.975){
                        setPlace(i, j, new Planet());
                        if(rand.nextDouble() > 0.9){
                            addObjectInSpace(i, j, new SpaceStation());
                            
                        }
                    } else if (random > 0.925){
                        setPlace(i, j, new EmptySpace());
                        addObjectInSpace(i, j, new Asteroid());
                    } else {
                        setPlace(i, j, new EmptySpace());
                    }
                    random = rand.nextDouble();
                    if(random > 0.999){
                        addObjectInSpace(i, j, new Gate());
                        
                    }
                    
                }
            }
        }
    }
    
    public void addObjectInSpace(int x, int y, ObjectInSpace ois){
        allObjectsInSpace.put(ois.getId(),ois);
        ois.setGridId(id);
        ois.setPlaceId(getPlace(x, y).getId());
    }
    
    public Place getPlace(int x,int y) {
        return places[convertCoordToGrid(x)][convertCoordToGrid(y)];
    }
    
    public void setPlace(int x, int y, Place place){
        
        place.setX(x);
        place.setY(y);
        places[convertCoordToGrid(x)][convertCoordToGrid(y)] = place;
        allPlaces.put(place.getId(), place);
        place.setGridId(id);
    }
    
    public Place[][] getPlaces() {
        return places;
    }

    public Collection<Place> getAllPlaces() {
        return allPlaces.values();
    }

    public Place getPlaceWithId(int id){
        return allPlaces.get(id);
    }
    
    public ObjectInSpace getObjectInSpaceWithId(int id){
        return allObjectsInSpace.get(id);
    }
    
    public Collection<ObjectInSpace> getAllObjectInSpace(){
        return allObjectsInSpace.values();
    }
    
    public List<ObjectInSpace> getObjectInSpaceAt(int placeId){
        List<ObjectInSpace> items = new ArrayList<>();
        for(ObjectInSpace obj : allObjectsInSpace.values()){
            if(obj.getPlaceId() == placeId){
                items.add(obj);
            }
        }
        return items;
    }
    
    public GridCommand getSubGrid(int centerX, int centerY, int width) {
        //logger.error(centerX + "::" + centerY + "::" + width);
        int maxVal = (2*width)+1;
        PlaceCommand[][] window = new PlaceCommand[maxVal][maxVal];;
        Place tempPlace = null;
        PlaceCommand temp = null;
        Place fog = new FogOfWar();
        
        for(int i = 0; i < maxVal; i++){
            for(int j = 0; j < maxVal; j++){
                //logger.error("Setting: " + i + "\t" + j + "\t" + convertCoordToGrid(i-width) + "\t" + convertCoordToGrid(j-width));
                tempPlace = places[convertCoordToGrid((centerX-width)+i)][convertCoordToGrid((centerY-width)+j)];
                if(tempPlace == null) {
                    tempPlace = fog;
                }
                temp = new PlaceCommand();
                BeanUtils.copyProperties(tempPlace,temp);
                populateObjectsInSpace(temp);
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
    
    public void populateObjectsInSpace(PlaceCommand place){
        for(ObjectInSpace ois : allObjectsInSpace.values()){
            if(ois.getPlaceId() == place.getId()) {
                place.addObject(ois);
            }
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
