package com.renderwaves.ld49.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class StatusBar {
    public float status;
    public Color borderColor, statusColor;
    public Sprite sprite;
    public Vector2 position, statusBarSize, spriteScale;

    public StatusBar(Vector2 position, Vector2 statusBarSize, float status, Color borderColor, Color statusColor, Texture texture, Vector2 spriteScale) {
        this.status = status;
        this.borderColor = borderColor;
        this.statusColor = statusColor;
        this.position = position;
        this.statusBarSize = statusBarSize;
        this.spriteScale = spriteScale;
        this.sprite = new Sprite(texture);
        sprite.setPosition(position.x, position.y + sprite.getWidth() / 2);
        sprite.setScale(spriteScale.x, spriteScale.y);
    }

    public void renderShape(ShapeRenderer sr) {
        if(status < 0) status = 0;

        sr.setColor(borderColor);
        sr.rect(position.x + sprite.getWidth() * sprite.getScaleX(), position.y, 128, 32);

        sr.setColor(statusColor);
        sr.rect(position.x + 1 + sprite.getWidth() * sprite.getScaleX(), position.y+1, 126 * status, 30);
    }

    public void renderSprite(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
