package com.engine.starquest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.engine.starquest.mobs.Player;

public class Hud {
    
    Stage stage;
    private Viewport viewport;
    private Label health;
    private Player player;
    

    
    public Hud(Batch sb, Player p) {
        player = p;

        viewport = new FitViewport(MainGameScene.V_WIDTH, MainGameScene.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        health = new Label(String.format("%03d", player.getCurrentHealth()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(health).align(0);

        stage.addActor(table);
    }

    public void drawHud(Batch b) {
        //draw ui in stage
        b.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();

        
    }

    public void updateHud() {
        health.setText(String.format("%03d", player.getCurrentHealth()));
    }
}
