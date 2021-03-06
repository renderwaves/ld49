package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;

public class EngineEvent extends GameEvent {
    private WarningLabel warningLabel;

    static final String TAG = "Engine Failure Event";

    /*
    public EngineEvent()  {
        this.eventName = "Engine Failure Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        GlobalShipVariables.engine1Health -= 0.5f;
        eventIcon = TextureManager.engine;
        onStart();
    }
     */

    private float damage;

    public EngineEvent(float damage) {
        super(TAG, TextureManager.engine, SoundManager.engineFailureSound);
        this.damage = damage;
    }

    @Override
    public void onStart() {
        if(Math.random() > 0.5f){
            GlobalShipVariables.engineFailed = 1;
        } else {
            GlobalShipVariables.engineFailed = 2;
        }
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING ENGINE "  + GlobalShipVariables.engineFailed + " HAS FAILED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);

        if(GlobalShipVariables.engineFailed == 1) {
            GlobalShipVariables.engine1Health -= damage;
        }
        else if(GlobalShipVariables.engineFailed == 2) {
            GlobalShipVariables.engine2Health -= damage;
        }
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
        if(getEventTook() > 0) {
            GlobalShipVariables.score += 300 / getEventTook();
        }

        if(GlobalShipVariables.tutorialMode) {
            TemplateScene.tutorialStage++;
        }
    }

    @Override
    public void onUpdate(float timer) {
        //progressF = GlobalShipVariables.engine1Health;
        if(GlobalShipVariables.engineFailed == 1) {
            setProgress(GlobalShipVariables.engine1Health);
        }
        if(GlobalShipVariables.engineFailed == 2) {
            setProgress(GlobalShipVariables.engine2Health);
        }
        if(GlobalShipVariables.engineFailed == 1 && GlobalShipVariables.engine1Health >= 1.0f) {
            setComplete(true);
        }
        if(GlobalShipVariables.engineFailed == 2 && GlobalShipVariables.engine2Health >= 1.0f) {
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
