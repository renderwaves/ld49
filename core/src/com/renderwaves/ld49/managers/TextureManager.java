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
    public static Texture blockedDoorTile = new Texture("textures/tiles/doorTileBlocked.png");
    public static Texture fireExtinguisher = new Texture("textures/entities/fire_extinguisher.png");
    public static Texture comms = new Texture("textures/entities/transmitter.png");
    public static Texture engine = new Texture("textures/entities/enginecontrollmodule.png");
    public static Texture engineWithThrusters = new Texture("textures/entities/enginewiththruster.png");
    public static Texture navigation = new Texture("textures/entities/navigation.png");
    public static Texture fireEvent = new Texture("textures/ui/fireEvent.png");
    public static Texture fireEvent1 = new Texture("textures/entities/fireEvent2.png");
    public static Texture fireEvent2 = new Texture("textures/entities/fireEvent3.png");
    public static Texture fireEvent3 = new Texture("textures/entities/fireEvent4.png");
    public static Texture dangerNuclear = new Texture("textures/ui/dangernuclear.png");
    public static Texture lifesupporticon = new Texture("textures/ui/lifesupporticon.png");
    public static Texture navigationicon = new Texture("textures/ui/navigationicon.png");
    public static Texture oxygenIcon = new Texture("textures/ui/oxygenIcon.png");
    public static Texture uraniumRod = new Texture("textures/entities/uraniumRod.png");
    public static Texture playerLeft = new Texture("textures/entities/player_left.png");
    public static Texture playerRight = new Texture("textures/entities/player_right.png");
    public static Texture playerBack = new Texture("textures/entities/player_back.png");

    public static Texture planetEarth = new Texture("textures/planet_earth.png");
    public static Texture planetEarthFict1 = new Texture("textures/planet_earth_fict_1.png");
    public static Texture planetEarthFict2 = new Texture("textures/planet_earth_fict_2.png");
    public static Texture planetFict1 = new Texture("textures/planet_fict_1.png");
    public static Texture planetJupiter = new Texture("textures/planet_jupiter.png");
    public static Texture planetMerkur = new Texture("textures/planet_merkur.png");
    public static Texture planetVenuse = new Texture("textures/planet_venuse.png");

    public static void disposeAllTextures() {
        img.dispose();
        planetEarth.dispose();
        planetEarthFict1.dispose();
        planetEarthFict2.dispose();
        fireEvent1.dispose();
        fireEvent2.dispose();
        fireEvent3.dispose();
        planetFict1.dispose();
        planetJupiter.dispose();
        planetMerkur.dispose();
        planetVenuse.dispose();
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
        blockedDoorTile.dispose();
        comms.dispose();
        engine.dispose();
        navigation.dispose();
        fireEvent.dispose();
        dangerNuclear.dispose();
        lifesupporticon.dispose();
        navigationicon.dispose();
        oxygenIcon.dispose();
        uraniumRod.dispose();
        playerLeft.dispose();
        playerRight.dispose();
        playerBack.dispose();
    }
}
