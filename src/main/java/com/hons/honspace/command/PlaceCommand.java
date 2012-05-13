package com.hons.honspace.command;

import java.util.ArrayList;
import java.util.List;

import com.hons.honspace.solarsystem.objects.ObjectInSpace;

public class PlaceCommand {
    private int id;
    private int gridId;
    private int x;
    private int y;
    private String type;
    private List<ObjectInSpace> objects = new ArrayList<>();
    
    public PlaceCommand() {
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public List<ObjectInSpace> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectInSpace> objects) {
        this.objects = objects;
    }
    
    public void addObject(ObjectInSpace ois){
        objects.add(ois);
    }
}
