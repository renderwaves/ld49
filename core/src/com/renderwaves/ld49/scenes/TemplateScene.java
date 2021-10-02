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
import com.renderwaves.ld49.entity.Entity;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.entities.*;
import com.renderwaves.ld49.events.*;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.ProgressManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.tilemap.Tilemap;
import com.renderwaves.ld49.ui.StatusBar;
import sun.jvm.hotspot.gc.shared.Space;

import java.awt.*;

public class TemplateScene implements Screen {
    Game game;
    GameEventSystem gameEventSystem;
    Sprite sprite;

    Stage stage;
    SpriteBatch batch;

    TemplateEntity templateEntity;

    public static Tilemap shipTilemap;
    private StatusBar statusBar;
    private ShapeRenderer shapeRenderer;
    private ProgressManager progressManager;

    private float ingameTime = 0f;
    private float period = 0.001f;

    /*
     */
    public TemplateScene(Game game) {
        this.game = game;
        this.gameEventSystem = new GameEventSystem();

        this.batch = game.batch;

        progressManager = new ProgressManager(50.0f, new Sprite(TextureManager.shipIndicator));
        shapeRenderer = new ShapeRenderer();

        shipTilemap = new Tilemap("maps/ship_new.png");
        shipTilemap.generateEntities();


        //Game.entityManager.addEntity(new Generator(new Vector2(440, 430), new Vector2(4, 4)));
        //Game.entityManager.addEntity(new LifeSupport(new Vector2(232, 420), new Vector2(2, 2)));
        //Game.entityManager.addEntity(new MedBay(new Vector2(512, 244), new Vector2(1, 1)));
        //Game.entityManager.addEntity(new Spacesuit(new Vector2(710, 250), new Vector2(2, 2)));
        //Game.entityManager.addEntity(new PlayerEntity(new Vector2(820, 400), new Vector2(2, 2)));
        //Game.entityManager.addEntity(new FireExtinguisher(new Vector2(450, 260), new Vector2(1.5f, 1.5f)));

        Sprite shipIndicator = new Sprite(TextureManager.shipIndicator);
        progressManager = new ProgressManager(50.0f, shipIndicator);

        statusBar = new StatusBar(new Vector2(10, 10), new Vector2(128, 64), 1.0f, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.energyTexture, new Vector2(2, 2));
    }

    @Override
    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        sprite = new Sprite(TextureManager.img);
    }

    /*
     */
    public void update(float delta) {

        if(GlobalShipVariables.shipHealth > 1.0f) GlobalShipVariables.shipHealth = 1.0f;
        else if(GlobalShipVariables.shipHealth < 0.0f) GlobalShipVariables.shipHealth = 0.0f;

        int min = -16;
        int max = 16;
        int gameState = (int)(Math.random()*(max-min+1)+min);
        switch(gameState) {
            case 0: gameEventSystem.addEvent(new CommsEvent()); break; // comms failure
            case 1: gameEventSystem.addEvent(new LifesupportEvent()); break; // lifesupport, oxygen failure (space suit)
            case 2: gameEventSystem.addEvent(new GeneratorEvent()); break; // generator failure
            case 3: gameEventSystem.addEvent(new NavigationEvent()); break; // navigation failure
            case 4: gameEventSystem.addEvent(new EngineEvent()); break; // engine failure
            case 5: gameEventSystem.addEvent(new FireEvent()); break; // fire in hull
            case 6: gameEventSystem.addEvent(new DoorEvent()); break; // door failure
            default:
                System.out.println("nothing is hapenning");
                // nothing is hapenning
                break;
        }

    }

    @Override
    public void render(float delta) {
        period = (float)(Math.random()*(1.0f-0.01f + 1.0f)+1.0f);

        ingameTime += Gdx.graphics.getRawDeltaTime();
        if (ingameTime > period) {
            ingameTime -= period;
            update(delta);
        }

        gameEventSystem.update();

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        shipTilemap.render(batch);
        game.entityManager.handleEntities(batch);
        statusBar.renderSprite(batch);

        GlobalShipVariables.shipHealth -= Gdx.graphics.getDeltaTime() / 5;
        statusBar.status = GlobalShipVariables.shipHealth;
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
