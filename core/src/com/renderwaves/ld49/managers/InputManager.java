package com.renderwaves.ld49.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public enum InputManager {
    MoveUp(Input.Keys.W, Input.Keys.UP, "Move Up"),
    MoveDown(Input.Keys.S, Input.Keys.DOWN, "Move Down"),
    MoveLeft(Input.Keys.A, Input.Keys.LEFT, "Move Left"),
    MoveRight(Input.Keys.D, Input.Keys.RIGHT, "Move Right"),

    Sprint(Input.Keys.SHIFT_LEFT, Input.Keys.R, "Sprint"),

    TakeSpacesuit(Input.Keys.E, -1, "Take Spacesuit"),

    TakeFireExtinguisher(Input.Keys.E, -1, "Take Fire Extinguisher");

    public int key1, key2;
    public String name;

    InputManager(int key1, int key2, String name) {
        this.key1 = key1;
        this.key2 = key2;
        this.name = name;
    }
}
