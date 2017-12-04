package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Created by guymc on 11/29/2017.
 */

public class MainMenuScreen implements Screen, GestureDetector.GestureListener{


    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;
    private ImageButton playButton;
    private Texture logo;
    OrthographicCamera camera;

    public MainMenuScreen(final ProjectOdyssey game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Button skin
        Skin playButtonSkin = new Skin();
        playButtonSkin.add("playButton", new Texture("buttons/play_button.png"));

        // Create button style
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = playButtonSkin.getDrawable("playButton"); // Unpressed
        playButtonStyle.imageDown = playButtonSkin.getDrawable("playButton"); // Pressed

        // Play button
        playButton = new ImageButton(playButtonStyle);
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());
        playButton.setSize(buttonSize, buttonSize);
        int width = (int) ((Gdx.graphics.getWidth() - playButton.getWidth())/2);
        int height = (int) ((Gdx.graphics.getHeight() - playButton.getHeight())/4);
        playButton.setBounds(width, height, playButton.getWidth(), playButton.getHeight());
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game)); // Switch screen to game state
                playButton.setDisabled(true);

            }
        });
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));



        System.out.println("Testing102");
        //this.game = game;
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //game.assets.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to your Odyssey", 100, 150);
        game.batch.end();

        batch.begin();
        playButton.draw(batch, 1);
        batch.end();

        if (Gdx.input.isTouched()) {
            //game.setScreen(new GameScreen(game));
            dispose();
        }

        camera.update();
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
