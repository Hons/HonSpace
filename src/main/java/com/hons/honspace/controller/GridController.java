package com.hons.honspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hons.honspace.command.GridCommand;
import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.GridMaker;

@Controller
public class GridController {

    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public String getAGrid(ModelMap model) {
        Grid grid = GridMaker.getIntance().makeGrid();
        GridCommand command = new GridCommand();
        command.setPlaces(grid.getPlaces());
        model.put("grid", grid);
        return "list";
    }
    
}
