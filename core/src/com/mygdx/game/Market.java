package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
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

import static com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.screenWidth;
import static com.mygdx.game.ProjectOdyssey.moneys;

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

    private BitmapFont moneyCounter = new BitmapFont(); //For drawing text
    private int screenWidth = Gdx.graphics.getWidth(); //Variable with screen width in it
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    private Texture logo;
    OrthographicCamera camera;
    public Market(final ProjectOdyssey game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        //This is for creating the text on the screen
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/century.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

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
        int buttonSize = (int) (100 * Gdx.graphics.getDensity());//sets variables for future button size
        sellIce.setSize(buttonSize, buttonSize);//uses variables to set size
        int width = (int) ((Gdx.graphics.getWidth() * .3) - (buttonSize/2));//Finding width of button for later use. We do buttonSize/2 to make sure the button is being moved by its center location, not its corner, as to assure for similar sizing on different size screens
        int height = (int) ((Gdx.graphics.getHeight() * .7) - (buttonSize/2));//Finding height of button for later use
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
        int buttonSize3 = (int) (100 * Gdx.graphics.getDensity());
        sbButton.setSize(buttonSize3, buttonSize3);
        int width3 = (int) (((Gdx.graphics.getWidth() * .7)) - (buttonSize3/2));
        int height3 = (int) (((Gdx.graphics.getHeight() * .7)) - (buttonSize3/2));
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
        int width4 = (int) (((Gdx.graphics.getWidth() *.3)) - (buttonSize4/2));
        int height4 = (int) (((Gdx.graphics.getHeight() * .35)) - (buttonSize4/2));
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
        shovelButtonSkin.add("shovelButton", new Texture("Market/Shovel.png"));

        // Create button style
        ImageButton.ImageButtonStyle shovelButtonStyle = new ImageButton.ImageButtonStyle();
        shovelButtonStyle.imageUp = shovelButtonSkin.getDrawable("shovelButton"); // Unpressed
        shovelButtonStyle.imageDown = shovelButtonSkin.getDrawable("shovelButton"); // Pressed

        // Market button
        shovelButton = new ImageButton(shovelButtonStyle);
        int buttonSize5 = (int) (100 * Gdx.graphics.getDensity());
        shovelButton.setSize(buttonSize5, buttonSize5);
        int width5 = (int) ((Gdx.graphics.getWidth() * .7) - (buttonSize5/2));
        int height5 = (int) ((Gdx.graphics.getHeight() * .35) - (buttonSize5/2));
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
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.9 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x<10
        }
        else if (moneys > 999999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.62 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>999999
        }
        else if (moneys > 99999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.67 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>99999
        }
        else if (moneys > 9999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.71 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>9999
        }
        else if (moneys > 999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.76 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>999
        }
        else if (moneys > 99) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.81 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>99
        }
        else if (moneys > 9) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int)(.85 * screenWidth)), ((int)(.98 * screenHeight))); //Position of money counter when x>9
        }

        //Placing # of iceCubes based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.ice < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.275 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.16 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.18 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.20 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.22 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.24 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.ice > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.ice , ((int)(.26 * screenWidth)),  ((int)(.85 * screenHeight))); //# of ice cubes/
        }

        //placing iceCube Price
        if (ProjectOdyssey.iceCubePrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCubePrice , ((int)(.26 * screenWidth)),  ((int)(.6 * screenHeight))); //# of ice cubes/
        }

        //Placing # of snowballs based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.snowBall < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.675 * screenWidth)),  ((int)(.85 * screenHeight))); //# of snowBalls
        }
        else if (ProjectOdyssey.snowBall > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.56 * screenWidth)),  ((int)(.85 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBall > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.58 * screenWidth)),  ((int)(.85 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBall > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.60 * screenWidth)),  ((int)(.85 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBall > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.62 * screenWidth)),  ((int)(.85 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBall > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.64 * screenWidth)),  ((int)(.85 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBall > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.snowBall , ((int)(.66 * screenWidth)),  ((int)(.85 * screenHeight)));
        }

        //placing snowball price
        if (ProjectOdyssey.snowBallPrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowBallPrice , ((int)(.66 * screenWidth)),  ((int)(.6 * screenHeight)));
        }
        else if (ProjectOdyssey.snowBallPrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.snowBallPrice , ((int)(.64 * screenWidth)),  ((int)(.6 * screenHeight)));
        }

        //Placing # of buckets based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.bucket < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.275 * screenWidth)),  ((int)(.485 * screenHeight))); //# of buckets
        }
        else if (ProjectOdyssey.bucket > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.16 * screenWidth)),  ((int)(.5 * screenHeight)));
        }
        else if (ProjectOdyssey.bucket > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.18 * screenWidth)),  ((int)(.5 * screenHeight)));
        }
        else if (ProjectOdyssey.bucket > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.20 * screenWidth)),  ((int)(.5 * screenHeight)));
        }
        else if (ProjectOdyssey.bucket > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.22 * screenWidth)),  ((int)(.5 * screenHeight)));
        }
        else if (ProjectOdyssey.bucket > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.24 * screenWidth)),  ((int)(.5 * screenHeight)));
        }
        else if (ProjectOdyssey.bucket > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucket , ((int)(.26 * screenWidth)),  ((int)(.5 * screenHeight)));
        }

        //placing bucket price
        if (ProjectOdyssey.bucketPrice < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucketPrice , ((int)(.26 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.bucketPrice > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.bucketPrice , ((int)(.22 * screenWidth)),  ((int)(.25 * screenHeight)));
        }
        else if (ProjectOdyssey.bucketPrice > 9) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.bucketPrice , ((int)(.24 * screenWidth)),  ((int)(.25 * screenHeight)));
        }

        //Placing # of shovels based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
        if (ProjectOdyssey.shovel < 10){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.675 * screenWidth)),  ((int)(.485 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 999999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.56 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 99999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.58 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 9999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.60 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 999){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.62 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.64 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovel > 9){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovel , ((int)(.66 * screenWidth)),  ((int)(.5 * screenHeight))); //# of ice cubes/
        }

        //placing shovel price
        if (ProjectOdyssey.shovelPrice < 10){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.shovelPrice , ((int)(.66 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovelPrice > 99){
            moneyCounter.draw(batch, "" + ProjectOdyssey.shovelPrice , ((int)(.62 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }
        else if (ProjectOdyssey.shovelPrice > 9){
            moneyCounter.draw(batch, "$" + ProjectOdyssey.shovelPrice , ((int)(.64 * screenWidth)),  ((int)(.25 * screenHeight))); //# of ice cubes/
        }


        batch.end();
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
        shovelButton.draw(batch, 1);
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
