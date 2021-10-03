package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.renderwaves.ld49.GlobalShipVariables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;

/*
 */
public class GeneratorEvent extends GameEvent {

    private boolean wasBelow = false;
    private WarningLabel warningLabel;

    public GeneratorEvent()  {
        this.eventName = "Generator Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        eventIcon = TextureManager.dangerNuclear;
        onStart();
    }

    @Override
    public void onStart() {
        this.progressF = 0.0f;
        System.out.println(String.format("%s is Active!", this.info(), this.eventName));
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING REACTOR HAS FAILED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
        System.out.println(String.format("%s is Solved!", this.info(), this.eventName));
    }


    @Override
    public void onUpdate(float timer) {
        if (GlobalShipVariables.shipHealth > 1.0f) GlobalShipVariables.shipHealth = 1.0f;
        else if(GlobalShipVariables.shipHealth < 0.0f) GlobalShipVariables.shipHealth = 0.0f;

        GlobalShipVariables.shipHealth -= Gdx.graphics.getDeltaTime() / 5;
        progressF = GlobalShipVariables.shipHealth;

        if (progressF < 0.0f) wasBelow = true;
        if (wasBelow == true && progressF >= 0.90f) setComplete(true);
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
