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
public class TamaModel extends Observable{
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
		
		//setChanged();
		//notifyObservers(message);
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
