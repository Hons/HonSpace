package com.hons.honspace.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.GridMaker;
import com.hons.honspace.solarsystem.places.Place;
import com.hons.honspace.solarsystem.places.Planet;
import com.hons.honspace.solarsystem.places.Sun;
import com.hons.honspace.storage.GridStorage;

@Controller
public class GridController {
    public static final Logger logger = Logger.getLogger(GridController.class);
    
    @Autowired
    GridStorage gs;

    @RequestMapping(value = "/grid/{id}", method = RequestMethod.GET)
    public String getAGrid(@PathVariable int id, ModelMap model) {
        logger.error(gs.toString());
        Grid grid = getGridAndMakeIfNeed(id);
        GridCommand command = new GridCommand();
        command.setPlaces(grid.getPlaces());
        model.put("grid", command);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{id}/window", method = RequestMethod.GET)
    public String getAGrid( @PathVariable int id,
                            ModelMap model, 
                            @RequestParam(value="centerX") int centerX, 
                            @RequestParam(value="centerY") int centerY, 
                            @RequestParam(value="width") int width ) {
        Grid grid = getGridAndMakeIfNeed(id);
        GridCommand command = grid.getSubGrid(centerX, centerY, width);
        model.put("grid", command);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{id}/explode", method = RequestMethod.GET)
    public String explodeAPlanet( @PathVariable int id,
                            ModelMap model ) {
        Grid grid = getGridAndMakeIfNeed(id);
        for(Place p : grid.getAllPlaces()){
            if (p instanceof Planet){
                int x = p.getX();
                int y = p.getY();
                grid.setPlace(x, y, new Sun());
                break;
            }
        }
        model.put("message", "success");
        return "list";
    }
    
    
    private Grid getGridAndMakeIfNeed(int id){
        Grid grid = gs.getGrid(id);
        if (grid == null){
            grid = GridMaker.getIntance().makeGrid();
            grid.setId(id);
            gs.storeGrid(grid);
        }
        return grid;
    }
    
}
