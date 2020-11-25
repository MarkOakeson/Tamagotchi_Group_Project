import java.io.IOException;
import javafx.application.Application;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

