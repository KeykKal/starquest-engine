package com.engine.starquest.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


//Für die zukunft kann man jedes item einmal generieren und dann je nach id die requested wird die selben bilder usw verwenden
//in anderen worten einfaches speicher managment

public abstract class Item {
    int id;
    String name;
    public Texture sprite;
    public Item(int id, String name, Texture sprite) { this.id = id; this.name = name; this.sprite = sprite; }

    public abstract void update();
    
    public static class TestItem extends Item {
        
        public TestItem() { super(0, "Test", new Texture(Gdx.files.internal("images/badlogic.jpg"))); }
        
        @Override
        public void update() {
            
        }
    }
}

