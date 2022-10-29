package com.engine.starquest.physics;

import com.badlogic.gdx.math.Vector2;

public class CollisionRect {
    private Vector2 position;
    int width, height;
    

    public CollisionRect(float x, float y, int w, int h) {
        position = new Vector2(x, y);
        width = w;
        height = h;
    }

    public void move(float x, float y) { position.set(x, y); }

    public boolean collidesWith(CollisionRect other) {
        return position.x < other.position.x + other.width &&
         position.y < other.position.y + other.height &&
         position.x + width  > other.position.x &&
         position.y + height > other.position.y;
    }

}