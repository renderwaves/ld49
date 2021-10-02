package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.renderwaves.ld49.GlobalShipVariables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 */
public class GeneratorEvent extends GameEvent {

    private boolean wasBelow = false;

    public GeneratorEvent()  {
        this.eventName = "Generator Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        onStart();
    }

    @Override
    public void onStart() {
        this.progressF = 0.0f;
        System.out.println(String.format("%s is Active!", this.info(), this.eventName));
    }

    @Override
    public void onEnd() {
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
