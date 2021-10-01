package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.entity.entities.TemplateEntity;
import com.renderwaves.ld49.managers.TextureManager;

public class TemplateScene implements Screen {
    Game game;
    Sprite sprite;

    Stage stage;

    SpriteBatch batch;

    TemplateEntity templateEntity;

    public TemplateScene(Game game) {
        this.game = game;
        this.batch = game.batch;

        game.entityManager.addEntity(new TemplateEntity(new Vector2(1, 1), new Vector2(1, 1)));
        game.entityManager.addEntity(new TemplateEntity(new Vector2(100, 1), new Vector2(1, 1)));
        game.entityManager.addEntity(new TemplateEntity(new Vector2(1, 100), new Vector2(1, 1)));

    }

    @Override
    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        final TextButton button1 = new TextButton("Button 1", uiSkin);
        button1.setPosition(300, 100);
        stage.addActor(button1);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                button1.setText("CLICKED");
            }
        });

        sprite = new Sprite(TextureManager.img);

        templateEntity = new TemplateEntity(new Vector2(1, 1), new Vector2(1, 1));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        game.entityManager.handleEntities(batch);
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
