package com.engine.starquest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.engine.starquest.scenes.MainGameScene;
import com.engine.starquest.scenes.Scene;

public class StarquestEngine extends Game {
    
    MainGameScene mainGame;
    

    public static enum sceneType {
        MainGame
    }

    public Scene getSceneType(sceneType sceneType) {
        switch(sceneType) {
            //MainGame
            default: return mainGame; 
        }
    }

    @Override
    public void create() {
        mainGame = new MainGameScene(this);
        setScreen(mainGame);
    }

    @Override
    public void dispose() {
        mainGame.dispose();
    }
}

/* 
    // ich habe keine ahnung wie ich collisionen implementieren soll also lass ich's
    // fürs erste
    // wichtig für das physik system incls. collisionen
    World world;

    OrthographicCamera cam;
    FitViewport viewport;
    Scene testScene;

    InventoryUI inv;
    Stage stage;

    SpriteBatch sb;
    Player player;

    float elapsedTime = 0;

    @Override
    public void create() {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);

        viewport = new FitViewport(16, 9);

        testScene = new TestScene(cam);
        sb = new SpriteBatch();
        inv = new InventoryUI();
        stage = new Stage(viewport);
        stage.addActor(inv);

        player = new Player(cam);
    }

    public void switchSene(Scene to) {
        testScene.dispose();
        this.testScene = to;
        testScene.init();
    }

    @Override
    public void render() {
        // clear screen
        ScreenUtils.clear(0, 0, 0, 1);

        // camera controlls

        // updates
        player.update();
        cam.update();

        // render

        // unterste ebene als erstes rendern

        // scene render
        testScene.render();

        sb.setProjectionMatrix(cam.combined);
        
        // render die animation | platz halter für den spieler an position x:200, y:250
        player.render();

        sb.begin();
        inv.draw(sb, 1f);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        testScene.dispose();
        player.dispose();
        sb.dispose();
        Assets.UI.UI_SKIN.dispose();
    }
*/