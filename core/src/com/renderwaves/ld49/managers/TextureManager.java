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
    public static Texture generatorTexture = new Texture("textures/entities/generator.png");
    public static Texture reactorTexture = new Texture("textures/entities/reactor.png");
    public static Texture spacesuitTexture = new Texture("textures/entities/spacesuit.png");
    public static Texture shipIndicator = new Texture("textures/ui/ship_indicator.png");

    public static void disposeAllTextures() {
        img.dispose();
        groundTile.dispose();
        playerEntity.dispose();
        energyTexture.dispose();
        generatorTexture.dispose();
        reactorTexture.dispose();
        spacesuitTexture.dispose();
        shipIndicator.dispose();
        componentTile.dispose();
        doorTile.dispose();
        wallTile.dispose();
    }
}
