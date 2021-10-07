package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.managers.SoundManager;
import com.renderwaves.ld49.other.Background;

public class IntroScene implements Screen {
    private Array<Sprite> video;
    private TextureAtlas videoAtlas;
    private Sprite vidCurSprite;
    private int vidCurFrame = 0;

    private Background background;
    private SoundManager soundManager;

    Game game;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    public IntroScene(Game game){
        this.game = game;
        this.background = new Background();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        videoAtlas = new TextureAtlas(Gdx.files.internal("textures/atlas/logo/logos.atlas"));
        video = videoAtlas.createSprites();

        soundManager = new SoundManager(SoundManager.menuMusic);
    }

    @Override
    public void show() {

    }

    private float time = 0;
    private int numLogo = 0;
    private void nextFrame(float delta){
        time += delta;
        if(time > 1.5f){
            vidCurFrame++;
            numLogo++;
            time = 0;
        }
        if(vidCurFrame<video.size){
            vidCurSprite = video.get(vidCurFrame);
        } else {
            this.game.setScreen(new MenuScene(game));
        }
        if(numLogo == 1){
            vidCurSprite.setPosition(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        }else{
            vidCurSprite.setPosition(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2.5f);
        }

        vidCurSprite.setScale(1.0f,1.0f);
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScene(game));
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nextFrame(delta);
        background.renderShape(shapeRenderer);
        spriteBatch.begin();
        background.renderSprite(spriteBatch);
        vidCurSprite.draw(spriteBatch);
        spriteBatch.end();
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

    }
}
