package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;

public class NavigationEvent extends GameEvent {
    public NavigationEvent()  {
        this.eventName = "Navigation Failure Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.eventIcon = TextureManager.navigationicon;
        GlobalShipVariables.navigationHealth -= 0.5f;
        onStart();
    }

    @Override
    public void onStart() {
        this.progressI = 0;
        System.out.println(String.format("%s is Active!", this.info(), this.eventName));
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.eventName));
    }

    @Override
    public void onUpdate(float timer) {
        progressF = GlobalShipVariables.navigationHealth;

        if(GlobalShipVariables.navigationHealth >= 1.0f) {
            setComplete(true);
        }
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
