package com.engine.starquest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.engine.Utils.Assets;
import com.engine.graphics.Animation;
import com.engine.graphics.SplicedImage;
import com.engine.mobs.Player;
import com.engine.scenes.Scene;
import com.engine.scenes.TestScene;

public class StarquestEngine extends ApplicationAdapter {
    OrthographicCamera cam;
    Scene testScene;

    // das hier in die player classe
    SpriteBatch sb;
    Player player;

    float elapsedTime = 0;

    @Override
    public void create() {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);
        testScene = new TestScene(cam);

        sb = new SpriteBatch();

        player = new Player();
    }

    @Override
    public void render() {
        // clear screen
        ScreenUtils.clear(0, 0, 0, 1);

        // camera controlls
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.translate(-1, 0);
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            cam.translate(1, 0);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.translate(0, 1);
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            cam.translate(0, -1);
        }

        //updates
        player.update(cam);
        cam.update();

        // render

        // unterste ebene als erstes rendern
        // scene render
        testScene.render();

        // render die animation | platz halter für den spieler an position x:200, y:250
        player.render();
    }

    @Override
    public void dispose() {
        testScene.dispose();
        player.dispose();
        sb.dispose();
    }
}
