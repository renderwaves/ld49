package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.tilemap.Tilemap;

public class DoorEvent extends GameEvent {
    public static int numberOfDoorEvents = 0;

    static final String TAG = "Door Event";

    public DoorEvent()  {
        super(TAG, null);
    }

    private Tilemap.DoorInstance door;

    @Override
    public void onStart() {
        int numDoors = TemplateScene.shipTilemap.doorHandler.size();
        int doorToBlock = (int) ((Math.random() * numDoors));

        door = TemplateScene.shipTilemap.doorHandler.get(doorToBlock);
        door.block();

        numberOfDoorEvents++;

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

    @Override
    public void onSound() {

    }
}
