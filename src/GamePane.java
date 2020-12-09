import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GamePane extends Pane implements Observer{

	private Sprite tama;
	private Sprite meal;
	private Sprite snack;
	private Sprite medicine;
	private TamaModel model;
	private Pane grid;
	private Text mealSelect;
	private Text snackSelect;
	private Text medicineSelect;
	private Text playSelect;
	private Text statsSelect;
	private Text selected; // Default
	private ArrayList<Text> selections;
	private int selectionIndex;
	private boolean eating = false;
	

	public GamePane(TamaModel model) {
		this.model = model;
		//controller = model.getController();
		
		model.addObserver(this);
		tama = new Sprite(3, 3, 160, 160, new Image("file:./res/images/tama.png"), Animation.INDEFINITE, 700);
		tama.setLayoutX(190);
		tama.setLayoutY(150);
		
		// MEAL
		mealSelect = new Text("MEAL");
		mealSelect.setFont(Font.font(15));
		mealSelect.setX(150);
		mealSelect.setY(130);
		
		// SNACK
		snackSelect = new Text("SNACK");
		snackSelect.setFont(Font.font(15));
		snackSelect.setX(210);
		snackSelect.setY(130);

		// MEDICINE
		medicineSelect = new Text("MEDICINE");
		medicineSelect.setFont(Font.font(15));
		medicineSelect.setX(270);
		medicineSelect.setY(130);
		
		// PLAY
		playSelect = new Text("PLAY");
		playSelect.setFont(Font.font(15));
		playSelect.setX(150);
		playSelect.setY(280);
		
		// creates an arraylist of all available options for selection
		selections = new ArrayList<Text>();
		selections.add(mealSelect);
		selections.add(snackSelect);
		selections.add(medicineSelect);
		selections.add(playSelect);


//

		grid = new Pane();
		grid.getChildren().addAll(tama, mealSelect, snackSelect, medicineSelect, playSelect);
		super.getChildren().add(grid);
		
		selected = mealSelect;
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(350), e -> changeFrame()));
		timeline.setCycleCount(Animation.INDEFINITE); // loop forever
		timeline.play();
		
	}

	/**
	 * blinks currently selected menu item
	 */
	private void changeFrame() {
		
		selected.setVisible(!selected.isVisible());
	}

	public void setDeadImg(){
		ImageView imageView = null;
		if(model.isHealthy()) {
			imageView = new ImageView(new Image("file:./res/images/deadTama.png"));
		}
		else{
			imageView = new ImageView(new Image("file:./res/images/sickDeadTama.png"));

		}
		imageView.setViewport(new Rectangle2D(0, 0, 160, 160));

		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setLayoutX(190);
		imageView.setLayoutY(150);
		grid.getChildren().remove(0);
		grid.getChildren().add(0, imageView);
	}


	public void setSickImg(){
		this.tama = new Sprite(3, 3, 160, 160, new Image("file:./res/images/sickTama.png"), Animation.INDEFINITE, 700);
		tama.setLayoutX(190);
		tama.setLayoutY(150);
		grid.getChildren().remove(0);
		grid.getChildren().add(0, tama);
	}

	public void setHealthyImg(){
		this.tama = new Sprite(3, 3, 160, 160, new Image("file:./res/images/tama.png"), Animation.INDEFINITE, 700);
		tama.setLayoutX(190);
		tama.setLayoutY(150);
		grid.getChildren().remove(0);
		grid.getChildren().add(0, tama);
	}

	
	/**
	 * makes the tamagotchi a meal, animated a meal, 
	 * calls the eatMeal controller function
	 */
	private void makeMeal() {
		eating = true;
		tama.setLayoutX(200);
		
		meal = new Sprite(3, 3, 27, 24, new Image("file:./res/images/meal.png"), 1, 2100);
		meal.setLayoutX(150);
		meal.setLayoutY(150);
		meal.setScaleX(0.5);
		meal.setScaleY(0.5);
		grid.getChildren().addAll(meal);
		PauseTransition pause = new PauseTransition(Duration.millis(2100));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(meal);
			tama.setLayoutX(190);
			eating = false;
			model.getController().eatMeal();
//			if(!model.isHealthy()){
//				setSickImg();
//			}
		});
		pause.play();
	}
	
	/**
	 * makes the tamagotchi a snack, animated a snack, 
	 * calls the eatSnack controller function
	 */
	private void makeSnack() {
		eating = true;
		tama.setLayoutX(200);
		
		snack = new Sprite(3, 3, 22, 25, new Image("file:./res/images/snack.png"), 1, 1000);
		snack.setLayoutX(150);
		snack.setLayoutY(150);
		snack.setScaleX(0.3);
		snack.setScaleY(0.3);
		grid.getChildren().addAll(snack);
		PauseTransition pause = new PauseTransition(Duration.millis(1500));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(snack);
			tama.setLayoutX(190);
			eating = false;
//			if(!model.isHealthy()){
//				setSickImg();
//			}
		});
		pause.play();
		
	}

	/**
	 * makes the tamagotchi some medicine, animated a medicine,
	 * calls the feedMedicine controller function
	 */
	private void makeMedicine() {
		eating = true;
		tama.setLayoutX(200);

		medicine = new Sprite(3, 3, 540, 478, new Image("file:./res/images/hearts.png"), 1, 2100);
		medicine.setLayoutX(150);
		medicine.setLayoutY(150);
		medicine.setScaleX(0.3);
		medicine.setScaleY(0.3);
		grid.getChildren().addAll(medicine);
		PauseTransition pause = new PauseTransition(Duration.millis(2100));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(medicine);
			tama.setLayoutX(190);
			eating = false;
			model.getController().feedMedicine();
			setHealthyImg();
		});
		pause.play();

	}
	

	// Plays selected animation, then calls the correct controller 
	// function to handle it.
	private void select() {
		if (selected.equals(mealSelect) && !eating) {
			makeMeal();
		} else if (selected.equals(snackSelect) && !eating) {
			makeSnack();
		} else if (selected.equals(medicineSelect) && !eating){
			makeMedicine();
		}
		
	}

	/**
	 * Selects the next available menu item.
	 */
	private void nextSelect() {
		if (selectionIndex + 1 > selections.size() - 1) {
			selectionIndex = 0;
		} else {
			selectionIndex++;
		}
		selected.setVisible(true);
		selected = selections.get(selectionIndex);
		
	}

	/*
	 * Selects the previous menu item
	 */
	private void prevSelect() {
		if (selectionIndex - 1 < 0) {
			selectionIndex = selections.size() - 1;
		} else {
		selectionIndex--;
		}
		selected.setVisible(true);
		selected = selections.get(selectionIndex);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null || !model.getController().getState().equals("game")) {
			return;
		}

		if (arg.equals("3")) {
			nextSelect();
		} else if (arg.equals("2")) {
			select();
		} else if (arg.equals("1")) {
			prevSelect();
		}
	}

}
