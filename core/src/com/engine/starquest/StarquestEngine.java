package com.engine.starquest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engine.Utils.Assets;
import com.engine.graphics.Animation;
import com.engine.graphics.SplicedImage;
import com.engine.mobs.Player;
import com.engine.scenes.Scene;
import com.engine.scenes.TestScene;
import com.engine.scenes.TestScene2;

public class StarquestEngine extends ApplicationAdapter {
    // ich habe keine ahnung wie ich collisionen implementieren soll also lass ich's fürs erste
    // wichtig für das physik system incls. collisionen
    World world;

    OrthographicCamera cam;
    Scene testScene;
    Texture t; // Texture um triggers zu testen

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
        testScene = new TestScene(cam);
        t = new Texture(Gdx.files.internal("images/badlogic.jpg"));
        sb = new SpriteBatch();

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
        // if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        // cam.translate(-1, 0);
        // } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
        // cam.translate(1, 0);
        // }

        // if(Gdx.input.isKeyPressed(Input.Keys.W)) {
        // cam.translate(0, 1);
        // } else if(Gdx.input.isKeyPressed(Input.Keys.S)){
        // cam.translate(0, -1);
        // }

        // updates
        player.update();
        cam.update();

        // render

        // unterste ebene als erstes rendern

        // scene render
        testScene.render();

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(t, -300, -300);
        sb.end();

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
