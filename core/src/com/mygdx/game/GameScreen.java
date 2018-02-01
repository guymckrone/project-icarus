package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by guymc on 11/29/2017.
 */

//Office

public class GameScreen implements Screen, GestureDetector.GestureListener {
    final ProjectOdyssey game;
    private Stage stage;
    int[] inventory = new int[10];
    private BitmapFont moneyCounter = new BitmapFont();
    private SpriteBatch batch;
    private ImageButton iceButton;
    private ImageButton marketButton;
    private ImageButton lobbyButton;
    //public static int ice;
    OrthographicCamera camera;
    float playTime;
    int playIntSec;
    int playIntHour;
    String gameClock;
    

    public GameScreen(final ProjectOdyssey game) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/century.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?: ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        this.game = game;
        Gdx.graphics.getDeltaTime();
        playTime = 0;
        playIntSec = 0;
        playIntHour = 0;

        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // IceButton skin
        Skin iceButtonSkin = new Skin();
        iceButtonSkin.add("iceButton", new Texture("MainScreen/IceMachine.png"));

        // Create IceButton style
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = iceButtonSkin.getDrawable("iceButton"); // Unpressed
        playButtonStyle.imageDown = iceButtonSkin.getDrawable("iceButton"); // Pressed



        // Play button
        iceButton = new ImageButton(playButtonStyle);
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());
        iceButton.setSize(buttonSize, buttonSize);
        int width = (int) ((Gdx.graphics.getWidth() - iceButton.getWidth())/2);
        int height = (int) ((Gdx.graphics.getHeight() - iceButton.getHeight())/4);
        iceButton.setBounds(width, height, iceButton.getWidth(), iceButton.getHeight());
        iceButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ProjectOdyssey.ice++;
                //iceButton.setDisabled(false);

            }
        });
        stage.addActor(iceButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin marketButtonSkin = new Skin();
        marketButtonSkin.add("marketButton", new Texture("buttons/market_arrow.png"));

        // Create button style
        ImageButton.ImageButtonStyle marketButtonStyle = new ImageButton.ImageButtonStyle();
        marketButtonStyle.imageUp = marketButtonSkin.getDrawable("marketButton"); // Unpressed
        marketButtonStyle.imageDown = marketButtonSkin.getDrawable("marketButton"); // Pressed

        // Market button
        marketButton = new ImageButton(marketButtonStyle);
        int buttonSize2 = (int) (100 * Gdx.graphics.getDensity());
        marketButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) ((Gdx.graphics.getWidth() - marketButton.getWidth())/2);
        int height2 = (int) ((Gdx.graphics.getHeight() - marketButton.getHeight())/2);
        marketButton.setBounds(width2, height2, marketButton.getWidth(), marketButton.getHeight());
        marketButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));
                //marketButton.setDisabled(false);

            }
        });
        marketButton.setTouchable(Touchable.disabled);
        stage.addActor(marketButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        /*// Button skin
        Skin lobbyButtonSkin = new Skin();
        lobbyButtonSkin.add("lobbyButton", new Texture("buttons/market_arrow.png"));

        // Create button style
        ImageButton.ImageButtonStyle lobbyButtonStyle = new ImageButton.ImageButtonStyle();
        lobbyButtonStyle.imageUp = lobbyButtonSkin.getDrawable("lobbyButton"); // Unpressed
        lobbyButtonStyle.imageDown = lobbyButtonSkin.getDrawable("lobbyButton"); // Pressed

        // Market button
        lobbyButton = new ImageButton(marketButtonStyle);
        int buttonSize3 = (int) (100 * Gdx.graphics.getDensity());
        lobbyButton.setSize(buttonSize2, buttonSize2);
        int width3 = (int) ((Gdx.graphics.getWidth() - lobbyButton.getWidth())/2);
        int height3 = (int) ((Gdx.graphics.getHeight() - lobbyButton.getHeight())/2);
        lobbyButton.setBounds(width3, height3, lobbyButton.getWidth(), lobbyButton.getHeight());
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));
                //marketButton.setDisabled(false);

            }
        });
        lobbyButton.setTouchable(Touchable.disabled);
        stage.addActor(lobbyButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));*/
    }


    @Override
    public void render(float delta) {
        System.out.println("marketshow" + ProjectOdyssey.marketShow);
        playTime = playTime + Gdx.graphics.getDeltaTime();
        playIntSec = (int)playTime;
        /*if(playIntSec == 60){
            playIntSec = 0;
            playIntHour++;
        }*/
        System.out.println(ProjectOdyssey.ice);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        batch.begin();
        moneyCounter.setColor(Color.TEAL);
        moneyCounter.draw(batch, "You have " + ProjectOdyssey.ice + " ice.", 100, 100);
        batch.end();


        batch.begin();
        iceButton.draw(batch, 1);
        batch.end();
        camera.update();

        if(ProjectOdyssey.ice >= 5 || ProjectOdyssey.marketShow == true) {
            marketButton.setTouchable(Touchable.enabled);
            batch.begin();
            marketButton.draw(batch, 1);
            batch.end();
            ProjectOdyssey.marketShow = true;
        }



    }

    public void inventory(){

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}

