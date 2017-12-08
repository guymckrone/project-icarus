package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.prefs.Preferences;

import sun.applet.Main;

public class ProjectOdyssey extends Game {
	float money; //total amount of money that the player has
	SpriteBatch batch;
	private Game game;
	private TextField moneyCounter;

	public ProjectOdyssey(){
		this.game = this;
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1); //White background color
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		game.setScreen(new MainMenuScreen(this));

		money = 0;
//		Skin counterSkin = new Skin(Gdx.files.internal("uiskin.json"));
//		moneyCounter = new TextField("Money: " + money, counterSkin);
//		moneyCounter.setOrigin(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
//		moneyCounter.moveBy(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
	}

	public void PlayScreen(){
		render();
	}


	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

}
