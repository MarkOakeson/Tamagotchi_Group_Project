import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class TamaView extends Application implements Observer {

	private TamaController controller;
	private TamaModel model;
	
	private Pane window;
	private float height = 500;
	private float width = 500;

	// Clock for testing purposes
	private TextField clock;
	
	
	public TamaView() {
		this.window = new Pane();
		this.model = new TamaModel();
		this.controller = new TamaController(this.model);
		model.addObserver(this);
	}
	
	/**
	 * Purpose: Creates the view. Ellipse in center, with a rectangle over it. 3 buttons.
	 * 
	 * @param stage to create the stage
	 */
	@Override
	public void start(Stage stage) {
		try{
			TamaModel tmp;
			tmp = controller.loadSave();
			if (tmp != null){
				model = tmp;
				controller.updateModel(tmp);
			}
		} catch	(IOException e){
			e.printStackTrace();
		}
		
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


		// Load button for testing
		HBox bar = new HBox();
		Button loadGame = new Button("Load");
		Button saveGame = new Button("Save");
		clock = new TextField("0");
		bar.getChildren().add(loadGame);
		bar.getChildren().add(saveGame);
		bar.getChildren().add(clock);
		window.getChildren().add(bar);

		loadGame.setOnAction(event ->{
			try {
				model = controller.loadSave();
				controller.updateModel(model);
				clock.setText(Integer.toString(controller.getSecondsPassed()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		saveGame.setOnAction(event ->{
			try{
				controller.save();
			}catch (IOException e){
				e.printStackTrace();
			}
		});




		stage.setScene(scene);
		stage.show(); // Show the stage

		// Sim for testing purposes
		new Thread(()->{
			while (true){
				controller.updatePet();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Integer.toString(controller.getSecondsPassed()));
			}
		}).start();

	}


	@Override
	public void update(Observable o, Object arg) {


	}
}
