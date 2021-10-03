package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.tilemap.Tilemap;

import java.util.Random;

public class DoorEvent extends GameEvent {

    static final String TAG = "Door Event";

    public DoorEvent()  {
        super(TAG, null);
    }

    private Tilemap.DoorInstance door;

    @Override
    public void onStart() {
        System.out.println(String.format("%s is Active!", this.info(), this.getName()));

        int numDoors = TemplateScene.shipTilemap.doorHandler.size();
        int doorToBlock = (int) ((Math.random() * numDoors));

        door = TemplateScene.shipTilemap.doorHandler.get(doorToBlock);
        door.block();

        //setProgress(1.0f);
    }

    @Override
    public void onEnd() {
        System.out.println(String.format("%s is Solved!", this.info(), this.getName()));
    }

    @Override
    public void onUpdate(float timer) {
        if(!door.blocked) setComplete(true);
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onRender(SpriteBatch batch) {

    }
}
