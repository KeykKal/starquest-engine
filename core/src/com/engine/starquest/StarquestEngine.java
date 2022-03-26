package com.engine.starquest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.engine.scenes.Scene;

public class StarquestEngine extends ApplicationAdapter {
    OrthographicCamera cam;
    Scene testScene;

	@Override
	public void create () {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false,w,h);
        testScene = new Scene("unbenannt.tmx", cam);
        testScene.setBackgroundMusic("Main_theme_trailer.mp3");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

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
        cam.update();

        testScene.update();
    }
	
	@Override
	public void dispose () {
        testScene.dispose();
	}
}
