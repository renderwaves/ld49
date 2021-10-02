package com.renderwaves.ld49.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Tilemap {
    public static final int TILE_SIZE = 32;

    public Vector2 offset = new Vector2(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/4);

    private int width, height;
    public Tile map[];

    public Tilemap(int width, int height, Tile map[]) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    public Tilemap(String path) {
        Texture texture = new Texture(path);

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
                if(c.toString().equalsIgnoreCase( "0026FFFF")) {
                    System.out.println(c.g);
                }

                if(c.equals(Tile.Air.color)) {
                    map[i * texture.getWidth() + j] = Tile.Air;
                }
                else if(c.toIntBits() == Tile.DoorTile.color.toIntBits()){
                    map[i * texture.getWidth() + j] = Tile.DoorTile;
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
        double processedX = Math.floor((x-offset.x) / TILE_SIZE);
        double processedY = Math.floor((y-offset.y) / TILE_SIZE);

        return new Vector2((float)processedX, (float)processedY);
    }

    public void render(SpriteBatch batch) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(map[i * width + j] != null) {
                    map[i * width + j].render(batch, j * TILE_SIZE + offset.x, i * TILE_SIZE + offset.y);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
