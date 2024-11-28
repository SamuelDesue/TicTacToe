package org.example.Util;

public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4);

    private final int directionValue;

    Direction(int directionValue) {
        this.directionValue = directionValue;
    }

    public int getDirectionValue() {
        return directionValue;
    }
}
