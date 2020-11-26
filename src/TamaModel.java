
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
 * 		Affects: sick (large effect)
 * 		Affected by: weight, random chance
 * 
 * Weight: Automatically decreases with time and increases when user feeds Tamaclonechi with a 
 * meal or snack. Meals increase weight and happiness slightly; snacks increase weight and 
 * happiness greattly. When over/underweight, Tamaclonechi is more likely to get sick. Also affects
 * health. 
 * 		Affects: health (medium effect), sick (medium effect for over/underweight)
 * 		Affected by: meal (large effect), snack (medium effect)
 * 
 * Happiness: When high, Tamaclonechi tends to get sick less and live longer. When low, vice versa.
 * Sickness makes happiness go down. Snacks, meals, and healthiness make Tamaclonechi happier.
 * 		Affects: sick (medium effect), alive (large effect)
 * 		Affected by: meal, snack, sick (large effect), not sick(small effect)
 * 
 * Fullness(?): Increases when max value is 0, decreases weight and happiness when at 0. Not 
 * included in core mechanics, so inclusion of this stat is up for debate. 
 */
public class TamaModel {
	//Might want to make a Tamaclonechi class and move these there
	private float age; 
	private int health; 
	private int weight;
	private int happiness; 
	//private float fullness; 
	
	private boolean sick;
	private boolean alive;
	
	public TamaModel() {
		this.age = 0;
		this.health = 100;
		this.weight = 100;
		this.happiness = 100;
		//this.fullness = 100;
		this.sick = false;
		this.alive = true;
	}
}
