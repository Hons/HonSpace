package com.hons.honspace.command;

import javax.xml.bind.annotation.XmlElement;

import com.hons.honspace.solarsystem.places.Place;

public class GridCommand {

    int id;
    String name;
    private Place[][] places;

    public Place[][] getPlaces() {
        return places;
    }
    @XmlElement
    public void setPlaces(Place[][] places) {
        this.places = places;
    }
    public int getId() {
        return id;
    }
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
