package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.mygdx.game.ProjectOdyssey.ice;
import static com.mygdx.game.ProjectOdyssey.moneys;
/**
 * Created by nleof4499 on 3/6/2018.
 */

public class Book implements Screen, GestureDetector.GestureListener {

    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont moneyCounter = new BitmapFont();
    private SpriteBatch batch;

    private int screenWidth = Gdx.graphics.getWidth(); //Variable with screen width in it
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    private int unlockPrice = 100;

    public static int upgradeOneProgress = 0; //This int tracks which upgrade the first upgrade space will show
    public static int upgradeTwoProgress = 0;
    public static int upgradeThreeProgress = 0;

    ImageButton lobbyButton;
    ImageButton upgradeOne;
    ImageButton upgradeTwo;
    ImageButton upgradeThree;

    OrthographicCamera camera;

    public Book(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        // Button skin
        Skin lobbyButtonSkin = new Skin();
        lobbyButtonSkin.add("lobbyButton", new Texture("buttons/ArrowLeft.png"));

        // Create button style
        ImageButton.ImageButtonStyle lobbyButtonStyle = new ImageButton.ImageButtonStyle();
        lobbyButtonStyle.imageUp = lobbyButtonSkin.getDrawable("lobbyButton"); // Unpressed
        lobbyButtonStyle.imageDown = lobbyButtonSkin.getDrawable("lobbyButton"); // Pressed

        // Market button
        lobbyButton = new ImageButton(lobbyButtonStyle);
        int buttonSize2 = (int) (75 * Gdx.graphics.getDensity());
        lobbyButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) (((Gdx.graphics.getWidth() - lobbyButton.getWidth())/4));
        int height2 = (int) ((Gdx.graphics.getHeight() - lobbyButton.getHeight())/20);
        lobbyButton.setBounds(width2, height2, lobbyButton.getWidth(), lobbyButton.getHeight());
        lobbyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Lobby(game));
            }
        });
        stage.addActor(lobbyButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin upgradeOneSkin = new Skin();
        upgradeOneSkin.add("upgradeOneButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeOneStyle = new ImageButton.ImageButtonStyle();
        upgradeOneStyle.imageUp = upgradeOneSkin.getDrawable("upgradeOneButton"); // Unpressed
        upgradeOneStyle.imageDown = upgradeOneSkin.getDrawable("upgradeOneButton"); // Pressed

        // Market button
        upgradeOne= new ImageButton(upgradeOneStyle);
        int buttonSize3 = (int) (75 * Gdx.graphics.getDensity());
        upgradeOne.setSize(buttonSize3, buttonSize3);
        int width3 = (int) (((Gdx.graphics.getWidth() - upgradeOne.getWidth())/1.15));
        int height3 = (int) ((Gdx.graphics.getHeight() - upgradeOne.getHeight())/1.3);
        upgradeOne.setBounds(width3, height3, upgradeOne.getWidth(), upgradeOne.getHeight());
        upgradeOne.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (upgradeOneProgress == 0 || moneys > 99){
                    upgradeOneProgress = 1;
                    moneys = moneys - 100;

                }
                else if (upgradeOneProgress == 1 || moneys > 299){

                }
                else if (upgradeOneProgress == 2 || moneys > 499){

                }
            }
        });
        stage.addActor(upgradeOne);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin upgradeTwoSkin = new Skin();
        upgradeTwoSkin.add("upgradeTwoButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeTwoStyle = new ImageButton.ImageButtonStyle();
        upgradeTwoStyle.imageUp = upgradeTwoSkin.getDrawable("upgradeTwoButton"); // Unpressed
        upgradeTwoStyle.imageDown = upgradeTwoSkin.getDrawable("upgradeTwoButton"); // Pressed

        // Market button
        upgradeTwo = new ImageButton(upgradeTwoStyle);
        int buttonSize4 = (int) (75 * Gdx.graphics.getDensity());
        upgradeTwo.setSize(buttonSize4, buttonSize4);
        int width4 = (int) (((Gdx.graphics.getWidth() - upgradeTwo.getWidth())/1.15));
        int height4 = (int) ((Gdx.graphics.getHeight() - upgradeTwo.getHeight())/2);
        upgradeTwo.setBounds(width4, height4, upgradeTwo.getWidth(), upgradeTwo.getHeight());
        upgradeTwo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Lobby(game));
            }
        });
        stage.addActor(upgradeTwo);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin upgradeThreeSkin = new Skin();
        upgradeThreeSkin.add("upgradeThreeButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeThreeStyle = new ImageButton.ImageButtonStyle();
        upgradeThreeStyle.imageUp = upgradeThreeSkin.getDrawable("upgradeThreeButton"); // Unpressed
        upgradeThreeStyle.imageDown = upgradeThreeSkin.getDrawable("upgradeThreeButton"); // Pressed

        // Market button
        upgradeThree = new ImageButton(upgradeThreeStyle);
        int buttonSize5 = (int) (75 * Gdx.graphics.getDensity());
        upgradeThree.setSize(buttonSize5, buttonSize5);
        int width5 = (int) (((Gdx.graphics.getWidth() - upgradeThree.getWidth())/1.15));
        int height5 = (int) ((Gdx.graphics.getHeight() - upgradeThree.getHeight())/4.8);
        upgradeThree.setBounds(width5, height5, upgradeThree.getWidth(), upgradeThree.getHeight());
        upgradeThree.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Lobby(game));
            }
        });
        stage.addActor(upgradeThree);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        batch.begin(); //Begin printing money counter

        moneyCounter.setColor(Color.BLACK); //money counter text color
        if (moneys < 10) { //position money counter
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.88 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x<10
        }
        else if (moneys > 999999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.58 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>999999
        }
        else if (moneys > 99999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.63 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>99999
        }
        else if (moneys > 9999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.68 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>9999
        }
        else if (moneys > 999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.73 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>999
        }
        else if (moneys > 99) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.78 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>99
        }
        else if (moneys > 9) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.83 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>9
        }


        if (upgradeOneProgress == 0){
            moneyCounter.draw(batch, "$100: Produce one", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cube", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 1){
            moneyCounter.draw(batch, "$300: Produce three", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 2){
            moneyCounter.draw(batch, "$500: Produce seven", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }

        if (upgradeTwoProgress == 0){
            moneyCounter.draw(batch, "$500: Produce one", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowball", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 1){

        }

        if (upgradeThreeProgress == 0){
            moneyCounter.draw(batch, "$2000: Produce one", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more bucket", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 1){

        }


        lobbyButton.draw(batch,1);
        upgradeOne.draw(batch,1);
        upgradeTwo.draw(batch,1);
        upgradeThree.draw(batch,1);
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
        moneyCounter.dispose();
        stage.dispose();
        batch.dispose();
        game.dispose();
    }
}
