package com.renderwaves.ld49.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {
    public static BitmapFont font_arial_20;
    public static BitmapFont font_droidBb_18;

    public static void generateFonts() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        font_arial_20 = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DisposableDroidBB.ttf"));
        parameter.size = 18;
        font_droidBb_18 = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();
    }
}
