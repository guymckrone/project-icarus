package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * Created by guymc on 11/29/2017.
 */

public class GameScreen implements Screen, GestureDetector.GestureListener {
    final ProjectOdyssey game;
    private Stage stage;
    int[] inventory = new int[10];
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;
    private ImageButton iceButton;
    public int ice;
    OrthographicCamera camera;
    float playTime;


    public GameScreen(final ProjectOdyssey game) {
        this.game = game;
        Gdx.graphics.getDeltaTime();
        playTime = 0;
        ice = 0;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Button skin
        Skin playButtonSkin = new Skin();
        playButtonSkin.add("iceButton", new Texture("buttons/ice_cube.png"));

        // Create button style
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = playButtonSkin.getDrawable("iceButton"); // Unpressed
        playButtonStyle.imageDown = playButtonSkin.getDrawable("iceButton"); // Pressed

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
                ice++;
                //iceButton.setDisabled(true);

            }
        });
        stage.addActor(iceButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, "Welcome to your Odyssey", 100, 150);
        game.font.draw(game.batch, "You have " + ice + " ice.", 100, 100);
        game.batch.end();

        batch.begin();
        iceButton.draw(batch, 1);
        batch.end();
        camera.update();

        playTime = playTime + Gdx.graphics.getDeltaTime();
        System.out.println(playTime);

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

