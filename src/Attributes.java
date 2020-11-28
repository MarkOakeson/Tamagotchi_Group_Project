import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Attributes implements Serializable{
	TamaModel model;

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

	// Limits/Standards
	private static final int MAX_HEALTH = 100;
	private static final int MAX_WEIGHT = 100;
	private static final int MAX_HAPPINESS = 100;

	private File saveFile = new File("saveState.sav");

	// Init with newgame stats
	public Attributes(TamaModel model) {
		this.model = model;
//		this.age = 0;
//		this.health = MAX_HEALTH;
//		this.weight = MAX_WEIGHT;
//		this.happiness = MAX_HAPPINESS;
//		
//		this.secondsPassed = 0;
//		
//		this.healthy = true;
//		this.alive = true;
	}

	public void updateSave() {
		this.age = model.getAge();
		this.health = model.getHealth();
		this.weight = model.getWeight();
		this.happiness = model.getHappiness();
		this.secondsPassed = model.getSecondsPassed();
		this.healthy = model.isHealthy();
		this.alive = model.isAlive();
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

	public int getHappiness() {
		return happiness;
	}

	public float getWeight() {
		return weight;
	}

	public float getHealth() {
		return health;
	}

	public boolean isHealthy() {
		return healthy;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getSecondsPassed() {
		return secondsPassed;
	}
	

}
