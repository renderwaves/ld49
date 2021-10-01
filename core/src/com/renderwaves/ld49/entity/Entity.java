package com.renderwaves.ld49.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.Game;

public class Entity {
    public Vector2 position, scale;
    public int uniqueID;

    public Entity(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

    public Entity() {
        this.position = new Vector2(0, 0);
        this.scale = new Vector2(1, 1);

        uniqueID = (int)(Math.random() * Integer.MAX_VALUE);
    }

    public void render(SpriteBatch spriteBatch) {}
    public void update() {}

    public void destroySelf() {
        Game.entityManager.remove(this);
    }

    public void followTarget(Vector2 targetPosition, float speed) {
        Vector2 processedPosition = new Vector2(targetPosition.x - position.x, (Gdx.graphics.getHeight() - targetPosition.y) - position.y);
        double dir = Math.atan2(processedPosition.y, processedPosition.x);
        position.x += Math.cos(dir) * speed;
        position.y += Math.sin(dir) * speed;
    }
}