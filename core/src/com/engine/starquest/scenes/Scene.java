package com.engine.starquest.scenes;

import com.badlogic.gdx.Screen;

//muss abstract in der zukunft sein damit wir mehrere scenen haben und es einfacher ist sie zu erstellen
//oder ein interface, ich glaub interface wäre sogar besser 
public class Scene implements Screen {
    
    @Override
    public void show() {}

    @Override
    public void render(float delta) {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
