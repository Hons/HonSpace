package com.hons.honspace.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.command.PlaceCommand;
import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.places.Place;
import com.hons.honspace.storage.GridStorage;

@Controller
public class GridController {
    public static final Logger logger = Logger.getLogger(GridController.class);
    
    @Autowired
    GridStorage gs;

    @RequestMapping(value = "/grid/{id}", method = RequestMethod.GET)
    public String getAGrid(@PathVariable int id, ModelMap model) {
        Grid grid = gs.getGridAndMakeIfNeed(id);
        GridCommand command = grid.getSubGrid(0, 0, Grid.MAX_DISTANCE);
        model.put("grid", command);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{id}/window", method = RequestMethod.GET)
    public String getAGrid( @PathVariable int id,
                            ModelMap model, 
                            @RequestParam(value="centerX") int centerX, 
                            @RequestParam(value="centerY") int centerY, 
                            @RequestParam(value="width") int width ) {
        Grid grid = gs.getGridAndMakeIfNeed(id);
        GridCommand command = grid.getSubGrid(centerX, centerY, width);
        model.put("grid", command);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{id}/place/{placeId}", method = RequestMethod.GET)
    public String getAPlace( @PathVariable int id,
                             @PathVariable int placeId,
                             ModelMap model) {
        Grid grid = gs.getGridAndMakeIfNeed(id);
        PlaceCommand placeCom = new PlaceCommand();
        Place place = grid.getPlaceWithId(placeId);
        BeanUtils.copyProperties(place,placeCom);
        grid.populateObjectsInSpace(placeCom);
        model.put("place", placeCom);
        return "list";
    }
    
}
