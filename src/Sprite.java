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

public class Sprite extends Pane{
	private int frame;

//	URL url = getClass().getResource("/testSpriteSheet.png");
//	Image spriteSheet = new Image("/images/testSpriteSheet.png");
//
//	Image spriteSheet = new Image("file:./images/testSpriteSheet.png");
//	ImageView imageView = new ImageView(spriteSheet);
//
//	public Sprite() {
//		frame = 0;
//		VBox vbox = new VBox();
//		vbox.getChildren().add(imageView);
//		super.getChildren().add(vbox);
//		
//
//	    Timeline timeline = 
//	        new Timeline(new KeyFrame(Duration.millis(100), e -> changeFrame()));
//	    timeline.setCycleCount(Animation.INDEFINITE); // loop forever
//	    timeline.play();
//	    
//
//	}
//	
//	private void changeFrame() {
//		imageView.setVisible(!imageView.isVisible());
//	}
	
	
	
	private Image img; // = new Image("file:./res/images/dino.png");
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private int col;
    private int count;
    private int w;
    private int h;
    private int cycle;
    private int duration;
    
    public Sprite(int col, int count, int w, int h, Image img, int cycle, int duration) {
    	this.cycle = cycle;
    	this.img = img;
    	this.col = col;
    	this.count = count;
    	this.w = w;
    	this.h = h;

        final ImageView imageView = new ImageView(img);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, w, h));

        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        
        VBox vbox = new VBox();
		vbox.getChildren().add(imageView);
		super.getChildren().add(vbox);
        
        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(duration),
                count, col,
                OFFSET_X, OFFSET_Y,
                w, h
        );
        animation.setCycleCount(cycle);
        animation.play();
    }



	
	
	
	
	
	
	
}
