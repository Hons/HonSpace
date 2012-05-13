package com.hons.honspace.command;

/**
 * @author jai
 *
 */
public class ObjectInSpaceCommand {
    protected int id;
    protected int gridId;
    protected int placeId;
    protected String name;
    protected String type;
    protected int moveDistance;
    protected int viewDistance;
    protected boolean moved;
    boolean canJump;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGridId() {
        return gridId;
    }
    public void setGridId(int gridId) {
        this.gridId = gridId;
    }
    public int getPlaceId() {
        return placeId;
    }
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
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
    public boolean getCanJump() {
        return canJump;
    }
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
    public int getMoveDistance() {
        return moveDistance;
    }
    public void setMoveDistance(int moveDistance) {
        this.moveDistance = moveDistance;
    }
    public int getViewDistance() {
        return viewDistance;
    }
    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }
    public boolean getMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }
    
}
