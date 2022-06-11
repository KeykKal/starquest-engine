package com.engine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.engine.audio.Music;

//muss abstract in der zukunft sein damit wir mehrere scenen haben und es einfacher ist sie zu erstellen
//oder ein interface, ich glaub interface wäre sogar besser 
public abstract class Scene {

    /**
     * Problems:
     * -shaders funktiunieren nicht wenn man die update methode zwichen batch.begin
     * und batch.end setzt
    */
    Level level;
    Music bgMusic;

    OrthographicCamera camera;

    //unsicher ob wir ein shader pro scene überhaupt haben wollen
    ShaderProgram shader; // noch nicht implementiert

    public Scene(OrthographicCamera cam) {
        this(null, cam);
    }

    public Scene(String tilemap, OrthographicCamera camera) {
        this(tilemap, camera, null);
    }

    public Scene(String tilemap, OrthographicCamera camera, ShaderProgram shader) {
        this.camera = camera;
        if(tilemap != null)
            level = new Level(tilemap, camera);
        bgMusic = new Music();

        setShader(shader);
    }

    public void init() {}

    public void render() {
        if(level != null) {
            level.render();
        }
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    protected void startBGMusic(String musicFile) {
        bgMusic.play(musicFile, true);
    }

    public void dispose() {
        if (level != null)
            level.dispose();
        if (shader != null)
            shader.dispose();
        if (bgMusic != null)
            bgMusic.end();
    }
}
