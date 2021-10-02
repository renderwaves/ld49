package com.renderwaves.ld49.events;

import java.util.ArrayList;

public class GameEventSystem {

    private ArrayList<GameEvent> events;

    /*
     */
    public GameEventSystem() {
        events = new ArrayList<GameEvent>(0);
    }

    /* deal with events
     */
    public void update() {
        if (events.size() <= 0) return;

        for (GameEvent event: events) {
            event.update();
        }
    }

    public void addEvent(GameEvent event) {
        events.add(event);
    }

    public void removeEvent() {
        for (GameEvent event : this.events) {
            if (event.isComplete()) events.remove(event);
        }
    }

    public GameEvent getEvent(int id) {
        if (id > events.size() || id < 0) return null;

        GameEvent event = events.get(id);
        if (event == null) return null;
        return event;
    }
}
