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
import java.util.Random;

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
    private String lobbying1 = "Hello Senator Mandell how \nare you doing today?";
    private String mandell1 = "I’m doing fine, thank you, but \nI’d like to know who this is \nand how you got this number.";
    private String lobbying2 = "I am a representative of MQLC, \na company that works with \nprospective clients looking \nto get involved in political \ndonations. We serve clientele \nwho wish to see their hard \nearned dollars go towards \na good cause, including your \ncampaign.";
    private String mandell2 = "Thanks, but I’m not interested. \nIt’s nothing personal, you \nhave to understand, but I like \nto keep a certain moral \ncompass that stay’s pointed \ntowards people’s hearts and \nminds, not some billionaire’s \npockets.";
    private String lobbying3 = "Quite the contrary, Senator, \nour clientele would be in \nyour pocket rather than the \nother way around. Anytime \nyou need funding, our clients \nwould be there to serve it up \nto you right on a silver \nplatter. But I understand \nyour reservations. Why don’t \nyou meet one of our clients, \nget to know them. Then decide \nif you want our services. \nWhat have you got to lose?";
    private String mandell3 = "Hmmm, I suppose it would be \nrude of me to refuse to meet \none of your clients.";

    //Offer to Fund Mandell's Campaign
    private String player1 = "Hello, Senator Mandell, my \nname is [BLANK]. I understand \nthat MQLC recently contacted \nyou about offering its \nservices and I wanted to \npersonally take a moment to \ncordially offer my support.";
    private String yesMandell = "Thank you, [Player], as a \nmatter of fact they did! I am \nhappy to finally meet one of \ntheir clients, and even more \nhappy to accept your \nsupport. I hope that this will \nprove to be a mutually \nbeneficial relationship.";
    private String noMandell = "Thank you, [Player], I appreciate \nthe offer and the time you \nand MQLC took to make it. \nHowever, I do not feel at this \ntime that it is in my best \ninterests, or even necessary, \nfor me to accept donations \nfrom third parties. Thank you \nagain for the offer.";

    //Attempt to Bribe Mandell
    private String lobbying4 = "Senator Mandell, how are you \non this fine day?";
    private String mandell4 = "I am doing well, thank you for \nasking. I hope you are as well. \nIt is pleasant hearing from \nMQLC again.";
    private String lobbying5 = "I am happy to hear it sir, we \nstrive to cultivate a close \nrelationship with all of our \ncustomers. Anyways, let's get \ndown to brass tacks shall \nwe? I hear there’s an ice \nsanctions bill on the floor \ntoday isn’t that right?";
    private String mandell5 = "That’s right, it’s a bill intended \nto help protect American ice \nworkers from having their \njobs outsourced to Russia \nand the North Pole. I have to \nsay I’m very impressed with it. \nI plan on giving my full \nsupport later this afternoon.";
    private String lobbying6 = "Yes, that is very noble and \nall isn’t it. But on a related \nnote, I also hear you’re up for \nreelection soon, correct?";
    private String mandell6 = "Yes, that I am, but I fail to see \nhow that’s related.";
    private String lobbying7 = "Well, you see, one of our \nclients is very concerned \nabout the effect this bill \nmight have on domestic ice \nmarkets. I mean, sure, it’ll save \na few jobs, but how will poor \nAmericans get their ice after \nthis bill is passed? Our client \nwas hoping to maybe sway \nyou to get you and your \nconstituents to vote down \nthis bill and in exchange our \nclient could provide monetary \nsupport in the next election.";
    private String yesMandell2 = "Hmm, well there I do believe \nthere would be no point in me \ntrying to protect American \nworkers if I’m not even in \noffice to do so. You have \nyourself a deal.";
    private String noMandell2 = "A very interesting proposal. \nHowever, I feel that we are \nentering into a moral and \nethical grey area here, and in \nany case I feel that this bill \nis the right thing for America. \nI’m afraid I’m going to have \nturn you down on this one, \nbut thank you for your time.";

    //Attempt to Blackmail
    private String lobbying8 = "Hello, Senator, I think it’s time \nme and you had a chat.";
    private String mandell7 = "Uhh to whom am I speaking?";
    private String lobbying9 = "I’d prefer to keep our \nrelationship to a no-name \nbasis if you don’t mind. \nBesides, I already plenty \nenough about you to keep us \ntalking for a good, long while.";
    private String mandell8 = "Listen, I am a United States \nSenator, and if this is a prank \ncall...";
    private String lobbying10 = "How’s Sheryl, Senator?";
    private String mandell9 = "...I beg your pardon?";
    private String lobbying11 = "She and the kids must be \nloving Grenada right now, \nwhat with the weather and \nsuch, but I’m sure they would \nbe enjoying it a lot more if \nnot for their-well, how should \nI say...condition.";
    private String mandell10 = "....How do you know about \nSheryl?";
    private String lobbying12 = "I asked you a question before \nSenator, and your answer may \ndecide whether this \nconversation continues or I \nhang up and call the New Ice \nTimes.";
    private String mandell11 = " . . . .";
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

    private String npcText = lobbying1;

    private int dialogueProgress = 26;
    private int randomOne;


    public Dialogue(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        Random random = new Random();
        randomOne = random.nextInt(2);
        System.out.println(randomOne+ "squeeep");
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
                dialogueProgress++;
                System.out.println(dialogueProgress);
                //showResponses = true;
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


        if(dialogueProgress == 1){
            npcText = lobbying1;
        }
        if(dialogueProgress == 2){
            npcText = mandell1;
        }
        if(dialogueProgress == 3){
            npcText = lobbying2;
        }
        if(dialogueProgress == 4){
            npcText = mandell2;
        }
        if(dialogueProgress == 5){
            npcText = lobbying3;
        }
        if(dialogueProgress == 6){
            npcText = mandell3;
        }

        if(dialogueProgress == 7){
            npcText = player1;
        }
        if(dialogueProgress == 8){
            npcText = yesMandell;
        }
        if(dialogueProgress == 9){
            npcText = noMandell;
        }

        if(dialogueProgress == 10){
            npcText = lobbying4;
        }
        if(dialogueProgress == 11){
            npcText = mandell4;
        }
        if(dialogueProgress == 12){
            npcText = lobbying5;
        }
        if(dialogueProgress == 13){
            npcText = mandell5;
        }
        if(dialogueProgress == 14){
            npcText = lobbying6;
        }
        if(dialogueProgress == 15){
            npcText = mandell6;
        }
        if(dialogueProgress == 16){
            npcText = lobbying7;
        }

        if(randomOne == 0 && dialogueProgress == 17) {
            npcText = yesMandell2;
        }
        if(randomOne == 1 && dialogueProgress == 17) {
            npcText = noMandell2;
        }


        if(dialogueProgress == 18){
            npcText = lobbying8;
        }
        if(dialogueProgress == 19){
            npcText = mandell7;
        }
        if(dialogueProgress == 20){
            npcText = lobbying9;
        }
        if(dialogueProgress == 21){
            npcText = mandell8;
        }
        if(dialogueProgress == 22){
            npcText = lobbying10;
        }
        if(dialogueProgress == 23){
            npcText = mandell9;
        }
        if(dialogueProgress == 24){
            npcText = lobbying11;
        }
        if(dialogueProgress == 25){
            npcText = mandell10;
        }
        if(dialogueProgress == 26){
            npcText = lobbying12;
        }
        if(dialogueProgress == 27){
            npcText = mandell11;
        }
        if(dialogueProgress == 28){
            npcText = mandell12;
        }



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
            //dialogueOne.draw(batch, npcText, (float) (Gdx.graphics.getWidth() * .1), (float) (Gdx.graphics.getHeight() * .95));
            dialogueOne.draw(batch, npcText, (float) (Gdx.graphics.getWidth() * .08), (float) (Gdx.graphics.getHeight() * .8));


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
