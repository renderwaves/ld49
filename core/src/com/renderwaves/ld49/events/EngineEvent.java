package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;
import jdk.nashorn.internal.objects.Global;

public class EngineEvent extends GameEvent {
    private WarningLabel warningLabel;

    public EngineEvent()  {
        this.eventName = "Engine Failure Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        GlobalShipVariables.engine1Health -= 0.5f;
        eventIcon = TextureManager.engine;
        onStart();
    }

    @Override
    public void onStart() {
        this.progressI = 0;
        System.out.println(String.format("%s is Active!", this.info(), this.eventName));
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING ENGINE HAS FAILED!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
        System.out.println(String.format("%s is Solved!", this.info(), this.eventName));
    }

    @Override
    public void onUpdate(float timer) {
        progressF = GlobalShipVariables.engine1Health;
        if(GlobalShipVariables.engine1Health >= 1.0f) setComplete(true);
    }

    @Override
    public void onRender() {
    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
