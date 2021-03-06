package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.events.GeneratorFuelEvent;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.ui.StatusBar;

import static com.renderwaves.ld49.scenes.MenuScene.difficulty;

public class Generator extends TexturedEntity {
    private StatusBar statusBar = new StatusBar(new Vector2(Gdx.graphics.getWidth() - 200, 75), new Vector2(128, 64), GlobalShipVariables.generatorFuel, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.energyTexture, new Vector2(2, 2));

    public Rectangle fireRectangle;

    public Generator(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.reactorTexture);
        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x * 2, texture.getHeight() * scale.y);
        fireRectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    private boolean runningOutOfFuel = false;

    @Override
    public void update() {
        if(!GlobalShipVariables.tutorialMode) {
            if (difficulty == 1) {
                GlobalShipVariables.generatorFuel -= Gdx.graphics.getDeltaTime() / 100;
            } else if (difficulty == 2) {
                GlobalShipVariables.generatorFuel -= Gdx.graphics.getDeltaTime() / 75;
            } else if (difficulty == 3) {
                GlobalShipVariables.generatorFuel -= Gdx.graphics.getDeltaTime() / 50;
            } else if (difficulty == 4) {
                GlobalShipVariables.generatorFuel -= Gdx.graphics.getDeltaTime() / 75;
            }
        }

        if(GlobalShipVariables.generatorFuel < 0) {
            GlobalShipVariables.generatorFuel = 0.0f;
        }
        else if(GlobalShipVariables.generatorFuel > 1) {
            GlobalShipVariables.generatorFuel = 1.0f;
        }
        if(GlobalShipVariables.generatorFuel <= 0.2f && !runningOutOfFuel) {
            TemplateScene.getInstance().gameEventSystem.addEvent(new GeneratorFuelEvent());
            runningOutOfFuel = true;
        }
        else if (GlobalShipVariables.generatorFuel > 0.2f) {
            runningOutOfFuel = false;
        }
        statusBar.status = GlobalShipVariables.generatorFuel;

        for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
            if(fireRectangle.overlaps(TemplateScene.shipTilemap.fireHandler.get(i).rectangle)) {
                GlobalShipVariables.generatorHealth -= Gdx.graphics.getDeltaTime() / 100;
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        statusBar.renderSprite(spriteBatch);
    }

    public void renderShape(ShapeRenderer renderer) {
        statusBar.renderShape(renderer);
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}
