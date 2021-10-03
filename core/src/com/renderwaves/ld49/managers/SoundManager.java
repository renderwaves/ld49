package com.renderwaves.ld49.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.renderwaves.ld49.events.GameEvent;
import com.renderwaves.ld49.events.GameEventSystem;

public class SoundManager {

    //public static Sound testSound = Gdx.audio.newSound(Gdx.files.internal("sounds/"));
    public static Sound alarmSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/alarm_1.mp3"));
    public static Sound alarmSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/alarm_2.mp3"));
    public static Sound alarmSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/alarm_3.mp3"));

    public static Music idleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/idle_music.mp3"));
    public static Music panicMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/panic_music.mp3"));
    public static Music idleMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/menu_idle.mp3"));

    public static void disposeAllSounds() {
        //testSound.dispose();
    }

    private Sound centralAlarm = null;

    private Music gameMusic = null;
    private Music prevMusic;
    private Integer prevGameState = -1;

    public SoundManager() {
        gameMusic = idleMusic;
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.2f);
        gameMusic.play();
    }

    public void forceStop() {
        if (centralAlarm != null)
            centralAlarm.stop();
        centralAlarm = null;
        if (gameMusic != null)
            gameMusic.stop();
        gameMusic = null;

    }

    /*
     */
    private void forceAlarmStop() {
        if (centralAlarm != null)
            centralAlarm.stop();
        centralAlarm = null;

    }

    private void forceStopMusic() {
        if (gameMusic != null)
            gameMusic.stop();
        gameMusic = null;
    }

    private void setMusic(Music music, float volume) {
        if (gameMusic != null) return;
        gameMusic = music;
        gameMusic.setLooping(true);
        gameMusic.setVolume(volume);
        gameMusic.play();
    }

    private void setAlarm(Sound alarm, float volume) {
        if (centralAlarm != null) return;
        centralAlarm = alarm;
        centralAlarm.loop(volume);
    }

    /*
     */
    public void update(int gameState) {

        System.out.println(String.format("SoundManager.update(): gamestate: %d prevGameState: %d\n", gameState, prevGameState));

        if (prevGameState != gameState) {
            prevGameState = gameState;

            forceAlarmStop();
            forceStopMusic();

            switch (prevGameState) {
                case 0: setMusic(idleMusic, 0.3f); break;
                case 1: setAlarm(alarmSound1, 0.4f); break;
                case 2: setAlarm(alarmSound2, 0.3f); setMusic(panicMusic, 0.5f); break;
                default:
                {
                    if (prevGameState > 3) {
                        setAlarm(alarmSound3, 0.6f);
                        break;
                    }
                    break;
                }
            }
        } else {
            if (gameMusic == null)
                setMusic(idleMusic, 0.2f);
        }


    }

}
