package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.Entity;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.MenuScene;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.tilemap.Tile;

public class Fire extends TexturedEntity {
    public float health;

    public Fire(Vector2 position) {
        super(position, new Vector2(2, 2), TextureManager.fireEvent);
        rectangle = new Rectangle(position.x, position.y, scale.x * sprite.getWidth(), scale.y * sprite.getHeight());
        health = 0.7f + MenuScene.difficulty;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
    }

    private float fireSpreadTimer = 0.0f;
    @Override
    public void update() {
        super.update();

        if(health <= 0.0f) {
            for(int i = 0; i < TemplateScene.getInstance().shipTilemap.fireHandler.size(); i++) {
                if(TemplateScene.getInstance().shipTilemap.fireHandler.get(i).equals(this)) {
                    System.out.println(health);
                    TemplateScene.getInstance().shipTilemap.fireHandler.remove(i);
                }
            }
        }

        fireSpreadTimer += Gdx.graphics.getDeltaTime();
        if(fireSpreadTimer >= 15.0f - MenuScene.difficulty) {
            Vector2 tilePos = TemplateScene.getInstance().shipTilemap.globalPositionToTilemapPosition(position.x+32, position.y);
            if(TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.GroundTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.EngineTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.NavTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.MedicTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.FireExtinguisherTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.ReactorTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.LifeSupportTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.CommsTile.tileID) {
                TemplateScene.getInstance().shipTilemap.fireHandler.add(new Fire(new Vector2(position.x+32, position.y)));
            }

            tilePos = TemplateScene.getInstance().shipTilemap.globalPositionToTilemapPosition(position.x-32, position.y);
            if(TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.GroundTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.EngineTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.NavTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.MedicTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.FireExtinguisherTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.ReactorTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.LifeSupportTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.CommsTile.tileID) {
                TemplateScene.getInstance().shipTilemap.fireHandler.add(new Fire(new Vector2(position.x-32, position.y)));
            }

            tilePos = TemplateScene.getInstance().shipTilemap.globalPositionToTilemapPosition(position.x, position.y+32);
            if(TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.GroundTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.EngineTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.NavTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.MedicTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.FireExtinguisherTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.ReactorTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.LifeSupportTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.CommsTile.tileID) {
                TemplateScene.getInstance().shipTilemap.fireHandler.add(new Fire(new Vector2(position.x, position.y+32)));
            }

            tilePos = TemplateScene.getInstance().shipTilemap.globalPositionToTilemapPosition(position.x, position.y-32);
            if(TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.GroundTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.EngineTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.NavTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.MedicTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.FireExtinguisherTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.ReactorTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.LifeSupportTile.tileID || TemplateScene.getInstance().shipTilemap.getTileByPosition((int)tilePos.x, (int)tilePos.y).tileID == Tile.CommsTile.tileID) {
                TemplateScene.getInstance().shipTilemap.fireHandler.add(new Fire(new Vector2(position.x, position.y-32)));
            }
            fireSpreadTimer = 0;
        }

        GlobalShipVariables.shipHealth -= Gdx.graphics.getDeltaTime() / 1000;
        GlobalShipVariables.oxygenLevel -= Gdx.graphics.getDeltaTime() / 100;
    }
}
