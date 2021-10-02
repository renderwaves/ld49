package com.renderwaves.ld49.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TexturedEntity extends Entity {
    public Texture texture;
    public Sprite sprite;

    public TexturedEntity(Vector2 position, Vector2 scale, Texture texture) {
        super(position, scale);
        this.texture = texture;
        sprite = new Sprite(texture);
        sprite.setScale(scale.x, scale.y);
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        sprite.draw(spriteBatch);
    }

    @Override
    public void update() {
        super.update();
        sprite.setPosition(position.x, position.y);
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
        sprite.setScale(scale.x, scale.y);
    }
}
