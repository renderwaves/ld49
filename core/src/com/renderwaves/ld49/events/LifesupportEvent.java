package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;

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
        if(getEventTook() > 0) {
            GlobalShipVariables.score += 300 / getEventTook();
        }
        if(GlobalShipVariables.tutorialMode) {
            TemplateScene.tutorialStage++;
        }
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
