import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuPane extends Pane {

	public MenuPane() {
		
		Text newGame = new Text("New Game");
		
		
		
		Text loadGame = new Text("Load Game");
		
		
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(newGame, loadGame);
		super.getChildren().add(vbox);
	}
}
