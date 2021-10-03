package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.eventName));
    }

    @Override
    public void onUpdate(float timer) {

    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
