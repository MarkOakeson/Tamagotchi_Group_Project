import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TamaView extends Application implements Observer {

	private TamaController controller;
	private TamaModel model;

	Pane rootPane = new Pane();
	private Pane window;
	private Pane sprite;
	private float height = 500;
	private float width = 500;

	private SoundPlayer buttonPress = new SoundPlayer("./res/buttonPress.wav");;

	// Attributes for testing purposes
	private TextField clock;
	private TextField age;
	private TextField health;
	private TextField weight;
	private TextField happiness;

	private Stage stage;

	// NODES
	private Ellipse shadow = new Ellipse();
	private Ellipse device = new Ellipse();
	private Rectangle screenInset = new Rectangle();
	private Rectangle screen = new Rectangle();
	private Ellipse button1_3d = new Ellipse();
	private Ellipse button1 = new Ellipse();
	private Ellipse button2_3d = new Ellipse();
	private Ellipse button2 = new Ellipse();
	private Ellipse button3_3d = new Ellipse();
	private Ellipse button3 = new Ellipse();

	private Ellipse saveGame_3d = new Ellipse();
	private Ellipse saveGame = new Ellipse();
	private Text saveLabel = new Text("LOAD");

	private Ellipse loadGame_3d = new Ellipse();
	private Ellipse loadGame = new Ellipse();
	
	private Text loadLabel = new Text("SAVE");

	public TamaView() {
		this.window = new Pane();
		this.model = new TamaModel();
		this.controller = new TamaController(this.model);
		model.addObserver(this);

	}

	/**
	 * Purpose: Creates the view. Ellipse in center, with a rectangle over it. 3
	 * buttons.
	 * 
	 * @param stage to create the stage
	 */
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		// If a save exists, load the save on startup
		new Thread(() -> {
			try {
				TamaModel tmp;
				tmp = controller.loadSave();
				if (tmp != null) {
					model = tmp;
					controller.updateModel(tmp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		stage.setTitle("Tamagotchi"); // Name the stage

		Scene scene = new Scene(rootPane, width, height);

		// Draw Device

		shadow.setFill(Color.DARKSLATEGREY);
		shadow.setCenterX(260);
		shadow.setCenterY(255);
		shadow.setRadiusX(200);
		shadow.setRadiusY(230);
		window.getChildren().add(shadow);

		device.setFill(Color.HOTPINK);
		device.setCenterX(250); // Controller//maybe sprite in future?
		device.setCenterY(250); // I'm happy with perceived 3d for now
		device.setRadiusX(200);
		device.setRadiusY(230);
		window.getChildren().add(device);

		// Draw Screen

		screenInset.setX(132);
		screenInset.setY(115);
		screenInset.setWidth(220);
		screenInset.setHeight(185);
		screenInset.setFill(Color.DARKSLATEGREY);
		window.getChildren().add(screenInset);

		screen.setX(140);
		screen.setY(120);
		screen.setWidth(210);
		screen.setHeight(180);
		screen.setFill(Color.BISQUE);
		window.getChildren().add(screen);

		// Draw buttons

		// Button 1
		button1_3d.setFill(Color.DARKSLATEGREY);
		button1_3d.setCenterX(153);
		button1_3d.setCenterY(350);
		button1_3d.setRadiusX(20);
		button1_3d.setRadiusY(20);
		window.getChildren().add(button1_3d);

		button1.setFill(Color.LIGHTPINK);
		button1.setCenterX(150);
		button1.setCenterY(350);
		button1.setRadiusX(20);
		button1.setRadiusY(20);
		window.getChildren().add(button1);

		// Button 2
		button2_3d.setFill(Color.DARKSLATEGREY);
		button2_3d.setCenterX(243);
		button2_3d.setCenterY(370);
		button2_3d.setRadiusX(20);
		button2_3d.setRadiusY(20);
		window.getChildren().add(button2_3d);

		button2.setFill(Color.LIGHTPINK);
		button2.setCenterX(240);
		button2.setCenterY(370);
		button2.setRadiusX(20);
		button2.setRadiusY(20);
		window.getChildren().add(button2);

		// Button 3
		button3_3d.setFill(Color.DARKSLATEGREY);
		button3_3d.setCenterX(333);
		button3_3d.setCenterY(350);
		button3_3d.setRadiusX(20);
		button3_3d.setRadiusY(20);
		window.getChildren().add(button3_3d);

		button3.setFill(Color.LIGHTPINK);
		button3.setCenterX(330);
		button3.setCenterY(350);
		button3.setRadiusX(20);
		button3.setRadiusY(20);

		window.getChildren().add(button3);

		// Load game
		loadGame_3d.setFill(Color.DARKSLATEGREY);
		loadGame_3d.setCenterX(202);
		loadGame_3d.setCenterY(420);
		loadGame_3d.setRadiusX(10);
		loadGame_3d.setRadiusY(10);
		window.getChildren().add(loadGame_3d);

		loadGame.setFill(Color.LIGHTPINK);
		loadGame.setCenterX(200);
		loadGame.setCenterY(420);
		loadGame.setRadiusX(10);
		loadGame.setRadiusY(10);
		window.getChildren().add(loadGame);

		loadLabel.setX(180);
		loadLabel.setY(450);
		loadLabel.setFill(Color.DARKSLATEGRAY);
		window.getChildren().add(loadLabel);

		// Save game
		saveGame_3d.setFill(Color.DARKSLATEGREY);
		saveGame_3d.setCenterX(292);
		saveGame_3d.setCenterY(420);
		saveGame_3d.setRadiusX(10);
		saveGame_3d.setRadiusY(10);
		window.getChildren().add(saveGame_3d);

		saveGame.setFill(Color.LIGHTPINK);
		saveGame.setCenterX(290);
		saveGame.setCenterY(420);
		saveGame.setRadiusX(10);
		saveGame.setRadiusY(10);
		window.getChildren().add(saveGame);

		saveLabel.setX(280);
		saveLabel.setY(450);
		saveLabel.setFill(Color.DARKSLATEGRAY);
		window.getChildren().add(saveLabel);

		// Sprite
		sprite = new Sprite();
		sprite.setLayoutX(200);
		sprite.setLayoutY(150);
		sprite.resize(50, 50);

		rootPane.getChildren().addAll(window, sprite);

		scene.setOnMousePressed(event -> {
			int[] pos = new int[] { (int) event.getSceneX(), (int) event.getSceneY() };
			handlePress(pos);
		});

		VBox stacker = new VBox();
		// Save and Load functionality (Not final view, quick and dirty for testing)
		HBox bar = new HBox();

		Button loadGame = new Button("Load");
		Button saveGame = new Button("Save");

		Label clockLabel = new Label("Time: ");
		clock = new TextField("0");
		clock.setPrefColumnCount(3);
		Label ageLabel = new Label("Age: ");
		age = new TextField("");
		age.setPrefColumnCount(2);
		Label healthLabel = new Label("Health: ");
		health = new TextField("");
		health.setPrefColumnCount(2);
		Label weightLabel = new Label("Weight: ");
		weight = new TextField("");
		weight.setPrefColumnCount(2);
		Label happinessLabel = new Label("Happiness: ");
		happiness = new TextField("");
		happiness.setPrefColumnCount(3);
		bar.getChildren().add(loadGame);
		bar.getChildren().add(saveGame);
		bar.getChildren().add(clockLabel);
		bar.getChildren().add(clock);
		bar.getChildren().add(ageLabel);
		bar.getChildren().add(age);
		bar.getChildren().add(healthLabel);
		bar.getChildren().add(health);
		bar.getChildren().add(weightLabel);
		bar.getChildren().add(weight);

		stacker.getChildren().add(bar);
		HBox bar2 = new HBox();
		bar2.getChildren().add(happinessLabel);
		bar2.getChildren().add(happiness);
		stacker.getChildren().add(bar2);
		window.getChildren().add(stacker);

		loadGame.setOnAction(event -> {

			try {
				model = controller.loadSave();
				controller.updateModel(model);
			} catch (IOException e) {
				e.printStackTrace();
			}
			updateUIAttributes();

		});

		saveGame.setOnAction(event -> {
			Platform.runLater(() -> {
				try {
					controller.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		});

		stage.setScene(scene);
		stage.show(); // Show the stage

		// Sim for testing purposes
		runSim();

	}

	/**
	 * Purpose: Handles the mouse press of any of the three button with an animation
	 * and a call to the controller.
	 * 
	 * @param pos
	 */
	private void handlePress(int[] pos) {
		if (pos[0] > 130 && pos[0] < 170 && pos[1] > 330 && pos[1] < 370) {

			// Play a sound
			buttonPress.play();

			// Call controller, tell it button 1 pressed
			controller.button1Press();

			button1.setVisible(false);
			button1_3d.setFill(Color.LIGHTPINK);

			PauseTransition pause = new PauseTransition(Duration.millis(200));
			pause.setOnFinished(e -> {

				button1_3d.setFill(Color.DARKSLATEGREY);
				button1.setVisible(true);

			});

			pause.play();

		} else if (pos[0] > 220 && pos[0] < 260 && pos[1] > 350 && pos[1] < 390) {

			// Play a sound
			buttonPress.play();

			// Call controller, tell it button 2 pressed
			controller.button2Press();

			button2.setVisible(false);
			button2_3d.setFill(Color.LIGHTPINK);

			PauseTransition pause = new PauseTransition(Duration.millis(200));
			pause.setOnFinished(e -> {

				button2_3d.setFill(Color.DARKSLATEGREY);
				button2.setVisible(true);

			});
			pause.play();

		} else if (pos[0] > 310 && pos[0] < 350 && pos[1] > 330 && pos[1] < 370) {

			// Play a sound
			buttonPress.play();

			// Call controller, tell it button 3 pressed
			controller.button3Press();

			button3.setVisible(false);
			button3_3d.setFill(Color.LIGHTPINK);

			PauseTransition pause = new PauseTransition(Duration.millis(200));
			pause.setOnFinished(e -> {

				button3_3d.setFill(Color.DARKSLATEGREY);
				button3.setVisible(true);

			});
			pause.play();

		} else if (pos[0] > 190 && pos[0] < 210 && pos[1] > 410 && pos[1] < 430) {
			controller.saveGamePress();

			loadGame.setVisible(false);
			loadGame_3d.setFill(Color.LIGHTPINK);

			PauseTransition pause = new PauseTransition(Duration.millis(200));
			pause.setOnFinished(e -> {

				loadGame_3d.setFill(Color.DARKSLATEGREY);
				loadGame.setVisible(true);

			});
			pause.play();
			

		} else if (pos[0] > 280 && pos[0] < 300 && pos[1] > 410 && pos[1] < 430) {
			
			controller.loadGamePress();

			saveGame.setVisible(false);
			saveGame_3d.setFill(Color.LIGHTPINK);

			PauseTransition pause = new PauseTransition(Duration.millis(200));
			pause.setOnFinished(e -> {

				saveGame_3d.setFill(Color.DARKSLATEGREY);
				saveGame.setVisible(true);

			});
			pause.play();
		}

	}

	/**
	 * Runs a simulation and updates the pet every second while displaying time
	 * passing on the clock
	 */
	public void runSim() {
		new Thread(() -> {
			while (stage.isShowing()) {
				controller.updatePet();
				updateUIAttributes();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Integer.toString(controller.getSecondsPassed()));
			}
		}).start();
	}

	public void updateUIAttributes() {
		Platform.runLater(() -> {
			clock.setText(Integer.toString(controller.getSecondsPassed()));
			age.setText(Float.toString(controller.getAge()));
			health.setText(Float.toString(controller.getAge()));
			weight.setText(Float.toString(controller.getWeight()));
			happiness.setText(Float.toString(controller.getHappiness()));
		});
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
