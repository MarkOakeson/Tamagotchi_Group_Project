import java.util.Observable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TamaView extends Application{

	private Observable observable;

	private TamaController controller;
	private TamaModel model;
	
	private BorderPane window;
	private float height = 500;
	private float width = 500;
	
	
	

	
	public TamaView() {
		this.window = new BorderPane();
		this.model = new TamaModel();
		this.controller = new TamaController(this.model);
	}
	
	/**
	 * Purpose: Creates the gameboard. Includes everything to construct the
	 * gameboard and gather event data.
	 * 
	 * @param object to create the stage
	 */
	@Override
	public void start(Stage stage) {
		
		stage.setTitle("Tamagotchi"); // Name the stage
		
		Scene scene = new Scene(window, width, height);

		stage.setScene(scene);
		stage.show(); // Show the stage
	}

	
	
	
}
