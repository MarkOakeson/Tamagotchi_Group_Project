import java.io.*;
import java.util.Observable;
import java.util.Random;

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
public class TamaModel extends Observable implements Serializable{
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
	
	//Limits/Standards
	private static final int AGE_PER_SECOND = 10; 
	private static final int MAX_HEALTH = 100;
	private static final int MAX_WEIGHT = 100;
	private static final int MAX_HAPPINESS = 100;
	
	private static final int MEAL_WEIGHT_GAIN = 10;
	private static final int MEAL_HAPPINESS_GAIN = 5;
	private static final int SNACK_WEIGHT_GAIN = 30;
	private static final int SNACK_HAPPINESS_GAIN = 20;
	private static final int MEDI_HAPPINESS_LOSS = 10;

	//Save file
	private File saveFile = new File("saveState.sav");

	
	public TamaModel() {
		this.age = 0;
		this.health = MAX_HEALTH;
		this.weight = MAX_WEIGHT;
		this.happiness = MAX_HAPPINESS;
		
		this.secondsPassed = 0;
		
		this.healthy = true;
		this.alive = true;
		
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
		
		if(rand.nextFloat()*100 <= chanceOfSickness) {makeSick();}
		
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
		weight -= 2;
		if(weight < 0) {weight = 0;}
		
		//Adjust happiness
		if(healthy) {
			happiness += 1;
		} else {
			happiness -= 5;
		}
		
		//Check if pet will die
		//TODO: Fill this in

		// Autosaves every 60 seconds
		if (secondsPassed % 60 == 0){
			try{
				save();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Saves the current game state, including the pet's attributes
	 * and current status, by writing this Model object to a save file
	 * in the local directory.
	 * @throws IOException
	 */
	public void save() throws IOException {
		if (!saveFile.exists()){
			saveFile.createNewFile();
		}
		FileOutputStream saveFileStream = new FileOutputStream("saveState.sav");
		ObjectOutputStream save = new ObjectOutputStream(saveFileStream);
		save.writeObject(this);
		System.out.println("Save written");

	}

	/**
	 * If a savefile does not exist or cannot be found, returns null.
	 * If savefile is found, returns the TamaModel object contained
	 * in the savefile and updates the running instance to be the
	 * newly returned model.
	 * @return - null if save not found. Otherwise, returns updated model
	 * @throws IOException
	 */
	public TamaModel load() throws IOException {
		if (!saveFile.exists()){
			return null;
		}
		FileInputStream saveFileStream = new FileInputStream("saveState.sav");
		ObjectInputStream load = new ObjectInputStream(saveFileStream);
		try {
			System.out.println("Save loaded");
			TamaModel updated =(TamaModel) load.readObject();
			System.out.println(updated.getSecondsPassed() + " seconds passed");
			return updated;
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		System.out.println("LOAD RETURNED NULL");
		return null;
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
	private void makeSick() {healthy = false;}
	public void feedMedicine() {healthy = true; happiness -= MEDI_HAPPINESS_LOSS;}
	
	public boolean isAlive() {return alive;}
	private void die() {alive = false;}
}
