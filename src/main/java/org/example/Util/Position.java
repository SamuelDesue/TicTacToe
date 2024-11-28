package org.example.Util;

import java.util.Random;

public class Position {
    private Random rand = new Random();
    private CollisionTypes collisionType;
    private int X;
    private int Y;

    // Constructor to initialize the pair
    public Position(CollisionTypes collisionType,int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.collisionType = collisionType;
    }

    public Position(CollisionTypes collisionType,Position pos) {
        this.X = pos.getX();
        this.Y = pos.getY();
        this.collisionType = collisionType;
    }

    // Getter methods
    public Position getPosition(){
        return this;
    }

    public CollisionTypes getCollisionType() {
        return collisionType;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    //Setter and add methods
    public void addToX(int increment) {
        this.X = this.X + increment;
    }

    public void addToY(int increment) {
        this.Y = this.Y + increment;
    }

    public void setX(int value) {
        this.X = value;
    }

    public void setY(int value) {
        this.Y = value;
    }

    public void setCollisionType(CollisionTypes value) {
        this.collisionType = value;
    }

    public boolean sameCord(Position pos2){
        return (this.getX() == pos2.getX() && this.getY() == pos2.getY());
    }

    @Override
    public String toString(){
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}
