package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class Navigation extends TexturedEntity {
    public Navigation(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.navigation);
        rectangle = new Rectangle(position.x + texture.getWidth()/2, position.y + texture.getHeight()/2, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {

    }
}
