package com.hons.honspace.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.GridMaker;

@Controller
public class GridController {
    public static final Logger logger = Logger.getLogger(GridController.class);

    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public String getAGrid(ModelMap model) {
        Grid grid = GridMaker.getIntance().makeGrid();
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
        logger.error("Hello");
        Grid grid = GridMaker.getIntance().makeGrid();
        GridCommand command = new GridCommand();
        command.setId(id);
        command.setPlaces(grid.getSubGrid(centerX, centerY, width));
        model.put("grid", command);
        return "list";
    }
}
