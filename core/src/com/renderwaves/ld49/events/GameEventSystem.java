package com.renderwaves.ld49.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.renderwaves.ld49.managers.ProgressManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.ui.StatusBar;

import java.awt.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/*
 */
public class GameEventSystem {

    private ArrayList<GameEvent> events;

    private final Integer offset_x = 10;
    private final Integer offset_y = 64;
    private Integer pos_x;
    private Integer pos_y;

    /*
     */
    public GameEventSystem() {
        events = new ArrayList<GameEvent>(0);
        pos_x = 10;
        pos_y = 10;
    }

    /* deal with events
     */
    public void update(float timer) {
        if (events.size() <= 0) return;

        for (GameEvent event: events) {
            event.update(timer);
        }

        // check for complete events
        removeEvent();
    }

    public void render(SpriteBatch batch) {
        for (GameEvent event: events) {
            event.render(batch);
        }
    }

    public void render(ShapeRenderer shape) {
        for (GameEvent event: events) {
            event.render(shape);
        }
    }

    public int numEvents() {
        return events.size();
    }

    public void addEvent(GameEvent event) {
        events.add(event);
        refreshUiPosition();
    }

    public void removeEvent() {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).isComplete()) {
                events.get(i).onEnd();
                events.remove(i);
            }
        }

        refreshUiPosition();
    }

    private void refreshUiPosition() {
        int __pos_x = pos_x;
        int __pos_y = pos_y;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getIcon() != null) {
                __pos_y += offset_y;
                events.get(i).overrideUiPosition(__pos_x, __pos_y);
            }
        }
    }

    public GameEvent getEvent(int id) {
        if (id > events.size() || id < 0) return null;
        GameEvent event = events.get(id);
        if (event == null) return null;
        return event;
    }

    public void completeAllEvents() {
        for(int i = 0; i < events.size(); i++) {
            events.get(i).setComplete(true);
        }
    }
}
