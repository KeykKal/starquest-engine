package com.engine.starquest.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    
    //Idee der sortierung geklaut von
    //https://github.com/00-Evan/shattered-pixel-dungeon/blob/master/core/src/main/java/com/shatteredpixel/shatteredpixeldungeon/Assets.java


    public static class TileMaps {
        public static final String TEST_TILE_MAP = "tilemaps/testTilemap.tmx";
        public static final String ISOMETRIC_TEST_MAP = "tilemaps/isometrie.tmx";
    }

    //der name sorgt etwas für verwirrung da es damit 3 klassen mit dem namen Music gibt
    public static class Music {
        public static final String BG_MUSIC_MAIN_THEME_MUSIC = "music/Main_theme_trailer.mp3";
        public static final String NICHT_VON_LOZ = "music/LOZ_FF.mp3";
    }

    public static class Sprites {
        public static final String TEMP_PLAYER_IDLE = "images/noBKG_KnightIdle_strip.png";
        public static final String TEMP_PLAYER_RUN = "images/noBKG_KnightRun_strip.png";
        public static final String TEMP_OTHER_PLAYER_SHEET = "images/Warrior_SheetnoEffect.png";
    }

    public static class UI {
        public static final String UI_SKIN_PATH = "data/uiskin.json";
        public static final Skin UI_SKIN = new Skin(Gdx.files.internal(UI_SKIN_PATH));
    }

}
