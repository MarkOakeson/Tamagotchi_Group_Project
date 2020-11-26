
import javafx.application.Application;


/**
 * 
 * @author 	Aidan McArthur
 *			Bryan Kuo
 *			Jared Malooly
 *			Mark Oakeson
 *
 * Purpose: Launch program
 */
public class TamaLauncher {

	public static void main(String[] args) {
		
		
		
		
		// Create model and controller objects
		TamaModel      model      = new TamaModel();
		TamaController controller = new TamaController(model);
		
		// Launch view
		Application.launch(TamaView.class, args);
		

		
		
		
		
		
	} // End main
} // End class

