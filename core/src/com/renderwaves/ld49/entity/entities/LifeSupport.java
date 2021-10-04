package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;

public class LifeSupport extends TexturedEntity {
    private Rectangle fireRectangle;

    public LifeSupport(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.lifeSupportTexture);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y);
        fireRectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {
        GlobalShipVariables.oxygenLevel -= Gdx.graphics.getDeltaTime() / 50;
        GlobalShipVariables.oxygenLevel +=  Gdx.graphics.getDeltaTime() / 10 * GlobalShipVariables.lifeSupportHealth * GlobalShipVariables.generatorFuel * GlobalShipVariables.generatorHealth;

        for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
            if(fireRectangle.overlaps(TemplateScene.shipTilemap.fireHandler.get(i).rectangle)) {
                GlobalShipVariables.lifeSupportHealth -= Gdx.graphics.getDeltaTime() / 100;
            }
        }
    }
}
