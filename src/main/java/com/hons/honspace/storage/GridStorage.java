package com.hons.honspace.storage;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hons.honspace.solarsystem.Grid;

@Component
public class GridStorage {
    public static final Logger logger = Logger.getLogger(GridStorage.class);

    private @Value("#{runtimeProperties['gridstorage.use.localmap']}") boolean useLocalMap;
    private @Value("#{runtimeProperties['gridstorage.use.memcache']}") boolean useMemcache;
    private @Value("#{runtimeProperties['gridstorage.use.hbase']}") boolean useHbase;
    
    private HashMap<Integer, Grid> localMap = new HashMap<Integer, Grid>();
    
    /*
     * This isn't a regular singleton, i.e. there's no getInstance() method.
     * The singleton is created by spring during init in order to populate the @Value
     * fields. That this means is you can inject it into any class with @Autowired.
     * BUT we still need the private constructor to stop mistaken creation of the class
     * 
     * As spring completely ignores wiring of static fields there's no way to have a static 
     * accessor autowired.  
     */
    private GridStorage(){
        
    }
    
    public Grid getGrid(int id) {
        Grid grid = null;
        if(useLocalMap){
            grid = localMap.get(id);
            if(grid != null) return grid;
        }

        if(useMemcache){
            // memcached logic goes here
            if(grid != null) return grid;
        }
        
        if(useHbase){
            // hbase logic goes here
            grid = localMap.get(id);
            if(grid != null) return grid;
        }
        return grid;
    }
    
    public void storeGrid(Grid grid){
        if(useLocalMap){
            localMap.put(grid.getId(), grid);
        }

        if(useMemcache){
            // memcached logic goes here
        }
        
        if(useHbase){
            // hbase logic goes here
        }
        
    }
    
    public String toString() {
        return "GridStorage [useLocalMap=" + useLocalMap + ", useMemcache="
                + useMemcache + ", useHbase=" + useHbase + "]";
    }
    
}
