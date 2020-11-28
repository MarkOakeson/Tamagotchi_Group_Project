import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundPlayer {
	MediaPlayer mediaPlayer;
	Media sound;
	
	public SoundPlayer(String musicFile) {
		
		sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	public void play() {
		System.out.println("Beep");
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
}
