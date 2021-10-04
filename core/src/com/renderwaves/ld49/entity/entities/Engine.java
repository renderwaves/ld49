package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;

public class Engine extends TexturedEntity {
    private Rectangle fireRectangle;

    public Engine(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.engineWithThrusters);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y);
        fireRectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {
        if(GlobalShipVariables.engineFailed == 1) {
            for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
                if(fireRectangle.overlaps(TemplateScene.shipTilemap.fireHandler.get(i).rectangle)) {
                    GlobalShipVariables.engine1Health -= Gdx.graphics.getDeltaTime() / 100;
                }
            }
        }

        if(GlobalShipVariables.engineFailed == 2) {
            for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
                if(fireRectangle.overlaps(TemplateScene.shipTilemap.fireHandler.get(i).rectangle)) {
                    GlobalShipVariables.engine2Health -= Gdx.graphics.getDeltaTime() / 100;
                }
            }
        }
    }
}