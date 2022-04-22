package com.engine.mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.engine.Utils.Assets;
import com.engine.graphics.Animation;
import com.engine.graphics.SplicedImage;

//die erbungs hirachie wird später noch verändert 
//aber fürs erste bin ich zu faul.
public class Player extends Entity {

    //statt nur eine animation zu haben könnte man ein array haben
    //oder man könnte alle animationen in einer Texture haben und 
    //sie durch die reihen wechseln (bsp.: reihe 1 ist die idle animation und reihe 2 die lauf animation)
    Animation playerAnim;

    SplicedImage playerSplice;
    int richtung = 1;
    float speed = 100;

    public Player() {
        playerSplice = new SplicedImage(Assets.Sprites.TEMP_PLAYER_RUN, 1, 8);
        playerAnim = new Animation(0.075f, playerSplice, true);
        position = new Vector2();
    }

    public void update(OrthographicCamera cam) {
        move(cam);
    }

    public void render() {
        playerAnim.playAnimation(position.x, position.y);
    }

    public void move(OrthographicCamera cam) {
        position.set(cam.position.x, cam.position.y);
        Vector2 dir = new Vector2();
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            dir.x--;

            // eine nicht so gute methode das zu machen aber es klapt und ich bin faul
            if(richtung != -1) {
                playerSplice.flipX(); 
                richtung = -1;
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            dir.x++;
            if(richtung != 1) {
                playerSplice.flipX();
                richtung = 1;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            dir.y++;
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            dir.y--;
        } 

        

        dir.x*=speed * Gdx.graphics.getDeltaTime();
        dir.y*=speed * Gdx.graphics.getDeltaTime();
        position.add(dir);  
        cam.position.add(new Vector3(dir, 0));
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void dispose() {
        playerAnim.dispose();
    }

}
