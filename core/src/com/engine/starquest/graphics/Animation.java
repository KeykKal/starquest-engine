package com.engine.starquest.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Update ich glaube ich sollte mich mal dran setzten und alles hier neu machen 
//Animationen sind nicht beendet ich hab nur keine lust weiter dran zu arbeiten

/*
    Das ist der plan für die animationen
    ein Baum listen ding dessen namen ich immer noch nicht kenne
    wenn die animation fertig ist geht er zurck auf die Idle animation
    solange die condition nicht erfüllt ist
    oder sogar nur zu Idle animation wenn eine condition erfüllt ist

    Beispiel 1:
        nach der sprung animation soll er nur zurück auf die Idle Animation,
        wenn der spieler denn boden berührt.
    Beispiel 2:
        nach dem Angriff soll er erst die zweite Angriffs Animation wenn denn ein zweiter Angriff ausgeführt wird.


               IDLE
                *
             /  |  \
       JUMP *   *   * ATTACK
           / \  RUN  \
          *   *       * ATTACK2
        DASH   FALL


*/

//TODO: verschönern
// man kann ja für die animationen nur die frame nummern speichern und so die animation abspielen. rescoursen spaaren
public class Animation {
    //new
    // List<AnimationStadium> states;

    AnimationStadium currentState;
    AnimationStadium nextAnimation;

    float startTime;
    boolean flipX;
    
    //jeder frame in eigenen Texturen
    SplicedImage splicedImage;

    Texture animationTexture;
    com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;

    //wie lange ein die animation schon spielt (ist wichtig um die momentane animation zu kennen)
    float currentFrameTime;
    SpriteBatch batch;
    boolean looping;

    public Animation() {
        // states = new ArrayList<>();
        nextAnimation = null;
        batch = new SpriteBatch();
    }

    // public Animation(float frameDur, String texture, int rows, int columns, boolean loop) {
    //     this(frameDur, new SplicedImage(texture, rows, columns), loop);
    // }

    public void playAnimation(float x, float y, OrthographicCamera cam, Texture img) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        currentFrameTime += Gdx.graphics.getDeltaTime();
        float xd = currentState.animationImage.framesArray()[0].getRegionWidth() / 2.0f;
        float yd = currentState.animationImage.framesArray()[0].getRegionHeight()/ 2.0f;
        batch.draw(currentState.animation.getKeyFrame(currentFrameTime, currentState.loop), x-xd, y-yd);
        // if(!currentState.loop && isCurrentAnimationFinished()) {
        //     currentState = null;
        // }
        if(nextAnimation != null) {
            // if(currentState.animation.isAnimationFinished(currentFrameTime)) {
                currentState = nextAnimation;
                nextAnimation = null;
                currentFrameTime = 0f;
            // }
        }
        if(isCurrentAnimationFinished() && !currentState.loop) {
            while(currentState.parent != null) {
                returnToParent();
            }
        }

        // batch.draw(img, x-(img.getWidth()/2), y-(img.getHeight()/2));

        batch.end();
    }

    public void playAnimation(float x, float y) {
        batch.begin();

        currentFrameTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(currentFrameTime, looping), x, y);

        batch.end();
    }

    public AnimationStadium addAnimationState(String name, SplicedImage animationImage, float dur, boolean loop) {
        AnimationStadium ani = new AnimationStadium(name, animationImage, dur, loop);
        // states.add(ani);
        currentState = ani;
        
        // if(currentState == null)
        //     currentState = ani;
        return ani;
    }

    //flipp while changing ;)
    public void animationFlip() {
        // for (int i = 0; i < states.size(); i++) {
        //     states.get(i).animationImage.flipX();
        // }
        flipX = true;
    }

    //Wont work anymore
    // public void changeAnimationState(String name) {
    //     if(currentState.name.equals(name)) return;
    //     for(int i = 0; i < states.size(); i++) {
    //         if(states.get(i).name.equals(name)) {
    //             nextAnimation = states.get(i);
    //             return;
    //         }
    //     }
    // }

    public void dispose() {
        batch.dispose();
    }

    public boolean isAnimationPlaying() {
        return currentState != null;
    }

    public boolean isCurrentAnimationFinished() {
        return currentFrameTime%currentState.animation.getFrameDuration() == 0;
    }

    // public void addNewAnimationToFollow(String parent, String childName, SplicedImage img, float dur, boolean loop) {
    //     //make child animation
    //     AnimationStadium ani = new AnimationStadium(childName, img, dur, loop);
    //     //find parent
    //     for(int i = 0; i < states.size(); i++) {
    //         if(states.get(i).name.equals(parent)) {
    //             //add to parent
    //             states.get(i).addChildAnimation(ani);
    //             return;
    //         }
    //     }
    // }

    public AnimationStadium returnToParent() {
        if(currentState.parent != null) {
            currentState = currentState.parent;
            return currentState;
        }
        return null;
    }

    public AnimationStadium changeToChild(String childName) {
        for (int i = 0; i < currentState.children.size(); i++) {
            if(currentState.children.get(i).name.equals(childName)) {
                if(flipX) {
                    currentState.animationImage.flipX();
                    flipX = false;
                }

                currentState = currentState.children.get(i);
                return currentState;
            }
        }

        return null;
    }

    public class AnimationStadium {
        public String name;
        public SplicedImage animationImage;
    
        public com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;
        public boolean loop;

        public List<AnimationStadium> children;
        AnimationStadium parent;

        public AnimationStadium(String name, SplicedImage image, float frameDur, boolean loop) {
            children = new ArrayList<>();
            this.name = name;
            this.loop = loop;
            animationImage = image;
            animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDur, image.framesArray());
        }

        //add new "branches" of animations to this animation
        public AnimationStadium addChildAnimation(AnimationStadium child) {
            child.parent = this;
            children.add(child);
            return child;
        }
        public AnimationStadium addChildAnimation(String name, SplicedImage image, float frameDur, boolean loop) {
            AnimationStadium child = new AnimationStadium(name, image, frameDur, loop);
            child.parent = this;
            children.add(child);
            return child;
        }

    }
}
