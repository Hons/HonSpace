package com.hons.honspace.command;

import com.hons.honspace.solarsystem.places.Place;

public class GridCommand {

    int id;
    String name;
    private PlaceCommand[][] places;

    public PlaceCommand[][] getPlaces() {
        return places;
    }
    public void setPlaces(PlaceCommand[][] places) {
        this.places = places;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
