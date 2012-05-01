package com.hons.honspace.command;

import javax.xml.bind.annotation.XmlElement;

import com.hons.honspace.solarsystem.places.Place;

public class GridCommand {

    private Place[][] places;

    public Place[][] getPlaces() {
        return places;
    }
    @XmlElement
    public void setPlaces(Place[][] places) {
        this.places = places;
    }
    
}
