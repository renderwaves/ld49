package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.managers.TextureManager;

/*
 */
public class LifesupportEvent extends GameEvent {

    static final String TAG = "Lifesupport Event";

    public LifesupportEvent() {
        super(TAG, TextureManager.lifesupporticon, SoundManager.alarmSound3);
    }

    @Override
    public void onStart() {
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));
        GlobalShipVariables.lifeSupportHealth -= 0.5f;
        setProgress(GlobalShipVariables.lifeSupportHealth);
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.getName()));
    }

    @Override
    public void onUpdate(float timer) {
        if(GlobalShipVariables.lifeSupportHealth >= 1.0f) GlobalShipVariables.lifeSupportHealth = 1.0f;
        else if(GlobalShipVariables.lifeSupportHealth <= 0.0f) GlobalShipVariables.lifeSupportHealth = 0.0f;

        //progressF = GlobalShipVariables.lifeSupportHealth;
        setProgress(GlobalShipVariables.lifeSupportHealth);

        if(GlobalShipVariables.lifeSupportHealth >= 1.0f) setComplete(true);
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }

    @Override
    public void onSound() {
        if (sound != null) {
            if (isComplete()) {
                sound.stop();
                sound.dispose();
            }
        }
    }
}
