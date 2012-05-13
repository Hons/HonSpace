package com.hons.honspace.solarsystem.objects.unmovable;

import com.hons.honspace.solarsystem.objects.ObjectInSpace;

public class Gate extends ObjectInSpace {

    int linksToGateId = -1;
    
    public Gate() {
        super();
    }

    public int getLinksToGateId() {
        return linksToGateId;
    }

    public void setLinksToGateId(int linksToGateId) {
        this.linksToGateId = linksToGateId;
    }

}
