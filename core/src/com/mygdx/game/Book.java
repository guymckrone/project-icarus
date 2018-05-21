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
        parameter.size = (int)(Gdx.graphics.getWidth()/15.4285714286);
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$% ";
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
        int width2 = (int) (Gdx.graphics.getWidth() *.3 - (lobbyButton.getWidth()/2));
        int height2 = (int) (Gdx.graphics.getHeight() *.1  - (lobbyButton.getHeight()/2));
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
        upgradeOneSkin.add("upgradeOneButton", new Texture("buttons/Purchase.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeOneStyle = new ImageButton.ImageButtonStyle();
        upgradeOneStyle.imageUp = upgradeOneSkin.getDrawable("upgradeOneButton"); // Unpressed
        upgradeOneStyle.imageDown = upgradeOneSkin.getDrawable("upgradeOneButton"); // Pressed

        // Market button
        upgradeOne= new ImageButton(upgradeOneStyle);
        int buttonSize3 = (int) (125 * Gdx.graphics.getDensity());
        upgradeOne.setSize(buttonSize3, buttonSize3);
        int width3 = (int) (Gdx.graphics.getWidth() * .75  - (upgradeOne.getWidth()/2));
        int height3 = (int) (Gdx.graphics.getHeight() *.72 - (upgradeOne.getHeight()/2));
        upgradeOne.setBounds(width3, height3, upgradeOne.getWidth(), upgradeOne.getHeight());
        upgradeOne.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (upgradeOneProgress == 0 && moneys > 149){
                    upgradeOneProgress = 1;
                    moneys = moneys - 150;
                    ProjectOdyssey.upgradeOneOne = true;
                }
                else if (upgradeOneProgress == 1 && moneys > 569){
                    upgradeOneProgress = 2;
                    moneys = moneys - 570;
                    ProjectOdyssey.upgradeOneTwo = true;
                }
                else if (upgradeOneProgress == 2 && moneys > 1299){
                    upgradeOneProgress = 3;
                    moneys = moneys - 1300;
                    ProjectOdyssey.upgradeOneThree = true;
                }
                else if (upgradeOneProgress == 3 && moneys > 1019){
                    upgradeOneProgress = 4;
                    moneys = moneys - 1350;
                    ProjectOdyssey.upgradeOneFour = true;
                }
                else if (upgradeOneProgress == 4 && moneys > 3239){
                    upgradeOneProgress = 5;
                    moneys = moneys - 3240;
                    ProjectOdyssey.upgradeOneFive = true;
                }
                else if (upgradeOneProgress == 5 && moneys > 4679){
                    upgradeOneProgress = 6;
                    moneys = moneys - 4680;
                    ProjectOdyssey.upgradeOneSix = true;
                }
                else if (upgradeOneProgress == 6 && moneys > 4949){
                    upgradeOneProgress = 7;
                    moneys = moneys - 4950;
                    ProjectOdyssey.upgradeOneSeven = true;
                }
                else if (upgradeOneProgress == 7 && moneys > 9799){
                    upgradeOneProgress = 8;
                    moneys = moneys - 9800;
                    ProjectOdyssey.upgradeOneEight = true;
                }
                else if (upgradeOneProgress == 8 && moneys > 14199){
                    upgradeOneProgress = 9;
                    moneys = moneys - 14200;
                    ProjectOdyssey.upgradeOneNine = true;
                }
                else if (upgradeOneProgress == 9 && moneys > 29999){
                    upgradeOneProgress = 10;
                    moneys = moneys - 30000;
                    ProjectOdyssey.upgradeOneTen = true;
                }
                else if (upgradeOneProgress == 10 && moneys > 59999){
                    upgradeOneProgress = 11;
                    moneys = moneys - 60000;
                    ProjectOdyssey.upgradeOneEleven = true;
                }
                else if (upgradeOneProgress == 11 && moneys > 119999){
                    upgradeOneProgress = 12;
                    moneys = moneys - 120000;
                    ProjectOdyssey.upgradeOneTwelve = true;

                }

            }
        });
        stage.addActor(upgradeOne);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin upgradeTwoSkin = new Skin();
        upgradeTwoSkin.add("upgradeTwoButton", new Texture("buttons/Purchase.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeTwoStyle = new ImageButton.ImageButtonStyle();
        upgradeTwoStyle.imageUp = upgradeTwoSkin.getDrawable("upgradeTwoButton"); // Unpressed
        upgradeTwoStyle.imageDown = upgradeTwoSkin.getDrawable("upgradeTwoButton"); // Pressed

        // Market button
        upgradeTwo = new ImageButton(upgradeTwoStyle);
        int buttonSize4 = (int) (125 * Gdx.graphics.getDensity());
        upgradeTwo.setSize(buttonSize4, buttonSize4);
        int width4 = (int) (Gdx.graphics.getWidth() *.75 - (upgradeTwo.getWidth()/2));
        int height4 = (int) (Gdx.graphics.getHeight() * .47 - (upgradeTwo.getHeight()/2));
        upgradeTwo.setBounds(width4, height4, upgradeTwo.getWidth(), upgradeTwo.getHeight());
        upgradeTwo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (upgradeTwoProgress == 0 && moneys > 1499){ //500, 1750, 5000
                    upgradeTwoProgress = 1;
                    moneys = moneys - 1450;
                    ProjectOdyssey.upgradeTwoOne = true;
                }
                else if (upgradeTwoProgress == 1 && moneys > 5199){
                    upgradeTwoProgress = 2;
                    moneys = moneys - 5200;
                    ProjectOdyssey.upgradeTwoTwo = true;
                }
                else if (upgradeTwoProgress == 2 && moneys > 8999){
                    upgradeTwoProgress = 3;
                    moneys = moneys - 9000;
                    ProjectOdyssey.upgradeTwoThree = true;
                }
                else if (upgradeTwoProgress == 3 && moneys > 9299){
                    upgradeTwoProgress = 4;
                    moneys = moneys - 9300;
                    ProjectOdyssey.upgradeTwoFour = true;
                }
                else if (upgradeTwoProgress == 4 && moneys > 14499){
                    upgradeTwoProgress = 5;
                    moneys = moneys - 14500;
                    ProjectOdyssey.upgradeTwoFive = true;
                }
                else if (upgradeTwoProgress == 5 && moneys > 23299){
                    upgradeTwoProgress = 6;
                    moneys = moneys - 23300;
                    ProjectOdyssey.upgradeTwoSix = true;
                }
                else if (upgradeTwoProgress == 6 && moneys > 27999){
                    upgradeTwoProgress = 7;
                    moneys = moneys - 28000;
                    ProjectOdyssey.upgradeTwoSeven = true;
                }
                else if (upgradeTwoProgress == 7 && moneys > 34999){
                    upgradeTwoProgress = 8;
                    moneys = moneys - 35000;
                    ProjectOdyssey.upgradeTwoEight = true;
                }
                else if (upgradeTwoProgress == 8 && moneys > 46599){
                    upgradeTwoProgress = 9;
                    moneys = moneys - 47600;
                    ProjectOdyssey.upgradeTwoNine = true;
                }
                else if (upgradeTwoProgress == 9 && moneys > 119999){
                    upgradeTwoProgress = 10;
                    moneys = moneys - 120000;
                    ProjectOdyssey.upgradeTwoTen = true;
                }
                else if (upgradeTwoProgress == 10 && moneys > 179999){
                    upgradeTwoProgress = 11;
                    moneys = moneys - 180000;
                    ProjectOdyssey.upgradeTwoEleven = true;
                }
                else if (upgradeTwoProgress == 11 && moneys > 359999){
                    upgradeTwoProgress = 12;
                    moneys = moneys - 360000;
                    ProjectOdyssey.upgradeTwoTwelve = true;
                }

            }
        });
        stage.addActor(upgradeTwo);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin upgradeThreeSkin = new Skin();
        upgradeThreeSkin.add("upgradeThreeButton", new Texture("buttons/Purchase.png"));

        // Create button style
        ImageButton.ImageButtonStyle upgradeThreeStyle = new ImageButton.ImageButtonStyle();
        upgradeThreeStyle.imageUp = upgradeThreeSkin.getDrawable("upgradeThreeButton"); // Unpressed
        upgradeThreeStyle.imageDown = upgradeThreeSkin.getDrawable("upgradeThreeButton"); // Pressed

        // Market button
        upgradeThree = new ImageButton(upgradeThreeStyle);
        int buttonSize5 = (int) (125 * Gdx.graphics.getDensity());
        upgradeThree.setSize(buttonSize5, buttonSize5);
        int width5 = (int) (Gdx.graphics.getWidth() *.75 - (upgradeThree.getWidth()/2));
        int height5 = (int) (Gdx.graphics.getHeight() *.21 - (upgradeThree.getHeight()/2));
        upgradeThree.setBounds(width5, height5, upgradeThree.getWidth(), upgradeThree.getHeight());
        upgradeThree.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (upgradeThreeProgress == 0 && moneys > 42399){
                    upgradeThreeProgress = 1;
                    moneys = moneys - 42400;
                    ProjectOdyssey.upgradeThreeOne = true;
                }
                else if (upgradeThreeProgress == 1 && moneys > 65999){
                    upgradeThreeProgress = 2;
                    moneys = moneys - 66000;
                    ProjectOdyssey.upgradeThreeTwo = true;
                }
                else if (upgradeThreeProgress == 2 && moneys > 116999){
                    upgradeThreeProgress = 3;
                    moneys = moneys - 117000;
                    ProjectOdyssey.upgradeThreeThree = true;
                }
                else if (upgradeThreeProgress == 3 && moneys > 124999){
                    upgradeThreeProgress = 4;
                    moneys = moneys - 125000;
                    ProjectOdyssey.upgradeThreeFour = true;
                }
                else if (upgradeThreeProgress == 4 && moneys > 179999){
                    upgradeThreeProgress = 5;
                    moneys = moneys - 180000;
                    ProjectOdyssey.upgradeThreeFive = true;
                }
                else if (upgradeThreeProgress == 5 && moneys > 317999){
                    upgradeThreeProgress = 6;
                    moneys = moneys - 318000;
                    ProjectOdyssey.upgradeThreeSix = true;
                }
                else if (upgradeThreeProgress == 6 && moneys > 499999){
                    upgradeThreeProgress = 7;
                    moneys = moneys - 500000;
                    ProjectOdyssey.upgradeThreeSeven = true;
                }
                else if (upgradeThreeProgress == 7 && moneys > 1199999){
                    upgradeThreeProgress = 8;
                    moneys = moneys - 1200000;
                    ProjectOdyssey.upgradeThreeEight = true;
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
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.825 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>9
        }

        //upgrade path 1
        if (upgradeOneProgress == 0){
            moneyCounter.draw(batch, "$150: Produce 1", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cube", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 1){
            moneyCounter.draw(batch, "$570: Produce 4", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 2){
            moneyCounter.draw(batch, "$1300: Produce 10", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more ice cubes", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 3){
            moneyCounter.draw(batch, "$1350: Produce 3", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 4){
            moneyCounter.draw(batch, "$3240: Produce 8", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 5){
            moneyCounter.draw(batch, "$4680: Produce 13", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more snowballs", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 6){
            moneyCounter.draw(batch, "$4950: Produce 5", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 7){
            moneyCounter.draw(batch, "$9800: Produce 11", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 8){
            moneyCounter.draw(batch, "$14200: Produce 16", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "more buckets", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 9){
            moneyCounter.draw(batch, "$30000: Increase", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "ice cubes: 50%", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 10){
            moneyCounter.draw(batch, "$60000: Increase", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "snowballs: 50%", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
        else if (upgradeOneProgress == 11){
            moneyCounter.draw(batch, "$120000: Increase", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "buckets: 50%", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }
/*        else if (upgradeOneProgress == 12){
            moneyCounter.draw(batch, "$120000: Increase", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
            moneyCounter.draw(batch, "buckets: 50%", ((int)(.05 * screenWidth)), ((int)(.8 * screenHeight))); //
        }*/
        else if (upgradeOneProgress == 12){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.90 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.85 * screenHeight))); //
        }

        //upgrade path 2
        if (upgradeTwoProgress == 0){
            moneyCounter.draw(batch, "$1450: Produce 1", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more shovel", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 1){
            moneyCounter.draw(batch, "$5200: Produce 4", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more shovels", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 2){
            moneyCounter.draw(batch, "$9000: Produce 7", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more shovels", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 3){
            moneyCounter.draw(batch, "$9300: Produce 3", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more icecreams", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 4){
            moneyCounter.draw(batch, "$14500: Produce 5", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more icecreams", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 5){
            moneyCounter.draw(batch, "$23300: Produce 9", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more icecreams", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 6){
            moneyCounter.draw(batch, "$28000: Produce 5", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowflakes", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 7){
            moneyCounter.draw(batch, "$35000: Produce 7", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowflakes", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 8){
            moneyCounter.draw(batch, "$47600: Produce 10", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "more snowflakes", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 9){
            moneyCounter.draw(batch, "$120000: Increase", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "shovels: 50%", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 10){
            moneyCounter.draw(batch, "$180000: Increase", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "icecreams: 50%", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
        else if (upgradeTwoProgress == 11){
            moneyCounter.draw(batch, "$360000: Increase", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "snowflakes: 50%", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }
/*        else if (upgradeTwoProgress == 12){
            moneyCounter.draw(batch, "$1300000: Increase", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
            moneyCounter.draw(batch, "Snowballs: 50%", ((int)(.05 * screenWidth)), ((int)(.55 * screenHeight))); //
        }*/
        else if (upgradeTwoProgress == 12){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.65 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.6 * screenHeight))); //
        }

        //upgrade path 3
        if (upgradeThreeProgress == 0){
            moneyCounter.draw(batch, "$42400: Produce 4", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more icicles", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 1){
            moneyCounter.draw(batch, "$66000: Produce 7", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more icicles", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 2){
            moneyCounter.draw(batch, "$117000: Produce 13", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more icicles", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 3){
            moneyCounter.draw(batch, "$125000: Produce 5", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more snowmen", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 4){
            moneyCounter.draw(batch, "$180000: Produce 8", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more snowmen", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 5){
            moneyCounter.draw(batch, "$318000: Produce 13", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "more snowmen", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 6){
            moneyCounter.draw(batch, "$500000: Increase", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "icicles: 50%", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 7){
            moneyCounter.draw(batch, "$1200000: Increase", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "max value of", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "snowmen: 50%", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
/*        else if (upgradeThreeProgress == 8){
            moneyCounter.draw(batch, "$9999999: Produce 41", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every minute.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 9){
            moneyCounter.draw(batch, "$9999999: Produce 2", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "every second.", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 10){
            moneyCounter.draw(batch, "$9999999: Increase", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "buckets: 50%", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }
        else if (upgradeThreeProgress == 11){
            moneyCounter.draw(batch, "$9999999: Increase", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "buckets: 50%", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }*/
/*        else if (upgradeThreeProgress == 12){
            moneyCounter.draw(batch, "$9999999: Increase", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "PLACEHOLDER", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
            moneyCounter.draw(batch, "buckets: 50%", ((int)(.05 * screenWidth)), ((int)(.3 * screenHeight))); //
        }*/
        else if (upgradeThreeProgress == 8){
            moneyCounter.draw(batch, "All upgrades", ((int)(.05 * screenWidth)), ((int)(.4 * screenHeight))); //
            moneyCounter.draw(batch, "purchased.", ((int)(.05 * screenWidth)), ((int)(.35 * screenHeight))); //
        }




        lobbyButton.draw(batch,1);

        if (upgradeOneProgress != 12){//If we have bought all of the upgrades, dont show an upgrade button.
            upgradeOne.draw(batch,1);
        }
        else{
            upgradeOne.setTouchable(Touchable.disabled);
        }

        if (upgradeTwoProgress != 12){
            upgradeTwo.draw(batch,1);
        }
        else{
            upgradeTwo.setTouchable(Touchable.disabled);
        }

        if (upgradeThreeProgress != 8){
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
