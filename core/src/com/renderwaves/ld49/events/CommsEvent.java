package com.renderwaves.ld49.events;

public class CommsEvent extends GameEvent {

    public CommsEvent()  {
        this.eventName = "Comms Event";
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
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

    }
}

