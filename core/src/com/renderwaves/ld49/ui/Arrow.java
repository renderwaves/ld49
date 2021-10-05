package com.renderwaves.ld49.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.managers.TextureManager;

public class Arrow {
    public Vector2 position;
    public Color color;
    public Texture texture;

    public Arrow() {
        color = Color.WHITE;
        texture = TextureManager.arrow;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        batch.draw(texture, position.x, Gdx.graphics.getHeight()-position.y, 16, 32);
    }
}
