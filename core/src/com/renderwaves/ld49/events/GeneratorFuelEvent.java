package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.WarningLabel;

public class GeneratorFuelEvent extends GameEvent {
    private WarningLabel warningLabel;

    static final String TAG = "Generator Fuel Event";

    /*
    public GeneratorFuelEvent()  {
        this.eventName = "Generator Fuel Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        onStart();
    }
     */
    public GeneratorFuelEvent() {
        super(TAG, TextureManager.energyTexture);
    }

    @Override
    public void onStart() {
        warningLabel = new WarningLabel(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight() - 30, "WARNING REACTOR IS RUNNING OUT OF FUEL!", Color.WHITE, Color.RED, 1);
        TemplateScene.warningLabels.add(warningLabel);

        setProgress(GlobalShipVariables.generatorFuel);
    }

    @Override
    public void onEnd() {
        TemplateScene.warningLabels.remove(warningLabel);
    }

    @Override
    public void onUpdate(float timer) {
        setProgress(GlobalShipVariables.generatorFuel);
        if(GlobalShipVariables.generatorFuel > 0.2f) {
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
