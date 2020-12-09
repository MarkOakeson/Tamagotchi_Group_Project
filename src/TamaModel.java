import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.layout.Pane;

/*
 * Stat Notes: 
 * Age: Begins at zero and increases at a rate that will be determined later. As age increases, 
 * Tamaclonechi becomes more difficult to maintain. The main objective of the game is to keep 
 * Tamaclonechi alive for as long as possible. 
 * 		Affects: alive (large effect)
 * 		Affected by: time elapsed while program was open
 * 
 * Health: Measures how healthy Tamaclonechi is. Happiness is dependant on this value. Health is 
 * influenced by weight and random chance. If health gets too low, Tamaclonechi can get sick. 
 * Sickness severely impacts a Tamaclonechi's ability to stay alive. To cure sickness, the user 
 * must feed Tamaclonechi medicine. 
 * 		Affects: healthy (large effect)
 * 		Affected by: weight, random chance
 * 
 * Weight: Automatically decreases with time and increases when user feeds Tamaclonechi with a 
 * meal or snack. Meals increase weight and happiness slightly; snacks increase weight and 
 * happiness greatly. When over/underweight, Tamaclonechi is more likely to get sick. Also affects
 * health. 
 * 		Affects: health (medium effect), healthy (medium effect for over/underweight)
 * 		Affected by: time, meal (small effect), snack (medium/large effect)
 * 
 * Happiness: When high, Tamaclonechi tends to get sick less and live longer. When low, vice versa.
 * Sickness makes happiness go down. Snacks, meals, and healthiness make Tamaclonechi happier.
 * 		Affects: healthy (small effect), alive (large effect)
 * 		Affected by: meal, snack, healthy (medium effect), not healthy(large effect)
 * 
 * Fullness(?): Increases when max value is 0, decreases weight and happiness when at 0. Not 
 * included in core mechanics, so inclusion of this stat is up for debate. 
 */
public class TamaModel extends Observable{
	
	// Game state
	private GameState state;
	private Pane screenPane = new MenuPane(this);

	private boolean isReset = false;

	//Core stats
	private float age; 
	private float health; 
	private float weight;
	private int happiness; 
	//private float fullness; 
	
	//System stats
	private int secondsPassed; //int will cap out at 68 years, which is probably good enough
	
	//Statuses 
	private boolean healthy;
	private boolean alive;
	
	private TamaController controller;
	
	// screenPanes
	private MenuPane menuPane = new MenuPane(this);
	private GamePane gamePane = new GamePane(this);
	
	//Limits/Standards
	private static final int AGE_PER_SECOND = 10; 
	private static final int MAX_HEALTH = 100;
	private static final int MAX_WEIGHT = 100;
	private static final int MAX_HAPPINESS = 100;
	
	private static final int MEAL_WEIGHT_GAIN = 7;
	private static final int MEAL_HAPPINESS_GAIN = 5;
	private static final int SNACK_WEIGHT_GAIN = 20;
	private static final int SNACK_HAPPINESS_GAIN = 20;
	private static final int MEDI_HAPPINESS_LOSS = 10;

	//Save file
	private File saveFile = new File("saveState.sav");
	private Attributes attributes;

	
	public TamaModel() {
		
		System.out.println("New model");
		state = new GameState();
		
		this.age = 0;
		this.health = 50;
		this.weight = 50;
		this.happiness = 50;
		
		this.secondsPassed = 0;
		
		this.healthy = true;
		this.alive = true;
		attributes = new Attributes();

	}
	
	public GameState getState() {
		return state;
	}
	
	public void resetPet() {
		isReset = true;
		age = 0;
		health = 50;
		weight = 50;
		happiness = 50;
		
		secondsPassed = 0;
		
		healthy = true;
		alive = true;


	}
	
	public void updatePet() {
		Random rand = new Random();
		
		//Adjust timer and age
		secondsPassed++;
		age = secondsPassed/AGE_PER_SECOND; 
		
		//Check if pet will become sick
		float chanceOfSickness = 1.5f;
		if(health < 35) {chanceOfSickness += 3;}
		if(health < 10) {chanceOfSickness += 5;}
		if(isUnderOverWt()){chanceOfSickness += 2;}
		if(isHappy()){chanceOfSickness -= 1;}
		if(isUnhappy()){chanceOfSickness += 1;}
		
		if(rand.nextFloat()*100 <= chanceOfSickness) {makeSick(); }
		
		//Adjust health and weight
		if(isUnderOverWt()) {
			health -= 1;
		}
		else {
			health += 0.5;
		}
		health += -0.5 + rand.nextFloat();
		if(health < 0) {health = 0;}
		else if(health > MAX_HEALTH) {health = MAX_HEALTH;}

		if(weight < 0) {weight = 0;}
		else if(weight > MAX_WEIGHT) {weight = MAX_WEIGHT;}

		if(happiness < 0) {happiness = 0;}
		else if(happiness > MAX_HAPPINESS) {happiness = MAX_HAPPINESS;}
		weight -= 2;
		if(weight < 0) {weight = 0;}
		
		//Adjust happiness
		if(healthy) {
			happiness += 1;
		} else {
			happiness -= 5;
		}

		//Check if pet will die
		determineDeath();

		// Autosaves every 30 seconds
		if (secondsPassed % 30 == 0){
			try{
				save();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
		setChanged();
		notifyObservers();
	}

	public TamaController getController() {
		return controller;
	}
	
	/**
	 * Saves the current game state, including the pet's attributes
	 * and current status, by writing this Model object to a save file
	 * in the local directory.
	 * @throws IOException
	 */
	public void save() throws IOException {
		
		attributes.setAge(age);
		attributes.setAlive(alive);
		attributes.setHealth(health);
		attributes.setHappiness(happiness);
		attributes.setWeight(weight);
		attributes.setHealthy(healthy);
		attributes.setSecondsPassed(secondsPassed);

		
		attributes.makeSave();

	}

	/**
	 * If a savefile does not exist or cannot be found, returns null.
	 * If savefile is found, returns the TamaModel object contained
	 * in the savefile and updates the running instance to be the
	 * newly returned model.
	 * @return - null if save not found. Otherwise, returns updated model
	 * @throws IOException
	 */
	public void load() throws IOException {
		if (!saveFile.exists()){
			save();
		}
		
		FileInputStream saveFileStream = new FileInputStream("saveState.sav");
		ObjectInputStream load = new ObjectInputStream(saveFileStream);
		try {
			System.out.println("Save loaded");
			Attributes updated =(Attributes) load.readObject();
			age = updated.getAge();
			happiness = updated.getHappiness();
			health = updated.getHealth();
			secondsPassed = updated.getSecondsPassed();
			alive = updated.isAlive();
			weight = updated.getWeight();
			healthy = updated.isHealthy();
			
			System.out.println(updated.getSecondsPassed() + " seconds passed");
			return;
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		System.out.println("LOAD RETURNED NULL");
		return;
	}

	// For testing purposes
	public int getSecondsPassed(){
		return secondsPassed;
	}
	
	public float getAge() {return age;}
	
	public float getHealth() {return health;}
	
	public float getWeight() {return weight;}
	public void feedMeal() {weight += MEAL_WEIGHT_GAIN; happiness += MEAL_HAPPINESS_GAIN;}
	public void feedSnack() {weight += SNACK_WEIGHT_GAIN; happiness += SNACK_HAPPINESS_GAIN;}
	private boolean isUnderOverWt() {return weight < MAX_WEIGHT/4 || weight > MAX_WEIGHT*3/4;}
	
	public int getHappiness() {return happiness;}
	public boolean isHappy() {return happiness > 70;}
	public boolean isUnhappy() {return happiness < 30;}
	
	public boolean isHealthy() {return healthy;}
	public void makeSick() {healthy = false;  gamePane.setSickImg();}
	public void feedMedicine() {healthy = true; happiness -= MEDI_HAPPINESS_LOSS;}
	
	public boolean isAlive() {return alive;}
	private void die() {
		alive = false;
		gamePane.setDeadImg();
	}

	
	
	/**
	 * for use by controller, sets the screen to be the right pane view
	 * @param
	 */
	public void setCurrentPane(String gameState) {
		state.changeState(gameState);
		if(state.getState().equals("game")){
			this.gamePane = new GamePane(this);
			this.screenPane = gamePane;
			this.menuPane = null;
		}
		else{
			this.menuPane = new MenuPane(this);
			this.screenPane = menuPane;
			this.gamePane = null;
		}

	}
	
	/*
	 * Returns the current screenPane to the model (or whatever calls it)
	 */
	public Pane getCurrentPane() {
		return screenPane;
	}
	
	/**
	 * adds an observer to the model
	 * @param o: observer
	 */
	public void plusObserver(Observer o) {
		addObserver(o);
	}
	
	/**
	 * Tells all observers that a button was pressed. This calls update.
	 * @param button: button ID
	 */
	public void pressed(String button) {
		setChanged();
		notifyObservers(button);
	}

	/*
	 * experimental, wanted to test if controller was referenced
	 */
	public void setController(TamaController controller) {
		this.controller = controller;
		
	}

	/**
	 * Determines whether pet is in a state wherein it could die, and
	 * if so, based on the severity of the conditions it is in calculates
	 * whether the pet will die or not. If pet should die, this method
	 * kills the pet.
	 */
	public void determineDeath(){
		float chanceOfDeath = 0;

		if(!isHealthy() && isUnderOverWt()){
			chanceOfDeath += (100 - health);
			if (weight < MAX_WEIGHT/4){
				chanceOfDeath += 100 - weight;
			}else{
				chanceOfDeath += weight / 2;
			}
		}
		else if(isUnhappy() && isUnderOverWt()){
			chanceOfDeath += (100 - happiness);
			if (weight < MAX_WEIGHT/4){
				chanceOfDeath += 100 - weight;
			}else{
				chanceOfDeath += weight / 2;
			}
		}
		else if(isUnhappy() && !isHealthy()){
			chanceOfDeath += (100 - health) / 2;
			chanceOfDeath += (float)(100 - happiness) / 2;
		}
		Random deathRand = new Random();
		float deathValue = deathRand.nextFloat() * 100;
		// If pet is calculated to die, kills pet
		if (deathValue < chanceOfDeath){
			die();
		}
	}

	public boolean getIsReset(){
		return isReset;
	}

	public void setIsReset(boolean isReset){
		this.isReset = isReset;
	}
}
