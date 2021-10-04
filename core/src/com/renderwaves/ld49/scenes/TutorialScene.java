package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.managers.FontManager;

public class TutorialScene implements Screen {
    public static final Texture tutorialTexture = new Texture("textures/tutorial.png");

    Game game;
    Stage stage;

    public TutorialScene(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        Skin uiSkin = new Skin();
        uiSkin.add("default-font", FontManager.font_droidBb_20);

        uiSkin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        uiSkin.load(Gdx.files.internal("skins/skin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextButton startGameButton = new TextButton("Start Game", uiSkin);
        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new TemplateScene(game));
            }
        });

        Table table = new Table();
        table.add(startGameButton).right().top();
        table.setPosition(Gdx.graphics.getWidth() - 55, Gdx.graphics.getHeight() - 20);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(tutorialTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

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
        tutorialTexture.dispose();
    }
}
