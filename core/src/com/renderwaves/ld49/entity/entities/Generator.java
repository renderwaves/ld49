package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class Generator extends TexturedEntity {
    public Generator(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.reactorTexture);
        rectangle = new Rectangle(position.x - (texture.getWidth() * scale.x * 8) / 2, position.y - (texture.getHeight() * scale.y * 4) / 2, texture.getWidth() * scale.x * 8, texture.getHeight() * scale.y * 4);
    }

    @Override
    public void update() {

    }
}
