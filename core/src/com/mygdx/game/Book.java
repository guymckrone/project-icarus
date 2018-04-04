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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Timer;
import java.util.TimerTask;

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
        final Timer t = new Timer(); //declare the timer for the upgrades method

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
                if (upgradeOneProgress == 0 && moneys > 99){
                    upgradeOneProgress = 1;
                    moneys = moneys - 100;
                    ProjectOdyssey.upgradeOneOne = true;
                }
                else if (upgradeOneProgress == 1 && moneys > 284){
                    upgradeOneProgress = 2;
                    moneys = moneys - 285;
                    ProjectOdyssey.upgradeOneTwo = true;
                }
                else if (upgradeOneProgress == 2 && moneys > 719){
                    upgradeOneProgress = 3;
                    moneys = moneys - 720;
                    ProjectOdyssey.upgradeOneThree = true;
                }
                else if (upgradeOneProgress == 3 && moneys > 1019){
                    upgradeOneProgress = 4;
                    moneys = moneys - 1020;
                    ProjectOdyssey.upgradeOneFour = true;
                }
                else if (upgradeOneProgress == 4 && moneys > 1439){
                    upgradeOneProgress = 5;
                    moneys = moneys - 1440;
                    ProjectOdyssey.upgradeOneFive = true;
                }
                else if (upgradeOneProgress == 5 && moneys > 1724){
                    upgradeOneProgress = 6;
                    moneys = moneys - 1725;
                    ProjectOdyssey.upgradeOneSix = true;
                }
                else if (upgradeOneProgress == 6 && moneys > 2239){
                    upgradeOneProgress = 7;
                    moneys = moneys - 2240;
                    ProjectOdyssey.upgradeOneSeven = true;
                }
                else if (upgradeOneProgress == 7 && moneys > 2794){
                    upgradeOneProgress = 8;
                    moneys = moneys - 2795;
                    ProjectOdyssey.upgradeOneEight = true;
                }
                else if (upgradeOneProgress == 8 && moneys > 3719){
                    upgradeOneProgress = 9;
                    moneys = moneys - 3720;
                    ProjectOdyssey.upgradeOneNine = true;
                }
                else if (upgradeOneProgress == 9 && moneys > 13199){
                    upgradeOneProgress = 10;
                    moneys = moneys - 13200;
                    ProjectOdyssey.upgradeOneTen = true;
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
                if (upgradeTwoProgress == 0 && moneys > 499){ //500, 1750, 5000
                    upgradeTwoProgress = 1;
                    moneys = moneys - 500;
                    ProjectOdyssey.upgradeTwoOne = true;
                }
                else if (upgradeTwoProgress == 1 && moneys > 949){
                    upgradeTwoProgress = 2;
                    moneys = moneys - 950;
                    ProjectOdyssey.upgradeTwoTwo = true;
                }
                else if (upgradeTwoProgress == 2 && moneys > 1799){
                    upgradeTwoProgress = 3;
                    moneys = moneys - 1800;
                    ProjectOdyssey.upgradeTwoThree = true;
                }
                else if (upgradeTwoProgress == 3 && moneys > 2974){
                    upgradeTwoProgress = 4;
                    moneys = moneys - 2975;
                    ProjectOdyssey.upgradeTwoFour = true;
                }
                else if (upgradeTwoProgress == 4 && moneys > 5199){
                    upgradeTwoProgress = 5;
                    moneys = moneys - 5200;
                    ProjectOdyssey.upgradeTwoFive = true;
                }
                else if (upgradeTwoProgress == 5 && moneys > 7124){
                    upgradeTwoProgress = 6;
                    moneys = moneys - 7125;
                    ProjectOdyssey.upgradeTwoSix = true;
                }
                else if (upgradeTwoProgress == 6 && moneys > 8399){
                    upgradeTwoProgress = 7;
                    moneys = moneys - 8400;
                    ProjectOdyssey.upgradeTwoSeven = true;
                }
                else if (upgradeTwoProgress == 7 && moneys > 10399){
                    upgradeTwoProgress = 8;
                    moneys = moneys - 10400;
                    ProjectOdyssey.upgradeTwoEight = true;
                }
                else if (upgradeTwoProgress == 8 && moneys > 12599){
                    upgradeTwoProgress = 9;
                    moneys = moneys - 12600;
                    ProjectOdyssey.upgradeTwoNine = true;
                }
                else if (upgradeTwoProgress == 9 && moneys > 49499){
                    upgradeTwoProgress = 10;
                    moneys = moneys - 49500;
                    ProjectOdyssey.upgradeTwoTen = true;
                }
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
                if (upgradeThreeProgress == 0 && moneys > 1999){ //2, 7, 14K
                    upgradeThreeProgress = 1;
                    moneys = moneys - 2000;
                    ProjectOdyssey.upgradeThreeOne = true;
                }
                else if (upgradeThreeProgress == 1 && moneys > 3799){
                    upgradeThreeProgress = 2;
                    moneys = moneys - 3800;
                    ProjectOdyssey.upgradeThreeTwo = true;
                }
                else if (upgradeThreeProgress == 2 && moneys > 5399){
                    upgradeThreeProgress = 3;
                    moneys = moneys - 5400;
                    ProjectOdyssey.upgradeThreeThree = true;
                }
                else if (upgradeThreeProgress == 3 && moneys > 10199){
                    upgradeThreeProgress = 4;
                    moneys = moneys - 10200;
                    ProjectOdyssey.upgradeThreeFour = true;
                }
                else if (upgradeThreeProgress == 4 && moneys > 17599){
                    upgradeThreeProgress = 5;
                    moneys = moneys - 17600;
                    ProjectOdyssey.upgradeThreeFive = true;
                }
                else if (upgradeThreeProgress == 5 && moneys > 23999){
                    upgradeThreeProgress = 6;
                    moneys = moneys - 24000;
                    ProjectOdyssey.upgradeThreeSix = true;
                }
                else if (upgradeThreeProgress == 6 && moneys > 32199){
                    upgradeThreeProgress = 7;
                    moneys = moneys - 32200;
                    ProjectOdyssey.upgradeThreeSeven = true;
                }
                else if (upgradeThreeProgress == 7 && moneys > 40299){
                    upgradeThreeProgress = 8;
                    moneys = moneys - 40300;
                    ProjectOdyssey.upgradeThreeEight = true;
                }
                else if (upgradeThreeProgress == 8 && moneys > 49199){
                    upgradeThreeProgress = 9;
                    moneys = moneys - 49200;
                    ProjectOdyssey.upgradeThreeNine = true;
                }
                else if (upgradeThreeProgress == 9 && moneys > 131999){
                    upgradeThreeProgress = 10;
                    moneys = moneys - 132000;
                    ProjectOdyssey.upgradeThreeTen = true;
                }
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

        //upgrade path 1
        if (upgradeOneProgress == 0){
            moneyCounter.draw(batch, "$100: Produce 1", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cube", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 1){
            moneyCounter.draw(batch, "$285: Produce 3", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 2){
            moneyCounter.draw(batch, "$720: Produce 8", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 3){
            moneyCounter.draw(batch, "$1020: Produce 12", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 4){
            moneyCounter.draw(batch, "$1440: Produce 18", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 5){
            moneyCounter.draw(batch, "$1725: Produce 23", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 6){
            moneyCounter.draw(batch, "$2240: Produce 32", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 7){
            moneyCounter.draw(batch, "$2795: Produce 43", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 8){
            moneyCounter.draw(batch, "$3720: Produce 62", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 9){
            moneyCounter.draw(batch, "$13200: Produce 4", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every second.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 10){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
        }

        //upgrade path 2
        if (upgradeTwoProgress == 0){
            moneyCounter.draw(batch, "$500: Produce 1", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowball", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 1){
            moneyCounter.draw(batch, "$950: Produce 2", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 2){
            moneyCounter.draw(batch, "$1800: Produce 4", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 3){
            moneyCounter.draw(batch, "$2975: Produce 7", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 4){
            moneyCounter.draw(batch, "$5200: Produce 13", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 5){
            moneyCounter.draw(batch, "$7125: Produce 19", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 6){
            moneyCounter.draw(batch, "$8400: Produce 24", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 7){
            moneyCounter.draw(batch, "$10400: Produce 31", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 8){
            moneyCounter.draw(batch, "$12600: Produce 24", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 9){
            moneyCounter.draw(batch, "$49500: Produce 3", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every second.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 10){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
        }

        //upgrade path 3
        if (upgradeThreeProgress == 0){
            moneyCounter.draw(batch, "$2000: Produce one", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more bucket", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 1){
            moneyCounter.draw(batch, "$3800: Produce 2", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 2){
            moneyCounter.draw(batch, "$5400: Produce 3", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 3){
            moneyCounter.draw(batch, "$10200: Produce 6", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 4){
            moneyCounter.draw(batch, "$17600: Produce 11", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 5){
            moneyCounter.draw(batch, "$24000: Produce 16", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 6){
            moneyCounter.draw(batch, "$32200: Produce 23", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 7){
            moneyCounter.draw(batch, "$40300: Produce 31", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 8){
            moneyCounter.draw(batch, "$49200: Produce 41", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 9){
            moneyCounter.draw(batch, "$132000: Produce 2", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every second.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 10){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
        }




        lobbyButton.draw(batch,1);

        if (upgradeOneProgress != 10){//If we have bought all of the upgrades, dont show an upgrade button.
            upgradeOne.draw(batch,1);
        }
        else{
            upgradeOne.setTouchable(Touchable.disabled);
        }

        if (upgradeTwoProgress != 10){
            upgradeTwo.draw(batch,1);
        }
        else{
            upgradeTwo.setTouchable(Touchable.disabled);
        }

        if (upgradeThreeProgress != 10){
            upgradeThree.draw(batch,1);

        }
        else{
            upgradeThree.setTouchable(Touchable.disabled);
        }
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
