import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Attributes implements Serializable{
	
	// Core stats
	private float age;
	private float health;
	private float weight;
	private int happiness;
	// private float fullness;

	// System stats
	private int secondsPassed; // int will cap out at 68 years, which is probably good enough

	// Statuses
	private boolean healthy;
	private boolean alive;


	private File saveFile = new File("saveState.sav");

	// Init with newgame stats
	public Attributes() {
		this.age = 0;
		this.health = 50;
		this.weight = 50;
		this.happiness = 50;

		this.secondsPassed = 0;

		this.healthy = true;
		this.alive = true;
	}

	public void makeSave() throws IOException {

		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream saveFileStream = new FileOutputStream("saveState.sav");
		ObjectOutputStream save = new ObjectOutputStream(saveFileStream);
		save.writeObject(this);
		System.out.println("Save written");
	}

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public int getSecondsPassed() {
		return secondsPassed;
	}

	public void setSecondsPassed(int secondsPassed) {
		this.secondsPassed = secondsPassed;
	}

	public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	

}
