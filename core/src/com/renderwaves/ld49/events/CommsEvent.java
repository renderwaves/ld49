package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;

public class CommsEvent extends GameEvent {

    static final String TAG = "Communication Event";

    public CommsEvent()  {
        super(TAG, TextureManager.comms);
    }

    @Override
    public void onStart() {
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));
        GlobalShipVariables.communicationsHealth -= 1.0f;
        setProgress(GlobalShipVariables.communicationsHealth);
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.getName()));
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
        if (isComplete() == false) {

        }
    }
}

