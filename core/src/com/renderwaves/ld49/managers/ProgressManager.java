package com.renderwaves.ld49.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ProgressManager {
    private class Point{
        public float x;
        public float y;
        Point(float x, float y){
            this.x = x;
            this.y = y;
        }
    }

    private Sprite indicator;
    private ArrayList<Point> points;
    private float radius;

    private int width;
    private int beginX, beginY;
    private Point curPoint;
    private String progressString;
    private float curProgress;

    public ProgressManager(float radius, Sprite indicator) {
        this.radius = radius;
        this.indicator = indicator;

        points = new ArrayList<>();
        points.clear();

        float angle = 1.0f;
        for(int i = 0; i < 180; i++) {
            double x = Math.cos(Math.toRadians(angle * i)) * (this.radius+this.indicator.getWidth());
            double y = Math.sin(Math.toRadians(angle * i)) * (this.radius+this.indicator.getHeight()/2);
            points.add(new Point((float) x, (float) y));
        }
        this.curPoint = points.get(0);
        setProgress(0);

        this.width = Gdx.graphics.getWidth();

        this.beginX = width / 2;
        this.beginY = 0;

    }

    public void setProgress(float progress){
        float iProgress = 1.0f - progress;
        if (progress > 1.0f) {
            this.curProgress = 1.0f;
            return;
        }
        else if(progress < 0.0f){
            this.curProgress = 0.0f;
            return;
        }

        int index = Math.round(179.0f * iProgress);
        curPoint = points.get(index);
        this.progressString = String.valueOf(Math.round(100.0f*progress)) + "%";
        this.curProgress = progress;
    }

    public float getProgress(){
        return this.curProgress;
    }

    public void renderSprites(SpriteBatch batch){
        this.indicator.setPosition(curPoint.x+width/2 - this.indicator.getWidth()/4, curPoint.y);
        this.indicator.draw(batch);
    }

    public void renderShapes(ShapeRenderer shapeRenderer){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.arc(this.beginX,this.beginY,this.radius,0,180, 36);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.arc(this.beginX,this.beginY,this.radius/1.1f,0,180, 36);
    }

    public void renderFonts(BitmapFont font, SpriteBatch batch){
        font.draw(batch, progressString, this.beginX-15, this.beginY + 20);
    }
}