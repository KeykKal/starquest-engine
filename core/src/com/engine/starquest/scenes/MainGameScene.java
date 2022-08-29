package com.engine.starquest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.engine.starquest.StarquestEngine;
import com.engine.starquest.Utils.Assets;
import com.engine.starquest.mobs.Entity;
import com.engine.starquest.mobs.Player;

public class MainGameScene extends Scene {

    FitViewport fViewport;
    private OrthographicCamera camera;
    private StarquestEngine _game;
    SpriteBatch sb;

    OrthogonalTiledMapRenderer mapRenderer;

    Player player;

    public MainGameScene(StarquestEngine game) {
        _game = game;

        fViewport = new FitViewport(160, 90);
        player = new Player("Player");

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, 20);

        // Gdx.input.setInputProcessor(player);
        
        mapRenderer = new OrthogonalTiledMapRenderer(new Map().getCurrentMap(), Map.scale);
        sb = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        mapRenderer.getBatch().setProjectionMatrix(camera.combined);
		mapRenderer.setView(camera);
        mapRenderer.getBatch().enableBlending();
		mapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // mapRenderer.getBatch().begin();
        mapRenderer.render();
        // mapRenderer.getBatch().end();
        sb.setProjectionMatrix(camera.combined);
        player.update(sb, delta);
        camera.position.set(player.getPosition(), 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        fViewport.update(width, height);
        camera.setToOrtho(false, width, height);
    }

}