package com.engine.starquest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.engine.starquest.StarquestEngine;
import com.engine.starquest.items.Item;
import com.engine.starquest.mobs.*;

public class MainGameScene extends Scene {

    public static final int V_WIDTH = 200, V_HEIGHT = 122;

    FitViewport fViewport;
    private OrthographicCamera camera;
    private StarquestEngine _game;
    SpriteBatch sb;
    private Item item;
    private Hud hud;

    OrthogonalTiledMapRenderer mapRenderer;

    Player player;
    Enemy testEnemy;

    public MainGameScene(StarquestEngine game) {
        _game = game;
        item = new Item.TestItem();

        fViewport = new FitViewport(V_WIDTH, V_HEIGHT);
        player = new Player("Player");

        testEnemy = new Enemy();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);

        // Gdx.input.setInputProcessor(player);
        
        mapRenderer = new OrthogonalTiledMapRenderer(new Map().getCurrentMap(), Map.scale);
        sb = new SpriteBatch();
        hud = new Hud(mapRenderer.getBatch(), player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.getBatch().setProjectionMatrix(camera.combined);
		mapRenderer.setView(camera);
        mapRenderer.getBatch().enableBlending();
		mapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        mapRenderer.render();

        sb.setProjectionMatrix(camera.combined);
        player.update(sb, delta);
        testEnemy.update(sb, delta);

        hud.updateHud();
        hud.drawHud(mapRenderer.getBatch());
        camera.position.set(player.getPosition(), 0);
        camera.update();

        if(player.collision.collidesWith(testEnemy.collision)) {
            player.curHealth--;
        }
    }

    @Override
    public void resize(int width, int height) {
        fViewport.update(width, height);
        camera.setToOrtho(false, width, height);
    }

}