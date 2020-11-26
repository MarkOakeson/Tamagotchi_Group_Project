import java.util.Observable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TamaView extends Application{

	private Observable observable;

	private TamaController controller;
	private TamaModel model;
	
	private StackPane window;
	private float height = 500;
	private float width = 500;
	
	
	public TamaView() {
		
		
		this.window = new StackPane();
		this.model = new TamaModel();
		this.controller = new TamaController(this.model);
	}
	
	/**
	 * Purpose: Creates the view. Ellipse in center, with a rectangle over it. 3 buttons.
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
