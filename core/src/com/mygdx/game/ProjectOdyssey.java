package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ProjectOdyssey extends Game {
	public AssetManager assets;
	//GameScreen.setupAssetManager(assets)
	public SpriteBatch batch;
	public BitmapFont font;
	//Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		//img.dispose();
	}
}
