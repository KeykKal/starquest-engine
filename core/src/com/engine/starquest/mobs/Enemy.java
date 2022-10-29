package com.engine.starquest.mobs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.engine.starquest.Utils.Assets;
import com.engine.starquest.physics.CollisionRect;

public class Enemy extends Entity {


    public Enemy() {
        super("Zombie", 100);
        position = new Vector2(-100, -18);
        Array<GridPoint2> points = new Array<>();
        frameW = 64;
        frameH = 64;

        collision = new CollisionRect(position.x, position.y, 16, 64);
        
        for(int i = 0; i < 13; i++) points.add(new GridPoint2(i, 0));
        loadAnimations(Assets.Sprites.TEMP_PLAYER_IDLE, "IDLE", points, 0.2f, true);
        points.clear();

    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        updateAnimation(delta);

        drawCharacter(batch);
    }
    

}
