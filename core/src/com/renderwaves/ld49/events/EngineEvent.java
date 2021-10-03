package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;
import jdk.nashorn.internal.objects.Global;

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

    public EngineEvent() {
        super(TAG, TextureManager.engine);
    }

    @Override
    public void onStart() {
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));
        if(Math.random() > 0.5f){
            GlobalShipVariables.engineFailed = 1;
        } else {
            GlobalShipVariables.engineFailed = 2;
        }
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING ENGINE"  + GlobalShipVariables.engineFailed + " HAS FAILED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);

        if(GlobalShipVariables.engineFailed == 1){
            GlobalShipVariables.engine1Health -= 0.5f;
            setProgress(GlobalShipVariables.engine1Health);

        } else {
            GlobalShipVariables.engine2Health -= 0.5f;
            setProgress(GlobalShipVariables.engine2Health);

        }
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
        System.out.println(String.format("%s is Solved!", this.info(), this.getName()));
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
        if (isComplete() == false) {

        }
    }
}
