package com.engine.starquest.mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.engine.starquest.Utils.Assets;

//die erbungs hirachie wird später noch verändert 
//aber fürs erste bin ich zu faul.
//Input sollte wo anders geregelt sein aber ich bin immernoch faul
//ok fuck die jump mechanik ich hab kein bock mehr drauf
//ich hab jetzt solange am jump gesessen, dass ich zum entschluss kahm das game topdown zu machen :)
public class Player extends Entity {

    private boolean grounded;
    private Vector2 vel;


    public Player(String name) {
        super(name);
        // load animations
        Array<GridPoint2> points = new Array<>();
        vel = new Vector2();

        // Idle positionen
        int x = 0, y = 0;
        for (int i = 0; i < 6; i++) {
            points.add(new GridPoint2(x, y));
            x += 1;
        }
        loadAnimations(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, "IDLE", points, 0.125f, true);

        // Walking animation
        points.clear();
        x = 0;
        y = 1;
        for (int i = 0; i < 8; i++) {
            points.add(new GridPoint2(x, y));
            x += 1;
            y = x < 6 ? y : y + 1;
            x %= 6;
        }
        loadAnimations(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, "RUN", points, 0.1f, true);
        
        // points.clear();
        // x = 1;
        // y = 6;
        // for (int i = 0; i < 8; i++) {
        //     points.add(new GridPoint2(x, y));
        //     x += 1;
        //     y = x < 6 ? y : y + 1;
        //     x %= 6;
        // }
        // loadAnimations(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, "JUMP", points, 1f);
        
        points.clear();
        x = 1;
        y = 7;
        points.add(new GridPoint2(4, 6));
        points.add(new GridPoint2(5, 6));
        points.add(new GridPoint2(0, 7));
        points.add(new GridPoint2(1, 7));
        points.add(new GridPoint2(2, 7));

        // for (int i = 0; i < 9; i++) {
            // points.add(new GridPoint2(x, y));
            // x += 1;
            // y = x < 6 ? y : y + 1;
            // x %= 6;
        // }
        loadAnimations(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, "JUMP", points, .1f, false);

        points.clear();
        x = 1;
        y = 7;
        points.add(new GridPoint2(3, 7));
        points.add(new GridPoint2(4, 7));
        points.add(new GridPoint2(5, 7));
        points.add(new GridPoint2(0, 8));
        loadAnimations(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, "FALL", points, .01f, false);
    }

    float speed = 100;
    float gravityConst = 9.81f;
    float gravitypull;
    boolean jump;

    @Override
    public void update(SpriteBatch batch, float delta) {
        updateAnimation(delta);

        Vector2 dir = new Vector2();
        String animS = "";

        if(!jump) {
            if (position.y > 0) {
                grounded = false;
                gravitypull += gravityConst*.068f;
                // position.y -= gravitypull;
                vel.y -= gravitypull;
            } else {
                grounded = true;
                gravitypull = 0;
            }
        }

        if(position.y < -0.1) position.y = 0;

        // Movement
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(grounded) {
                // dir.add(0, 1);
                System.out.println();
                vel.add(0, 25);
                jump = true;
                animS = "JUMP";
            }
        } else jump = false; 
        // if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        //     dir.add(0, -1);
        // }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dir.add(-1, 0);
            flip = true;
            animS = "RUN";
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dir.add(1, 0);
            flip = false;
            animS = "RUN";
        }

        if (dir.isZero() || animS.isEmpty()) {
            changeAnimation("IDLE");
        } else {
            dir.nor();
            moveAnim(dir.x, dir.y, delta * speed, animS);
        }

        calculateVelocity(delta, animS);
        // draw
        drawCharacter(batch);
    }

    //das klappt nicht so gut versuch ist etwas her
    private void calculateVelocity(float delta, String animS) {
        // if(animS.length() == 0) return;

        // if(vel.x < 0.01) vel.x = 0;
        // if(vel.y < 0.01) vel.y = 0;
        // if(vel.isZero()) return;
        // System.out.println(vel);
        
        move(vel.x == 0 ? 0 : vel.x/10, vel.y == 0 ? 0 : vel.y/10, delta*speed);
        vel.x *= 0.01f;
        vel.y *= 0.01f;
    }

    
    protected void moveAnim(float x, float y, float delta, String animS) {
        changeAnimation(animS);
        super.move(x, y, delta);
    }

}

//der code hier unten ist der alte damit ich nicht alles verliere
/*
 * 
 * // statt nur eine animation zu haben könnte man ein array haben
 * // oder man könnte alle animationen in einer Texture haben und
 * // sie durch die reihen wechseln (bsp.: reihe 1 ist die idle animation und
 * reihe
 * // 2 die lauf animation)
 * Animation playerAnim;
 * 
 * SplicedImage playerSplice;
 * int richtung = 1;
 * float speed = 100;
 * OrthographicCamera cam;
 * 
 * public Player(OrthographicCamera cam) {
 * playerAnim = new Animation();
 * 
 * // playerSplice = new SplicedImage(Assets.Sprites.TEMP_PLAYER_IDLE, 1, 8);
 * playerSplice = new SplicedImage(Assets.Sprites.TEMP_OTHER_PLAYER_SHEET, 17,
 * 6);
 * position = new Vector2();
 * 
 * 
 * Animation.AnimationStadium idle = playerAnim.addAnimationState("IDLE",
 * playerSplice.subPart(0, 6),
 * 0.125f,
 * true
 * );
 * 
 * idle.addChildAnimation("RUN",
 * playerSplice.subPart(7, 14),
 * 0.1f,
 * true
 * );
 * 
 * 
 * idle.addChildAnimation("ATTACK1",
 * playerSplice.subPart(15, 23),
 * 0.13f,
 * false
 * )
 * .addChildAnimation("ATTACK2",
 * playerSplice.subPart(24, 26),
 * 0.130f,
 * false
 * );
 * 
 * //jump und fall muss später getrennt werden
 * idle.addChildAnimation("JUMP",
 * playerSplice.subPart(38, 49),
 * 0.130f,
 * false
 * );
 * 
 * this.cam = cam;
 * 
 * }
 * 
 * float g = 9.81f; // m/s
 * float timeInAir = 0f;
 * boolean jump;
 * 
 * public void update() {
 * if (position.y > 0.001f) {
 * timeInAir += Gdx.graphics.getDeltaTime();
 * position.y = position.y - g * (timeInAir * timeInAir) / 2;
 * } else {
 * timeInAir = 0f;
 * }
 * 
 * if (position.y < 0f) {
 * position.y-=position.y;
 * }
 * move();
 * 
 * }
 * 
 * int step = 0;
 * 
 * public void render() {
 * playerAnim.playAnimation(position.x, position.y, cam,
 * playerSplice.spriteSheet);
 * // System.out.println(step);
 * }
 * 
 * public void move() {
 * // position.set(cam.position.x, cam.position.y);
 * 
 * if(!playerAnim.isAnimationPlaying()) {
 * playerAnim.changeToChild("IDLE");
 * }
 * 
 * Vector2 dir = new Vector2();
 * if (Gdx.input.isKeyPressed(Input.Keys.A)) {
 * dir.x--;
 * playerAnim.changeToChild("RUN");
 * 
 * // eine nicht so gute methode das zu machen aber es klapt und ich bin faul
 * if (richtung != -1) {
 * playerAnim.animationFlip();
 * richtung = -1;
 * }
 * } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
 * dir.x++;
 * playerAnim.changeToChild("RUN");
 * 
 * if (richtung != 1) {
 * playerAnim.animationFlip();
 * richtung = 1;
 * }
 * }
 * 
 * // Jump
 * if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
 * jump = true;
 * playerAnim.changeToChild("JUMP");
 * }
 * else if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
 * jump = false;
 * }
 * 
 * if(jump) {
 * dir.y += 2;
 * }
 * 
 * // if (Gdx.input.isKeyPressed(Input.Keys.W)) {
 * // dir.y++;
 * // } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
 * // dir.y--;
 * // }
 * 
 * // if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
 * 
 * // }
 * 
 * dir.x *= speed * Gdx.graphics.getDeltaTime();
 * dir.y *= speed * Gdx.graphics.getDeltaTime();
 * 
 * //WIE IN ALLES IN DER WELT KLAPPT DAS???
 * //also ich beschwere mich nicht
 * if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
 * playerAnim.changeToChild("ATTACK1");
 * playerAnim.changeToChild("ATTACK2");
 * }
 * 
 * //Add no animation playing function for this
 * 
 * 
 * position.add(dir);
 * cam.position.set(position, 0);
 * // cam.position.add(new Vector3(dir, 0));
 * // System.out.println();
 * 
 * }
 * 
 * public Vector2 getPosition() {
 * return position;
 * }
 * 
 * public void setPosition(float x, float y) {
 * position.x = x;
 * position.y = y;
 * }
 * 
 * public void dispose() {
 * playerAnim.dispose();
 * }
 * 
 * }
 */