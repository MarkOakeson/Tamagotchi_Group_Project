import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
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
	private TamaModel model;
	private Pane grid;
	private Text mealSelect;
	private Text snackSelect;
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
		
		tama = new Sprite(2, 2, 150, 150, new Image("file:./res/images/dino.png"), Animation.INDEFINITE, 700);
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
		
		// PLAY
		playSelect = new Text("PLAY");
		playSelect.setFont(Font.font(15));
		playSelect.setX(150);
		playSelect.setY(280);
		
		// creates an arraylist of all available options for selection
		selections = new ArrayList<Text>();
		selections.add(mealSelect);
		selections.add(snackSelect);
		selections.add(playSelect);
		
		
		grid = new Pane();
		grid.getChildren().addAll(tama, mealSelect, snackSelect, playSelect);
		super.getChildren().add(grid);
		
		selected = mealSelect;
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(350), e -> changeFrame()));
		timeline.setCycleCount(Animation.INDEFINITE); // loop forever
		timeline.play();
		
	}

	/*
	 * blinks currently selected menu item
	 */
	private void changeFrame() {
		
		selected.setVisible(!selected.isVisible());
	}
	
	/**
	 * makes the tamagotchi a meal, animated a meal, 
	 * calls the eatSnack controller function
	 */
	private void makeMeal() {
		eating = true;
		tama.setLayoutX(200);
		
		meal = new Sprite(3, 3, 27, 24, new Image("file:./res/images/meal.png"), 1, 1000);
		meal.setLayoutX(150);
		meal.setLayoutY(150);
		meal.setScaleX(0.5);
		meal.setScaleY(0.5);
		grid.getChildren().addAll(meal);
		PauseTransition pause = new PauseTransition(Duration.millis(1000));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(meal);
			tama.setLayoutX(190);
			eating = false;
			model.getController().eatMeal();
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
		PauseTransition pause = new PauseTransition(Duration.millis(1000));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(snack);
			tama.setLayoutX(190);
			eating = false;
			model.getController().eatSnack();
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
			prevSelect();
		} else if (arg.equals("2")) {
			select();
		} else if (arg.equals("1")) {
			nextSelect();
		}
	}

}
