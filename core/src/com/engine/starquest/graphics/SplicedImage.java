package com.engine.starquest.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//Framed spliced Image
public class SplicedImage {

    // Frame[] frames;
    TextureRegion[] textFrames;

    // row = horizontal | column = vertical
    private final int ROWS, COLUMNS;
    public Texture spriteSheet;
    String texturePath;

    public SplicedImage(String spriteSheetPath, int rows, int columns) {
        this(new Texture(Gdx.files.internal(spriteSheetPath)), rows, columns);
    }

    public SplicedImage(Texture texture, int rows, int columns) {
        spriteSheet = texture;
        ROWS = rows;
        COLUMNS = columns;
    }

    public TextureRegion[] framesArray() {
        if (textFrames == null) {
            TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                    spriteSheet.getWidth() / COLUMNS,
                    spriteSheet.getHeight() / ROWS);

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
        for (int i = 0; i < textFrames.length; i++)
            textFrames[i].flip(true, false);
    }

    public SplicedImage subPart(int from, int to) {
        if (from > to)
            return null;
        int numberOfPictures = to - from;

        TextureRegion[] tmp = new TextureRegion[numberOfPictures + 1];
        int j = 0;
        for (int i = from; i <= to; i++) {
            tmp[j++] = framesArray()[i];
        }

        Pixmap pixmap = new Pixmap(tmp[j-1].getRegionWidth() * numberOfPictures, tmp[j-1].getRegionHeight(),
                Format.RGBA4444); // dispose

        int drawnPixel = 0;
        // System.out.println(tmp.length);
        for (int i = 0; i < tmp.length; i++) {
            TextureRegion tmpT = tmp[i]; // dispose
            Pixmap tmpP = textReagionToPixmap(tmpT); // dispose

            pixmap.drawPixmap(tmpP, drawnPixel, 0);
            drawnPixel += tmp[i].getRegionWidth();

            // tmpP.dispose();
            // tmpT.getTexture().dispose();
        }

        Texture t = new Texture(pixmap);
        pixmap.dispose();
        return new SplicedImage(t, 1, numberOfPictures);
    }

    public Pixmap textReagionToPixmap(TextureRegion tr) {
        TextureData textureData = tr.getTexture().getTextureData();

        if(!textureData.isPrepared()) textureData.prepare();

        Pixmap result = new Pixmap(tr.getRegionWidth(), tr.getRegionHeight(), textureData.getFormat());
        Pixmap other = textureData.consumePixmap();

        for (int x = 0; x < tr.getRegionWidth(); x++) {
            for (int y = 0; y < tr.getRegionHeight(); y++) {
                int colorInt = other.getPixel(tr.getRegionX() + x, tr.getRegionY() + y);
                result.drawPixel(x, y, colorInt);
            }
        }

        return result;
    }

    public Pixmap extractPixmapFromTextureRegion(TextureRegion textureRegion) {
        TextureData textureData = textureRegion.getTexture().getTextureData();
        
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        Pixmap pixmap = new Pixmap(
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),
                textureData.getFormat());
        pixmap.drawPixmap(
                textureData.consumePixmap(), // The other Pixmap
                0, // The target x-coordinate (top left corner)
                0, // The target y-coordinate (top left corner)
                textureRegion.getRegionX(), // The source x-coordinate (top left corner)
                textureRegion.getRegionY(), // The source y-coordinate (top left corner)
                textureRegion.getRegionWidth(), // The width of the area from the other Pixmap in pixels
                textureRegion.getRegionHeight() // The height of the area from the other Pixmap in pixels
        );
        return pixmap;
    }

}

// eine eige classe hierfür wäre nicht schlecht
// wenn es öfters gebraucht wird aber momentan nutzlos
class Frame {

    float left;
    float top;
    float right;
    float bottom;

    public Frame() {
        set(0, 0, 0, 0);
    }

    public Frame(Frame f) {
        set(f.left, f.top, f.right, f.bottom);
    }

    public Frame(float left, float top, float right, float buttom) {
        set(left, top, right, buttom);
    }

    public Frame set(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        return this;
    }

    public float width() {
        return right - left;
    }

    public float height() {
        return bottom - top;
    }

    public Frame setPos(float x, float y) {
        return set(x, y, x + (right - left), y + (bottom - top));
    }

    public Frame shift(float x, float y) {
        return set(left + x, top + y, right + x, bottom + y);
    }

}