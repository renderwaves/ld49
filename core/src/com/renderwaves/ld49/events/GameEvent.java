package com.renderwaves.ld49.events;

import java.util.ArrayList;

/*
 */
public abstract class GameEvent {

    protected String eventName;
    protected Integer uniqueId;
    private boolean isComplete;

    protected Integer progressI;
    protected float progressF;

    public boolean isComplete() { return this.isComplete; }
    public void setComplete(boolean s) { this.isComplete = s; }

    public String getName() { return eventName; }

    public void update() {
        onUpdate();
        onRender();
    }

    public String info() {
        return String.format("uuid: %d event: %s", this.uniqueId, this.eventName);
    }

    /*
     */
    public abstract void onStart();
    public abstract void onEnd();
    public abstract void onUpdate();
    public abstract void onRender();

}
