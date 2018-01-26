package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;

// COLORS: good ice blue: #90f5ff

public class ProjectOdyssey extends Game {
	public static int ice = 10;//ice variable used in every class
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
	public static int moneys;
	public static float scale = 1;

	//Texture img;

    Timer t = new Timer(); //declare the timer for the upgrades method

    int upgradeOne = 1;
    int upgradeTwo = 1;


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

	int snowManPrice = 0;
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



		//final prices for the day

		super.render();
	}

	public void upgrades(){

	    if (upgradeOne == 1) {
	        upgradeOne = 0;
            t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
                                      @Override
                                      public void run() { //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                          callEverything();
                                      }
                                  },
                    0, //Set how long before to start calling the TimerTask (in milliseconds)
                    5000);  //Set the amount of time between each execution (in milliseconds)
        }

        if (upgradeTwo == 1) {
            upgradeTwo = 0;
            t.scheduleAtFixedRate(new TimerTask() {  //Set the schedule function and rate
                                      @Override
                                      public void run() { //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                          System.out.println("Price RUNS AT THE SAME TIME YES");
                                      }
                                  },
                    0, //Set how long before to start calling the TimerTask (in milliseconds)
                    5000);  //Set the amount of time between each execution (in milliseconds)
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
			shovelPrice = shovelMin;
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
