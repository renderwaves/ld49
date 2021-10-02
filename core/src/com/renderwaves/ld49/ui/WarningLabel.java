package com.renderwaves.ld49.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.renderwaves.ld49.managers.FontManager;

public class WarningLabel {
    public int x, y;
    public String text;
    public Color color1, color2;
    public float time;
    public BitmapFont font;

    private float timer;
    private float startTime;

    public WarningLabel(int x, int y, String text, Color color1, Color color2, float time) {
        this.text = text;
        this.color1 = color1;
        this.color2 = color2;
        this.time = time;
        this.font = FontManager.font_droidBb_40;
        this.x = x;
        this.y = y;
        timer = 0;
    }

    private float period = 1f;

    private boolean switchColors = false;
    public void render(SpriteBatch spriteBatch) {
        timer += Gdx.graphics.getRawDeltaTime();
        if (timer > period) {
            timer -= period;
            if (font != null)
                event();
        }
        /*
        System.out.println(timer);
        if(timer >= 2) {
            font.setColor(switchColors ? color2 : color1);
            switchColors = !switchColors;
            timer = 0;
            startTime = System.nanoTime();
        }

        */
        if (font != null) font.draw(spriteBatch, this.text, this.x, this.y);
    }

    private void event() {
        font.setColor(switchColors ? color2 : color1);
        switchColors = !switchColors;
    }
}
