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

import static com.mygdx.game.ProjectOdyssey.moneys;

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

    private ImageButton onePersonNoRing;
    private ImageButton onePersonRing;

    private ImageButton twoPersonNoRing;
    private ImageButton twoPersonRing;

    private ImageButton threePersonNoRing;
    private ImageButton threePersonRing;


    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    //public static int ice;
    OrthographicCamera camera;
    float playTime;
    int playIntSec;
    int playIntHour;
    String gameClock;

    public static int whichTableGameScreen = 0; //0 = onePersonNoRing, 1 = onePersonRing, 2 = twoPersonNoRing, 3 = twoPersonRing, 4 = threePersonNoRing, 5 = threePersonRing

    public GameScreen(final ProjectOdyssey game) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth()/15.4285714286);
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
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
        int width = (int) (Gdx.graphics.getWidth() * .5 - (iceButton.getWidth()/2));
        int height = (int) (Gdx.graphics.getHeight() * .3 - (iceButton.getHeight()/2));
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
        Skin lobbyButtonSkin = new Skin();
        lobbyButtonSkin.add("lobbyButton", new Texture("buttons/ArrowRight.png"));

        // Create button style
        ImageButton.ImageButtonStyle lobbyButtonStyle = new ImageButton.ImageButtonStyle();
        lobbyButtonStyle.imageUp = lobbyButtonSkin.getDrawable("lobbyButton"); // Unpressed
        lobbyButtonStyle.imageDown = lobbyButtonSkin.getDrawable("lobbyButton"); // Pressed

        // Market button
        lobbyButton = new ImageButton(lobbyButtonStyle);
        int buttonSize2 = (int) (75 * Gdx.graphics.getDensity());
        lobbyButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) (Gdx.graphics.getWidth() *.7 - (lobbyButton.getWidth()/2));
        int height2 = (int) (Gdx.graphics.getHeight() *.1 - (lobbyButton.getHeight()/2));
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
        Skin marketButtonSkin = new Skin();
        marketButtonSkin.add("marketTwoButton", new Texture("buttons/ArrowLeft.png"));

        // Create button style
        ImageButton.ImageButtonStyle marketButtonStyle = new ImageButton.ImageButtonStyle();
        marketButtonStyle.imageUp = marketButtonSkin.getDrawable("marketTwoButton"); // Unpressed
        marketButtonStyle.imageDown = marketButtonSkin.getDrawable("marketTwoButton"); // Pressed

        // Market button
        marketButton = new ImageButton(marketButtonStyle);
        int buttonSize3 = (int) (75 * Gdx.graphics.getDensity());
        marketButton.setSize(buttonSize3, buttonSize3);
        int width3 = (int) (Gdx.graphics.getWidth() *.3 - (marketButton.getWidth()/2));
        int height3 = (int) (Gdx.graphics.getHeight() *.1 - (marketButton.getHeight()/2));
        marketButton.setBounds(width3, height3, marketButton.getWidth(), marketButton.getHeight());
        marketButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));

            }
        });

        stage.addActor(marketButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // onePersonNoRing
        Skin onePersonNoRingSkin = new Skin();
        onePersonNoRingSkin.add("onePersonNoRing", new Texture("MainScreen/OnePersonNoRing.png"));

        ImageButton.ImageButtonStyle onePersonNoRingStyle = new ImageButton.ImageButtonStyle();
        onePersonNoRingStyle.imageUp = onePersonNoRingSkin.getDrawable("onePersonNoRing"); // Unpressed
        onePersonNoRingStyle.imageDown = onePersonNoRingSkin.getDrawable("onePersonNoRing"); // Pressed

        onePersonNoRing = new ImageButton(onePersonNoRingStyle);
        int buttonSize4 = (int) (300 * Gdx.graphics.getDensity());
        onePersonNoRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        int width4 = (int) (Gdx.graphics.getWidth() * .5 - (onePersonNoRing.getWidth()/2));
        int height4 = (int) (Gdx.graphics.getHeight() * .8 - (onePersonNoRing.getHeight()/2));
        onePersonNoRing.setBounds(width4, height4, onePersonNoRing.getWidth(), onePersonNoRing.getHeight());
        onePersonNoRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(onePersonNoRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // onePersonRing
        Skin onePersonRingSkin = new Skin();
        onePersonRingSkin.add("onePersonRing", new Texture("MainScreen/OnePersonRing.png"));

        ImageButton.ImageButtonStyle onePersonRingStyle = new ImageButton.ImageButtonStyle();
        onePersonRingStyle.imageUp = onePersonRingSkin.getDrawable("onePersonRing"); // Unpressed
        onePersonRingStyle.imageDown = onePersonRingSkin.getDrawable("onePersonRing"); // Pressed

        onePersonRing = new ImageButton(onePersonRingStyle);
        onePersonRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        onePersonRing.setBounds(width4, height4, onePersonRing.getWidth(), onePersonRing.getHeight());
        onePersonRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(onePersonRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // twoPersonNoRing
        Skin twoPersonNoRingSkin = new Skin();
        twoPersonNoRingSkin.add("twoPersonNoRing", new Texture("MainScreen/TwoPersonNoRing.png"));

        ImageButton.ImageButtonStyle twoPersonNoRingStyle = new ImageButton.ImageButtonStyle();
        twoPersonNoRingStyle.imageUp = twoPersonNoRingSkin.getDrawable("twoPersonNoRing"); // Unpressed
        twoPersonNoRingStyle.imageDown = twoPersonNoRingSkin.getDrawable("twoPersonNoRing"); // Pressed

        twoPersonNoRing = new ImageButton(twoPersonNoRingStyle);
        twoPersonNoRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        twoPersonNoRing.setBounds(width4, height4, twoPersonNoRing.getWidth(), twoPersonNoRing.getHeight());
        twoPersonNoRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(twoPersonNoRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // twoPersonRing
        Skin twoPersonRingSkin = new Skin();
        twoPersonRingSkin.add("twoPersonRing", new Texture("MainScreen/TwoPersonRing.png"));

        ImageButton.ImageButtonStyle twoPersonRingStyle = new ImageButton.ImageButtonStyle();
        twoPersonRingStyle.imageUp = twoPersonRingSkin.getDrawable("twoPersonRing"); // Unpressed
        twoPersonRingStyle.imageDown = twoPersonRingSkin.getDrawable("twoPersonRing"); // Pressed

        twoPersonRing = new ImageButton(twoPersonRingStyle);
        twoPersonRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        twoPersonRing.setBounds(width4, height4, twoPersonRing.getWidth(), twoPersonRing.getHeight());
        twoPersonRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(twoPersonRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // threePersonNoRing
        Skin threePersonNoRingSkin = new Skin();
        threePersonNoRingSkin.add("threePersonNoRing", new Texture("MainScreen/ThreePersonNoRing.png"));

        ImageButton.ImageButtonStyle threePersonNoRingStyle = new ImageButton.ImageButtonStyle();
        threePersonNoRingStyle.imageUp = threePersonNoRingSkin.getDrawable("threePersonNoRing"); // Unpressed
        threePersonNoRingStyle.imageDown = threePersonNoRingSkin.getDrawable("threePersonNoRing"); // Pressed

        threePersonNoRing = new ImageButton(threePersonNoRingStyle);
        threePersonNoRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        threePersonNoRing.setBounds(width4, height4, threePersonNoRing.getWidth(), threePersonNoRing.getHeight());
        threePersonNoRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(threePersonNoRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // threePersonRing
        Skin threePersonRingSkin = new Skin();
        threePersonRingSkin.add("threePersonRing", new Texture("MainScreen/ThreePersonRing.png"));

        ImageButton.ImageButtonStyle threePersonRingStyle = new ImageButton.ImageButtonStyle();
        threePersonRingStyle.imageUp = threePersonRingSkin.getDrawable("threePersonRing"); // Unpressed
        threePersonRingStyle.imageDown = threePersonRingSkin.getDrawable("threePersonRing"); // Pressed

        threePersonRing = new ImageButton(threePersonRingStyle);
        threePersonRing.setSize( buttonSize4, (buttonSize4 *(float).386) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        threePersonRing.setBounds(width4, height4, threePersonRing.getWidth(), threePersonRing.getHeight());
        threePersonRing.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        //onePersonNoRing.setTouchable(Touchable.disabled);
        stage.addActor(threePersonRing);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));
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

        batch.end();
        batch.begin();

        iceButton.draw(batch, 1);


        if (ProjectOdyssey.ice == 4) {
            whichTableGameScreen = 1;
        }
        else if (ProjectOdyssey.ice == 5) {
            whichTableGameScreen = 2;
        }
        else if (ProjectOdyssey.ice == 6) {
            whichTableGameScreen = 3;
        }
        else if (ProjectOdyssey.ice == 7) {
            whichTableGameScreen = 4;
        }
        else if (ProjectOdyssey.ice == 8) { //thsi one doesnt work idk why
            whichTableGameScreen = 5;
        }
        else { //for testing
            whichTableGameScreen = 0;
        }

        if (whichTableGameScreen == 0){ //draw the table sprite depending on the whichTableGameScreen variable. IDK if this will work well with clicking the tables because maybe they will be stacking.
            onePersonNoRing.draw(batch, 1);
        }
        else if (whichTableGameScreen == 1){
            onePersonRing.draw(batch, 1);
        }
        else if (whichTableGameScreen == 2){
            twoPersonNoRing.draw(batch, 1);
        }
        else if (whichTableGameScreen == 3){
            twoPersonRing.draw(batch, 1);
        }
        else if (whichTableGameScreen == 4){
            threePersonNoRing.draw(batch, 1);
        }
        else if (whichTableGameScreen == 5){
            threePersonRing.draw(batch, 1);
        }
        batch.end();
        camera.update();







        if(ProjectOdyssey.ice >= 5 || ProjectOdyssey.marketShow == true) {
            marketButton.setTouchable(Touchable.enabled);
            lobbyButton.setTouchable(Touchable.enabled);

            batch.begin();
            marketButton.draw(batch, 1);
            lobbyButton.draw(batch, 1);
            batch.end();
            ProjectOdyssey.marketShow = true;
        }
       /* if(ProjectOdyssey.lobbyUnlock == true){
            lobbyButton.draw(batch, 1);
        }*/



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
        moneyCounter.dispose();
        stage.dispose();
        batch.dispose();
        game.dispose();
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

