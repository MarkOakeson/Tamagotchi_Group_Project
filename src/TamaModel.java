
/*
 * Stat Notes: 
 * Age: 
 */
public class TamaModel {
	private float age; //Might want to make a Tamaclonechi class and move these there
	private int health; 
	private int weight;
	private int happiness; 
	private float fullness; //When this goes above 100, increase weight. Decrease when below
	
	public TamaModel() {
		this.age = 0;
		this.health = 100;
		this.weight = 100;
		this.happiness = 100;
		this.fullness = 100;
	}
}
