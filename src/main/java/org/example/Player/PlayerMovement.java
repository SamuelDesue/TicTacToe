package org.example.Player;

import org.example.Util.Direction;

public class PlayerMovement {
    private static PlayerMovement INSTANCE = new PlayerMovement();
    Direction playerDirection = Direction.RIGHT;

    public static PlayerMovement getINSTANCE() {
        return INSTANCE;
    }

    public PlayerMovement(){
    }

    public void setMovement(Direction direction){
        this.playerDirection = direction;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }
}
