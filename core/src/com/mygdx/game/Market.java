package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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

    private ImageButton gameButton;
    private ImageButton sellIce;
    private ImageButton sbButton;
    private ImageButton bucketButton;
    private ImageButton shovelButton;
    private ImageButton sellModeButton;
    private ImageButton buyModeButton;
    private ImageButton creamButton;
    private ImageButton flakeButton;
    private ImageButton snowManButton;

    private Table marketTable;
    private boolean sellMode = true;




    private Texture logo;
    OrthographicCamera camera;
    public Market(final ProjectOdyssey game){
        System.out.println("SellIce???");
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        // Button skin
        Skin sellIceSkin = new Skin();//Creates new skin
        sellIceSkin.add("sellIce", new Texture("Market/IceCube.png"));//sets button skin to image from assets

        // Create button style
        ImageButton.ImageButtonStyle sellIceStyle = new ImageButton.ImageButtonStyle();//creates imagebutton style for new button
        sellIceStyle.imageUp = sellIceSkin.getDrawable("sellIce"); // Unpressed
        sellIceStyle.imageDown = sellIceSkin.getDrawable("sellIce"); // Pressed

        // Play button
        sellIce = new ImageButton(sellIceStyle);//creates button
        int buttonSize = (int) (Gdx.graphics.getDensity());//sets variables for future button size
        sellIce.setSize(100*buttonSize, 100*buttonSize);//uses variables to set size
        int width = (int) ((Gdx.graphics.getWidth() - sellIce.getWidth())/2);//Finding width of button for later use
        int height = (int) ((Gdx.graphics.getHeight() - sellIce.getHeight())/2);//Finding height of button for later use
        sellIce.setBounds(width, height, sellIce.getWidth(), sellIce.getHeight());//
        //Next few lines set the function of the bot
        sellIce.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
                //We dont want touch down to do anything, as touch up is better to use
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (sellMode == true && ProjectOdyssey.ice > 0){//prevents from selling into negatives
                    ProjectOdyssey.ice--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.iceCubePrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.iceCubePrice ){//prevents from selling into negatives
                    ProjectOdyssey.ice++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.iceCubePrice;//adds iceprice amount of moneys each time you click button
                }
               // System.out.println(ProjectOdyssey.ice);//debug code, prints ice value
                //sellIce.setDisabled(true);//disables after each press so it does not repeat

            }
        });
        stage.addActor(sellIce);//adds button to stage

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));//creates the gesture detector, without this you cannot have a button press

        // Button skin
        Skin gameButtonSkin = new Skin();
        gameButtonSkin.add("gameButton", new Texture("buttons/ArrowRight.png"));

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
                //game.setScreen(new GameScreen(game));
                //gameButton.setDisabled(false);

            }
        });
        //gameButton.setTouchable(Touchable.disabled);
        stage.addActor(gameButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin sbButtonSkin = new Skin();
        sbButtonSkin.add("sbButton", new Texture("Market/SnowBall.png"));

        // Create button style
        ImageButton.ImageButtonStyle sbButtonStyle = new ImageButton.ImageButtonStyle();
        sbButtonStyle.imageUp = sbButtonSkin.getDrawable("sbButton"); // Unpressed
        sbButtonStyle.imageDown = sbButtonSkin.getDrawable("sbButton"); // Pressed

        // Market button
        sbButton = new ImageButton(sbButtonStyle);
        int buttonSize3 = (int) (Gdx.graphics.getDensity());
        sbButton.setSize(100*buttonSize3, 100*buttonSize3);
        int width3 = (int) (((Gdx.graphics.getWidth() - (2*sbButton.getWidth()))/6)*2);
        int height3 = (int) (((Gdx.graphics.getHeight() - (sbButton.getHeight()))/4)*3);
        sbButton.setBounds(width3, height3, sbButton.getWidth(), sbButton.getHeight());
        sbButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (sellMode == true && ProjectOdyssey.snowBall > 0){//prevents from selling into negatives
                    ProjectOdyssey.snowBall--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.snowBallPrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.snowBallPrice ){//prevents from selling into negatives
                    ProjectOdyssey.snowBall++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.snowBallPrice;//adds iceprice amount of moneys each time you click button
                }
                //marketButton.setDisabled(false);

            }
        });
        //sbButton.setTouchable(Touchable.disabled);
        stage.addActor(sbButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // Button skin
        Skin bucketButtonSkin = new Skin();
        bucketButtonSkin.add("bucketButton", new Texture("Market/Bucket.png"));

        // Create button style
        ImageButton.ImageButtonStyle bucketButtonStyle = new ImageButton.ImageButtonStyle();
        bucketButtonStyle.imageUp = bucketButtonSkin.getDrawable("bucketButton"); // Unpressed
        bucketButtonStyle.imageDown = bucketButtonSkin.getDrawable("bucketButton"); // Pressed

        // Market button
        bucketButton = new ImageButton(bucketButtonStyle);
        int buttonSize4 = (int) (100 * Gdx.graphics.getDensity());
        bucketButton.setSize(buttonSize4, buttonSize4);
        int width4 = (int) (((Gdx.graphics.getWidth() - bucketButton.getWidth()))/6)*4;
        int height4 = (int) (((Gdx.graphics.getHeight() - bucketButton.getHeight())/4)*3);
        bucketButton.setBounds(width4, height4, bucketButton.getWidth(), bucketButton.getHeight());

        bucketButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (sellMode == true && ProjectOdyssey.bucket > 0){//prevents from selling into negatives
                    ProjectOdyssey.bucket--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.bucketPrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.bucketPrice ){//prevents from selling into negatives
                    ProjectOdyssey.bucket++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.bucketPrice;//adds iceprice amount of moneys each time you click button
                }
                //ProjectOdyssey.bucket++;
                //game.setScreen(new MainMenuScreen(game));
                //marketButton.setDisabled(false);

            }
        });

        //bucketButton.setTouchable(Touchable.disabled);
        stage.addActor(bucketButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin shovelButtonSkin = new Skin();
        shovelButtonSkin.add("shovelButton", new Texture("buttons/market_arrow.png"));

        // Create button style
        ImageButton.ImageButtonStyle shovelButtonStyle = new ImageButton.ImageButtonStyle();
        shovelButtonStyle.imageUp = shovelButtonSkin.getDrawable("shovelButton"); // Unpressed
        shovelButtonStyle.imageDown = shovelButtonSkin.getDrawable("shovelButton"); // Pressed

        // Market button
        shovelButton = new ImageButton(shovelButtonStyle);
        int buttonSize5 = (int) (100 * Gdx.graphics.getDensity());
        shovelButton.setSize(buttonSize5, buttonSize5);
        int width5 = (int) ((Gdx.graphics.getWidth() - shovelButton.getWidth())/2);
        int height5 = (int) ((Gdx.graphics.getHeight() - shovelButton.getHeight())/2);
        shovelButton.setBounds(width5, height5, shovelButton.getWidth(), shovelButton.getHeight());
        shovelButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));


            }
        });
        shovelButton.setTouchable(Touchable.disabled);
        stage.addActor(shovelButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin sellModeButtonSkin = new Skin();
        sellModeButtonSkin.add("sellModeButton", new Texture("Market/SellOn.png"));

        // Create button style
        ImageButton.ImageButtonStyle sellModeButtonStyle = new ImageButton.ImageButtonStyle();
        sellModeButtonStyle.imageUp = sellModeButtonSkin.getDrawable("sellModeButton"); // Unpressed
        sellModeButtonStyle.imageDown = sellModeButtonSkin.getDrawable("sellModeButton"); // Pressed

        // Play button
        sellModeButton = new ImageButton(sellModeButtonStyle);
        int buttonSize6 = (int) (100 * Gdx.graphics.getDensity());
        sellModeButton.setSize(buttonSize6, buttonSize6);
        int width6 = (int) ((Gdx.graphics.getWidth() - sellModeButton.getWidth())/2);
        int height6 = (int) (((Gdx.graphics.getHeight() - sellModeButton.getHeight())/4));
        sellModeButton.setBounds(width6, height6, sellModeButton.getWidth(), sellModeButton.getHeight());
        sellModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                sellMode = false;
                sellModeButton.setTouchable(Touchable.disabled);
                buyModeButton.setTouchable((Touchable.enabled));
                System.out.println("buy");



            }
        });

        stage.addActor(sellModeButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin buyModeButtonSkin = new Skin();
        buyModeButtonSkin.add("buyModeButton", new Texture("Market/BuyOn.png"));

        // Create button style
        ImageButton.ImageButtonStyle buyModeButtonStyle = new ImageButton.ImageButtonStyle();
        buyModeButtonStyle.imageUp = buyModeButtonSkin.getDrawable("buyModeButton"); // Unpressed
        buyModeButtonStyle.imageDown = buyModeButtonSkin.getDrawable("buyModeButton"); // Pressed

        // Play button
        buyModeButton = new ImageButton(buyModeButtonStyle);
        int buttonSize7 = (int) (100 * Gdx.graphics.getDensity());
        buyModeButton.setSize(buttonSize7, buttonSize7);
        int width7 = (int) ((Gdx.graphics.getWidth() - buyModeButton.getWidth())/2);
        int height7 = (int) (((Gdx.graphics.getHeight() - buyModeButton.getHeight())/4));
        buyModeButton.setBounds(width7, height7, buyModeButton.getWidth(), buyModeButton.getHeight());
        buyModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                sellMode = true;
                buyModeButton.setTouchable(Touchable.disabled);
                sellModeButton.setTouchable((Touchable.enabled));
                System.out.println("sell");

            }
        });

        stage.addActor(buyModeButton);

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
        game.font.draw(game.batch, "You have " + ProjectOdyssey.shovel + " shovels.", 100, 200);
        game.font.draw(game.batch, "You have " + ProjectOdyssey.snowBall + " snowballs.", 100, 175);
        game.font.draw(game.batch, "You have " + ProjectOdyssey.bucket + " buckets.", 100, 150);
        game.font.draw(game.batch, "You have " + ProjectOdyssey.moneys + " money.", 100, 125);
        game.font.draw(game.batch, "You have " + ProjectOdyssey.ice + " ice.", 100, 100);
        game.batch.end();
        batch.begin();
        sellIce.draw(batch, 1);//draw button, opacity
        if (sellMode == true){
            sellModeButton.draw(batch,1);
        }
        if (sellMode == false){
            buyModeButton.draw(batch,1);
        }
        //gameButton.draw(batch, 1);
        sbButton.draw(batch, 1);
        bucketButton.draw(batch, 1);
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
