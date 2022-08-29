package com.engine.starquest.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item {
    int id;
    String name;
    Texture sprite;

    public abstract void update();
}
