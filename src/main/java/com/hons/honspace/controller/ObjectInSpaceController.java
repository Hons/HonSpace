package com.hons.honspace.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hons.honspace.actionqueue.ActionQueue;
import com.hons.honspace.actionqueue.JumpObjectAction;
import com.hons.honspace.actionqueue.MoveObjectAction;
import com.hons.honspace.command.ObjectInSpaceCommand;
import com.hons.honspace.solarsystem.Grid;
import com.hons.honspace.solarsystem.objects.ObjectInSpace;
import com.hons.honspace.solarsystem.objects.movable.MovableObjectInSpace;
import com.hons.honspace.solarsystem.objects.unmovable.Gate;
import com.hons.honspace.solarsystem.places.Place;
import com.hons.honspace.storage.GridStorage;

@Controller
public class ObjectInSpaceController {
    public static final Logger logger = Logger.getLogger(ObjectInSpaceController.class);
    
    @Autowired
    GridStorage gs;
    
    @RequestMapping(value = "/grid/{gridId}/ois", method = RequestMethod.GET)
    public String getAllObjects(@PathVariable int gridId, ModelMap model) {
        Grid grid = gs.getGridAndMakeIfNeed(gridId);
        List<ObjectInSpaceCommand> objs = new ArrayList<ObjectInSpaceCommand>();
        for (ObjectInSpace ois :grid.getAllObjectInSpace()){
            ObjectInSpaceCommand command = new ObjectInSpaceCommand(); 
            BeanUtils.copyProperties(ois,command);
            Gate gate = canJump(grid, ois);
            command.setCanJump(gate == null ? false : true);
            objs.add(command);
        }
        model.put("objects", objs);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{gridId}/ois/{id}", method = RequestMethod.GET)
    public String getAnObject(@PathVariable int gridId,@PathVariable int id, ModelMap model) {
        Grid grid = gs.getGridAndMakeIfNeed(gridId);
        ObjectInSpace ois = grid.getObjectInSpaceWithId(id);
        ObjectInSpaceCommand command = new ObjectInSpaceCommand(); 
        BeanUtils.copyProperties(ois,command);
        Gate gate = canJump(grid, ois);
        command.setCanJump(gate == null ? false : true);
        
        model.put("ois", command);
        return "list";
    }
    
    @RequestMapping(value = "/grid/{gridId}/ois/{id}/move", method = RequestMethod.POST)
    public String moveAnObject( @PathVariable int gridId,
                                @PathVariable int id,
                                @RequestParam(value="destinationX") int destinationX,
                                @RequestParam(value="destinationY") int destinationY,
                                ModelMap model) {
        Grid grid = gs.getGridAndMakeIfNeed(gridId);
        
        MovableObjectInSpace ois = (MovableObjectInSpace) grid.getObjectInSpaceWithId(id);
        if(ois.getMoved() == true){
            model.put("status", "Failure: Already moved");
            return "list";
        }
        
        Place from = grid.getPlaceWithId(ois.getPlaceId());
        Place to = grid.getPlace(destinationX, destinationY);

        int xDelta = Math.abs(from.getX() - to.getX());
        int yDelta = Math.abs(from.getY() - to.getY());
        int delta = xDelta + yDelta;
        
        if(delta <= ois.getMoveDistance()) {
            ois.setMoved(true);
            MoveObjectAction action = new MoveObjectAction(ois,grid,to.getX(),to.getY());
            ActionQueue.getInstance().queueItem(action);
            model.put("status", "Success");
        } else {
            model.put("status", "Failure: cannot move that far");
        }
        return "list";
    }
    
    @RequestMapping(value = "/grid/{gridId}/ois/{id}/jump", method = RequestMethod.POST)
    public String jumpAnObject( @PathVariable int gridId,
                                @PathVariable int id,
                                ModelMap model) {
        
        Grid grid = gs.getGridAndMakeIfNeed(gridId);
        
        MovableObjectInSpace ois = (MovableObjectInSpace) grid.getObjectInSpaceWithId(id);
        if(ois.getMoved() == true){
            model.put("status", "Failure: Already moved");
            return "list";
        }
        Gate gate = canJump(grid, ois);
        
        if(gate != null) {
            ois.setMoved(true);
            JumpObjectAction action = new JumpObjectAction(ois, grid, gate);
            ActionQueue.getInstance().queueItem(action);
            model.put("status", "Success");
        } else {
            model.put("status", "Failure: no gate");
        }
        return "list";
    }
    
    private Gate canJump(Grid grid, ObjectInSpace ois){
        if(!(ois instanceof MovableObjectInSpace)) return null;
        
        List<ObjectInSpace> objects = grid.getObjectInSpaceAt(ois.getPlaceId());
        for(ObjectInSpace obj : objects){
            if(obj instanceof Gate){
                return (Gate) obj;
            }
        }
        return null;
    }
    
}
