package com.engine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level {
    
    TiledMap map;
    TiledMapRenderer tileMapRenderer;
    OrthographicCamera camera;

    public Level(String tilemap, OrthographicCamera camera) {
        this.camera = camera;
        map = new TmxMapLoader().load(tilemap);
        tileMapRenderer = new OrthogonalTiledMapRenderer(map);
        render();
    }

    public void render() {
        if(tileMapRenderer == null || camera == null) return;
        
        tileMapRenderer.setView(camera);
        tileMapRenderer.render();
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public TiledMapRenderer getTileMapRenderer() {
        return tileMapRenderer;
    }

    public void setTileMapRenderer(TiledMapRenderer tileMapRenderer) {
        this.tileMapRenderer = tileMapRenderer;
    }

    public void dispose() {
        if(map != null) map.dispose();
    }

}
