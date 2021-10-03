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
    private ArrayList<StatusBar> progress_sl;
    private ArrayList<ProgressManager> progress_manager_sl;
    private Skin skin;

    private final Integer offset_x = 10;
    private final Integer offset_y = 64;
    private Integer pos_x;
    private Integer pos_y;

    /*
     */
    public GameEventSystem() {
        events = new ArrayList<GameEvent>(0);
        progress_sl = new ArrayList<StatusBar>(0);

        pos_x = 10;
        pos_y = 10;
    }

    public void setUiSkin(Skin skin) {
        this.skin = skin;
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

        if (progress_sl.size() < 0) return;

        for (GameEvent event: events) {
            event.render(batch);
        }


        // render sliders
        for (int slider = 0; slider < progress_sl.size(); slider++) {
            progress_sl.get(slider).renderSprite(batch);

            // prasarna treba to upravit
            progress_sl.get(slider).status = events.get(slider).progressF;
        }
    }

    public void render(ShapeRenderer shape) {
        if (progress_sl.size() < 0) return;

        for (int slider = 0; slider < progress_sl.size(); slider++) {
            progress_sl.get(slider).renderShape(shape);
        }
    }

    public int numEvents() {
        return events.size();
    }

    public void addEvent(GameEvent event) {
        events.add(event);

        pos_x = offset_x;
        final StatusBar statusBar = new StatusBar(
                new Vector2(pos_x, pos_y),
                new Vector2(128, 64),
                1.0f,
                new Color(255, 255, 255, 255),
                new Color(255, 0, 0, 255),
                event.eventIcon,
                new Vector2(2, 2)
        );

        if(statusBar.sprite != null)
            pos_y += offset_y;

        progress_sl.add(statusBar);
    }

    public void removeEvent() {
        //for (GameEvent event : this.events) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).isComplete()) {
                events.get(i).onEnd();

                events.remove(i);
                if(progress_sl.get(i).sprite != null)
                    pos_y -= offset_y;
                progress_sl.remove(i);
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
