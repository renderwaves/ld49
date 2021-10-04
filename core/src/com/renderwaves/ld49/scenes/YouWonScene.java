package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.entity.entities.PlayerEntity;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.SoundManager;

public class YouWonScene implements Screen {
    private Stage stage;
    private Table table;
    private Table creditsTable;

    Game game;

    private Array<Sprite> video;
    private TextureAtlas videoAtlas;
    private Sprite vidCurSprite;
    private int vidCurFrame = 0;
    private SpriteBatch spriteBatch;

    public YouWonScene(Game game){
        this.game = game;
        Skin uiSkin = new Skin();
        uiSkin.add("default-font", FontManager.font_droidBb_20);

        uiSkin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        uiSkin.load(Gdx.files.internal("skins/skin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);

        stage.addActor(table);
        //table.setDebug(true);

        Pixmap pixmapName = new Pixmap(1,1,Pixmap.Format.RGBA8888);
        pixmapName.setColor(1,0, 0.5f, 0.4f);
        pixmapName.fill();
        TextureRegionDrawable backYouWon = new TextureRegionDrawable(new Texture(pixmapName));

        Label.LabelStyle gameNameStyle = new Label.LabelStyle();
        gameNameStyle.font = FontManager.font_droidBb_40;
        gameNameStyle.fontColor = new Color(1,1,1,1);
        gameNameStyle.background = backYouWon;

        final Label winText = new Label("YOU WON!", gameNameStyle);
        winText.setAlignment(Align.center);
        final TextButton restartButton = new TextButton("Restart", uiSkin);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                callbackStartGame();
            }
        });

        final TextButton quitButton = new TextButton("Quit", uiSkin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                callbackQuit();
            }
        });

        Label scoreLabel = new Label("Your score: " + String.valueOf(GlobalShipVariables.score),gameNameStyle);
        scoreLabel.setAlignment(Align.center);

        table.add(winText).width(150);
        table.row();
        table.add(scoreLabel);
        table.row();
        table.add(restartButton).spaceTop(20).width(100).height(30);
        table.row();
        table.add(quitButton).spaceTop(5).width(100).height(30);

        videoAtlas = new TextureAtlas(Gdx.files.internal("textures/atlas/goodend/goodend.atlas"));
        video = videoAtlas.createSprites();
        spriteBatch = new SpriteBatch();

        SoundManager sound = new SoundManager(SoundManager.goodEnding);
    }

    public void callbackStartGame(){
        TemplateScene.shipTilemap.doorHandler.clear();
        TemplateScene.shipTilemap.fireHandler.clear();
        TemplateScene.getInstance().gameEventSystem.completeAllEvents();
        TemplateScene.warningLabels.clear();
        com.renderwaves.ld49.Game.entityManager.clear();
        PlayerEntity.health = 1.0f;
        GlobalShipVariables.engine1Health = 1.0f;
        GlobalShipVariables.oxygenLevel = 1.0f;
        GlobalShipVariables.shipHealth = 1.0f;
        GlobalShipVariables.communicationsHealth = 1.0f;
        GlobalShipVariables.navigationHealth = 1.0f;
        GlobalShipVariables.lifeSupportHealth = 1.0f;
        GlobalShipVariables.generatorFuel = 1.0f;
        GlobalShipVariables.generatorHealth = 1.0f;
        GlobalShipVariables.engine2Health = 1.0f;
        GlobalShipVariables.score = 0;
        GlobalShipVariables.globalShipTimer = 25000;

        this.game.setScreen(new TemplateScene((com.renderwaves.ld49.Game) game));
    }

    public void callbackQuit(){
        Gdx.app.exit();
    }

    @Override
    public void show() {

    }

    private float time = 0;
    private boolean toGame = false;
    private void nextFrame(float delta){
        time += delta;
        if(time > 0.1f){
            vidCurFrame++;
            time = 0;
        }
        if(vidCurFrame<video.size){
            vidCurSprite = video.get(vidCurFrame);
        } else {
            vidCurFrame = 0;
            time = 0;
        }
        vidCurSprite.setPosition(Gdx.graphics.getWidth()/2.425f, Gdx.graphics.getHeight()/2.425f);
        vidCurSprite.setScale(5,5);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        nextFrame(delta);
        spriteBatch.begin();
        vidCurSprite.draw(spriteBatch);
        spriteBatch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
