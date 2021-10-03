package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;

/*
 */
public class LifesupportEvent extends GameEvent {

    public LifesupportEvent()  {
        this.eventName = "Lifesupport Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        eventIcon = TextureManager.lifesupporticon;
        onStart();
    }

    @Override
    public void onStart() {
        this.progressI = 0;
        System.out.println(String.format("%s is Active!", this.info(), this.eventName));
        GlobalShipVariables.lifeSupportHealth -= 0.5f;
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.eventName));
    }

    @Override
    public void onUpdate(float timer) {
        if(GlobalShipVariables.lifeSupportHealth >= 1.0f) GlobalShipVariables.lifeSupportHealth = 1.0f;
        else if(GlobalShipVariables.lifeSupportHealth <= 0.0f) GlobalShipVariables.lifeSupportHealth = 0.0f;

        progressF = GlobalShipVariables.lifeSupportHealth;

        if(GlobalShipVariables.lifeSupportHealth >= 1.0f) setComplete(true);
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
