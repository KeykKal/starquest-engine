package com.engine.starquest.scenes;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.engine.starquest.Utils.Assets;

//eigentlich abstract aber ich bin fürs erste mal faul
public class Map {  

    protected TiledMap currentMap;
    public static float scale = 1f+1/16f;

    public Map() {
        currentMap = new TmxMapLoader().load(Assets.TileMaps.TEST_TILE_MAP);
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

}
