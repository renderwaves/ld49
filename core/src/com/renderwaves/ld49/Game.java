package com.renderwaves.ld49;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.renderwaves.ld49.entity.EntityManager;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.TextureManager;

import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.scenes.MenuScene;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;

	public static final EntityManager entityManager = new EntityManager();

	
	@Override
	public void create () {
		FontManager.generateFonts();

		batch = new SpriteBatch();
		this.setScreen(new MenuScene(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		TextureManager.disposeAllTextures();
	}
}
