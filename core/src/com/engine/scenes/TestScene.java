package com.engine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.engine.Utils.Assets;

public class TestScene extends Scene {

    public TestScene(OrthographicCamera camera) {
        super(Assets.TileMaps.TEST_TILE_MAP, camera);
        super.startBGMusic(Assets.Music.BG_MUSIC_MAIN_THEME_MUSIC);
        bgMusic.volume(.1f);
    }

    @Override
    public void init() {
        System.out.println("starte TestScene");
    }

}
