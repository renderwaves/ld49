package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class LifeSupport extends TexturedEntity {
    public LifeSupport(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.lifeSupportTexture);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {
        GlobalShipVariables.oxygenLevel +=  Gdx.graphics.getDeltaTime() / 10 * GlobalShipVariables.lifeSupportHealth * GlobalShipVariables.generatorFuel * GlobalShipVariables.generatorHealth;
    }
}
