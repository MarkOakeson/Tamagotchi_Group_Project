import java.io.IOException;

public class TamaController {
	private TamaModel model;

	public TamaController(TamaModel model) {
		this.model = model;
	}


	/**
	 * Updates the model with a newly loaded model passed in
	 * by the view object
	 * @param updatedModel - updated model loaded in from save file
	 */
	public void updateModel(TamaModel updatedModel){
		model = updatedModel;
	}

	/**
	 * Saves the current game state, including the pet's attributes
	 * and current status, by writing the Model object to a save file
	 * in the local directory.
	 * @throws IOException
	 */
	public void save() throws IOException {
		model.save();
	}

	/**
	 * If a savefile does not exist or cannot be found, returns null.
	 * If savefile is found, returns the TamaModel object contained
	 * in the savefile and updates the running instance to be the
	 * newly returned model.
	 * @return - null if save not found. Otherwise, returns updated model
	 * @throws IOException
	 */
	public TamaModel loadSave() throws IOException {
		return model.load();
	}

	/**
	 * Updates the model every second, autosaving every
	 * 60 seconds.
	 */
	public void updatePet(){
		model.updatePet();
	}

	public float getAge(){ return model.getAge(); }

	public float getHealth(){ return model.getHealth(); }

	public float getWeight(){ return model.getWeight(); }

	public float getHappiness(){ return model.getHappiness();}

	public boolean isHealthy(){ return model.isHealthy();}

	public void feedMedicine(){ model.feedMedicine();}

	public boolean isAlive(){ return model.isAlive();}

	public void feedMeal(){ model.feedMeal();}

	public void feedSnack(){ model.feedSnack();}


	// For testing purposes
	public int getSecondsPassed(){
		return model.getSecondsPassed();
	}
	
}
