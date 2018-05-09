package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;

// COLORS: good ice blue: #90f5ff


public class ProjectOdyssey extends Game {

	public static int story = 0; //Ok, so this variable tracks the players progress it the game.

	public static int ice = 0;//ice variable used in every class
	public static int snowBall = 0;
	public static int bucket = 0;
	public static int shovel = 0;
	public static int iceCream = 0;
	public static int snowFlake = 0;
	public static int icicle = 0;
	public static int snowMan = 0;
	public AssetManager assets;
	//GameScreen.setupAssetManager(assets)
	public SpriteBatch batch;
	public BitmapFont font;
	public static boolean marketShow;
	public static int moneys = 9999999; //changing for testing - natty
	public static float scale = 1;


	
	public static boolean lobbyUnlock = false;

	public static int unlocksOne = 0;
	public static int unlocksTwo = 0;
	public static boolean sellBuyBought = false;
	public static boolean sbBought = false;
	public static boolean shovelBought = false;
	public static boolean bucketBought = false;
	public static boolean marketTwoBought = false;
	public static boolean firstDraw = false;
	public static boolean sellMode = true;

	//Texture img;

    Timer t = new Timer(); //declare the timer for the upgrades method

    public static boolean isMarketRunning = false;

    //These variables are all used to track if upgrades have been purchased on the Book.java screen. Upgrade OneOne is the first upgrade for the top (visually) upgrade path. And upgrade three four is the fourth upgrade for the button upgrade path.

	public static boolean upgradeOneOne = false;
	public static boolean upgradeOneTwo = false;
	public static boolean upgradeOneThree = false;
	public static boolean upgradeOneFour = false;
	public static boolean upgradeOneFive = false;
	public static boolean upgradeOneSix = false;
	public static boolean upgradeOneSeven = false;
	public static boolean upgradeOneEight = false;
	public static boolean upgradeOneNine = false;
	public static boolean upgradeOneTen = false;
	public static boolean upgradeOneEleven = false;
	public static boolean upgradeOneTwelve = false;
	public static boolean upgradeOneThirteen = false;


	public static boolean upgradeTwoOne = false;
	public static boolean upgradeTwoTwo = false;
	public static boolean upgradeTwoThree = false;
	public static boolean upgradeTwoFour = false;
	public static boolean upgradeTwoFive = false;
	public static boolean upgradeTwoSix = false;
	public static boolean upgradeTwoSeven = false;
	public static boolean upgradeTwoEight = false;
	public static boolean upgradeTwoNine = false;
	public static boolean upgradeTwoTen = false;
	public static boolean upgradeTwoEleven = false;
	public static boolean upgradeTwoTwelve = false;
	public static boolean upgradeTwoThirteen = false;

	public static boolean upgradeThreeOne = false;
	public static boolean upgradeThreeTwo = false;
	public static boolean upgradeThreeThree = false;
	public static boolean upgradeThreeFour = false;
	public static boolean upgradeThreeFive = false;
	public static boolean upgradeThreeSix = false;
	public static boolean upgradeThreeSeven = false;
	public static boolean upgradeThreeEight = false;
	public static boolean upgradeThreeNine = false;
	public static boolean upgradeThreeTen = false;
	public static boolean upgradeThreeEleven = false;
	public static boolean upgradeThreeTwelve = false;
	public static boolean upgradeThreeThirteen = false;

	String marketEvent = ""; //Text of market event

	public static int iceCubePrice = 0; //current price of item
	int iceCubeMin = 3; //minimum possible price in the algorithm
	int iceCubeMax = 5; //maximum possible price in the algorithm

	public static int snowBallPrice = 0;
	int snowBallMin = 8;
	int snowBallMax = 15;

	public static int bucketPrice = 0;
	int bucketMin = 16;
	int bucketMax = 33;


	public static int shovelPrice = 0;
	int shovelMin = 25;
	int shovelMax = 49;

	public static int iceCreamPrice = 0;
	int iceCreamMin = 56;
	int iceCreamMax = 108;

	public static int snowFlakePrice = 0;
	int snowFlakeMin = 112;
	int snowFlakeMax = 187;

	public static int iciclePrice = 0;
	int icicleMin = 188;
	int icicleMax = 354;

	public static int snowManPrice = 0;
	int snowManMin = 456;
	int snowManMax = 832;



	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}



	@Override
	public void render () {

		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();

		upgrades();
		changeMaxValues();


		//final prices for the day

		super.render();
	}

	public void changeMaxValues(){
		if (upgradeOneTwelve == true){
			bucketMax = 50;
		}
		else if(upgradeOneEleven == true){
			snowBallMax = 23;
		}
		else if (upgradeOneTen == true){
			iceCubeMax = 7;
		}

		if (upgradeTwoTwelve == true){
			snowFlakeMax = 281;
		}
		else if(upgradeTwoEleven == true){
			iceCreamMax = 162;
		}
		else if (upgradeTwoTen == true){
			shovelMax = 74;
		}

		if (upgradeThreeTwelve == true){
			//tbd
		}
		else if(upgradeThreeEleven == true){
			snowManMax = 1248;
		}
		else if (upgradeThreeEleven == true){
			icicleMax = 531;
		}


	}


	public void upgrades(){ //These upgrades will begin "stacking", or running themselves more than wanted, if you run the game with the yellow lightning bolt. If you do the green arrow, or a full restart, it will get rid of the old loops and only run the new ones once, as intended. We are going to need to monitor this.

	    if (isMarketRunning == false) {
	        isMarketRunning = true;
            t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
                                      @Override
                                      public void run() { //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                          callEverything();
                                      }
                                  },
                    0, //Set how long before to start calling the TimerTask (in milliseconds)
                    10000);  //Set the amount of time between each execution (in milliseconds)
        }

        //Upgrade path 1
		if (upgradeOneOne == true){ //one icecube a minute
	    	upgradeOneOne = false;
			t.scheduleAtFixedRate(new TimerTask() {
									  @Override
									  public void run() {
											ice++;
									  }
								  },
					0,
					60000);
		}
		if (upgradeOneTwo == true){ //three icecubes a minute
			upgradeOneTwo = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  ice = ice + 4;
									  }
								  },
					0,
					60000);
		}
		if (upgradeOneThree == true){ //seven icecubes a minute
			upgradeOneThree = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  ice = ice + 10;
									  }
								  },
					0,
					60000);
		}
		if (upgradeOneFour == true){
			upgradeOneFour = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowBall = snowBall + 3;
									  }
								  },
					0,
					60000);
		}
		if (upgradeOneFive == true){
			upgradeOneFive = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowBall = snowBall + 8;					  }
								  },
					0,
					60000);
		}
		if (upgradeOneSix == true){
			upgradeOneSix = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowBall = snowBall + 13;								  }
								  },
					0,
					60000);
		}
		if (upgradeOneSeven == true){
			upgradeOneSeven = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 5;									  }
								  },
					0,
					60000);
		}
		if (upgradeOneEight == true){
			upgradeOneEight = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 11;							  }
								  },
					0,
					60000);
		}
		if (upgradeOneNine == true){
			upgradeOneNine = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 16;							  }
								  },
					0,
					60000);
		}
		if (upgradeOneTen == true){
			upgradeOneTen = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate, this one is not complete
									  @Override
									  public void run() { //
										  ice = ice + 0;									  }
								  },
					0,
					1000);
		}

		//upgrade path 2
		if (upgradeTwoOne == true){
			upgradeTwoOne = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { // one snowball a minute
										 shovel++;
									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoTwo == true){
			upgradeTwoTwo = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { // four snowballs a minute
										  shovel = shovel + 4;
									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoThree == true){
			upgradeTwoThree = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { // twelve snowballs a minute
										  shovel = shovel + 7;
									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoFour == true){
			upgradeTwoFour = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  iceCream = iceCream + 3;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoFive == true){
			upgradeTwoFive = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  iceCream = iceCream + 5;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoSix == true){
			upgradeTwoSix = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  iceCream = iceCream + 9;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoSeven == true){
			upgradeTwoSeven = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowFlake = snowFlake + 5;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoEight == true){
			upgradeTwoEight = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowFlake = snowFlake + 7;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoNine == true){
			upgradeTwoNine = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowFlake = snowFlake + 10;									  }
								  },
					0,
					60000);
		}
		if (upgradeTwoTen == true){ //placeholder
			upgradeTwoTen = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowBall = snowBall + 0;									  }
								  },
					0,
					1000);
		}

		//Upgrade path 3
		if (upgradeThreeOne == true){
			upgradeThreeOne = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  icicle = icicle + 4;
									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeTwo == true){
			upgradeThreeTwo = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  icicle = icicle + 7;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeThree == true){
			upgradeThreeThree = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  icicle = icicle + 13;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeFour == true){
			upgradeThreeFour = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowMan = snowMan + 5;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeFive == true){
			upgradeThreeFive = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowMan = snowMan + 8;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeSix == true){
			upgradeThreeSix = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  snowMan = snowMan + 13;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeSeven == true){
			upgradeThreeSeven = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 0;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeEight == true){
			upgradeThreeEight = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 0;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeNine == true){
			upgradeThreeNine = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 0;									  }
								  },
					0,
					60000);
		}
		if (upgradeThreeTen == true){
			upgradeThreeTen = false;
			t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
									  @Override
									  public void run() { //
										  bucket = bucket + 0;									  }
								  },
					0,
					1000);
		}


    }




	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		//img.dispose();
	}

	public void callEverything(){
		iceCubeChange();
		snowBallChange();
		bucketChange();
		shovelChange();
		iceCreamChange();
		snowFlakeChange();
		icicleChange();
		snowManChange();
		checkForEvent();

        System.out.println(" --------------- Price "); //Break line to read the market easier
	}

	public void checkForEvent() {
		int random = (int) (Math.random() * 300 + 1); //makes a random number between 1 and 300*60

		if (random == 123) { //if random == 123, heat waves comes and lowers the value of all ice products.
			iceCubePrice = iceCubeMin;
			snowBallPrice = snowBallMin;
			bucketPrice = bucketMin;
			snowManPrice = shovelMin;
			iceCreamPrice = iceCreamMin;
			snowFlakePrice = snowFlakeMin;
			iciclePrice = icicleMin;
			snowManPrice = snowManMin;
			random = (int) (Math.random() * 5 + 1);
			if (random == 1) { //Make 5 different event statements all for catastrophes.
				marketEvent = "There has been a heatwave destroying ice product infrastructure!";
			}
			if (random == 2){
				marketEvent = "Congress has defunded the Ice Product Subsidies Program!";
			}
			if (random == 3){
				marketEvent = "A 60 minute special about the harmful effects of frostbite has gone viral!";
			}
			if (random == 4){
				marketEvent = "Sand products have now become the hit sensation in pop culture!";
			}
			if (random == 5){
				marketEvent = "China has banned all ice products for no reason!";
			}
		}
	}

	public void iceCubeChange(){
		if (iceCubePrice == iceCubeMin){ //If the price of icecubes have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase it by 1.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				iceCubePrice++;
			}
		}
		else if (iceCubePrice == iceCubeMax){//If the price is at a high, drop it to the min. We drop it all the way to the min for the iceCube because it has the smallest of possible values, and it has to be hard for the player to make a profit in the begging.
			iceCubePrice = 3;
		}
		else{ //If the price is neither max or min, so in this case 4:
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2){ //50 % chance it stays the same value
				iceCubePrice = 4;
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					iceCubePrice++;
				}
				else {
					iceCubePrice--;
				}
			}
		}
		if (iceCubePrice > iceCubeMax){ //This makes sure the price of the item never goes outside its designated price range.
			iceCubePrice = iceCubeMax;
		}
		if (iceCubePrice < iceCubeMin){
			iceCubePrice = iceCubeMin;
		}
		System.out.println("Ice Cube Price:" + iceCubePrice);
	}

	public void snowBallChange(){
		if (snowBallPrice == snowBallMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				snowBallPrice = snowBallPrice + (random = (int) (Math.random() * 4 + 1));
			}
		}
		else if (snowBallPrice == snowBallMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 4 + 1);
			snowBallPrice = snowBallPrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 3 + 1);
			if (random == 3){ //33 % chance it stays the same value
				//nothing changes

			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					snowBallPrice = snowBallPrice + (random = (int) (Math.random() * 2 + 1));
				}
				else {
					snowBallPrice = snowBallPrice - (random = (int) (Math.random() * 3 + 1));//A slightly smaller chance to down turn, because who buys snowballs?
				}
			}
		}
		if (snowBallPrice > snowBallMax){ //This makes sure the price of the item never exceeds its designated max price
			snowBallPrice = snowBallMax;
		}
		if (snowBallPrice < snowBallMin){
			snowBallPrice = snowBallMin;
		}
		System.out.println("SnowBall Price:" + snowBallPrice);
	}

	public void bucketChange(){
		if (bucketPrice == bucketMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				bucketPrice = bucketPrice + (random = (int) (Math.random() * 7 + 1));
			}
		}
		else if (bucketPrice == bucketMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 7 + 1);
			bucketPrice = bucketPrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					bucketPrice = bucketPrice + (random = (int) (Math.random() * 4 + 1));
				}
				else {
					bucketPrice = bucketPrice - (random = (int) (Math.random() * 5 + 1));
				}
			}
		}
		if (bucketPrice > bucketMax){ //This makes sure the price of the item never exceeds its designated max price
			bucketPrice = bucketMax;
		}
		if (bucketPrice < bucketMin){
			bucketPrice = bucketMin;
		}
		System.out.println("Bucket Price:" + bucketPrice);
	}

	public void shovelChange(){
		if (shovelPrice == shovelMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				shovelPrice = shovelPrice + (random = (int) (Math.random() * 11 + 1));
			}
		}
		else if (shovelPrice == shovelMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 11 + 1);
			shovelPrice = shovelPrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					shovelPrice = shovelPrice + (random = (int) (Math.random() * 6 + 1));
				}
				else {
					shovelPrice = shovelPrice - (random = (int) (Math.random() * 6 + 1));
				}
			}
		}
		if (shovelPrice > shovelMax){ //This makes sure the price of the item never exceeds its designated max price
			shovelPrice = shovelMax;
		}
		if (shovelPrice < shovelMin){
			shovelPrice = shovelMin;
		}
		System.out.println("Shovel Price:" + shovelPrice);
	}

	public void iceCreamChange(){
		if (iceCreamPrice == iceCreamMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				iceCreamPrice = iceCreamPrice + (random = (int) (Math.random() * 18 + 1));
			}
		}
		else if (iceCreamPrice == iceCreamMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 18 + 1);
			iceCreamPrice = iceCreamPrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					iceCreamPrice = iceCreamPrice + (random = (int) (Math.random() * 12 + 1));
				}
				else {
					iceCreamPrice = iceCreamPrice - (random = (int) (Math.random() * 12 + 1));
				}
			}
		}
		if (iceCreamPrice > iceCreamMax){ //This makes sure the price of the item never exceeds its designated max price
			iceCreamPrice = iceCreamMax;
		}
		if (iceCreamPrice < iceCreamMin){
			iceCreamPrice = iceCreamMin;
		}
		System.out.println("IceCream Price:" + iceCreamPrice);
	}

	public void snowFlakeChange(){
		if (snowFlakePrice == snowFlakeMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				snowFlakePrice = snowFlakePrice + (random = (int) (Math.random() * 28 + 1));
			}
		}
		else if (snowFlakePrice == snowFlakeMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 28 + 1);
			snowFlakePrice = snowFlakePrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					snowFlakePrice = snowFlakePrice + (random = (int) (Math.random() * 22 + 1));
				}
				else {
					snowFlakePrice = snowFlakePrice - (random = (int) (Math.random() * 22 + 1));
				}
			}
		}
		if (snowFlakePrice > snowFlakeMax){ //This makes sure the price of the item never exceeds its designated max price
			snowFlakePrice = snowFlakeMax;
		}
		if (snowFlakePrice < snowFlakeMin){
			snowFlakePrice = snowFlakeMin;
		}
		System.out.println("SnowFlake Price:" + snowFlakePrice);
	}

	public void icicleChange(){
		if (iciclePrice == icicleMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				iciclePrice = iciclePrice + (random = (int) (Math.random() * 40 + 1));
			}
		}
		else if (iciclePrice == icicleMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 40 + 1);
			iciclePrice = iciclePrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					iciclePrice = iciclePrice + (random = (int) (Math.random() * 30 + 1));
				}
				else {
					iciclePrice = iciclePrice - (random = (int) (Math.random() * 30 + 1));
				}
			}
		}
		if (iciclePrice > icicleMax){ //This makes sure the price of the item never exceeds its designated max price
			iciclePrice = icicleMax;
		}
		if (iciclePrice < icicleMin){
			iciclePrice = icicleMin;
		}
		System.out.println("Icicle Price:" + iciclePrice);
	}

	public void snowManChange(){
		if (snowManPrice == snowManMin){ //If the price of the item have hit the bottom, have there be a 50% chance chance the values stays there, otherwise increase by a random amount.
			int random = (int) (Math.random() * 2 + 1);
			if (random == 2) {
				snowManPrice = snowManPrice + (random = (int) (Math.random() * 100 + 1));
			}
		}
		else if (snowManPrice == snowManMax){//If the price is at a high, drop it a random amount.
			int random = (int) (Math.random() * 100 + 1);
			snowManPrice = snowManPrice - random;
		}
		else{ //If the price is not neither max or min:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 4){ //25 % chance it stays the same value
				//nothing changes
			}
			else{
				random = (int) (Math.random() * 2 + 1); //If it does not stay the same, 50% chance it goes up, 50% chance it goes down.
				if (random == 2) {
					snowManPrice = snowManPrice + (random = (int) (Math.random() * 60 + 1));
				}
				else {
					snowManPrice = snowManPrice - (random = (int) (Math.random() * 60 + 1));
				}
			}
		}
		if (snowManPrice > snowManMax){ //This makes sure the price of the item never exceeds its designated max price
			snowManPrice = snowManMax;
		}
		if (snowManPrice < snowManMin){
			snowManPrice = snowManMin;
		}
		System.out.println("SnowMan Price:" + snowManPrice);
	}


}
