
public class GameState {
	private String gameState;
	
	public GameState() {
		gameState = "menu";
	}
	
	public void changeState(String state) {
		gameState = state;
	}
	
	public String getState() {
		return gameState;
	}
	
}
