package com.renderwaves.ld49.events;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.ui.StatusBar;

import java.util.ArrayList;

/*
 */
public abstract class GameEvent {

    protected String name = null;
    protected Integer uniqueId = -9999;
    protected SpriteBatch parentBatch = null;
    private boolean isComplete = false;

    protected float progress = -9999.0f;
    private StatusBar progressBar = null;

    private Texture icon = null;
    private Integer uiPositionX = -9999;
    private Integer uiPositionY = -9999;


    private Sound sound = null;

    /*
     */
    public GameEvent() {
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        onStart();
    }

    public GameEvent(String name) {
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.name = name;
        onStart();
    }

    public GameEvent(String name, Texture icon) {
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.name = name;
        this.icon = icon;
        progressBar = new StatusBar(
                new Vector2(this.uiPositionX, this.uiPositionY),
                new Vector2(128, 64),
                1.0f,
                new Color(255, 255, 255, 255),
                new Color(255, 0, 0, 255),
                this.icon,
                new Vector2(2, 2)
        );
        onStart();
    }

    public GameEvent(String name, int x, int y, Texture icon) {
        this.name = name;
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.icon = icon;
        progressBar = new StatusBar(
                new Vector2(this.uiPositionX, this.uiPositionY),
                new Vector2(128, 64),
                1.0f,
                new Color(255, 255, 255, 255),
                new Color(255, 0, 0, 255),
                this.icon,
                new Vector2(2, 2)
        );
        onStart();
    }

    public GameEvent(String name, int x, int y, Texture icon, Sound sound) {
        this.name = name;
        this.uniqueId = (int)(Math.random() * Integer.MAX_VALUE);
        this.uiPositionX = x;
        this.uiPositionY = y;
        this.icon = icon;
        progressBar = new StatusBar(
                new Vector2(this.uiPositionX, this.uiPositionY),
                new Vector2(128, 64),
                1.0f,
                new Color(255, 255, 255, 255),
                new Color(255, 0, 0, 255),
                this.icon,
                new Vector2(2, 2)
        );
        this.sound = sound;
        onStart();
    }

    public String info() {
        return String.format("uuid: %d event: %s\n---------------------\n\tuiPositionX: %d\n\tuiPositionY: %d\n\ticon: %s\n\tprogressBar: %s\n\tsound: %s\n\n",
                this.uniqueId, this.name, this.uiPositionX, this.uiPositionX, this.icon, this.progressBar, this.sound);
    }

    public void overrideUiPosition(int x, int y) {
        this.uiPositionX = x;
        this.uiPositionY = y;
        if (progressBar != null) {
            progressBar.setPosition(x,y);
        }
    }

    /* internal update
     */
    public void update(float timer) {

        if (isComplete() == false && sound != null) {
            sound.play();
            sound.loop();
        } else if (sound != null) {
            sound.stop();
        }

        onUpdate(timer);
    }

    /* internal render
     */
    public void render() {
        onRender();
    }

    public void render(SpriteBatch batch) {
        if (progressBar != null) {
            progressBar.renderSprite(batch);
        }
        onRender(batch);
    }

    public void render(ShapeRenderer shape) {
        if (progressBar != null) {
            progressBar.renderShape(shape);
        }
    }

    /* user defined methods
     */
    public abstract void onStart();
    public abstract void onEnd();
    public abstract void onUpdate(float timer);
    public abstract void onRender();
    public abstract void onRender(SpriteBatch batch);

    /* setters, getters
     */
    public void setProgress(float progress) { this.progress = progress; if (progressBar != null) this.progressBar.status = progress; }
    public float getProgress() { return (this.progress != progressBar.status) ? this.progress : this.progressBar.status; }
    public boolean isComplete() { return this.isComplete; }
    public void setComplete(boolean s) { this.isComplete = s; }
    public String getName() { return this.name; }
    public Texture getIcon() {return this.icon; }
}
