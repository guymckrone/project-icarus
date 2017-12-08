package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by gmcfeeters3345 on 10/16/2017.
 */

public class MainMenuScreen extends Game implements Screen {

    TextButton sButton;
    private Stage mainMenuStage;
    private Table mainMenuTable;
    private BitmapFont sButtonFont;

    public MainMenuScreen(Game game) {
        create();
        render();
    }

    @Override
    public void create() {

        mainMenuStage = new Stage();

        Gdx.input.setInputProcessor(mainMenuStage);

        mainMenuTable = new Table();
        mainMenuTable.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sButtonFont = new BitmapFont();

        TextButton.TextButtonStyle sButtonStyle = new TextButton.TextButtonStyle();
        sButtonStyle.font = sButtonFont;
        sButtonStyle.pressedOffsetX = 1;
        sButtonStyle.pressedOffsetY = 1;

        sButton = new TextButton("Begin Empire", sButtonStyle);
        sButton.pad(100);
        sButton.getLabel().setFontScale(4, 4);
        sButton.getLabel().setColor(0, 0, 0 ,1 );

        mainMenuTable.add(sButton);
        mainMenuTable.debug();
        mainMenuStage.addActor(mainMenuTable);

        mainMenuTable.setTransform(true);
        mainMenuTable.rotateBy(90);
        mainMenuTable.setOrigin(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        mainMenuTable.moveBy(Gdx.graphics.getWidth()/3, 0);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1); //White background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
        mainMenuStage.draw();
    }

    @Override
    public void show() {
     }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {

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
