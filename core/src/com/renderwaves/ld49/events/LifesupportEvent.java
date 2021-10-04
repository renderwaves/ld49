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

    public LifesupportEvent(float damage) {
        super(TAG, TextureManager.lifesupporticon, SoundManager.alarmSound3);
        GlobalShipVariables.lifeSupportHealth -= damage;
    }

    @Override
    public void onStart() {
        setProgress(GlobalShipVariables.lifeSupportHealth);
    }

    @Override
    public void onEnd() {

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

    }
}
