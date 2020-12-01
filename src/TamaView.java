import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TamaView extends Application implements Observer {

	private TamaController controller;
	private TamaModel model;
	
	private Observable o;

	Pane rootPane = new Pane();
	private Pane window;
	private Pane screenPane;
	private VBox layout;
	private float height = 660;
	private float width = 500;

	// Sounds
	private SoundPlayer buttonPress = new SoundPlayer("./res/sounds/buttonPress.wav");
	private SoundPlayer saveGameSound = new SoundPlayer("./res/sounds/saveGame.wav");
	private SoundPlayer loadGameSound = new SoundPlayer("./res/sounds/loadGame.wav");
	private SoundPlayer resetSound = new SoundPlayer("./res/sounds/resetGame.wav");

	// Attributes for testing purposes
	private Label clock;
	private Label age;
	private Rectangle healthRectangle;
	private Rectangle weightRectangle;
	private Rectangle happinessRectangle;

	private Stage stage;

	// NODES
	private Ellipse shadow = new Ellipse();
	private Ellipse device = new Ellipse();
	private Rectangle screenInset = new Rectangle();
	private Rectangle menuArea = new Rectangle();
	private Rectangle screen = new Rectangle();

	private Ellipse button1_3d = new Ellipse();
	private Ellipse button1 = new Ellipse();
	private Ellipse button2_3d = new Ellipse();
	private Ellipse button2 = new Ellipse();
	private Ellipse button3_3d = new Ellipse();
	private Ellipse button3 = new Ellipse();

	private Ellipse saveGame_3d = new Ellipse();
	private Ellipse saveGame = new Ellipse();
	private Text saveLabel = new Text("LOAD"); // Yes these are backwards, I'll get to it later.

	private Ellipse loadGame_3d = new Ellipse();
	private Ellipse loadGame = new Ellipse();
	private Text loadLabel = new Text("SAVE"); // It was easier to just rename here.

	private Ellipse resetGame_3d = new Ellipse();
	private Ellipse resetGame = new Ellipse();
	private Text resetLabel = new Text("RESET");

	public TamaView() {
		this.model = new TamaModel();
		model.plusObserver(this);
		this.controller = new TamaController(this.model);

		this.window = new Pane();

		this.layout = new VBox();
		

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
		
		
		// TODO reset observable
		
		// If a save exists, load the save on startup

//		new Thread(() -> {
//			try {
//				TamaModel tmp;
//				tmp = controller.loadSave();
//				if (tmp != null) {
//					model = tmp;
//					controller.updateModel(tmp);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}).start();

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
		screenInset.setY(95);
		screenInset.setWidth(220);
		screenInset.setHeight(205);
		screenInset.setFill(Color.DARKSLATEGREY);
		window.getChildren().add(screenInset);

		menuArea.setX(140);
		menuArea.setY(100);
		menuArea.setWidth(210);
		menuArea.setHeight(200);
		menuArea.setFill(Color.BISQUE);
		window.getChildren().add(menuArea);

		screen.setX(140);
		screen.setY(140);
		screen.setWidth(210);
		screen.setHeight(120);
		screen.setFill(Color.BEIGE);
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

		// reset game
		resetGame_3d.setFill(Color.DARKSLATEGREY);
		resetGame_3d.setCenterX(401);
		resetGame_3d.setCenterY(150);
		resetGame_3d.setRadiusX(5);
		resetGame_3d.setRadiusY(5);
		window.getChildren().add(resetGame_3d);

		resetGame.setFill(Color.LIGHTPINK);
		resetGame.setCenterX(400);
		resetGame.setCenterY(150);
		resetGame.setRadiusX(5);
		resetGame.setRadiusY(5);
		window.getChildren().add(resetGame);

		resetLabel.setX(390);
		resetLabel.setY(175);
		resetLabel.setFill(Color.DARKSLATEGRAY);
		window.getChildren().add(resetLabel);

		// Sprite
		
		// Here's the idea: We'll have a wildcard pane that displays the screen,
		//					the update function changes the pane, and the model chooses
		//					the correct pane based on the gamestate. Can you do this
		//					in javafx? who's to say for sure
		

		screenPane = model.getCurrentPane();
		
		layout.getChildren().add(window);
		rootPane.getChildren().addAll(layout, screenPane);

		scene.setOnMousePressed(event -> {
			int[] pos = new int[] { (int) event.getSceneX(), (int) event.getSceneY() };
			handlePress(pos);
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case DIGIT1:
					press1();
					break;

				case DIGIT2:
					press2();
					break;

				case DIGIT3:
					press3();
					break;
				case R:
					pressReset();
					break;
				case S:
					pressSave();
					break;
				case L:
					try {
						pressLoad();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		});

		// Bar will be the main VBox for holding all stats
		VBox bar = new VBox();
		// topbox holds time and age
		HBox topbox = new HBox();
		//Holds time
		HBox clockBox = new HBox();
		//Holds age
		HBox ageBox = new HBox();


		// Creating the top box
		Label clockLabel = new Label("Time:   ");
		clock = new Label("0");
		clock.setFont(new Font("Calibri", 20));
		clockBox.getChildren().add(clockLabel);
		clockBox.getChildren().add(clock);
		clockBox.setAlignment(Pos.CENTER);

		Label ageLabel = new Label("Age:   ");
		age = new Label("");
		age.setFont(new Font("Calibri", 20));
		ageBox.getChildren().add(ageLabel);
		ageBox.getChildren().add(age);
		ageBox.setAlignment(Pos.CENTER);

		topbox.getChildren().add(clockBox);
		topbox.getChildren().add(ageBox);
		topbox.setAlignment(Pos.CENTER);
		topbox.setSpacing(70);
		bar.getChildren().add(topbox);


		//Creating Labels for the stat display
		Label healthLabel = new Label("  Health: ");
		healthLabel.setPrefWidth(80);
		healthLabel.setAlignment(Pos.CENTER);
		healthLabel.setFont(new Font("Calibri", 15));

		Label weightLabel = new Label("  Weight: ");
		weightLabel.setPrefWidth(80);
		weightLabel.setAlignment(Pos.CENTER);
		weightLabel.setFont(new Font("Calibri", 15));

		Label happinessLabel = new Label("  Happiness: ");
		happinessLabel.setPrefWidth(80);
		happinessLabel.setAlignment(Pos.CENTER_LEFT);
		happinessLabel.setFont(new Font("Calibri", 15));


		// Creating rectangles to display stats
		healthRectangle = new Rectangle(400, 20, Color.GREEN);
		weightRectangle = new Rectangle(400, 20, Color.GREEN);
		happinessRectangle = new Rectangle(400, 20, Color.GREEN);


		// Creating boxes that hold the stat label and stat display rectangle
		HBox healthBox = getStat(healthRectangle, healthLabel);
		HBox weightBox = getStat(weightRectangle, weightLabel);
		HBox happinessBox = getStat(happinessRectangle, happinessLabel);

		bar.setPrefHeight(20);
		bar.setAlignment(Pos.CENTER);
		bar.getChildren().add(healthBox);
		bar.getChildren().add(weightBox);
		bar.getChildren().add(happinessBox);
		bar.setPrefWidth(500);
		bar.setSpacing(7);

		//Setting background color + border
		bar.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		bar.setPrefHeight(170);
		bar.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


		// Adding separators to break up the stat display
		Separator separator1 = new Separator();
		separator1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		bar.getChildren().add(1, separator1);

		Separator separator2 = new Separator();
		separator2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		bar.getChildren().add(3, separator2);

		Separator separator3 = new Separator();
		separator3.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		bar.getChildren().add(5, separator3);

		layout.setSpacing(10);
		layout.getChildren().add(bar);

		stage.setScene(scene);
		stage.show(); // Show the stage

		// Sim for testing purposes
		runSim();

	}

	/**
	 * Method takes in a rectangle and a label that correlate to an aspect of the tamagotchi.
	 * Then, puts the label and rectangle into two seperate HBox's and combines them into one
	 * HBox (this is done for spacing and displaying bars correctly).
	 * @param rect  A Rectangle that is used to display visual of the stat
	 * @param label A Label that defines what stat to display
	 * @return An HBox that is used to display the label and rectangle for the stat display
	 */
	private HBox getStat(Rectangle rect, Label label){
		HBox statBox = new HBox();
		HBox displayBox = new HBox();
		statBox.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		statBox.getChildren().add(rect);
		statBox.setPrefWidth(400);
		statBox.setMaxWidth(400);
		statBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		statBox.setAlignment(Pos.CENTER_LEFT);

		displayBox.getChildren().add(label);
		displayBox.getChildren().add(statBox);
		displayBox.setAlignment(Pos.CENTER_LEFT);
		displayBox.setSpacing(10);

		return displayBox;
	}


	protected void press1() {
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

	}

	protected void press2() {
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

	}

	protected void press3() {

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

	}

	protected void pressReset() {
		resetSound.play();
		controller.resetPet();

		resetGame.setVisible(false);
		resetGame_3d.setFill(Color.LIGHTPINK);

		PauseTransition pause = new PauseTransition(Duration.millis(200));
		pause.setOnFinished(e -> {

			resetGame_3d.setFill(Color.DARKSLATEGREY);
			resetGame.setVisible(true);

		});
		pause.play();

	}

	protected void pressLoad() throws IOException {
		loadGameSound.play();
		controller.loadGamePress();

		
		updateUIAttributes();

		saveGame.setVisible(false);
		saveGame_3d.setFill(Color.LIGHTPINK);

		PauseTransition pause = new PauseTransition(Duration.millis(200));
		pause.setOnFinished(e -> {

			saveGame_3d.setFill(Color.DARKSLATEGREY);
			saveGame.setVisible(true);

		});
		pause.play();

	}

	protected void pressSave() {
		saveGameSound.play();

		controller.saveGamePress();

		Platform.runLater(() -> {
			try {
				controller.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		loadGame.setVisible(false);
		loadGame_3d.setFill(Color.LIGHTPINK);

		PauseTransition pause = new PauseTransition(Duration.millis(200));
		pause.setOnFinished(e -> {

			loadGame_3d.setFill(Color.DARKSLATEGREY);
			loadGame.setVisible(true);

		});
		pause.play();

	}

	/**
	 * Purpose: Handles the mouse press of any of the three button with an animation
	 * and a call to the controller.
	 * 
	 * @param pos
	 */
	private void handlePress(int[] pos) {
		if (pos[0] > 130 && pos[0] < 170 && pos[1] > 330 && pos[1] < 370) {
			press1();

		} else if (pos[0] > 220 && pos[0] < 260 && pos[1] > 350 && pos[1] < 390) {
			press2();

		} else if (pos[0] > 310 && pos[0] < 350 && pos[1] > 330 && pos[1] < 370) {
			press3();

		} else if (pos[0] > 190 && pos[0] < 210 && pos[1] > 410 && pos[1] < 430) {
			pressSave();

		} else if (pos[0] > 280 && pos[0] < 300 && pos[1] > 410 && pos[1] < 430) {
			try {
				pressLoad();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (pos[0] > 395 && pos[0] < 405 && pos[1] > 145 && pos[1] < 155) {
			pressReset();
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
				if(!controller.isAlive()){
					break;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Integer.toString(controller.getSecondsPassed()));
			}
			System.out.println("Tama has died");
		}).start();
	}

	public void updateUIAttributes() {
		Platform.runLater(() -> {

			clock.setText(Integer.toString(controller.getSecondsPassed()));
			System.out.println(controller.getAge());
			age.setText(Float.toString(controller.getAge()));
			setStat(controller.getHealth(), healthRectangle);
			setStat(controller.getWeight(), weightRectangle);
			setStat(controller.getHappiness(), happinessRectangle);

		});
	}

	private void setStat(float val, Rectangle rect){
		float newWidth = val * 4;
		if(val > 100){
			rect.setWidth(400);
			rect.setFill(Color.GREEN);
		}
		else if( val >= 75 ){
			rect.setFill(Color.GREEN);
			rect.setWidth(newWidth);
		}
		else if( val >= 60 ){
			rect.setFill(Color.YELLOW);
			rect.setWidth(newWidth);
		}
		else if( val >= 40 ){
			rect.setFill(Color.ORANGE);
			rect.setWidth(newWidth);
		}
		else if( val >= 20 ){
			rect.setFill(Color.ORANGERED);
			rect.setWidth(newWidth);
		}
		else if( val > 0 ){
			rect.setFill(Color.DARKRED);
			rect.setWidth(newWidth);
		}
		else if(val <= 0){
			rect.setFill(Color.BLACK);
			rect.setWidth(400);
		}
	}



	@Override
	public void update(Observable o, Object arg) {
		if (arg == null)
			return;
		
		rootPane.getChildren().remove(screenPane);
		screenPane = model.getCurrentPane();
		rootPane.getChildren().add(screenPane);
		
	}


}
