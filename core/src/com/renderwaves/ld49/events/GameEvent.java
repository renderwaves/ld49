package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/*
 */
public abstract class GameEvent {

    protected String eventName;
    protected Integer uniqueId;
    protected SpriteBatch parentBatch;
    private boolean isComplete;

    protected Integer progressI;
    protected float progressF;

    public Texture eventIcon;

    public boolean isComplete() { return this.isComplete; }
    public void setComplete(boolean s) { this.isComplete = s; }

    public String getName() { return eventName; }

    public void update(float timer) {
        onUpdate(timer);
    }

    public void render() {
        onRender();
    }

    public void render(SpriteBatch batch) {
        onRender(batch);
    }

    public String info() {
        return String.format("uuid: %d event: %s", this.uniqueId, this.eventName);
    }

    /*
     */
    public abstract void onStart();
    public abstract void onEnd();
    public abstract void onUpdate(float timer);
    public abstract void onRender();
    public abstract void onRender(SpriteBatch batch);

}
