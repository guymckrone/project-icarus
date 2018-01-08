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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by guymc on 11/30/2017.
 */

//Sell and Buy

public class Market implements Screen, GestureDetector.GestureListener{
    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;
    private ImageButton sellIce;
    private ImageButton gameButton;
    int moneys;


    private Texture logo;
    OrthographicCamera camera;

    public Market(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        // Button skin
        Skin sellIceSkin = new Skin();//Creates new skin
        sellIceSkin.add("sellIce", new Texture("buttons/play_button.png"));//sets button skin to image from assets

        // Create button style
        ImageButton.ImageButtonStyle sellIceStyle = new ImageButton.ImageButtonStyle();//creates imagebutton style for new button
        sellIceStyle.imageUp = sellIceSkin.getDrawable("sellIce"); // Unpressed
        sellIceStyle.imageDown = sellIceSkin.getDrawable("sellIce"); // Pressed

        // Play button
        sellIce = new ImageButton(sellIceStyle);//creates button
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());//sets variables for future button size
        sellIce.setSize(buttonSize, buttonSize);//uses variables to set size
        int width = (int) ((Gdx.graphics.getWidth() - sellIce.getWidth())/2);//Finding width of button for later use
        int height = (int) ((Gdx.graphics.getHeight() - sellIce.getHeight())/4);//Finding height of button for later use
        sellIce.setBounds(width, height, sellIce.getWidth(), sellIce.getHeight());//
        //Next few lines set the function of the bot
        sellIce.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
                //We dont want touch down to do anything, as touch up is better to use the touch up
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (ProjectOdyssey.ice > 0){//prevents from selling into negatives
                    ProjectOdyssey.ice--;//subtracts one ice each touchup
                    moneys = moneys + ProjectOdyssey.iceCubePrice;//adds iceprice amount of moneys each time you click button
                }
               // System.out.println(ProjectOdyssey.ice);//debug code, prints ice value
                sellIce.setDisabled(true);//disables after each press so it does not repeat

            }
        });
        stage.addActor(sellIce);//adds button to stage

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));//creates the gesture detector, without this you cannot have a button press

        // Button skin
        Skin gameButtonSkin = new Skin();
        gameButtonSkin.add("gameButton", new Texture("buttons/market_arrow.png"));

        // Create button style
        ImageButton.ImageButtonStyle gameButtonStyle = new ImageButton.ImageButtonStyle();
        gameButtonStyle.imageUp = gameButtonSkin.getDrawable("gameButton"); // Unpressed
        gameButtonStyle.imageDown = gameButtonSkin.getDrawable("gameButton"); // Pressed

        // Market button
        gameButton = new ImageButton(gameButtonStyle);
        int buttonSize2 = (int) (100 * Gdx.graphics.getDensity());
        gameButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) ((Gdx.graphics.getWidth() - gameButton.getWidth())/3);
        int height2 = (int) ((Gdx.graphics.getHeight() - gameButton.getHeight())/2);
        gameButton.setBounds(width2, height2, gameButton.getWidth(), gameButton.getHeight());
        gameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                //gameButton.setDisabled(false);

            }
        });
        //gameButton.setTouchable(Touchable.disabled);
        stage.addActor(gameButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "You have " + moneys + " money.", 100, 150);
        game.font.draw(game.batch, "You have " + ProjectOdyssey.ice + " ice.", 100, 100);
        game.batch.end();
        batch.begin();
        sellIce.draw(batch, 1);//draw button, opacity
        gameButton.draw(batch, 1);
        batch.end();
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
