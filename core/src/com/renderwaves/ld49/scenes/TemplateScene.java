package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.entity.entities.PlayerEntity;
import com.renderwaves.ld49.entity.entities.TemplateEntity;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.ProgressManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.tilemap.Tilemap;
import com.renderwaves.ld49.ui.StatusBar;

import java.awt.*;

public class TemplateScene implements Screen {
    Game game;
    Sprite sprite;

    Stage stage;

    SpriteBatch batch;

    TemplateEntity templateEntity;

    public static Tilemap shipTilemap;

    private StatusBar statusBar;

    private ShapeRenderer shapeRenderer;

    private ProgressManager progressManager;

    public TemplateScene(Game game) {
        this.game = game;
        this.batch = game.batch;
        shapeRenderer = new ShapeRenderer();

        Game.entityManager.addEntity(new PlayerEntity(new Vector2(820, 400), new Vector2(2, 2)));

        shipTilemap = new Tilemap("maps/ship_new.png");
        Sprite shipIndicator = new Sprite(TextureManager.shipIndicator);
        progressManager = new ProgressManager(50.0f, shipIndicator);

        statusBar = new StatusBar(new Vector2(10, 10), new Vector2(128, 64), 1.0f, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.energyTexture, new Vector2(2, 2));
    }

    @Override
    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        final TextButton button1 = new TextButton("Button 1", uiSkin);
        button1.setPosition(300, 100);
        //stage.addActor(button1);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                button1.setText("CLICKED");
            }
        });

        sprite = new Sprite(TextureManager.img);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        shipTilemap.render(batch);
        game.entityManager.handleEntities(batch);
        statusBar.renderSprite(batch);
        statusBar.status -= Gdx.graphics.getDeltaTime() / 5;
        if(statusBar.status <= 0) statusBar.status = 1.0f;
        FontManager.font_arial_20.setColor(Color.WHITE);
        FontManager.font_arial_20.draw(batch, "Hello World!", 10, 400);
        progressManager.renderSprites(batch);
        if(progressManager.getProgress() >= 1.0f) progressManager.setProgress(0.0f);
        progressManager.setProgress(progressManager.getProgress() + delta / 5);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        statusBar.renderShape(shapeRenderer);
        progressManager.renderShapes(shapeRenderer);
        shapeRenderer.end();

        // overlay fonts
        batch.begin();
        progressManager.renderFonts(FontManager.font_droidBb_18, batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
