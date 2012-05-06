package com.hons.honspace.solarsystem.places;

import java.util.ArrayList;
import java.util.List;

import com.hons.honspace.solarsystem.objects.ObjectInSpace;

public abstract class Place {
    private int x;
    private int y;
    private String type = this.getClass().getSimpleName();
    private List<ObjectInSpace> objects = new ArrayList<ObjectInSpace>();
    
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
    public List<ObjectInSpace> getObjects() {
        return objects;
    }
    public void setObjects(List<ObjectInSpace> objects) {
        this.objects = objects;
    }
    public void addObject(ObjectInSpace object) {
        this.objects.add(object);
    }
    
}
