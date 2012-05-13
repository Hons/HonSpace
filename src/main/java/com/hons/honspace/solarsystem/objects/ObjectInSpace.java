package com.hons.honspace.solarsystem.objects;

import com.hons.honspace.util.IdGenerator;

public abstract class ObjectInSpace {
    protected int id;
    protected int gridId;
    protected int placeId;
    protected String name;
    protected String type = this.getClass().getSimpleName();
    
    public ObjectInSpace() {
        id = IdGenerator.getInstance().getNextId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    
}
