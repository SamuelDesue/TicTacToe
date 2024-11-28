package org.example.Player;

import org.example.Exceptions.OutOfBounds;
import org.example.Exceptions.SelfContact;
import org.example.Main;
import org.example.Util.CollisionTypes;
import org.example.Util.Position;
import org.example.World.Box;
import org.example.World.Jworld;

import java.util.*;

public class PlayerController {
    private Box worldBox = Main.getWorldBox();

    private final PlayerMovement playerMovement = PlayerMovement.getINSTANCE();

    // Player Pos
    private static final Position playerPos = new Position(CollisionTypes.Self,3,5);

    public Position getPlayerPos() {
        return playerPos;
    }

    private CollisionTypes checkForCollision(){
        List<Position> objectPos = Main.getWorldBox().getObjLocations();

        for(Position pos : objectPos){
            if(Objects.equals(pos.toString(), playerPos.toString())){
                return pos.getCollisionType();
            }
        }

        return CollisionTypes.None;
    }

    private void removeObject(CollisionTypes type){
        List<Position> objectPos = Main.getWorldBox().getObjLocations();
        Iterator<Position> iterator = objectPos.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCollisionType().equals(type)) {
                iterator.remove();
                break; // Stop after removing the first match
            }
        }
    }

    public void doPlayerMovement(){


        //change player pos
        switch (playerMovement.getPlayerDirection()){
            case UP:
                //UP
                playerPos.addToY(-1);
                break;
            case DOWN:
                //DOWN
                playerPos.addToY(1);
                break;
            case LEFT:
                //LEFT
                playerPos.addToX(-1);
                break;
            case RIGHT:
                //RIGHT
                playerPos.addToX(1);
                break;
        }

        if(playerPos.getX() > worldBox.getWidth() || playerPos.getX() < 0 || playerPos.getY() > worldBox.getHeight() || playerPos.getY() < 0) throw new OutOfBounds();

        //check for collision
        switch (checkForCollision()){
            case None:
                //remove last tail
                removeObject(CollisionTypes.Self);
                break;
            case Apple:
                System.out.println("apple");
                removeObject(CollisionTypes.Apple);
                Jworld.putApple();
                //add to score
                Main.score += 1;
                break;
            default:
                System.out.println("self or block");
                //Self or Block
                throw new SelfContact();
        }
        //addCurrent pos
        worldBox.addObject(new Position(CollisionTypes.Self, playerPos));
        Jworld.repaintPanel();
    }
}
