package com.renderwaves.ld49.tilemap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.managers.TextureManager;

public enum Tile {
    Air(0, null, new Color(0.49803922f, 0.49803922f, 0.49803922f, 255), true),
    GroundTile(1, TextureManager.groundTile, new Color(1, 1, 1, 1), false),
    WallTile(2, TextureManager.wallTile, new Color(0,0,0,1), true),
    DoorTile(3, TextureManager.closedDoorTile, new Color(0,0.14901961f,1,1), true),
    DoorTileOpened(3, TextureManager.doorTile, new Color(0,0.14901961f,1,1), false),
    ReactorTile(4, TextureManager.groundTile, new Color(0.29803925f, 1, 0, 1), false),
    LifeSupportTile(5, TextureManager.componentTile, new Color(1, 0.84705882f, 0, 1), false),
    EngineTile(6, TextureManager.componentTile, new Color(1, 0, 0, 1), false),
    NavTile(7, TextureManager.componentTile, new Color(0.69803922f, 0, 1, 1), false),
    CommsTile(8, TextureManager.componentTile, new Color(0.49803922f, 0.41568627f, 0, 1), false),
    MedicTile(9, TextureManager.componentTile, new Color(0.31764706f, 0.97647059f, 1, 1), false),
    SuitTile(10, TextureManager.componentTile, new Color(0, 0.49803922f, 0, 1), false),
    FireExtinguisherTile(11, TextureManager.groundTile, new Color(1,0,1,1), false),
    PlayerTile(12, TextureManager.groundTile, new Color(0.49803922f,0,0,1), false),
    ;

    public int tileID;
    public Texture texture;
    public Sprite sprite;
    public Color color;
    public boolean collidable;
    public float x = 0,y = 0;

    Tile(int tileID, Texture texture, Color color, boolean collidable) {
        this.tileID = tileID;
        this.texture = texture;
        this.color = color;
        this.collidable = collidable;
        if(texture != null) {
            this.sprite = new Sprite(texture);
            sprite.setSize(32, 32);
        }
    }

    public void render(SpriteBatch batch, float x, float y) {
        if(sprite == null) return;
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }
}
