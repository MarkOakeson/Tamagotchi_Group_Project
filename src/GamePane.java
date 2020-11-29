import java.io.IOException;
import java.io.Serializable;
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
	private Sprite food;
	private TamaModel model;
	private Pane grid;
	private Image foodSelect;
	private Image playSelect;
	
	private TamaController controller;

	public GamePane(TamaModel model) {
		this.model = model;
		//controller = model.getController();
		
		model.addObserver(this);
		
		tama = new Sprite(2, 2, 150, 150, new Image("file:./res/images/dino.png"), Animation.INDEFINITE, 700);
		tama.setLayoutX(190);
		tama.setLayoutY(150);
		
		//foodSelect = new Image();
		//playSelect = new Image();
		
		grid = new Pane();
		grid.getChildren().addAll(tama);
		super.getChildren().add(grid);
	}

	private void makeFood() {
		tama.setLayoutX(200);
		
		food = new Sprite(3, 3, 27, 24, new Image("file:./res/images/food.png"), 1, 1000);
		food.setLayoutX(150);
		food.setLayoutY(150);
		food.setScaleX(0.5);
		food.setScaleY(0.5);
		grid.getChildren().addAll(food);
		PauseTransition pause = new PauseTransition(Duration.millis(1000));
		pause.setOnFinished(e -> {
			grid.getChildren().remove(food);
			tama.setLayoutX(190);
		});
		pause.play();
		
	}
	

	private void select() {
		// TODO Auto-generated method stub
		
	}

	private void nextSelect() {
		// TODO Auto-generated method stub
		
	}

	private void prevSelect() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null || !model.getState().getState().equals("game")) {
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
