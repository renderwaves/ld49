package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;

public class FireEvent extends GameEvent {
    private WarningLabel warningLabel;

    static final String TAG = "Fire Event";

    /*
    public FireEvent() {
        this.eventName = "Fire in Hull Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        eventIcon = TextureManager.fireEvent;
        onStart();
    }
     */

    public FireEvent() {
        super(TAG, TextureManager.fireEvent);
    }

    @Override
    public void onStart() {
        setProgress(0.0f);
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING FIRE HAS ERUPTED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
        TemplateScene.fireEvent = false;
        System.out.println(String.format("%s is Solved!", this.info(), this.getName()));
    }

    @Override
    public void onUpdate(float timer) {

    }

    @Override
    public void onRender() {
        System.out.println("TEST");
    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
