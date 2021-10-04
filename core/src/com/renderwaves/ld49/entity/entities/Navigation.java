package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;

public class Navigation extends TexturedEntity {
    private Rectangle fireRectangle;

    public Navigation(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.navigation);
        rectangle = new Rectangle(position.x + texture.getWidth()/2, position.y + texture.getHeight()/2, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
        fireRectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    @Override
    public void update() {
        for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
            if(fireRectangle.overlaps(TemplateScene.shipTilemap.fireHandler.get(i).rectangle)) {
                GlobalShipVariables.navigationHealth -= Gdx.graphics.getDeltaTime() / 100;
            }
        }
    }
}
