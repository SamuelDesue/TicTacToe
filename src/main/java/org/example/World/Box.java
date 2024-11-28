package org.example.World;

import org.example.Util.CollisionTypes;
import org.example.Util.Position;

import java.util.List;

public class Box {
    private int width;
    private int height;
    List<Position> objLocations = new java.util.ArrayList<>(List.of(new Position(CollisionTypes.Apple,10,10)));

    public Box(int width, int height){
        this.width = width;
        this.height = height;
    }

    public List<Position> getObjLocations() {
        return objLocations;
    }

    public void addObject(Position p){
        objLocations.add(p);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
