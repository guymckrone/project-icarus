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

    //All dialogue
    //Contact Senator Mandell
    private String lobbying1 = "Senator Mandell, how are you doing today?";
    private String mandell1 = "I’m doing fine, thank you, but I’d like to know who this is and how you got this number.";
    private String lobbying2 = "I am a representative of MQLC, a company that works with prospective clients looking to get involved in political donations. We serve clientele who wish to see their hard-earned dollars go towards a good cause, including your campaign.";
    private String mandell2 = "Thanks, but I’m not interested. It’s nothing personal, you have to understand, but I like to keep a certain moral compass that stay’s pointed towards people’s hearts and minds, not some billionaire’s pockets.";
    private String lobbying3 = "Quite the contrary, Senator, our clientele would be in your pocket rather than the other way around. Anytime you need funding, our clients would be there to serve it up to you right on a silver platter. But I understand your reservations, and you’re right! There are plenty of dishonest men and women out there, but that’s not us. Tell ya what, why don’t you meet one of our clients, get to know them, look em’ up if you want, and then decide if you want our services. What have you got to lose?";
    private String mandell3 = "Hmmm, I suppose it would be rude of me to refuse to meet one of your clients.";

    //Offer to Fund Mandell's Campaign
    private String player1 = "Hello, Senator Mandell, my name is [BLANK]. I understand that MQLC recently contacted you about offering its services and I wanted to personally take a moment to cordially offer my support.";
    private String yesMandell = "Thank you, [Player], as a matter of fact they did! I am happy to finally meet one of their clients, and even more happy to accept your support. I hope that this will prove to be a mutually beneficial relationship.";
    private String noMandell = "Thank you, [Player], I appreciate the offer and the time you and MQLC took to make it. However, I do not feel at this time that it is in my best interests, or even necessary, for me to accept donations from third parties. Thank you again for the offer.";

    //Attempt to Bribe Mandell
    private String lobbying4 = "Senator Mandell, how are you on this fine day?";
    private String mandell4 = "I am doing well, thank you for asking, I hope you are as well. It is pleasant hearing from MQLC again.";
    private String lobbying5 = "I am happy to hear it sir, we strive to cultivate a close relationship with all of our customers. Anyways, let's get down to brass tacks shall we? I hear there’s an ice sanctions bill on the floor today isn’t that right?";
    private String mandell5 = "That’s right, it’s a bill intended to help protect American ice workers from having their jobs outsourced to Antarctica and the North Pole. I have to say I’m very impressed with it, I plan on giving my full support later this afternoon.";
    private String lobbying6 = "Yes, that is very noble and all isn’t it. But on a related note, I also hear you’re up for reelection soon, correct?";
    private String mandell6 = "Yes, that I am, but I fail to see how that’s related.";
    private String lobbying7 = "Well, you see, one of our clients is very concerned about the effect this bill might have on domestic ice markets. I mean, sure, it’ll save a few jobs, but how will poor Americans get their ice after this bill is passed? Our client was hoping to maybe sway you to get you and your constituents to vote down this bill and in exchange our client could provide monetary support in the next election.";
    private String yesMandell2 = "Hmm, well there I do believe there would be no point in me trying to protect American workers if I’m not even in office to do so. You have yourself a deal.";
    private String noMandell2 = "A very interesting proposal. However, I feel that we are entering into a moral and ethical grey area here, and in any case I feel that this bill is the right thing for America. I’m afraid I’m going to have turn you down on this one, but thank you for your time.";

    //Attempt to Blackmail
    private String lobbying8 = "Hello, Senator, I think it’s time me and you had a chat.";
    private String mandell7 = "Uhh to whom am I speaking?";
    private String lobbying9 = "I’d prefer to keep our relationship to a no-name basis if you don’t mind. Besides, I already plenty enough about you to keep us talking for a good, long while.";
    private String mandell8 = "Listen, I am a United States Senator, and if this is a prank call-";
    private String lobbying10 = "How’s Sheryl, Senator?";
    private String mandell9 = "...I beg your pardon?";
    private String lobbying11 = "She and the kids must be loving Grenada right now, what with the weather and such, but I’m sure they would be enjoying it a lot more if not for their-well, how should I say-condition.";
    private String mandell10 = "....How do you know about Sheryl?";
    private String lobbying12 = "I asked you a question before Senator, and your answer may decide whether this conversation continues or I hang up and call the New Ice Times.";
    private String mandell11 = "....";
    private String mandell12 = "What do you want";

    //Outcome Possibilities
    private String iceBroadcasting = "Senator Mandell wants to strengthen American industry and fix our healthcare.";
    private String iceBroadcasting2 = "Senator Mandell works at local soup kitchens on Sundays, regularly donates to elementary and high schools across the nation, and has a consistently-updated edgy meme account. Vote for a true man of the people!";
    private String iceBroadcasting3 = "Did you know that Senator Mandell’s favorite snack is a snow cone and that regularly keeps his house at a cool 53 degrees? Vote Mandell, a true friend of American ice.";

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
