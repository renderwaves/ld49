package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;

public class NavigationEvent extends GameEvent {

    static final String TAG = "Navigation Event";
    /*
    public NavigationEvent()  {
        this.eventName = "Navigation Failure Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.eventIcon = TextureManager.navigationicon;
        GlobalShipVariables.navigationHealth -= 0.5f;
        onStart();
    }
     */
    public NavigationEvent(float damage) {
        super(TAG, TextureManager.navigationicon);
        GlobalShipVariables.navigationHealth -= damage;
    }

    @Override
    public void onStart() {
        setProgress(GlobalShipVariables.navigationHealth);
    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onUpdate(float timer) {
        //progressF = GlobalShipVariables.navigationHealth;

        setProgress(GlobalShipVariables.navigationHealth);

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

    @Override
    public void onSound() {

    }
}
