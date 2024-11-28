package org.example.Util;

import java.awt.*;

public enum CollisionTypes {
    None(Color.white),
    Apple(Color.red),
    Block(Color.darkGray),
    Self(Color.blue);

    private final Color CollisionColor;

    CollisionTypes(Color CollisionColor) {
        this.CollisionColor = CollisionColor;
    }

    public Color getCollisionColor() {
        return CollisionColor;
    }
}
