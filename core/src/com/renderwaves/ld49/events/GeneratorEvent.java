package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.renderwaves.ld49.other.GlobalShipVariables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;

/*
 */
public class GeneratorEvent extends GameEvent {

    private boolean wasBelow = false;
    private WarningLabel warningLabel;
    static final String TAG = "Generator Event";

    /*
    public GeneratorEvent()  {
        this.eventName = "Generator Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        eventIcon = TextureManager.dangerNuclear;
        onStart();
    }
     */

    public GeneratorEvent(float damage) {
        super(TAG, TextureManager.dangerNuclear, SoundManager.reactorFailureSound);
        GlobalShipVariables.generatorHealth -= damage;
    }

    @Override
    public void onStart() {
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING REACTOR HAS FAILED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);

        //GlobalShipVariables.generatorHealth -= 0.5f;
        setProgress(GlobalShipVariables.generatorHealth);
    }

    @Override
    public void onEnd() {
        if(getEventTook() > 0) {
            GlobalShipVariables.score += 300 / getEventTook();
        }
        TemplateScene.warningLabels.remove(warningLabel);
    }


    @Override
    public void onUpdate(float timer) {
        if (GlobalShipVariables.generatorHealth > 1.0f) GlobalShipVariables.generatorHealth = 1.0f;
        else if(GlobalShipVariables.generatorHealth < 0.0f) GlobalShipVariables.generatorHealth = 0.0f;

        setProgress(GlobalShipVariables.generatorHealth);

        if (GlobalShipVariables.generatorHealth < 0.0f) wasBelow = true;
        if (GlobalShipVariables.generatorHealth >= 1.0) setComplete(true);
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
