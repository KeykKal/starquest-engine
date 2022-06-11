package com.engine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.Utils.Assets;

public class TestScene2 extends Scene {

    SpriteBatch sb;

    public TestScene2(OrthographicCamera camera) {
        super(Assets.TileMaps.ISOMETRIC_TEST_MAP, camera);
        super.startBGMusic(Assets.Music.NICHT_VON_LOZ);
        super.bgMusic.volume(1f);
        sb = new SpriteBatch();
    }

    @Override
    public void render() {
        super.render();
    }
    
    
    @Override
    public void dispose() {
        sb.dispose();
        super.dispose();
    }

}
