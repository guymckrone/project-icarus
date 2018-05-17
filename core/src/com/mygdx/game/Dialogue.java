package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Created by guymckrone on 4/10/18.
 */

public class Dialogue implements Screen, GestureDetector.GestureListener{

    public static int dialougeStatus = 0;
    boolean showResponses = false;

    final ProjectOdyssey game;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont moneyCounter = new BitmapFont();
    OrthographicCamera camera;

    private BitmapFont dialogueOne = new BitmapFont();
    private BitmapFont dialogueTwo = new BitmapFont();
    private BitmapFont dialogueThree = new BitmapFont();

    private String example = "Hello World"; //Use this line to add all the text, can be copied infinite times
    private String nameOfTextOption = "What the text should be"; //example of what to change, keep the variable name short, i have to type these out 4 times each after!!!

    private ImageButton optionOneButton;
    private ImageButton optionTwoButton;
    private ImageButton optionThreeButton;
    private ImageButton nextButton;

    private String npcText = "HERE IS SOME TEST TEXT FOR WHEN YOU LOAD ON THIS SCREEN. LIKE HEY PLAYER I HOPE YOU ARE READY TO CORRUPT SOME LITTLE POLITICIANSS!!!! THEN THE PLAYER HITS NEXT AND IT CHANGES TO THE RESPONSE SCREEN, AND THEN YOU CAN CHANGE THIS TEXT SO IT CHANGES WHEN THEY CLICK A CHOICE, OR SOMETHING";


    public Dialogue(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        dialogueOne = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 70;
        parameter2.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        dialogueTwo = generator.generateFont(parameter2); // font size 80 pixels
        generator2.dispose(); // don't forget to dispose to avoid memory leaks!

        FreeTypeFontGenerator generator3 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.size = 70;
        parameter3.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        dialogueThree = generator.generateFont(parameter3); // font size 80 pixels
        generator3.dispose(); // don't forget to dispose to avoid memory leaks!

        // Button skin
        Skin optionOneButtonSkin = new Skin();
        optionOneButtonSkin.add("lobbySignButton", new Texture("buttons/Choose.png"));

        // Create button style
        ImageButton.ImageButtonStyle optionOneButtonStyle = new ImageButton.ImageButtonStyle();
        optionOneButtonStyle.imageUp = optionOneButtonSkin.getDrawable("lobbySignButton"); // Unpressed
        optionOneButtonStyle.imageDown = optionOneButtonSkin.getDrawable("lobbySignButton"); // Pressed

        // Market button
        optionOneButton = new ImageButton(optionOneButtonStyle);
        int optionOneButtonSize = (int) (120 * Gdx.graphics.getDensity());
        optionOneButton.setSize(optionOneButtonSize, optionOneButtonSize);
        int optionOneWidth = (int) (Gdx.graphics.getWidth() * .25 - (optionOneButton.getWidth()/2));
        int optionOneHeight = (int) (Gdx.graphics.getHeight() * .95 - (optionOneButton.getHeight()/2));
        optionOneButton.setBounds(optionOneWidth, optionOneHeight, optionOneButton.getWidth(), optionOneButton.getHeight());
        optionOneButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                showResponses = false;

            }
        });
        stage.addActor(optionOneButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin optionTwoButtonSkin = new Skin();
        optionTwoButtonSkin.add("lobbySignButton", new Texture("buttons/Choose.png"));

        // Create button style
        ImageButton.ImageButtonStyle optionTwoButtonStyle = new ImageButton.ImageButtonStyle();
        optionTwoButtonStyle.imageUp = optionTwoButtonSkin.getDrawable("lobbySignButton"); // Unpressed
        optionTwoButtonStyle.imageDown = optionTwoButtonSkin.getDrawable("lobbySignButton"); // Pressed

        // Market button
        optionTwoButton = new ImageButton(optionTwoButtonStyle);
        int lobbySignButtonSize = (int) (120 * Gdx.graphics.getDensity());
        optionTwoButton.setSize(lobbySignButtonSize, lobbySignButtonSize);
        int optionTwoWidth = (int) (((Gdx.graphics.getWidth() * .25 - (optionTwoButton.getWidth()/2))));
        int optionTwoHeight = (int) ((Gdx.graphics.getHeight() * .65 - (optionTwoButton.getHeight()/2)));
        optionTwoButton.setBounds(optionTwoWidth, optionTwoHeight, optionTwoButton.getWidth(), optionTwoButton.getHeight());
        optionTwoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                showResponses = false;

            }
        });
        stage.addActor(optionTwoButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin optionThreeButtonSkin = new Skin();
        optionThreeButtonSkin.add("lobbySignButton", new Texture("buttons/Choose.png"));

        // Create button style
        ImageButton.ImageButtonStyle optionThreeButtonStyle = new ImageButton.ImageButtonStyle();
        optionThreeButtonStyle.imageUp = optionThreeButtonSkin.getDrawable("lobbySignButton"); // Unpressed
        optionThreeButtonStyle.imageDown = optionThreeButtonSkin.getDrawable("lobbySignButton"); // Pressed

        // Market button
        optionThreeButton = new ImageButton(optionThreeButtonStyle);
        int optionThreeButtonSize = (int) (120 * Gdx.graphics.getDensity());
        optionThreeButton.setSize(optionThreeButtonSize, optionThreeButtonSize);
        int lobbySignWidth = (int) (Gdx.graphics.getWidth() * .25 - (optionThreeButton.getWidth()/2));
        int lobbySignHeight = (int) (Gdx.graphics.getHeight() * .35 - (optionThreeButton.getHeight()/2));
        optionThreeButton.setBounds(lobbySignWidth, lobbySignHeight, optionThreeButton.getWidth(), optionThreeButton.getHeight());
        optionThreeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                showResponses = false;

            }
        });
        stage.addActor(optionThreeButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin nextButtonSkin = new Skin();
        nextButtonSkin.add("nextButton", new Texture("buttons/Next.png"));

        // Create button style
        ImageButton.ImageButtonStyle nextButtonStyle = new ImageButton.ImageButtonStyle();
        nextButtonStyle.imageUp = nextButtonSkin.getDrawable("nextButton"); // Unpressed
        nextButtonStyle.imageDown = nextButtonSkin.getDrawable("nextButton"); // Pressed

        // Market button
        nextButton = new ImageButton(nextButtonStyle);
        int nextButtonSize = (int) (120 * Gdx.graphics.getDensity());
        nextButton.setSize(nextButtonSize, nextButtonSize);
        int nextButtonWidth = (int) (Gdx.graphics.getWidth() * .5 - (nextButton.getWidth()/2));
        int nextButtonHeight = (int) (Gdx.graphics.getHeight() * .1 - (nextButton.getHeight()/2));
        nextButton.setBounds(nextButtonWidth, nextButtonHeight, nextButton.getWidth(), nextButton.getHeight());
        nextButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                showResponses = true;
            }
        });
        stage.addActor(nextButton);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        dialogueOne.setColor(Color.BLACK);
        dialogueTwo.setColor(Color.BLACK);
        dialogueThree.setColor(Color.BLACK);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (showResponses == true) {
            optionOneButton.draw(batch, 1);
            optionTwoButton.draw(batch, 1);
            optionThreeButton.draw(batch, 1);

            if (dialougeStatus == 0) {
                dialogueOne.draw(batch, "Hello world", (float) (Gdx.graphics.getWidth() * .2), (float) (Gdx.graphics.getHeight() * .9));
                dialogueOne.draw(batch, "Hello world", (float) (Gdx.graphics.getWidth() * .2), (float) (Gdx.graphics.getHeight() * .6));
                dialogueOne.draw(batch, "Hello world", (float) (Gdx.graphics.getWidth() * .2), (float) (Gdx.graphics.getHeight() * .3));
            }
        }
        else{
            nextButton.draw(batch,1);
            dialogueOne.draw(batch, npcText, (float) (Gdx.graphics.getWidth() * .2), (float) (Gdx.graphics.getHeight() * .95));


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
