package com.renderwaves.ld49.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.entity.EntityManager;
import com.renderwaves.ld49.entity.entities.*;
import com.renderwaves.ld49.scenes.TemplateScene;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.renderwaves.ld49.managers.TextureManager.doorTile;

public class Tilemap {
    public static final int TILE_SIZE = 32;

    public Vector2 offset = new Vector2(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6);

    private int width, height;
    public Tile map[];
    private boolean entitiesGenerated = false;

    public class DoorInstance {
        public float timer;
        public int x, y;
        public boolean closed;

        public DoorInstance(float timer, int x, int y, boolean closed) {
            this.timer = timer;
            this.x = x;
            this.y = y;
            this.closed = closed;
        }
    }

    public ArrayList<DoorInstance> doorHandler = new ArrayList<DoorInstance>();

    public Tilemap(int width, int height, Tile map[]) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    private Texture textureMap;
    public Tilemap(String path) {
        Texture texture = new Texture(path);
        this.textureMap = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();

        map = new Tile[texture.getWidth() * texture.getHeight()];

        if(!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        for(int i = 0; i < pixmap.getHeight(); i++) {
            for(int j = 0; j < pixmap.getWidth(); j++) {
                Color c = new Color(pixmap.getPixel(j, i));

                if(c.equals(Tile.Air.color)) {
                    map[i * texture.getWidth() + j] = Tile.Air;
                }
                else if(c.equals(Tile.DoorTile.color)){
                    map[i * texture.getWidth() + j] = Tile.DoorTile;
                    doorHandler.add(new DoorInstance(5.0f, j, i, true));
                }
                else if(c.equals(Tile.GroundTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.GroundTile;
                }
                else if(c.equals(Tile.WallTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.WallTile;
                }
                else if(c.equals(Tile.ReactorTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.ReactorTile;
                }
                else if(c.equals(Tile.LifeSupportTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.LifeSupportTile;
                }
                else if(c.equals(Tile.EngineTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.EngineTile;
                }
                else if(c.equals(Tile.NavTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.NavTile;
                }
                else if(c.equals(Tile.CommsTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.CommsTile;
                }
                else if(c.equals(Tile.MedicTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.MedicTile;
                }
                else if(c.equals(Tile.SuitTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.SuitTile;
                }
                else if(c.equals(Tile.FireExtinguisherTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.FireExtinguisherTile;
                }
                else if(c.equals(Tile.PlayerTile.color)) {
                    map[i * texture.getWidth() + j] = Tile.PlayerTile;
                }
            }
        }
    }

    public Tile getTileByPosition(int x, int y) {
        if(x >= width) return Tile.Air;
        else if(x < 0) return Tile.Air;
        else if(y >= height) return Tile.Air;
        else if(y < 0) return Tile.Air;
        return map[y * width + x];
    }

    public Vector2 globalPositionToTilemapPosition(float x, float y) {
        double processedX = Math.floor((x-(offset.x - this.textureMap.getWidth()/2)) / TILE_SIZE);
        double processedY = Math.floor((y-(offset.y - this.textureMap.getHeight()/2)) / TILE_SIZE);

        return new Vector2((float)processedX, (float)processedY);
    }

    public Vector2 tilemapPositionToGlobalPosition(float x, float y) {
        double processedX = (x+(offset.x - this.textureMap.getWidth()/2)) * TILE_SIZE;
        double processedY = (y+(offset.y - this.textureMap.getHeight()/2)) * TILE_SIZE;

        return new Vector2((float)processedX, (float)processedY);
    }

    public void generateEntities(){
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(map[i * width + j] != null) {
                    float x = j * TILE_SIZE + (offset.x - this.textureMap.getWidth()/2);
                    float y = i * TILE_SIZE + (offset.y - this.textureMap.getHeight()/2);

                    if(map[i * width + j].tileID == Tile.ReactorTile.tileID){
                        Game.entityManager.addEntity(new Generator(new Vector2(x, y), new Vector2(1, 1)));
                    }
                    else if(map[i * width + j].tileID == Tile.LifeSupportTile.tileID){
                        Game.entityManager.addEntity(new LifeSupport(new Vector2(x+8, y-16), new Vector2(2, 2)));
                    }
                    else if(map[i * width + j].tileID == Tile.SuitTile.tileID){
                        Game.entityManager.addEntity(new Spacesuit(new Vector2(x+5, y+10), new Vector2(2, 2)));
                    }
                    else if(map[i * width + j].tileID == Tile.MedicTile.tileID){
                        Game.entityManager.addEntity(new MedBay(new Vector2(x, y-32), new Vector2(1, 1)));
                    }
                    else if(map[i * width + j].tileID == Tile.FireExtinguisherTile.tileID){
                        Game.entityManager.addEntity(new FireExtinguisher(new Vector2(x, y), new Vector2(1.5f, 2.0f)));
                    }
                    else if(map[i * width + j].tileID == Tile.PlayerTile.tileID){
                        Game.entityManager.addEntity(new PlayerEntity(new Vector2(x, y), new Vector2(2, 2)));
                    }
                    else if(map[i * width + j].tileID == Tile.EngineTile.tileID){
                        Game.entityManager.addEntity(new Engine(new Vector2(x+10, y+5), new Vector2(2, 2)));
                    }
                    else if(map[i * width + j].tileID == Tile.CommsTile.tileID){
                        Game.entityManager.addEntity(new Comms(new Vector2(x+5, y), new Vector2(2, 2)));
                    }
                    else if(map[i * width + j].tileID == Tile.NavTile.tileID){
                        Game.entityManager.addEntity(new Navigation(new Vector2(x+8, y-17), new Vector2(2, 2)));
                    }
                }
            }
        }
        entitiesGenerated = true;

    }

    public void render(SpriteBatch batch) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(map[i * width + j] != null) {
                    map[i * width + j].render(batch, j * TILE_SIZE + (offset.x - this.textureMap.getWidth()/2), i * TILE_SIZE + (offset.y - this.textureMap.getHeight()/2));
                }
            }
        }

        for(int i = 0; i < doorHandler.size(); i++) {
            doorHandler.get(i).timer -= Gdx.graphics.getDeltaTime();
            if(doorHandler.get(i).timer <= 0) {
                setTile(doorHandler.get(i).x, doorHandler.get(i).y, Tile.DoorTile);
                doorHandler.get(i).timer = 5.0f;
                doorHandler.get(i).closed = true;
            }
        }

        //if(!entitiesGenerated) generateEntities();
    }

    public void setTile(int x, int y, Tile tile) {
        map[y * width + x] = tile;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
