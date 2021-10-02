package com.renderwaves.ld49.managers;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    public static Texture img = new Texture("badlogic.jpg");
    public static Texture groundTile = new Texture("textures/tiles/groundTile.png");
    public static Texture wallTile = new Texture("textures/tiles/wallTile.png");
    public static Texture doorTile = new Texture("textures/tiles/doorTile.png");
    public static Texture componentTile = new Texture("textures/tiles/componentTile.png");
    public static Texture playerEntity = new Texture("textures/entities/player.png");
    public static Texture energyTexture = new Texture("textures/ui/energy.png");
    public static Texture shipIndicator = new Texture("textures/ui/ship_indicator.png");

    public static void disposeAllTextures() {
        img.dispose();
        groundTile.dispose();
        playerEntity.dispose();
        shipIndicator.dispose();
        componentTile.dispose();
        doorTile.dispose();
        wallTile.dispose();
    }
}
