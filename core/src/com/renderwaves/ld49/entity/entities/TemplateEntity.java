package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class TemplateEntity extends TexturedEntity {
    public TemplateEntity(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.img);
    }

    @Override
    public void update() {
        super.update();
        followTarget(new Vector2(Gdx.input.getX(), Gdx.input.getY()), 10 * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
    }
}
