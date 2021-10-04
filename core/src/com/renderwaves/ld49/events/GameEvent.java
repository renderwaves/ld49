package com.renderwaves.ld49.events;

import com.badlogic.gdx.Gdx;
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


    protected Sound sound = null;
    protected long soundId = 0;

    // internal timer of how long it took to solve event
    private float internalTimer;
    private float timePeriod = 1f;
    private Integer eventTook = 0;

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

    public GameEvent(String name, Texture icon, Sound sound) {
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

        this.sound = sound;
        if (this.sound != null)
            this.sound.loop(0.3f);

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
        if (this.sound != null)
            this.sound.loop(0.3f);

        onStart();
    }

    protected void finalize() {
        onEnd(); // call user method

        // remove sound if present
        if (this.sound != null) {
            sound.stop();
            sound = null;
        }

        System.out.println(String.format("Event: %s is Solved!\n\t: Solve time took '%s' seconds\n", this.getName(), Integer.toString(this.eventTook)));
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
        if (isComplete() == false)
            onTimeSolve();
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

    private void onTimeSolve() {
        internalTimer += Gdx.graphics.getRawDeltaTime();
        if (internalTimer > timePeriod) {
            internalTimer -= timePeriod;
            // count score value
            eventTook += 1;
        }
    }

    /* user defined methods
     */
    public abstract void onStart();
    public abstract void onEnd();
    public abstract void onUpdate(float timer);
    public abstract void onRender();
    public abstract void onRender(SpriteBatch batch);
    public abstract void onSound();

    /* setters, getters
     */
    public void setProgress(float progress) { this.progress = progress; if (progressBar != null) this.progressBar.status = progress; }
    public float getProgress() { return (this.progress != progressBar.status) ? this.progress : this.progressBar.status; }
    public boolean isComplete() {
        if (this.isComplete == true) {
            if (this.sound != null)
                this.sound.stop();
        }
        return this.isComplete;
    }
    public void setComplete(boolean s) { this.isComplete = s; }
    public String getName() { return this.name; }
    public Texture getIcon() {return this.icon; }
    public Integer getEventTook() {
        return eventTook;
    }
}
