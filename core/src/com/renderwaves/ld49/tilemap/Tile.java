package com.renderwaves.ld49.tilemap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.managers.TextureManager;

public enum Tile {
    Air(0, null, new Color(0.49803922f, 0.49803922f, 0.49803922f, 255)),
    GroundTile(1, TextureManager.groundTile, new Color(1, 1, 1, 1)),
    WallTile(2, TextureManager.wallTile, new Color(0,0,0,1)),
    DoorTile(3, TextureManager.doorTile, new Color(0,0.14901961f,1,1)),
    ReactorTile(4, TextureManager.componentTile, new Color(0.29803925f, 1, 0, 1)),
    LifeSupportTile(5, TextureManager.componentTile, new Color(1, 0.84705882f, 0, 1)),
    EngineTile(6, TextureManager.componentTile, new Color(1, 0, 0, 1)),
    NavTile(7, TextureManager.componentTile, new Color(0.69803922f, 0, 1, 1)),
    CommsTile(8, TextureManager.componentTile, new Color(0.49803922f, 0.41568627f, 0, 1)),
    MedicTile(9, TextureManager.componentTile, new Color(0.31764706f, 0.97647059f, 1, 1)),
    SuitTile(10, TextureManager.componentTile, new Color(0, 0.49803922f, 0, 1)),
    ;

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
