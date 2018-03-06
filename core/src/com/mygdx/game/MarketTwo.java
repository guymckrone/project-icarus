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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.mygdx.game.ProjectOdyssey.moneys;

/**
 * Created by guymc on 11/30/2017.
 */

//Sell and Buy

public class MarketTwo implements Screen, GestureDetector.GestureListener{

    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;

    private ImageButton gameButton;
    private ImageButton sellIceCream;
    private ImageButton sellSnowFlake;
    private ImageButton sellIcicle;
    private ImageButton sellSnowMan;
    private ImageButton sellModeButton;
    private ImageButton buyModeButton;
    private ImageButton marketTwoButton;

    private Table marketTable;
    private boolean sellMode = true;

    private BitmapFont moneyCounter = new BitmapFont(); //For drawing text
    private int screenWidth = Gdx.graphics.getWidth(); //Variable with screen width in it
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    private Texture logo;
    OrthographicCamera camera;
    public MarketTwo(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        //This is for creating the text on the screen
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        // Button skin
        Skin sellIceSkin = new Skin();//Creates new skin
        sellIceSkin.add("sellIceCream", new Texture("Market/IceCream.png"));//sets button skin to image from assets

        // Create button style
        ImageButton.ImageButtonStyle sellIceStyle = new ImageButton.ImageButtonStyle();//creates imagebutton style for new button
        sellIceStyle.imageUp = sellIceSkin.getDrawable("sellIceCream"); // Unpressed
        sellIceStyle.imageDown = sellIceSkin.getDrawable("sellIceCream"); // Pressed

        // Play button
        sellIceCream = new ImageButton(sellIceStyle);//creates button
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());//sets variables for future button size
        sellIceCream.setSize(buttonSize, buttonSize);//uses variables to set size
        int width = (int) ((Gdx.graphics.getWidth() * .3) - (buttonSize/2));//Finding width of button for later use. We do buttonSize/2 to make sure the button is being moved by its center location, not its corner, as to assure for similar sizing on different size screens
        int height = (int) ((Gdx.graphics.getHeight() * .7) - (buttonSize/2));//Finding height of button for later use
        sellIceCream.setBounds(width, height, sellIceCream.getWidth(), sellIceCream.getHeight());//
        //Next few lines set the function of the bot
        sellIceCream.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
                //We dont want touch down to do anything, as touch up is better to use
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if (sellMode == true && ProjectOdyssey.iceCream > 0){//prevents from selling into negatives
                    ProjectOdyssey.iceCream--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.iceCreamPrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.iceCreamPrice ){//prevents from selling into negatives
                    ProjectOdyssey.iceCream++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.iceCreamPrice;//adds iceprice amount of moneys each time you click button
                }
                // System.out.println(ProjectOdyssey.ice);//debug code, prints ice value
                //sellIceCream.setDisabled(true);//disables after each press so it does not repeat

            }
        });
        stage.addActor(sellIceCream);//adds button to stage

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));//creates the gesture detector, without this you cannot have a button press

        // Button skin
        Skin sbButtonSkin = new Skin();
        sbButtonSkin.add("sellSnowFlake", new Texture("Market/SnowFlake.png"));

        // Create button style
        ImageButton.ImageButtonStyle sbButtonStyle = new ImageButton.ImageButtonStyle();
        sbButtonStyle.imageUp = sbButtonSkin.getDrawable("sellSnowFlake"); // Unpressed
        sbButtonStyle.imageDown = sbButtonSkin.getDrawable("sellSnowFlake"); // Pressed

        // Market button
        sellSnowFlake = new ImageButton(sbButtonStyle);
        int buttonSize3 = (int) (100 * Gdx.graphics.getDensity());
        sellSnowFlake.setSize(buttonSize3, buttonSize3);
        int width3 = (int) (((Gdx.graphics.getWidth() * .7)) - (buttonSize3/2));
        int height3 = (int) (((Gdx.graphics.getHeight() * .7)) - (buttonSize3/2));
        sellSnowFlake.setBounds(width3, height3, sellSnowFlake.getWidth(), sellSnowFlake.getHeight());
        sellSnowFlake.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (sellMode == true && ProjectOdyssey.snowFlake > 0){//prevents from selling into negatives
                    ProjectOdyssey.snowFlake--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.snowFlakePrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.snowFlakePrice ){//prevents from selling into negatives
                    ProjectOdyssey.snowFlake++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.snowFlakePrice;//adds iceprice amount of moneys each time you click button
                }
                //marketButton.setDisabled(false);

            }
        });
        //sellSnowFlake.setTouchable(Touchable.disabled);
        stage.addActor(sellSnowFlake);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // Button skin
        Skin bucketButtonSkin = new Skin();
        bucketButtonSkin.add("sellIcicle", new Texture("Market/Icicle.png"));

        // Create button style
        ImageButton.ImageButtonStyle bucketButtonStyle = new ImageButton.ImageButtonStyle();
        bucketButtonStyle.imageUp = bucketButtonSkin.getDrawable("sellIcicle"); // Unpressed
        bucketButtonStyle.imageDown = bucketButtonSkin.getDrawable("sellIcicle"); // Pressed

        // Market button
        sellIcicle = new ImageButton(bucketButtonStyle);
        int buttonSize4 = (int) (100 * Gdx.graphics.getDensity());
        sellIcicle.setSize(buttonSize4, buttonSize4);
        int width4 = (int) (((Gdx.graphics.getWidth() *.3)) - (buttonSize4/2));
        int height4 = (int) (((Gdx.graphics.getHeight() * .35)) - (buttonSize4/2));
        sellIcicle.setBounds(width4, height4, sellIcicle.getWidth(), sellIcicle.getHeight());

        sellIcicle.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (sellMode == true && ProjectOdyssey.icicle > 0){//prevents from selling into negatives
                    ProjectOdyssey.icicle--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.iciclePrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.iciclePrice ){//prevents from selling into negatives
                    ProjectOdyssey.icicle++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.iciclePrice;//adds iceprice amount of moneys each time you click button
                }
                //ProjectOdyssey.bucket++;
                //game.setScreen(new MainMenuScreen(game));
                //marketButton.setDisabled(false);

            }
        });

        //sellIcicle.setTouchable(Touchable.disabled);
        stage.addActor(sellIcicle);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin shovelButtonSkin = new Skin();
        shovelButtonSkin.add("sellSnowMan", new Texture("Market/SnowMan.png"));

        // Create button style
        ImageButton.ImageButtonStyle shovelButtonStyle = new ImageButton.ImageButtonStyle();
        shovelButtonStyle.imageUp = shovelButtonSkin.getDrawable("sellSnowMan"); // Unpressed
        shovelButtonStyle.imageDown = shovelButtonSkin.getDrawable("sellSnowMan"); // Pressed

        // Market button
        sellSnowMan = new ImageButton(shovelButtonStyle);
        int buttonSize5 = (int) (100 * Gdx.graphics.getDensity());
        sellSnowMan.setSize(buttonSize5, buttonSize5);
        int width5 = (int) ((Gdx.graphics.getWidth() * .7) - (buttonSize5/2));
        int height5 = (int) ((Gdx.graphics.getHeight() * .35) - (buttonSize5/2));
        sellSnowMan.setBounds(width5, height5, sellSnowMan.getWidth(), sellSnowMan.getHeight());
        sellSnowMan.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (sellMode == true && ProjectOdyssey.snowMan > 0){//prevents from selling into negatives
                    ProjectOdyssey.snowMan--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys + ProjectOdyssey.snowManPrice;//adds iceprice amount of moneys each time you click button
                }
                if (sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.snowManPrice){//prevents from selling into negatives
                    ProjectOdyssey.snowMan++;
                    ProjectOdyssey.moneys =  ProjectOdyssey.moneys - ProjectOdyssey.snowManPrice;//adds iceprice amount of moneys each time you click button
                }


            }
        });
        //sellSnowMan.setTouchable(Touchable.disabled);
        stage.addActor(sellSnowMan);

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
        int buttonSize6 = (int) (120 * Gdx.graphics.getDensity());
        sellModeButton.setSize(buttonSize6, buttonSize6);
        /*int width6 = (int) ((Gdx.graphics.getWidth() - sellModeButton.getWidth())/2);
        int height6 = (int) (((Gdx.graphics.getHeight() - sellModeButton.getHeight())/4)); */
        int width6 = (int) ((Gdx.graphics.getWidth() *.2) - (buttonSize6/2));
        int height6 = (int) (((Gdx.graphics.getHeight() * .95)) - (buttonSize6/2));
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
        int buttonSize7 = (int) (120 * Gdx.graphics.getDensity());
        buyModeButton.setSize(buttonSize7, buttonSize7);
        int width7 = (int) ((Gdx.graphics.getWidth() *.2) - (buttonSize7/2));
        int height7 = (int) (((Gdx.graphics.getHeight() * .95)) - (buttonSize7/2));
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

        // Button skin
        Skin gameButtonSkin = new Skin();
        gameButtonSkin.add("gameButton", new Texture("buttons/ArrowRight.png"));

        // Create button style
        ImageButton.ImageButtonStyle gameButtonStyle = new ImageButton.ImageButtonStyle();
        gameButtonStyle.imageUp = gameButtonSkin.getDrawable("gameButton"); // Unpressed
        gameButtonStyle.imageDown = gameButtonSkin.getDrawable("gameButton"); // Pressed

        // Market button
        gameButton = new ImageButton(gameButtonStyle);
        int buttonSize8 = (int) (75 * Gdx.graphics.getDensity());
        gameButton.setSize(buttonSize8, buttonSize8);
        int width8 = (int) (((Gdx.graphics.getWidth() - gameButton.getWidth())/4)*3);
        int height8 = (int) ((Gdx.graphics.getHeight() - gameButton.getHeight())/10);
        gameButton.setBounds(width8, height8, gameButton.getWidth(), gameButton.getHeight());
        gameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Market(game));
                //marketButton.setDisabled(false);

            }
        });
        stage.addActor(gameButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin marketTwoButtonSkin = new Skin();
        marketTwoButtonSkin.add("marketTwoButton", new Texture("buttons/ArrowLeft.png"));

        // Create button style
        ImageButton.ImageButtonStyle marketTwoButtonStyle = new ImageButton.ImageButtonStyle();
        marketTwoButtonStyle.imageUp = marketTwoButtonSkin.getDrawable("marketTwoButton"); // Unpressed
        marketTwoButtonStyle.imageDown = marketTwoButtonSkin.getDrawable("marketTwoButton"); // Pressed

        // Market button
        marketTwoButton = new ImageButton(marketTwoButtonStyle);
        int buttonSize9 = (int) (75 * Gdx.graphics.getDensity());
        marketTwoButton.setSize(buttonSize9, buttonSize9);
        int width9 = (int) (((Gdx.graphics.getWidth() - marketTwoButton.getWidth())/4));
        int height9 = (int) (((Gdx.graphics.getHeight() - marketTwoButton.getHeight())/10));
        marketTwoButton.setBounds(width9, height9, marketTwoButton.getWidth(), marketTwoButton.getHeight());
        marketTwoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //game.setScreen(new GameScreen(game));
                //game.setScreen(new MarketTwo(game));
                //marketButton.setDisabled(false);

            }
        });
        marketTwoButton.setTouchable(Touchable.disabled);
        stage.addActor(marketTwoButton);

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

        //Placing # of iceCubes based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.iceCream < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.275 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.125 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.15 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.175 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.2 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.225 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCream > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream , ((int)(.25 * screenWidth)),  ((int)(.835 * screenHeight))); //# of ice cubes/
        }

        //placing iceCube Price
        if (ProjectOdyssey.iceCreamPrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice , ((int)(.25 * screenWidth)),  ((int)(.6 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCreamPrice > 999){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice , ((int)(.175 * screenWidth)),  ((int)(.6 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCreamPrice > 99){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice , ((int)(.2 * screenWidth)),  ((int)(.6 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.iceCreamPrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice , ((int)(.225 * screenWidth)),  ((int)(.6 * screenHeight))); //# of ice cubes/
        }

        //Placing # of snowballs based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.snowFlake < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.675 * screenWidth)),  ((int)(.835 * screenHeight))); //# of snowBalls
        }
        else if (ProjectOdyssey.snowFlake > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.525 * screenWidth)),  ((int)(.835 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlake > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.55 * screenWidth)),  ((int)(.835 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlake > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.575 * screenWidth)),  ((int)(.835 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlake > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.6 * screenWidth)),  ((int)(.835 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlake > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.625 * screenWidth)),  ((int)(.835 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlake > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake , ((int)(.65 * screenWidth)),  ((int)(.835 * screenHeight)));
        }

        //placing snowFlake price
        if (ProjectOdyssey.snowFlakePrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice , ((int)(.65 * screenWidth)),  ((int)(.6 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlakePrice > 999){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice , ((int)(.575 * screenWidth)),  ((int)(.6 * screenHeight)));
        }
        else  if (ProjectOdyssey.snowFlakePrice > 99){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice , ((int)(.6 * screenWidth)),  ((int)(.6 * screenHeight)));
        }
        else if (ProjectOdyssey.snowFlakePrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice , ((int)(.625 * screenWidth)),  ((int)(.6 * screenHeight)));
        }

        //Placing # of buckets based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.icicle < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.275 * screenWidth)),  ((int)(.485 * screenHeight))); //# of buckets
        }
        else if (ProjectOdyssey.icicle > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.125 * screenWidth)),  ((int)(.485 * screenHeight)));
        }
        else if (ProjectOdyssey.icicle > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.15 * screenWidth)),  ((int)(.485 * screenHeight)));
        }
        else if (ProjectOdyssey.icicle > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.175 * screenWidth)),  ((int)(.485 * screenHeight)));
        }
        else if (ProjectOdyssey.icicle > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.2 * screenWidth)),  ((int)(.485 * screenHeight)));
        }
        else if (ProjectOdyssey.icicle > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.225 * screenWidth)),  ((int)(.485 * screenHeight)));
        }
        else if (ProjectOdyssey.icicle > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.icicle , ((int)(.25 * screenWidth)),  ((int)(.485 * screenHeight)));
        }

        //placing bucket price
        if (ProjectOdyssey.iciclePrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice , ((int)(.25 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.iciclePrice > 9999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice , ((int)(.15 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.iciclePrice > 999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice , ((int)(.175 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.iciclePrice > 99) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice , ((int)(.2 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.iciclePrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice , ((int)(.225 * screenWidth)),  ((int)(.25 * screenHeight)));
        }

        //Placing # of shovels based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.snowMan < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.675 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.525 * screenWidth)),  ((int)(.4855 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.5 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.575 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.6 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.625 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowMan > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int)(.65 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }

        //placing shovel price
        if (ProjectOdyssey.snowManPrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int)(.65 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowManPrice > 9999){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int)(.5 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowManPrice > 999){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int)(.575 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowManPrice > 99){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int)(.6 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.snowManPrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int)(.625 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }

        batch.end();
        batch.begin();
        sellIceCream.draw(batch, 1);//draw button, opacity
        if (sellMode == true){
            sellModeButton.draw(batch,1);
        }
        if (sellMode == false){
            buyModeButton.draw(batch,1);
        }
        gameButton.draw(batch, 1);

        sellSnowFlake.draw(batch, 1);
        sellIcicle.draw(batch, 1);
        sellSnowMan.draw(batch, 1);
        gameButton.draw(batch, 1);
        marketTwoButton.draw(batch, 1);
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
