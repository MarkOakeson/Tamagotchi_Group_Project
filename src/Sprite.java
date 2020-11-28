import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Sprite extends Pane {
	private int frame;
	
	private static final Image IMAGE = new Image("file:./res/images/dino.png");

    private static final int COLUMNS  =  2;
    private static final int COUNT    =  2;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private static final int WIDTH    = 150;
    private static final int HEIGHT   = 150;
    
    public Sprite() {

        final ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        
        VBox vbox = new VBox();
		vbox.getChildren().add(imageView);
		super.getChildren().add(vbox);
        
        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(700),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
	
	
	
	
	
	
	
}
