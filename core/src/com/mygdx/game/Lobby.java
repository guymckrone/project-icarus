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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.mygdx.game.Book.upgradeOneProgress;
import static com.mygdx.game.Book.upgradeThreeProgress;
import static com.mygdx.game.Book.upgradeTwoProgress;
import static com.mygdx.game.ProjectOdyssey.ice;
import static com.mygdx.game.ProjectOdyssey.moneys;

/**
 * Created by nleof4499 on 2/26/2018.
 */

public class Lobby implements Screen, GestureDetector.GestureListener{

    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont moneyCounter = new BitmapFont();
    private SpriteBatch batch;

    private ImageButton conTableOne;
    private ImageButton conTableTwo;
    private ImageButton conTableThree;
    private ImageButton conTableFour;

    private ImageButton lobbySignButton;

    private ImageButton gameScreenButton;

    private ImageButton phoneButton;
    private ImageButton phoneOffButton;
    private ImageButton bookNoMarkButton;
    private ImageButton bookMarkButton;

    private int screenWidth = Gdx.graphics.getWidth(); //Variable with screen width in it
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    private int unlockPrice = 100;

    OrthographicCamera camera;

    public static int whichTableLobbyScreen = 0; //0 = onePersonNoRing, 1 = onePersonRing, 2 = twoPersonNoRing, 3 = twoPersonRing, 4 = threePersonNoRing, 5 = threePersonRing
    public static boolean whichBook = false; //false = no bookmark, true = bookmark
    public static boolean phoneDisabled = false;

    public Lobby(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth()/15.4285714286);
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!




        //onePersonNoRing
        Skin conTableOneSkin = new Skin();
        conTableOneSkin.add("conTableOne", new Texture("LobbyingScreen/ConferenceTableOne.png"));

        ImageButton.ImageButtonStyle conTableOneStyle = new ImageButton.ImageButtonStyle();
        conTableOneStyle.imageUp = conTableOneSkin.getDrawable("conTableOne"); // Unpressed
        conTableOneStyle.imageDown = conTableOneSkin.getDrawable("conTableOne"); // Pressed

        conTableOne = new ImageButton(conTableOneStyle);
        int conTableSize = (int) (300 * Gdx.graphics.getDensity());
        conTableOne.setSize( conTableSize, (conTableSize *(float)1.14) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        int conTableWidth = (int) ((Gdx.graphics.getWidth() * .5 - (conTableOne.getWidth()/2)));
        int conTableHeight = (int) ((Gdx.graphics.getHeight() * .6 - (conTableOne.getHeight()/2)));
        conTableOne.setBounds(conTableWidth, conTableHeight, conTableOne.getWidth(), conTableOne.getHeight());
        conTableOne.addListener(new InputListener() {
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
        stage.addActor(conTableOne);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));



        // conTableTwo
        Skin conTableTwoSkin = new Skin();
        conTableTwoSkin.add("conTableTwo", new Texture("LobbyingScreen/ConferenceTableTwo.png"));

        ImageButton.ImageButtonStyle conTableTwoStyle = new ImageButton.ImageButtonStyle();
        conTableTwoStyle.imageUp = conTableTwoSkin.getDrawable("conTableTwo"); // Unpressed
        conTableTwoStyle.imageDown = conTableTwoSkin.getDrawable("conTableTwo"); // Pressed

        conTableTwo = new ImageButton(conTableTwoStyle);
        conTableTwo.setSize( conTableSize, (conTableSize *(float)1.14) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        conTableTwo.setBounds(conTableWidth, conTableHeight, conTableTwo.getWidth(), conTableTwo.getHeight());
        conTableTwo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        stage.addActor(conTableTwo);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

               // conTableThree
        Skin conTableThreeSkin = new Skin();
        conTableThreeSkin.add("conTableThree", new Texture("LobbyingScreen/ConferenceTableThree.png"));

        ImageButton.ImageButtonStyle conTableThreeStyle = new ImageButton.ImageButtonStyle();
        conTableThreeStyle.imageUp = conTableThreeSkin.getDrawable("conTableThree"); // Unpressed
        conTableThreeStyle.imageDown = conTableThreeSkin.getDrawable("conTableThree"); // Pressed

        conTableThree = new ImageButton(conTableThreeStyle);
        conTableThree.setSize( conTableSize, (conTableSize *(float)1.14) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        conTableThree.setBounds(conTableWidth, conTableHeight, conTableThree.getWidth(), conTableThree.getHeight());
        conTableThree.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        stage.addActor(conTableThree);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // conTableFour
        Skin conTableFourSkin = new Skin();
        conTableFourSkin.add("conTableFour", new Texture("LobbyingScreen/ConferenceTableFour.png"));

        ImageButton.ImageButtonStyle conTableFourStyle = new ImageButton.ImageButtonStyle();
        conTableFourStyle.imageUp = conTableFourSkin.getDrawable("conTableFour"); // Unpressed
        conTableFourStyle.imageDown = conTableFourSkin.getDrawable("conTableFour"); // Pressed

        conTableFour = new ImageButton(conTableFourStyle);
        conTableFour.setSize( conTableSize, (conTableSize *(float)1.14) ); //.386 is the ratio of the sprites height/width to ensure accurate hit detection
        conTableFour.setBounds(conTableWidth, conTableHeight, conTableFour.getWidth(), conTableFour.getHeight());
        conTableFour.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moneys++;

            }
        });
        stage.addActor(conTableFour);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // Button skin
        Skin gameScreenButtonSkin = new Skin();
        gameScreenButtonSkin.add("gameScreenButton", new Texture("buttons/ArrowLeft.png"));

        // Create button style
        ImageButton.ImageButtonStyle gameScreenButtonStyle = new ImageButton.ImageButtonStyle();
        gameScreenButtonStyle.imageUp = gameScreenButtonSkin.getDrawable("gameScreenButton"); // Unpressed
        gameScreenButtonStyle.imageDown = gameScreenButtonSkin.getDrawable("gameScreenButton"); // Pressed

        // Market button
        gameScreenButton = new ImageButton(gameScreenButtonStyle);
        int buttonSize2 = (int) (75 * Gdx.graphics.getDensity());
        gameScreenButton.setSize(buttonSize2, buttonSize2);
        int width2 = (int) ((Gdx.graphics.getWidth() * .3 - (gameScreenButton.getWidth()/2)));
        int height2 = (int) ((Gdx.graphics.getHeight() * .1 - (gameScreenButton.getHeight()/2)));
        gameScreenButton.setBounds(width2, height2, gameScreenButton.getWidth(), gameScreenButton.getHeight());
        gameScreenButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(gameScreenButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin lobbySignButtonSkin = new Skin();
        lobbySignButtonSkin.add("lobbySignButton", new Texture("LobbyingScreen/LobbyingSign.png"));

        // Create button style
        ImageButton.ImageButtonStyle lobbySignButtonStyle = new ImageButton.ImageButtonStyle();
        lobbySignButtonStyle.imageUp = lobbySignButtonSkin.getDrawable("lobbySignButton"); // Unpressed
        lobbySignButtonStyle.imageDown = lobbySignButtonSkin.getDrawable("lobbySignButton"); // Pressed

        // Market button
        lobbySignButton = new ImageButton(lobbySignButtonStyle);
        int lobbySignButtonSize = (int) (100 * Gdx.graphics.getDensity());
        lobbySignButton.setSize(lobbySignButtonSize, lobbySignButtonSize);
        int lobbySignWidth = (int) ((Gdx.graphics.getWidth() * .15 - (lobbySignButton.getWidth()/2)));
        int lobbySignHeight = (int) ((Gdx.graphics.getHeight() * .95 - (lobbySignButton.getHeight()/2)));
        lobbySignButton.setBounds(lobbySignWidth, lobbySignHeight, lobbySignButton.getWidth(), lobbySignButton.getHeight());
        lobbySignButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                moneys++;
            }
        });
        stage.addActor(lobbySignButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

       /* // Button skin
        Skin phoneOffButtonSkin = new Skin();
        phoneOffButtonSkin.add("phoneButton", new Texture("LobbyingScreen/Telephone.png"));

        // Create button style
        ImageButton.ImageButtonStyle phoneOffButtonStyle = new ImageButton.ImageButtonStyle();
        phoneOffButtonStyle.imageUp = phoneOffButtonSkin.getDrawable("phoneButton"); // Unpressed
        phoneOffButtonStyle.imageDown = phoneOffButtonSkin.getDrawable("phoneButton"); // Pressed

        // Market button
        phoneOffButton = new ImageButton(phoneOffButtonStyle);
        int phoneOffButtonSize = (int) (80 * Gdx.graphics.getDensity());
        phoneOffButton.setSize(phoneOffButtonSize, phoneOffButtonSize);
        //int phoneButtonWidth = (int) (((Gdx.graphics.getWidth() *.25) - phoneButton.getWidth())/2);
        int phoneOffButtonWidth = (int) ((Gdx.graphics.getWidth() *.33)- (phoneOffButton.getWidth()/2));
        int phoneOffButtonHeight = (int) ((Gdx.graphics.getHeight()*.5)- (phoneOffButton.getWidth()/2));
        phoneOffButton.setBounds(phoneOffButtonWidth, phoneOffButtonHeight, phoneOffButton.getWidth(), phoneOffButton.getHeight());
        phoneOffButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        stage.addActor(phoneOffButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));*/


        // Button skin
        Skin phoneButtonSkin = new Skin();
        phoneButtonSkin.add("phoneButton", new Texture("LobbyingScreen/Telephone.png"));

        // Create button style
        ImageButton.ImageButtonStyle phoneButtonStyle = new ImageButton.ImageButtonStyle();
        phoneButtonStyle.imageUp = phoneButtonSkin.getDrawable("phoneButton"); // Unpressed
        phoneButtonStyle.imageDown = phoneButtonSkin.getDrawable("phoneButton"); // Pressed

        // Market button
        phoneButton = new ImageButton(phoneButtonStyle);
        int phoneButtonSize = (int) (80 * Gdx.graphics.getDensity());
        phoneButton.setSize(phoneButtonSize, phoneButtonSize);
        //int phoneButtonWidth = (int) (((Gdx.graphics.getWidth() *.25) - phoneButton.getWidth())/2);
        int phoneButtonWidth = (int) ((Gdx.graphics.getWidth() *.33)- (phoneButton.getWidth()/2));
        int phoneButtonHeight = (int) ((Gdx.graphics.getHeight()*.5)- (phoneButton.getWidth()/2));
        phoneButton.setBounds(phoneButtonWidth, phoneButtonHeight, phoneButton.getWidth(), phoneButton.getHeight());
        phoneButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(Dialogue.cuando);
                if(Dialogue.cuando = true) {
                    Dialogue.showResponses = true;
                    Dialogue.cuando = false;
                    game.setScreen(new Dialogue(game));
                }
                else{
                }
                System.out.println(Dialogue.cuando);
                if(phoneDisabled = true){
                    phoneButton.setTouchable(Touchable.disabled);
                }
            }

        });
        stage.addActor(phoneButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin bookNoMarkButtonSkin = new Skin();
        bookNoMarkButtonSkin.add("bookNoMark", new Texture("LobbyingScreen/Book.png"));

        // Create button style
        ImageButton.ImageButtonStyle bookNoMarkButtonStyle = new ImageButton.ImageButtonStyle();
        bookNoMarkButtonStyle.imageUp = bookNoMarkButtonSkin.getDrawable("bookNoMark"); // Unpressed
        bookNoMarkButtonStyle.imageDown = bookNoMarkButtonSkin.getDrawable("bookNoMark"); // Pressed

        // Market button
        bookNoMarkButton = new ImageButton(bookNoMarkButtonStyle);
        int bookSize = (int) (80 * Gdx.graphics.getDensity());
        bookNoMarkButton.setSize(bookSize, bookSize);
        int bookWidth = (int) ((Gdx.graphics.getWidth() * .66 - (bookNoMarkButton.getWidth()/2)));
        int bookHeight = (int) ((Gdx.graphics.getHeight() * .5 - (bookNoMarkButton.getHeight()/2)));
        bookNoMarkButton.setBounds(bookWidth, bookHeight, bookNoMarkButton.getWidth(), bookNoMarkButton.getHeight());
        bookNoMarkButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new Book(game));
            }
        });
        stage.addActor(bookNoMarkButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin bookMarkButtonSkin = new Skin();
        bookMarkButtonSkin.add("bookMark", new Texture("LobbyingScreen/BookBookmark.png"));

        // Create button style
        ImageButton.ImageButtonStyle bookMarkButtonStyle = new ImageButton.ImageButtonStyle();
        bookMarkButtonStyle.imageUp = bookMarkButtonSkin.getDrawable("bookMark"); // Unpressed
        bookMarkButtonStyle.imageDown = bookMarkButtonSkin.getDrawable("bookMark"); // Pressed

        // Market button
        bookMarkButton = new ImageButton(bookMarkButtonStyle);
        bookMarkButton.setSize(bookSize, bookSize);
        bookMarkButton.setBounds(bookWidth, bookHeight, bookMarkButton.getWidth(), bookMarkButton.getHeight());
        bookMarkButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(new Book(game));
            }
        });
        stage.addActor(bookMarkButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));






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
        conTableOne.draw(batch, 1);
        gameScreenButton.draw(batch,1);



        if ( upgradeThreeProgress == 8){
            whichTableLobbyScreen = 3;

        }
        else if (upgradeTwoProgress == 12){
            whichTableLobbyScreen = 2;

        }
        else if (upgradeOneProgress == 12){
            whichTableLobbyScreen = 1;

        }
        else{
            whichTableLobbyScreen = 0;
        }




        if (whichTableLobbyScreen == 0){ //draw the table sprite depending on the whichTableGameScreen variable. IDK if this will work well with clicking the tables because maybe they will be stacking.
            conTableOne.draw(batch, 1);
        }
        else if (whichTableLobbyScreen == 1){
            conTableTwo.draw(batch, 1);
        }
        else if (whichTableLobbyScreen == 2){
            conTableThree.draw(batch, 1);
        }
        else if (whichTableLobbyScreen == 3){
            conTableFour.draw(batch, 1);
        }

        //determine which book to show
        if (ice == 5){
            whichBook = true;
        }
        else{
            whichBook = false;
        }

        if (whichBook == true){
            bookMarkButton.draw(batch,1);
        }
        else if (whichBook == false){
            bookNoMarkButton.draw(batch, 1);
        }

        batch.end();
        camera.update();
        batch.begin();

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





        phoneButton.draw(batch,1);
        lobbySignButton.draw(batch,1);
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
