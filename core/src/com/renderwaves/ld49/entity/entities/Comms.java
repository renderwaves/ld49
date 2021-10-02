package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class Comms extends TexturedEntity {
    public Comms(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.comms);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {

    }
}