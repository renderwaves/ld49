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

    /*
     */
    public StatusBar(Vector2 position, Vector2 statusBarSize, float status, Color borderColor, Color statusColor, Texture texture, Vector2 spriteScale) {
        this.status = status;
        this.borderColor = borderColor;
        this.statusColor = statusColor;
        this.position = position;
        this.statusBarSize = statusBarSize;
        this.spriteScale = spriteScale;
        if(texture != null) {
            this.sprite = new Sprite(texture);
            sprite.setPosition(position.x, position.y + sprite.getWidth() / 2);
            sprite.setScale(spriteScale.x, spriteScale.y);
        }
    }

    /* setters / getters
     */
    public void setPosition(int x, int y) {
        Vector2 _pos = new Vector2();
        _pos.x = x;
        _pos.y = y;

        this.position = _pos;
        if (this.sprite != null) {
            sprite.setPosition(_pos.x, _pos.y + sprite.getWidth() / 2);
        }
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setSize(Vector2 size) {
        this.statusBarSize = size;
    }

    public Vector2 getSize() {
        return this.statusBarSize;
    }

    public void setColor(Color borderColor, Color statusColor) {
        this.borderColor = borderColor;
        this.statusColor = statusColor;
    }

    public void setSprite(Texture texture, Vector2 scale) {
        this.sprite = new Sprite(texture);
        sprite.setPosition(position.x, position.y + sprite.getWidth() / 2);
        sprite.setScale(scale.x, scale.y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void renderShape(ShapeRenderer sr) {
        if(sprite == null) return;
        if(status < 0) status = 0;
        else if(status > 1) status = 1;

        sr.setColor(borderColor);
        sr.rect(position.x + sprite.getWidth() * sprite.getScaleX(), position.y, 128, 32);

        sr.setColor(statusColor);
        sr.rect(position.x + 1 + sprite.getWidth() * sprite.getScaleX(), position.y+1, 126 * status, 30);
    }

    public void renderSprite(SpriteBatch batch) {
        if(sprite == null) return;
        sprite.draw(batch);
    }
}
