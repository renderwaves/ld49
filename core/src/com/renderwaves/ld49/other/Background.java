package com.renderwaves.ld49.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.managers.TextureManager;

import java.util.ArrayList;

public class Background {
    class Star {
        Vector2 position, velocity;
        float radius;

        public Star(Vector2 position, float radius) {
            this.position = position;
            this.radius = radius;
            this.velocity = new Vector2(-radius * 200, 0);
        }
    }

    class Planet {
        Vector2 position, velocity;
        float radius;

        public Texture texture;

        public Planet(Vector2 position, float radius) {
            this.position = position;
            this.radius = radius;
            this.velocity = new Vector2(-radius * 5, 0);
        }
    }

    private Planet planet;

    private ArrayList<Star> stars = new ArrayList<Star>();

    public static final int NUMBER_OF_STARS = 75;

    private Texture getRandomPlanetTexture() {
        double r = Math.random();
        Texture tex = null;

        if(r >= 0.8) {
            tex = TextureManager.planetEarth;
        }
        else if(r >= 0.7) {
            tex = TextureManager.planetEarthFict1;
        }
        else if(r >= 0.6) {
            tex = TextureManager.planetEarthFict2;
        }
        else if(r >= 0.5) {
            tex = TextureManager.planetFict1;
        }
        else if(r >= 0.4) {
            tex = TextureManager.planetJupiter;
        }
        else if(r >= 0.2) {
            tex = TextureManager.planetVenuse;
        }
        else if(r >= 0.0) {
            tex = TextureManager.planetMerkur;
        }

        return tex;
    }

    public Background() {
        stars.clear();

        planet = new Planet(new Vector2((float) (Gdx.graphics.getWidth() * Math.random()), (float) (Gdx.graphics.getHeight() * Math.random())), (float) (Math.random() * 200 + 25));
        planet.texture = getRandomPlanetTexture();

        for(int i = 0; i < NUMBER_OF_STARS; i++) {
            stars.add(new Star(new Vector2((float) (Gdx.graphics.getWidth() * Math.random()), (float) (Gdx.graphics.getHeight() * Math.random())), (float) (Math.random() * 4 + 1)));
        }
    }

    public void renderSprite(SpriteBatch spriteBatch) {
        planet.position.x += Gdx.graphics.getDeltaTime() * planet.velocity.x;

        if(planet.position.x < -planet.radius) {
            planet.position.x = (float) (Gdx.graphics.getWidth() + (Gdx.graphics.getWidth()*5*Math.random()));
            planet.position.y = (float) (Gdx.graphics.getHeight() * Math.random());
            planet.radius = (float) (Math.random() * 200 + 25);
            planet.texture = getRandomPlanetTexture();
        }

        spriteBatch.draw(planet.texture, planet.position.x, planet.position.y, planet.radius, planet.radius);
    }

    public void renderShape(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0; i < stars.size(); i++) {
            stars.get(i).position.x += Gdx.graphics.getDeltaTime() * stars.get(i).velocity.x;

            if(stars.get(i).position.x < 0) {
                stars.get(i).position.x = Gdx.graphics.getWidth();
                stars.get(i).position.y = (float) (Gdx.graphics.getHeight() * Math.random());
            }

            renderer.setColor(Color.WHITE);
            renderer.circle(stars.get(i).position.x , stars.get(i).position.y, stars.get(i).radius);
            renderer.setColor(new Color(200, 200, 200, 255));
            renderer.circle(stars.get(i).position.x+2 , stars.get(i).position.y, stars.get(i).radius);
            renderer.setColor(new Color(150, 150, 150, 255));
            renderer.circle(stars.get(i).position.x+4 , stars.get(i).position.y, stars.get(i).radius);
            renderer.setColor(new Color(100, 100, 100, 255));
            renderer.circle(stars.get(i).position.x+6 , stars.get(i).position.y, stars.get(i).radius);
            renderer.setColor(new Color(50, 50, 50, 255));
            renderer.circle(stars.get(i).position.x+8 , stars.get(i).position.y, stars.get(i).radius);
        }

        renderer.end();
    }
}
