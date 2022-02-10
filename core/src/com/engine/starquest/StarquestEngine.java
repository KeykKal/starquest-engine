package com.engine.starquest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarquestEngine extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    ShaderProgram s;
	String v, f;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        v = Gdx.files.internal("vertexShader.glsl").readString();
        f = Gdx.files.internal("fragmentShader.glsl").readString();
        s = new ShaderProgram(v, f);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
        batch.setShader(s);
        batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
