package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.InputManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.tilemap.Tile;

public class PlayerEntity extends TexturedEntity {
    private float movementSpeed = 100.0f;
    private float sprint = 1.0f;
    private Vector2 velocity;

    public PlayerEntity(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.playerEntity);
        velocity = new Vector2(0, 0);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
    }

    @Override
    public void update() {
        super.update();
        if(Gdx.input.isKeyPressed(InputManager.Sprint.key1) || Gdx.input.isKeyPressed(InputManager.Sprint.key2)) {
            sprint = 2.0f;
        }

        if(Gdx.input.isKeyPressed(InputManager.MoveUp.key1) || Gdx.input.isKeyPressed(InputManager.MoveUp.key2)) {
            velocity.y = Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        else if(Gdx.input.isKeyPressed(InputManager.MoveDown.key1) || Gdx.input.isKeyPressed(InputManager.MoveDown.key2)) {
            velocity.y = -Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        if(Gdx.input.isKeyPressed(InputManager.MoveRight.key1) || Gdx.input.isKeyPressed(InputManager.MoveRight.key2)) {
            velocity.x = Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        else if(Gdx.input.isKeyPressed(InputManager.MoveLeft.key1) || Gdx.input.isKeyPressed(InputManager.MoveLeft.key2)) {
            velocity.x = -Gdx.graphics.getDeltaTime() * movementSpeed;
        }

        Vector2 playerPositionOnTilemap = TemplateScene.shipTilemap.globalPositionToTilemapPosition(position.x+8 + velocity.x, position.y-4 + velocity.y);
        int tileID = TemplateScene.shipTilemap.getTileByPosition((int)playerPositionOnTilemap.x, (int)playerPositionOnTilemap.y).tileID;
        if(tileID == Tile.Air.tileID|| tileID == Tile.WallTile.tileID) {
            velocity.x = 0;
            velocity.y = 0;
        }

        position.x += velocity.x * sprint;
        position.y += velocity.y * sprint;

        velocity.x = 0;
        velocity.y = 0;

        sprint = 1.0f;
    }
}
