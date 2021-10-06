package com.renderwaves.ld49.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.managers.TextureManager;

public class Arrow {
    public Vector2 position;
    public Color color1;
    public Color color2;
    public Texture texture;

    public Arrow() {
        color1 = Color.WHITE;
        color2 = Color.RED;
        texture = TextureManager.arrow;
    }

    private float timer = 0;
    private boolean switchColors = false;
    public void render(SpriteBatch batch) {
        timer += Gdx.graphics.getDeltaTime();
        if(timer >= 1) {
            switchColors = !switchColors;
            timer = 0;
        }

        batch.setColor(switchColors ? color1 : color2);

        batch.draw(texture, position.x, Gdx.graphics.getHeight()-position.y, 32, 64);

        batch.setColor(Color.WHITE);
    }
}
