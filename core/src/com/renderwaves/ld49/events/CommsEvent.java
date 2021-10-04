package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;

public class CommsEvent extends GameEvent {

    static final String TAG = "Communication Event";

    public CommsEvent(float damage)  {
        super(TAG, TextureManager.comms, SoundManager.commsFailureSound);
        GlobalShipVariables.communicationsHealth -= damage;
    }

    @Override
    public void onStart() {
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));
        setProgress(GlobalShipVariables.communicationsHealth);
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
        setProgress(GlobalShipVariables.communicationsHealth);
        if(GlobalShipVariables.communicationsHealth >= 1.0f) {
            setComplete(true);
        }
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

