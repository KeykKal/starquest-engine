package com.engine.starquest.audio;

import java.security.acl.LastOwnerException;

import com.badlogic.gdx.Gdx;

//hab mir hier ein beispiel genommen: 
//https://github.com/00-Evan/shattered-pixel-dungeon/blob/5f4b2560d4faddec793e0459ac73d87fa4f2bf12/SPD-classes/src/main/java/com/watabou/noosa/audio/Music.java
public class Music {
    private com.badlogic.gdx.audio.Music player;

    private boolean looping;
	
	private float volume = 1f;
    private String playing;

    //synchronized im falle das wir die music multithreadet abspielen wollen
    public synchronized void play(String track, boolean looping) {
        if(isPlaying() && playing != null && playing.equals(track)) return;
        if(track == null) return;

        stop();
        
        playing = track;
        this.looping = looping;

        try {
            player = Gdx.audio.newMusic(Gdx.files.internal(track));
            player.setLooping(this.looping = looping);
            player.setVolume(volume);
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
            player = null;
        }

    }

    //kann man eig auch mit der stop methode zusammen legen
    public synchronized void end() {
        playing = null;
        stop();
    }
    
    public synchronized void stop() {
        if(player != null) {
            player.dispose();
            player = null;
        }
    }
    
    public synchronized void pause() {
        if(player != null)
            player.pause();
    }

    public synchronized void resumes() {
        if(player != null) {
            player.play();
            player.setLooping(looping);
        }
    }

    public synchronized void volume(float volume) {
        if(player != null) 
            player.setVolume(this.volume = volume); //Klapt das?? (ja das tut es)
    }

    public synchronized void setLoop(boolean looping) { 
        this.looping = looping;
    }

    public synchronized boolean isPlaying() {
        return player != null && player.isPlaying();
    }


}
