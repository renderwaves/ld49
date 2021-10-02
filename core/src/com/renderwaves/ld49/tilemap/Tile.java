package com.renderwaves.ld49.tilemap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.TextureManager;

public enum Tile {
    Air(0, null, new Color(0, 0, 0, 255)),
    GroundTile(1, TextureManager.groundTile, new Color(255, 255, 255, 255)),;

    public int tileID;
    public Texture texture;
    public Sprite sprite;
    public Color color;

    Tile(int tileID, Texture texture, Color color) {
        this.tileID = tileID;
        this.texture = texture;
        this.color = color;
        if(texture != null) {
            this.sprite = new Sprite(texture);
            sprite.setSize(32, 32);
        }
    }

    public void render(SpriteBatch batch, float x, float y) {
        if(sprite == null) return;

        sprite.setPosition(x, y);
        sprite.draw(batch);
    }
}
