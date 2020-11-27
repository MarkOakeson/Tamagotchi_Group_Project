import java.io.IOException;

import javafx.event.EventHandler;

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

	/**
	 * Getter for pet age from model
	 * @return - float value age of pet
	 */
	public float getAge(){ return model.getAge(); }

	/**
	 * Getter for pet health from model
	 * @return - float value health of pet
	 */
	public float getHealth(){ return model.getHealth(); }

	/**
	 * Getter for pet weight from model
	 * @return - float value weight of pet
	 */
	public float getWeight(){ return model.getWeight(); }

	/**
	 * Getter for pet happiness from model
	 * @return - float value happiness of pet
	 */
	public float getHappiness(){ return model.getHappiness();}

	/**
	 * Getter for whether pet is healthy or not
	 * @return - boolean true if pet is healthy, false if sick
	 */
	public boolean isHealthy(){ return model.isHealthy();}

	/**
	 * Feeds pet medicine to make it healthy
	 */
	public void feedMedicine(){ model.feedMedicine();}

	/**
	 * Getter for whether pet is alive or not
	 * @return - boolean true if pet is alive, false if dead
	 */
	public boolean isAlive(){ return model.isAlive();}

	/**
	 * Feeds pet meal to make it much less hungry
	 */
	public void feedMeal(){ model.feedMeal();}

	/**
	 * Feeds pet snack to make it slightly less hungry
	 */
	public void feedSnack(){ model.feedSnack();}


	// For testing purposes
	public int getSecondsPassed(){
		return model.getSecondsPassed();
	}



	public void button1Press() {
		System.out.println("Pressed button 1");
		
	}



	public void button2Press() {
		System.out.println("Pressed button 2");
		
	}



	public void button3Press() {
		System.out.println("Pressed button 3");
		
	}
	
}
