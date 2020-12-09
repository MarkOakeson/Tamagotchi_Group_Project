import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuPane extends Pane implements Observer{

	private Text newGame;
	private Text loadGame;
	private Text selected;
	private TamaModel model;
	private Pane pane;

	public MenuPane(TamaModel model) {

		this.model = model;
		model.addObserver(this);
		
		newGame = new Text("NEW GAME");
		newGame.setFont(Font.font(20));
		newGame.setFill(Color.DARKSLATEGRAY);
		newGame.setX(190);
		newGame.setY(170);

		loadGame = new Text("\nLOAD GAME");
		loadGame.setFont(Font.font(20));
		loadGame.setFill(Color.DARKSLATEGRAY);
		loadGame.setX(190);
		loadGame.setY(200);

		selected = newGame; // default

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(400), e -> changeFrame()));
		timeline.setCycleCount(Animation.INDEFINITE); // loop forever
		timeline.play();

		pane = new Pane();
		pane.getChildren().addAll(newGame, loadGame);
		super.getChildren().add(pane);
	}

	/*
	 * blinks currently selected option
	 */
	private void changeFrame() {
		selected.setVisible(!selected.isVisible());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null || !model.getState().getState().equals("menu")) {
			return;
		}
		if (arg.equals("1") || arg.equals("3")) {
			if (selected.equals(newGame)) {
				selected.setVisible(true);
				selected = loadGame;
			} else {
				selected.setVisible(true);
				selected = newGame;
			}
		} else if (arg.equals("2")) {
			
			model.getController().changeState("game");
			
			model.pressed("updateScreenPane. This string is bogus and doesnt matter");
			
			if (selected.equals(newGame)) {
				model.resetPet();
			} else {
				try {
					model.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
