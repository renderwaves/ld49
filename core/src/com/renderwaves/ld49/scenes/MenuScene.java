package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.SoundManager;

import java.awt.*;

public class MenuScene implements Screen {
    private Stage stage;
    private Table table;
    private Table creditsTable;
    private Table difficultyTable;

    private Array<Sprite> video;
    private TextureAtlas videoAtlas;
    private Sprite vidCurSprite;
    private int vidCurFrame = 0;

    Game game;

    private SpriteBatch spriteBatch;

    public static int difficulty = 1;

    public MenuScene(Game game){
        this.game = game;
        spriteBatch = new SpriteBatch();

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
        pixmapName.setColor(1,0, 0.5f, 1.0f);
        pixmapName.fill();
        TextureRegionDrawable backgroundName = new TextureRegionDrawable(new Texture(pixmapName));

        Label.LabelStyle gameNameStyle = new Label.LabelStyle();
        gameNameStyle.font = FontManager.font_droidBb_40;
        gameNameStyle.fontColor = new Color(1,1,1,1);
        gameNameStyle.background = backgroundName;


        final Label gameName = new Label("PANIC ON THE SHIP", gameNameStyle);
        gameName.setAlignment(Align.center);
        final TextButton playButton = new TextButton("Play", uiSkin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                callbackDifficulty();
            }
        });
        final TextButton creditsButton = new TextButton("Credits", uiSkin);
        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                callbackCredits();
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

        table.add(gameName).width(300);
        table.row();
        table.add(playButton).spaceTop(20).width(100).height(30);
        table.row();
        table.add(creditsButton).spaceTop(5).width(100).height(30);
        table.row();
        table.add(quitButton).spaceTop(5).width(100).height(30);

        creditsTable = new Table();
        creditsTable.setFillParent(true);
        creditsTable.pad(50);

        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGBA8888);
        pixmap.setColor(0.4f,0.4f, 0.4f, 0.8f);
        pixmap.fill();
        TextureRegionDrawable background = new TextureRegionDrawable(new Texture(pixmap));
        creditsTable.setBackground(background);

        Label.LabelStyle creditsHeaders = new Label.LabelStyle();
        creditsHeaders.font = FontManager.font_droidBb_30;
        creditsHeaders.fontColor = new Color(1,1,1,1);
        //creditsTable.setColor(0.5f,0.5f,0.5f,1);

        Label creditsHeader = new Label("Credits", gameNameStyle);
        creditsHeader.setAlignment(Align.center);
        Label nameRenderwaves = new Label("RenderWaves Team", creditsHeaders);
        Label nameAknavj = new Label("Aknavj (@aknavj)", uiSkin);
        Label nameLeumas = new Label("Leumas__ (@samuelbencak)", uiSkin);
        Label nameAdam = new Label("Adam077x (@adam077x)", uiSkin);
        Label nameJiri = new Label("Jiri (@zorzo)", uiSkin);
        Label rwUrl = new Label("www.renderwaves.com", uiSkin);
        TextButton closeCredits = new TextButton("Close", uiSkin);
        closeCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                callbackCloseCredits();
            }
        });

        //creditsTable.center();
        creditsTable.add(creditsHeader).width(200);
        creditsTable.row();
        creditsTable.add(nameRenderwaves).spaceTop(20);
        creditsTable.row();
        creditsTable.add(nameAknavj).spaceTop(5);
        creditsTable.row();
        creditsTable.add(nameLeumas).spaceTop(5);
        creditsTable.row();
        creditsTable.add(nameAdam).spaceTop(5);
        creditsTable.row();
        creditsTable.add(nameJiri).spaceTop(5);
        creditsTable.row();
        creditsTable.add(rwUrl).spaceTop(10);
        creditsTable.row();
        creditsTable.add(closeCredits).width(100).height(30).spaceTop(50);

        difficultyTable = new Table();
        difficultyTable.setFillParent(true);
        difficultyTable.pad(50);

        TextButton easyModeButton = new TextButton("Easy", uiSkin);
        TextButton mediumModeButton = new TextButton("Medium", uiSkin);
        TextButton hardModeButton = new TextButton("Hard", uiSkin);

        difficultyTable.add(easyModeButton).spaceTop(5);
        difficultyTable.row();
        difficultyTable.add(mediumModeButton).spaceTop(5);
        difficultyTable.row();
        difficultyTable.add(hardModeButton).spaceTop(5);

        easyModeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                difficulty = 1;
                callbackStartGame();
            }
        });

        mediumModeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                difficulty = 2;
                callbackStartGame();
            }
        });

        hardModeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                difficulty = 3;
                callbackStartGame();
            }
        });

        videoAtlas = new TextureAtlas(Gdx.files.internal("textures/atlas/mainloop/mainloop.atlas"));
        video = videoAtlas.createSprites();

        SoundManager sound = new SoundManager(SoundManager.menuMusic);
    }

    public void startGame(){
        this.game.setScreen(new TutorialScene((com.renderwaves.ld49.Game) game));
    }

    public void callbackCredits(){
        stage.clear();
        stage.addActor(creditsTable);
    }

    public void callbackDifficulty(){
        stage.clear();
        stage.addActor(difficultyTable);
    }

    public void callbackCloseCredits(){
        stage.clear();
        stage.addActor(table);
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
            if(toGame) startGame();
            vidCurFrame = 0;
        }
        vidCurSprite.setPosition(Gdx.graphics.getWidth()/2.425f, Gdx.graphics.getHeight()/2.425f);
        vidCurSprite.setScale(5,5);
    }

    private void callbackStartGame(){
        stage.clear();
        video.clear();
        videoAtlas.dispose();
        videoAtlas = new TextureAtlas(Gdx.files.internal("textures/atlas/transition/transitiontogame.atlas"));
        video = videoAtlas.createSprites();
        vidCurFrame = 0;
        toGame = true;
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
        video.clear();
        videoAtlas.dispose();
    }
}
