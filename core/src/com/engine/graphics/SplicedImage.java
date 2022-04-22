package com.engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//Framed spliced Image
public class SplicedImage {
    
    Frame[] frames;
    TextureRegion[] textFrames;
    
    // row = horizontal | column = vertical
    private final int ROWS, COLUMNS;
    Texture spriteSheet;
    String texturePath;

    public SplicedImage(String spriteSheetPath, int rows, int columns) {
        spriteSheet = new Texture(Gdx.files.internal(spriteSheetPath));
        ROWS = rows;
        COLUMNS = columns;
    }

    public TextureRegion[] framesArray() {
        if(textFrames == null) {

            TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 
            spriteSheet.getWidth()/COLUMNS,
            spriteSheet.getHeight()/ROWS);        	
            TextureRegion[] walkFrames = new TextureRegion[COLUMNS * ROWS];

            int index = 0;
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
            this.textFrames = walkFrames;

        }
        return textFrames;
    }

    public void flipX() {
        for(int i = 0; i < textFrames.length;i++)
            textFrames[i].flip(true, false);
    }

}

//eine eige classe hierfür wäre nicht schlecht
//wenn es öfters gebraucht wird
class Frame {

    float left;
    float top;
    float right;
    float bottom;

    public Frame()  {
        set(0,0,0,0);
    }

    public Frame(Frame f) {
        set(f.left, f.top, f.right, f.bottom);
    }

    public Frame(float left, float top, float right, float buttom) {
        set(left, top, right, buttom); 
    }

    public Frame set( float left, float top, float right, float bottom ) {
		this.left	= left;
		this.top	= top;
		this.right	= right;
		this.bottom	= bottom;
		return this;
	}

    public float width() {
		return right - left;
	}
	
	public float height() {
		return bottom - top;
	}

    public Frame setPos( float x, float y ) {
		return set( x, y, x + (right - left), y + (bottom - top));
	}
	
	public Frame shift( float x, float y ) {
		return set( left+x, top+y, right+x, bottom+y );
	}

}