package com.renderwaves.ld49.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.ui.StatusBar;

public class OxygenManager {
    public StatusBar statusBar;

    public OxygenManager() {
        this.statusBar = new StatusBar(new Vector2(Gdx.graphics.getWidth() - 200, 125), new Vector2(128, 64), GlobalShipVariables.oxygenLevel, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.oxygenIcon, new Vector2(2, 2));;
        this.statusBar.sprite.setScale(1);
        this.statusBar.sprite.setPosition(this.statusBar.sprite.getX() - 10, this.statusBar.sprite.getY() - 16);
    }

    public void renderSprite(SpriteBatch batch) {
        statusBar.renderSprite(batch);

        statusBar.status = GlobalShipVariables.oxygenLevel;
    }

    public void renderShape(ShapeRenderer sr) {
        statusBar.renderShape(sr);
    }
}
