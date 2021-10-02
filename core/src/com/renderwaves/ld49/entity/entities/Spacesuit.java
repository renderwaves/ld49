package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class Spacesuit extends TexturedEntity {
    public boolean spacesuitTaken = false;

    public Spacesuit(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.spacesuitTexture);
        rectangle = new Rectangle(position.x - (texture.getWidth() * scale.x * 2) / 2, position.y - 16, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y * 2);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(spacesuitTaken) return;
        super.render(spriteBatch);
    }

    @Override
    public void update() {

    }
}
