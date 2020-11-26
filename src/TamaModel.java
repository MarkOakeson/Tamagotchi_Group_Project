
/*
 * Stat Notes: 
 * Age: Begins at zero and increases at a rate that will be determined later. As age increases, 
 * Tamaclonechi becomes more difficult to maintain. The main objective of the game is to keep 
 * Tamaclonechi alive for as long as possible. 
 * 		Affects: alive (large effect)
 * 		Affected by: time elapsed while program was open (may change to even when program is
 * 		closed)
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
 * happiness greattly. When over/underweight, Tamaclonechi is more likely to get sick. Also affects
 * health. 
 * 		Affects: health (medium effect), healthy (medium effect for over/underweight)
 * 		Affected by: meal (large effect), snack (medium effect)
 * 
 * Happiness: When high, Tamaclonechi tends to get sick less and live longer. When low, vice versa.
 * Sickness makes happiness go down. Snacks, meals, and healthiness make Tamaclonechi happier.
 * 		Affects: healthy (small effect), alive (large effect)
 * 		Affected by: meal, snack, healthy (medium effect), not healthy(large effect)
 * 
 * Fullness(?): Increases when max value is 0, decreases weight and happiness when at 0. Not 
 * included in core mechanics, so inclusion of this stat is up for debate. 
 */
public class TamaModel {
	//Core stats
	private float age; 
	private int health; 
	private int weight;
	private int happiness; 
	//private float fullness; 
	
	//Statuses 
	private boolean healthy;
	private boolean alive;
	
	//Limits/Standards
	private static final int HEALTH_MAX = 100;
	private static final int WEIGHT_MAX = 100;
	private static final int HAPPINESS_MAX = 100;
	
	private static final int MEAL_WEIGHT_GAIN = 10;
	private static final int MEAL_HAPPINESS_GAIN = 10;
	private static final int SNACK_WEIGHT_GAIN = 30;
	private static final int SNACK_HAPPINESS_GAIN = 20;
	private static final int MEDI_HAPPINESS_LOSS = 10;
	
	public TamaModel() {
		this.age = 0;
		this.health = HEALTH_MAX;
		this.weight = WEIGHT_MAX;
		this.happiness = HAPPINESS_MAX;
		this.healthy = true;
		this.alive = true;
	}
	
	public float getAge() {return age;}
	public int getHealth() {return health;}
	public int getWeight() {return weight;}
	public void feedMeal() {weight += MEAL_WEIGHT_GAIN; happiness += MEAL_HAPPINESS_GAIN;}
	public void feedSnack() {weight += SNACK_WEIGHT_GAIN; happiness += SNACK_HAPPINESS_GAIN;}
	public int getHappiness() {return happiness;}
	public boolean isHealthy() {return healthy;}
	private void makeHealthy() {healthy = true;}
	private void makeSick() {healthy = false;}
	public void feedMedicine() {healthy = true; happiness -= MEDI_HAPPINESS_LOSS;}
	public boolean isAlive() {return alive;}
	private void die() {alive = false;};
}
