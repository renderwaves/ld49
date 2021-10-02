package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.ui.WarningLabel;

public class FireEvent extends GameEvent {

    private WarningLabel warningLabel;

    public FireEvent() {
        this.eventName = "Fire in Hull Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING FIRE HAS ERUPTED!", Color.WHITE, Color.RED, 2000);
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
    public void onUpdate() {

    }

    @Override
    public void onRender() {
        System.out.println("TEST");
    }

    @Override
    public void onRender(SpriteBatch batch) {
        warningLabel.render(batch);

    }
}
