package com.hons.honspace.solarsystem.places;

public abstract class Place {
    int x;
    int y;
    String type = this.getClass().getSimpleName();
    
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
}
