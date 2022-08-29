package com.engine.starquest.mobs;

import java.time.temporal.IsoFields;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

//Der name des packages "mob" ist nur temporär. Bei besseren ideen einfach ändern

//Ich bin am flippen des characters noch dran
public abstract class Entity {
    protected String name;
    protected Vector2 position;

    public Entity(String name) {
        this.name = name;
        position = new Vector2(); //400, 225
        animations = new Hashtable<>();
    }

    //Animations system

    //fixed kann man in der zukunft ändern
    protected int frameW = 69, frameH = 44;
    
    
    //        String = animation state name | Animation = die animation selbst
    protected Hashtable<String, Animation<TextureRegion>> animations;
    protected TextureRegion currentFrame = null;

    protected boolean flip;

    protected Animation<TextureRegion> currentAnimation; 

    protected float frameTime;

    public abstract void update(SpriteBatch batch, float delta);
    
    protected void updateAnimation(float delta) {
        frameTime = (frameTime+delta)%8;
        
        //wenn es keine animation gibt geh zurück zur Idle
        if(currentAnimation == null) {
            //Idle könnte erstmal nicht existent sein also vorsicht
            changeAnimation("IDLE");
        }

        //animation handling
        currentFrame = currentAnimation.getKeyFrame(frameTime);
    }

    protected Animation<TextureRegion> loadAnimations(String texturePath, String animationType, Array<GridPoint2> points, float frameDuration, boolean loop) {
        //check if animationstate exists already
        if(animations.containsKey(animationType)) {
            System.out.println("EXISTS");
            return null;
        }


        Texture texture = new Texture(Gdx.files.internal(texturePath));
        TextureRegion[][] textureFrames = TextureRegion.split(texture, frameW, frameH);
        TextureRegion[] keyFrames = new TextureRegion[points.size];

        for(int i = 0; i < points.size; i++) {
            keyFrames[i] = textureFrames[points.get(i).y][points.get(i).x];
        }
        


        Animation<TextureRegion> animation = new Animation<>(frameDuration, keyFrames);
        if(loop) animation.setPlayMode(Animation.PlayMode.LOOP);
        else animation.setPlayMode(Animation.PlayMode.NORMAL);

        animations.put(animationType, animation);

        return animation;
    }

    protected void move(float x, float y, float delta) {
        position.add(x*delta, y*delta);
    }

    protected void changeAnimation(String animationState) {
        if(!animations.containsKey(animationState)) {
            System.out.println( animationState + " animation does not exist!");
            return;
        }

        //such die animation die wir haben wollen
        Animation<TextureRegion> c = animations.get(animationState);
        if(c.equals(currentAnimation)) return;
        frameTime = 0;
        currentAnimation = c;

        // System.out.println(animationState);
    }

    protected TextureRegion getCurrentFrame() { return currentFrame; }
    public Vector2 getPosition() { return position; }
    
    protected void drawCharacter(SpriteBatch batch) {
        batch.begin();
        batch.draw(getCurrentFrame(), flip ? position.x + frameW : position.x, position.y, flip ? -frameW : frameW, frameH);
        batch.end();
    }
}
