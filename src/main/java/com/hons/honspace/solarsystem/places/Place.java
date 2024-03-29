package com.hons.honspace.solarsystem.places;

import com.hons.honspace.util.IdGenerator;

public abstract class Place {
    private int id;
    private int gridId;
    private int x;
    private int y;
    private String type = this.getClass().getSimpleName();
    
    public Place() {
        id = IdGenerator.getInstance().getNextId();
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
    
}
