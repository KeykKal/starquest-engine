package com.engine.scenes;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.engine.starquest.StarquestEngine;


public class Scene {
    
    /**
     * Problems:
     *  -shaders funktiunieren nicht wenn man die update methode zwichen batch.begin und batch.end setzt
    */


    TiledMap map;
    TiledMapRenderer tileMapRenderer;
    OrthographicCamera camera;

    //Vlt sogar einen eigenen shader pro Scene um jeder scene eienen unique feeling zu geben
    ShaderProgram shader;

    public Scene(String tilemap, OrthographicCamera camera) {
        this(tilemap, camera, null);
    }

    public Scene(String tilemap, OrthographicCamera camera, ShaderProgram shader) {
        this.camera = camera;
        map = new TmxMapLoader().load(tilemap);
        tileMapRenderer = new OrthogonalTiledMapRenderer(map);

        tileMapRenderer.setView(camera);

        setShader(shader);

        init();
    }


    public void init() {}

    public void update() {
        tileMapRenderer.setView(camera);
        tileMapRenderer.render();
    }


    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public ShaderProgram getShader() {
        return this.shader;
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

}
