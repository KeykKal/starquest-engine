package com.engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//TODO: am besten noch animation stände hinzufügen (Bsp.: Idle, attack, sprint, ...)
public class Animation {
    
    //jeder frame in eigenen Texturen
    SplicedImage splicedImage;

    Texture animationTexture;
    com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;

    //wie lange ein die animation schon spielt (ist wichtig um die momentane animation zu kennen)
    float currentFrameTime;
    SpriteBatch batch;
    boolean looping;

    public Animation(float frameDur, SplicedImage splicedFrame, boolean loop) {
        batch = new SpriteBatch();
        splicedImage = splicedFrame;
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDur, splicedImage.framesArray());
        looping = loop;
    }

    public Animation(float frameDur, String texture, int rows, int columns, boolean loop) {
        this(frameDur, new SplicedImage(texture, rows, columns), loop);
    }

    public void playAnimation(float x, float y, OrthographicCamera cam) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        currentFrameTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(currentFrameTime, looping), x, y);

        batch.end();
    }

    public void playAnimation(float x, float y) {
        batch.begin();

        currentFrameTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(currentFrameTime, looping), x, y);

        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }
}
