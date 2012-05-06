package com.hons.honspace.solarsystem.objects;

import com.hons.honspace.solarsystem.places.Place;

public abstract class ObjectInSpace {
    private String name;
    private Place location;
    private String type = this.getClass().getSimpleName();
    
    public ObjectInSpace(Place loc) {
        location = loc;
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
    
}
