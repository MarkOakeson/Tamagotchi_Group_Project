import java.util.Observable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TamaView extends Application{

	private Observable observable;

	private TamaController controller;
	private TamaModel model;
	
	private Pane window;
	private float height = 500;
	private float width = 500;
	
	
	public TamaView() {
		this.window = new Pane();
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
		
		// Draw Device
		Ellipse shadow = new Ellipse();
		shadow.setFill(Color.DARKSLATEGREY);
		shadow.setCenterX(260);					
		shadow.setCenterY(255);
		shadow.setRadiusX(200);
		shadow.setRadiusY(230);
		window.getChildren().add(shadow);
		
		Ellipse device = new Ellipse();
		device.setFill(Color.HOTPINK);
		device.setCenterX(250);					// Controller//maybe sprite in future?
		device.setCenterY(250);					// I'm happy with perceived 3d for now
		device.setRadiusX(200);
		device.setRadiusY(230);
		window.getChildren().add(device);
		
		// Draw Screen
		Rectangle screenInset = new Rectangle();
        screenInset.setX(132);
        screenInset.setY(115);
        screenInset.setWidth(220);
        screenInset.setHeight(170);
        screenInset.setFill(Color.DARKSLATEGREY);
        window.getChildren().add(screenInset);
        
		Rectangle screen = new Rectangle();
        screen.setX(140);
        screen.setY(120);
        screen.setWidth(210);
        screen.setHeight(160);
        screen.setFill(Color.BISQUE);
        window.getChildren().add(screen);
        
        // Draw buttons
        Ellipse button1_3d = new Ellipse();
        button1_3d.setFill(Color.DARKSLATEGREY);
        button1_3d.setCenterX(153);					
        button1_3d.setCenterY(350);
        button1_3d.setRadiusX(20);
        button1_3d.setRadiusY(20);
		window.getChildren().add(button1_3d);
        
        Ellipse button1 = new Ellipse();
        button1.setFill(Color.LIGHTPINK);
        button1.setCenterX(150);					
        button1.setCenterY(350);
        button1.setRadiusX(20);
        button1.setRadiusY(20);
		window.getChildren().add(button1);
		
		
		Ellipse button2_3d = new Ellipse();
        button2_3d.setFill(Color.DARKSLATEGREY);
        button2_3d.setCenterX(243);					
        button2_3d.setCenterY(370);
        button2_3d.setRadiusX(20);
        button2_3d.setRadiusY(20);
		window.getChildren().add(button2_3d);
		
		Ellipse button2 = new Ellipse();
        button2.setFill(Color.LIGHTPINK);
        button2.setCenterX(240);					
        button2.setCenterY(370);
        button2.setRadiusX(20);
        button2.setRadiusY(20);
		window.getChildren().add(button2);
		
		
		Ellipse button3_3d = new Ellipse();
        button3_3d.setFill(Color.DARKSLATEGREY);
        button3_3d.setCenterX(333);					
        button3_3d.setCenterY(350);
        button3_3d.setRadiusX(20);
        button3_3d.setRadiusY(20);
		window.getChildren().add(button3_3d);
		
		Ellipse button3 = new Ellipse();
        button3.setFill(Color.LIGHTPINK);
        button3.setCenterX(330);					
        button3.setCenterY(350);
        button3.setRadiusX(20);
        button3.setRadiusY(20);
		window.getChildren().add(button3);
        
        
        
		stage.setScene(scene);
		stage.show(); // Show the stage
	}

	
	
	
}
