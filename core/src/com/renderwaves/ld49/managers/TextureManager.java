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
    public static Texture lifeSupportTexture = new Texture("textures/entities/life_support.png");
    public static Texture medBay0Texture = new Texture("textures/entities/medbay_0.png");
    public static Texture medBay1Texture = new Texture("textures/entities/medbay_1.png");
    public static Texture reactorTexture = new Texture("textures/entities/reactor.png");
    public static Texture spacesuitTexture = new Texture("textures/entities/spacesuit.png");
    public static Texture shipIndicator = new Texture("textures/ui/ship_indicator.png");
    public static Texture closedDoorTile = new Texture("textures/tiles/doorTileClosed.png");
    public static Texture fireExtinguisher = new Texture("textures/entities/fire_extinguisher.png");
    public static Texture comms = new Texture("textures/entities/transmitter.png");
    public static Texture engine = new Texture("textures/entities/enginecontrollmodule.png");
    public static Texture engineWithThrusters = new Texture("textures/entities/enginewiththruster.png");
    public static Texture navigation = new Texture("textures/entities/navigation.png");
    public static Texture fireEvent = new Texture("textures/ui/fireEvent.png");
    public static Texture dangerNuclear = new Texture("textures/ui/dangernuclear.png");
    public static Texture lifesupporticon = new Texture("textures/ui/lifesupporticon.png");
    public static Texture navigationicon = new Texture("textures/ui/navigationicon.png");
    public static Texture oxygenIcon = new Texture("textures/ui/oxygenIcon.png");
    public static Texture uraniumRod = new Texture("textures/entities/uraniumRod.png");

    public static void disposeAllTextures() {
        img.dispose();
        groundTile.dispose();
        playerEntity.dispose();
        energyTexture.dispose();
        generatorTexture.dispose();
        reactorTexture.dispose();
        medBay0Texture.dispose();
        medBay1Texture.dispose();
        lifeSupportTexture.dispose();
        spacesuitTexture.dispose();
        shipIndicator.dispose();
        componentTile.dispose();
        doorTile.dispose();
        wallTile.dispose();
        fireExtinguisher.dispose();
        closedDoorTile.dispose();
        comms.dispose();
        engine.dispose();
        navigation.dispose();
        fireEvent.dispose();
        dangerNuclear.dispose();
        lifesupporticon.dispose();
        navigationicon.dispose();
        oxygenIcon.dispose();
        uraniumRod.dispose();
    }
}
