package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class Uranium extends TexturedEntity {
    public Uranium(Vector2 position) {
        super(position, new Vector2(2, 2), TextureManager.uraniumRod);
        rectangle = new Rectangle(position.x, position.y, scale.x * texture.getWidth(), scale.y * texture.getHeight());
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
    }

    @Override
    public void update() {
        super.update();
    }
}
