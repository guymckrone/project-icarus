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
import static com.mygdx.game.ProjectOdyssey.sellMode;
import static com.mygdx.game.ProjectOdyssey.unlocksOne;

/**
 * Created by guymc on 11/30/2017.
 */

//Sell and Buy

public class MarketTwo implements Screen, GestureDetector.GestureListener {

    final ProjectOdyssey game;
    private Stage stage;
    private AssetManager menuManager = new AssetManager();
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch;

    private ImageButton marketButton;
    private ImageButton iceCreamButton;
    private ImageButton snowFlakeButton;
    private ImageButton icicleButton;
    private ImageButton snowManButton;
    private ImageButton sellModeButton;
    private ImageButton buyModeButton;
    private ImageButton unlockIceCreamButton;
    private ImageButton unlockSnowFlakeButton;
    private ImageButton unlockIcicleButton;
    private ImageButton unlockSnowManButton;
    private ImageButton unlockMarketTwoButton;

    private Table marketTable;

    private BitmapFont moneyCounter = new BitmapFont(); //For drawing text
    private int screenWidth = Gdx.graphics.getWidth(); //Variable with screen width in it
    private int screenHeight = Gdx.graphics.getHeight(); //Variable for screen height
    private int unlockPrice = 100;

    private Texture logo;
    OrthographicCamera camera;

    public MarketTwo(final ProjectOdyssey game) {
        System.out.println(sellMode + "MarketTwo");
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();

        //This is for creating the text on the screen
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/slkscr.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth()/15.4285714286);
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
        moneyCounter = generator.generateFont(parameter); // font size 80 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        camera = new OrthographicCamera();//creates camera
        camera.setToOrtho(false, 800, 480);//creates viewport

        // Button skin
        Skin iceCreamButtonSkin = new Skin();
        iceCreamButtonSkin.add("sbButton", new Texture("Market/IceCream.png"));

        // Create button style
        ImageButton.ImageButtonStyle iceCreamButtonStyle = new ImageButton.ImageButtonStyle();
        iceCreamButtonStyle.imageUp = iceCreamButtonSkin.getDrawable("sbButton"); // Unpressed
        iceCreamButtonStyle.imageDown = iceCreamButtonSkin.getDrawable("sbButton"); // Pressed

        // Market button
        iceCreamButton = new ImageButton(iceCreamButtonStyle);
        int buttonSize3 = (int) (100 * Gdx.graphics.getDensity());
        iceCreamButton.setSize(buttonSize3, buttonSize3);
        int width3 = (int) ((Gdx.graphics.getWidth() * .3) - (buttonSize3 / 2));//Finding width of button for later use. We do buttonSize/2 to make sure the button is being moved by its center location, not its corner, as to assure for similar sizing on different size screens
        int height3 = (int) ((Gdx.graphics.getHeight() * .7) - (buttonSize3 / 2));//Finding height of button for later use
        iceCreamButton.setBounds(width3, height3, iceCreamButton.getWidth(), iceCreamButton.getHeight());
        iceCreamButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (ProjectOdyssey.sellMode == true && ProjectOdyssey.iceCream > 0) {//prevents from selling into negatives
                    ProjectOdyssey.iceCream--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys + ProjectOdyssey.iceCreamPrice;//adds iceprice amount of moneys each time you click button
                }
                if (ProjectOdyssey.sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.iceCreamPrice) {//prevents from selling into negatives
                    ProjectOdyssey.iceCream++;
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys - ProjectOdyssey.iceCreamPrice;//adds iceprice amount of moneys each time you click button
                }

            }

        });
        stage.addActor(iceCreamButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // Button skin
        Skin snowFlakeButtonSkin = new Skin();
        snowFlakeButtonSkin.add("bucketButton", new Texture("Market/SnowFlake.png"));

        // Create button style
        ImageButton.ImageButtonStyle snowFlakeButtonStyle = new ImageButton.ImageButtonStyle();
        snowFlakeButtonStyle.imageUp = snowFlakeButtonSkin.getDrawable("bucketButton"); // Unpressed
        snowFlakeButtonStyle.imageDown = snowFlakeButtonSkin.getDrawable("bucketButton"); // Pressed

        // Market button
        snowFlakeButton = new ImageButton(snowFlakeButtonStyle);
        int buttonSize4 = (int) (100 * Gdx.graphics.getDensity());
        snowFlakeButton.setSize(buttonSize4, buttonSize4);
        final int width4 = (int) (((Gdx.graphics.getWidth() * .7)) - (buttonSize4 / 2));
        final int height4 = (int) (((Gdx.graphics.getHeight() * .7)) - (buttonSize4 / 2));
        snowFlakeButton.setBounds(width4, height4, snowFlakeButton.getWidth(), snowFlakeButton.getHeight());

        snowFlakeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (ProjectOdyssey.sellMode == true && ProjectOdyssey.snowFlake > 0) {//prevents from selling into negatives
                    ProjectOdyssey.snowFlake--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys + ProjectOdyssey.snowFlakePrice;//adds iceprice amount of moneys each time you click button
                }
                if (ProjectOdyssey.sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.snowFlakePrice) {//prevents from selling into negatives
                    ProjectOdyssey.snowFlake++;
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys - ProjectOdyssey.snowFlakePrice;//adds iceprice amount of moneys each time you click button
                }
                //ProjectOdyssey.bucket++;
                //game.setScreen(new MainMenuScreen(game));
                //marketButton.setDisabled(false);

            }
        });

        //bucketButton.setTouchable(Touchable.disabled);
        stage.addActor(snowFlakeButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin icicleButtonSkin = new Skin();
        icicleButtonSkin.add("shovelButton", new Texture("Market/Icicle.png"));


        // Create button style
        ImageButton.ImageButtonStyle icicleButtonStyle = new ImageButton.ImageButtonStyle();
        icicleButtonStyle.imageUp = icicleButtonSkin.getDrawable("shovelButton"); // Unpressed
        icicleButtonStyle.imageDown = icicleButtonSkin.getDrawable("shovelButton"); // Pressed

        // Market button
        icicleButton = new ImageButton(icicleButtonStyle);
        int buttonSize5 = (int) (100 * Gdx.graphics.getDensity());
        icicleButton.setSize(buttonSize5, buttonSize5);
        int width5 = (int) (((Gdx.graphics.getWidth() * .3)) - (buttonSize5 / 2));
        int height5 = (int) (((Gdx.graphics.getHeight() * .35)) - (buttonSize5 / 2));
        icicleButton.setBounds(width5, height5, icicleButton.getWidth(), icicleButton.getHeight());
        icicleButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (ProjectOdyssey.sellMode == true && ProjectOdyssey.icicle > 0) {//prevents from selling into negatives
                    ProjectOdyssey.icicle--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys + ProjectOdyssey.iciclePrice;//adds iceprice amount of moneys each time you click button
                }
                if (ProjectOdyssey.sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.iciclePrice) {//prevents from selling into negatives
                    ProjectOdyssey.icicle++;
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys - ProjectOdyssey.iciclePrice;//adds iceprice amount of moneys each time you click button
                }


            }
        });
        stage.addActor(icicleButton);

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
        int width7 = (int) ((Gdx.graphics.getWidth() * .2) - (buttonSize7 / 2));
        int height7 = (int) (((Gdx.graphics.getHeight() * .95)) - (buttonSize7 / 2));
        buyModeButton.setBounds(width7, height7, buyModeButton.getWidth(), buyModeButton.getHeight());
        buyModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ProjectOdyssey.sellMode = true;
                buyModeButton.setTouchable(Touchable.disabled);
                sellModeButton.setTouchable((Touchable.enabled));
                System.out.println("sell");

            }
        });
        stage.addActor(buyModeButton);

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
        int width6 = (int) ((Gdx.graphics.getWidth() * .2) - (buttonSize6 / 2));
        int height6 = (int) (((Gdx.graphics.getHeight() * .95)) - (buttonSize6 / 2));
        sellModeButton.setBounds(width6, height6, sellModeButton.getWidth(), sellModeButton.getHeight());
        sellModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ProjectOdyssey.sellMode = false;
                sellModeButton.setTouchable(Touchable.disabled);
                buyModeButton.setTouchable((Touchable.enabled));
                System.out.println("buy");

            }
        });

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        stage.addActor(sellModeButton);
        // Button skin
        Skin gameButtonSkin = new Skin();
        gameButtonSkin.add("marketButton", new Texture("buttons/ArrowRight.png"));

        // Create button style
        ImageButton.ImageButtonStyle gameButtonStyle = new ImageButton.ImageButtonStyle();
        gameButtonStyle.imageUp = gameButtonSkin.getDrawable("marketButton"); // Unpressed
        gameButtonStyle.imageDown = gameButtonSkin.getDrawable("marketButton"); // Pressed

        // Market button
        marketButton = new ImageButton(gameButtonStyle);
        int buttonSize8 = (int) (75 * Gdx.graphics.getDensity());
        marketButton.setSize(buttonSize8, buttonSize8);
        int width8 = (int) (((Gdx.graphics.getWidth() - marketButton.getWidth()) / 4) * 3);
        int height8 = (int) ((Gdx.graphics.getHeight() - marketButton.getHeight()) / 20);
        marketButton.setBounds(width8, height8, marketButton.getWidth(), marketButton.getHeight());
        marketButton.addListener(new InputListener() {
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
        stage.addActor(marketButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin snowManButtonSkin = new Skin();
        snowManButtonSkin.add("snowManButton", new Texture("Market/SnowMan.png"));

        // Create button style
        ImageButton.ImageButtonStyle snowManButtonStyle = new ImageButton.ImageButtonStyle();
        snowManButtonStyle.imageUp = snowManButtonSkin.getDrawable("snowManButton"); // Unpressed
        snowManButtonStyle.imageDown = snowManButtonSkin.getDrawable("snowManButton"); // Pressed

        // Market button
        snowManButton = new ImageButton(snowManButtonStyle);
        int buttonSize9 = (int) (75 * Gdx.graphics.getDensity());
        snowManButton.setSize(buttonSize9, buttonSize9);
        int width9 = (int) ((Gdx.graphics.getWidth() * .7) - (buttonSize9 / 2));
        int height9 = (int) ((Gdx.graphics.getHeight() * .35) - (buttonSize9 / 2));
        snowManButton.setBounds(width9, height9, snowManButton.getWidth(), snowManButton.getHeight());
        snowManButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (ProjectOdyssey.sellMode == true && ProjectOdyssey.snowMan > 0) {//prevents from selling into negatives
                    ProjectOdyssey.snowMan--;//subtracts one ice each touchup
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys + ProjectOdyssey.iceCream;//adds iceprice amount of moneys each time you click button
                }
                if (ProjectOdyssey.sellMode == false && ProjectOdyssey.moneys >= ProjectOdyssey.snowManPrice) {//prevents from selling into negatives
                    ProjectOdyssey.snowMan++;
                    ProjectOdyssey.moneys = ProjectOdyssey.moneys - ProjectOdyssey.snowManPrice;//adds iceprice amount of moneys each time you click button
                }


            }
        });
        //snowManButton.setTouchable(Touchable.disabled);
        stage.addActor(snowManButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));


        // Button skin
        Skin unlockIceCreamButtonSkin = new Skin();
        unlockIceCreamButtonSkin.add("unlockSnowBallButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle unlockIceCreamButtonStyle = new ImageButton.ImageButtonStyle();
        unlockIceCreamButtonStyle.imageUp = unlockIceCreamButtonSkin.getDrawable("unlockSnowBallButton"); // Unpressed
        unlockIceCreamButtonStyle.imageDown = unlockIceCreamButtonSkin.getDrawable("unlockSnowBallButton"); // Pressed

        // Play button
        unlockIceCreamButton = new ImageButton(unlockIceCreamButtonStyle);
        int buttonSize12 = (int) (120 * Gdx.graphics.getDensity());
        unlockIceCreamButton.setSize(buttonSize12, buttonSize12);
        int width12 = (int) ((Gdx.graphics.getWidth() * .3) - (buttonSize12 / 2));//Finding width of button for later use. We do buttonSize/2 to make sure the button is being moved by its center location, not its corner, as to assure for similar sizing on different size screens
        int height12 = (int) ((Gdx.graphics.getHeight() * .7) - (buttonSize12 / 2));//Finding height of button for later use
        unlockIceCreamButton.setBounds(width12, height12, unlockIceCreamButton.getWidth(), unlockIceCreamButton.getHeight());
        unlockIceCreamButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(moneys > 4999){
                    moneys = moneys - 5000;
                    ProjectOdyssey.unlocksTwo++;
                }

            }
        });
        stage.addActor(unlockIceCreamButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin unlockSnowFlakeButtonSkin = new Skin();
        unlockSnowFlakeButtonSkin.add("unlockBucketButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle unlockSnowFlakeButtonStyle = new ImageButton.ImageButtonStyle();
        unlockSnowFlakeButtonStyle.imageUp = unlockSnowFlakeButtonSkin.getDrawable("unlockBucketButton"); // Unpressed
        unlockSnowFlakeButtonStyle.imageDown = unlockSnowFlakeButtonSkin.getDrawable("unlockBucketButton"); // Pressed

        // Play button
        unlockSnowFlakeButton = new ImageButton(unlockSnowFlakeButtonStyle);
        int buttonSize13 = (int) (120 * Gdx.graphics.getDensity());
        unlockSnowFlakeButton.setSize(buttonSize13, buttonSize13);
        final int width13 = (int) (((Gdx.graphics.getWidth() * .7)) - (buttonSize13 / 2));
        final int height13 = (int) (((Gdx.graphics.getHeight() * .7)) - (buttonSize13 / 2));
        unlockSnowFlakeButton.setBounds(width13, height13, unlockSnowFlakeButton.getWidth(), unlockSnowFlakeButton.getHeight());
        unlockSnowFlakeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(moneys > 9999){
                    moneys = moneys - 10000;
                    ProjectOdyssey.unlocksTwo++;
                }

            }
        });
        stage.addActor(unlockSnowFlakeButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin unlockIcicleButtonSkin = new Skin();
        unlockIcicleButtonSkin.add("playButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle unlockIcicleButtonStyle = new ImageButton.ImageButtonStyle();
        unlockIcicleButtonStyle.imageUp = unlockIcicleButtonSkin.getDrawable("playButton"); // Unpressed
        unlockIcicleButtonStyle.imageDown = unlockIcicleButtonSkin.getDrawable("playButton"); // Pressed

        // Play button
        unlockIcicleButton = new ImageButton(unlockIcicleButtonStyle);
        int buttonSize14 = (int) (120 * Gdx.graphics.getDensity());
        unlockIcicleButton.setSize(buttonSize14, buttonSize14);
        int width14 = (int) (((Gdx.graphics.getWidth() * .3)) - (buttonSize14 / 2));
        int height14 = (int) (((Gdx.graphics.getHeight() * .35)) - (buttonSize14 / 2));
        unlockIcicleButton.setBounds(width14, height14, unlockIcicleButton.getWidth(), unlockIcicleButton.getHeight());
        unlockIcicleButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(moneys > 24999){
                    moneys = moneys - 25000;
                    ProjectOdyssey.unlocksTwo++;
                }

            }
        });
        stage.addActor(unlockIcicleButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        // Button skin
        Skin unlockSnowManButtonSkin = new Skin();
        unlockSnowManButtonSkin.add("playButton", new Texture("buttons/Upgrade.png"));

        // Create button style
        ImageButton.ImageButtonStyle unlockSnowManButtonStyle = new ImageButton.ImageButtonStyle();
        unlockSnowManButtonStyle.imageUp = unlockSnowManButtonSkin.getDrawable("playButton"); // Unpressed
        unlockSnowManButtonStyle.imageDown = unlockSnowManButtonSkin.getDrawable("playButton"); // Pressed

        // Play button
        unlockSnowManButton = new ImageButton(unlockSnowManButtonStyle);
        int buttonSize15 = (int) (120 * Gdx.graphics.getDensity());
        unlockSnowManButton.setSize(buttonSize15, buttonSize15);
        int width15 = (int) ((Gdx.graphics.getWidth() * .7) - (buttonSize15 / 2));
        int height15 = (int) ((Gdx.graphics.getHeight() * .35) - (buttonSize15 / 2));
        unlockSnowManButton.setBounds(width15, height15, unlockSnowManButton.getWidth(), unlockSnowManButton.getHeight());
        unlockSnowManButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(moneys > 99999){
                    moneys = moneys - 99999;
                    ProjectOdyssey.unlocksTwo++;
                }

            }
        });
        stage.addActor(unlockSnowManButton);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new GestureDetector(this)));

        if (sellMode == true) {
            sellModeButton.setTouchable(Touchable.enabled);
            buyModeButton.setTouchable((Touchable.disabled));
        }
        if (sellMode == false) {
            sellModeButton.setTouchable(Touchable.disabled);
            buyModeButton.setTouchable((Touchable.enabled));
        }
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
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.88 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x<10
        } else if (moneys > 999999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.58 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>999999
        } else if (moneys > 99999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.63 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>99999
        } else if (moneys > 9999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.68 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>9999
        } else if (moneys > 999) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.73 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>999
        } else if (moneys > 99) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.78 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>99
        } else if (moneys > 9) {
            moneyCounter.draw(batch, "$" + ProjectOdyssey.moneys, ((int) (.83 * screenWidth)), ((int) (.98 * screenHeight))); //Position of money counter when x>9
        }
        if (ProjectOdyssey.unlocksTwo >= 1) {
            //Placing # of iceCubes based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
            if (ProjectOdyssey.iceCream < 10) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.275 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 999999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.125 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 99999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.15 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 9999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.175 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.2 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 99) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.225 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCream > 9) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.iceCream, ((int) (.25 * screenWidth)), ((int) (.835 * screenHeight))); //# of ice cubes/
            }


            //placing iceCube Price
            if (ProjectOdyssey.iceCreamPrice < 10) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice, ((int) (.25 * screenWidth)), ((int) (.6 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCreamPrice > 999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice, ((int) (.175 * screenWidth)), ((int) (.6 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCreamPrice > 99) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice, ((int) (.2 * screenWidth)), ((int) (.6 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.iceCreamPrice > 9) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iceCreamPrice, ((int) (.225 * screenWidth)), ((int) (.6 * screenHeight))); //# of ice cubes/
            }
        }
        if (ProjectOdyssey.unlocksTwo >= 2) {
            //Placing # of snowballs based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
            if (ProjectOdyssey.snowFlake < 10) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.675 * screenWidth)), ((int) (.835 * screenHeight))); //# of snowBalls
            } else if (ProjectOdyssey.snowFlake > 999999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.525 * screenWidth)), ((int) (.835 * screenHeight)));
            } else if (ProjectOdyssey.snowFlake > 99999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.55 * screenWidth)), ((int) (.835 * screenHeight)));
            } else if (ProjectOdyssey.snowFlake > 9999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.575 * screenWidth)), ((int) (.835 * screenHeight)));
            } else if (ProjectOdyssey.snowFlake > 999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.6 * screenWidth)), ((int) (.835 * screenHeight)));
            } else if (ProjectOdyssey.snowFlake > 99) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.625 * screenWidth)), ((int) (.835 * screenHeight)));
            } else if (ProjectOdyssey.snowFlake > 9) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowFlake, ((int) (.65 * screenWidth)), ((int) (.835 * screenHeight)));
            }

            //placing snowFlake price
            if (ProjectOdyssey.snowFlakePrice < 10) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice, ((int) (.65 * screenWidth)), ((int) (.6 * screenHeight)));
            } else if (ProjectOdyssey.snowFlakePrice > 999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice, ((int) (.575 * screenWidth)), ((int) (.6 * screenHeight)));
            } else if (ProjectOdyssey.snowFlakePrice > 99) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice, ((int) (.6 * screenWidth)), ((int) (.6 * screenHeight)));
            } else if (ProjectOdyssey.snowFlakePrice > 9) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowFlakePrice, ((int) (.625 * screenWidth)), ((int) (.6 * screenHeight)));
            }
        }
        if (ProjectOdyssey.unlocksTwo >= 3) {
            //Placing # of buckets based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
            if (ProjectOdyssey.icicle < 10) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.275 * screenWidth)), ((int) (.485 * screenHeight))); //# of buckets
            } else if (ProjectOdyssey.icicle > 999999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.125 * screenWidth)), ((int) (.485 * screenHeight)));
            } else if (ProjectOdyssey.icicle > 99999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.15 * screenWidth)), ((int) (.485 * screenHeight)));
            } else if (ProjectOdyssey.icicle > 9999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.175 * screenWidth)), ((int) (.485 * screenHeight)));
            } else if (ProjectOdyssey.icicle > 999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.2 * screenWidth)), ((int) (.485 * screenHeight)));
            } else if (ProjectOdyssey.icicle > 99) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.225 * screenWidth)), ((int) (.485 * screenHeight)));
            } else if (ProjectOdyssey.icicle > 9) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.icicle, ((int) (.25 * screenWidth)), ((int) (.485 * screenHeight)));
            }

            //placing bucket price
            if (ProjectOdyssey.iciclePrice < 10) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice, ((int) (.25 * screenWidth)), ((int) (.25 * screenHeight)));
            } else if (ProjectOdyssey.iciclePrice > 9999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice, ((int) (.15 * screenWidth)), ((int) (.25 * screenHeight)));
            } else if (ProjectOdyssey.iciclePrice > 999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice, ((int) (.175 * screenWidth)), ((int) (.25 * screenHeight)));
            } else if (ProjectOdyssey.iciclePrice > 99) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice, ((int) (.2 * screenWidth)), ((int) (.25 * screenHeight)));
            } else if (ProjectOdyssey.iciclePrice > 9) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.iciclePrice, ((int) (.225 * screenWidth)), ((int) (.25 * screenHeight)));
            }
        }

        if (ProjectOdyssey.unlocksTwo >= 4) {
            //Placing # of shovels based off the number of digits to ensure the number is centered over the Ice Cube Sprite.
            if (ProjectOdyssey.snowMan < 10) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.675 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 999999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.525 * screenWidth)), ((int) (.4855 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 99999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.5 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 9999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.575 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 999) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.6 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 99) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.625 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowMan > 9) {
                moneyCounter.draw(batch, "" + ProjectOdyssey.snowMan, ((int) (.65 * screenWidth)), ((int) (.485 * screenHeight))); //# of ice cubes/
            }

            //placing shovel price
            if (ProjectOdyssey.snowManPrice < 10) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int) (.65 * screenWidth)), ((int) (.25 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowManPrice > 9999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int) (.5 * screenWidth)), ((int) (.25 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowManPrice > 999) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int) (.575 * screenWidth)), ((int) (.25 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowManPrice > 99) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int) (.6 * screenWidth)), ((int) (.25 * screenHeight))); //# of ice cubes/
            } else if (ProjectOdyssey.snowManPrice > 9) {
                moneyCounter.draw(batch, "$" + ProjectOdyssey.snowManPrice, ((int) (.625 * screenWidth)), ((int) (.25 * screenHeight))); //# of ice cubes/
            }
        }

        batch.end();
        batch.begin();
        if (ProjectOdyssey.sellMode == true) {
            sellModeButton.draw(batch, 1);

        }
        if (ProjectOdyssey.sellMode == false) {
            buyModeButton.draw(batch, 1);

        }
        if (ProjectOdyssey.unlocksTwo == 0) {
            unlockIceCreamButton.draw(batch, 1);
            moneyCounter.draw(batch, "$" + 5000, ((int) (.3 * screenWidth - (unlockIceCreamButton.getWidth()/2))), ((int) (.71 * screenHeight )));
        }
        if (ProjectOdyssey.unlocksTwo >= 1) {
            iceCreamButton.draw(batch, 1);
            unlockIceCreamButton.setTouchable(Touchable.disabled);
        }
        if (ProjectOdyssey.unlocksTwo == 1) {
            unlockSnowFlakeButton.draw(batch, 1);
            moneyCounter.draw(batch, "$" + 10000, ((int) (.7 * screenWidth - (unlockIceCreamButton.getWidth()/2))), ((int) (.71 * screenHeight )));
        }
        if (ProjectOdyssey.unlocksTwo >= 2) {
            snowFlakeButton.draw(batch, 1);
            unlockSnowFlakeButton.setTouchable(Touchable.disabled);
        }
        if (ProjectOdyssey.unlocksTwo == 2) {
            unlockIcicleButton.draw(batch, 1);
            moneyCounter.draw(batch, "$" + 25000, ((int) (.3 * screenWidth - (unlockIceCreamButton.getWidth()/2))), ((int) (.36 * screenHeight )));
        }
        if (ProjectOdyssey.unlocksTwo >= 3) {
            icicleButton.draw(batch, 1);
            unlockIcicleButton.setTouchable(Touchable.disabled);
        }
        if (ProjectOdyssey.unlocksTwo == 3) {
            unlockSnowManButton.draw(batch, 1);
            moneyCounter.draw(batch, "$" + 99999, ((int) (.7 * screenWidth - (unlockIceCreamButton.getWidth()/2))), ((int) (.36 * screenHeight )));
        }
        if (ProjectOdyssey.unlocksTwo >= 4) {
            snowManButton.draw(batch, 1);
            unlockSnowManButton.setTouchable(Touchable.disabled);
        }

        marketButton.draw(batch, 1);
        batch.end();

        /*sellSnowFlake.draw(batch, 1);
        sellIcicle.draw(batch, 1);
        sellSnowMan.draw(batch, 1);
        marketButton.draw(batch, 1);
        snowManButton.draw(batch, 1);*/
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
