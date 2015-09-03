package Application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Board {

	private Canvas canvas;
	private GraphicsContext gc;
	private ArrayList<Ball> balls;
	private int ballSpeed = 3;
	int imageX;
	int imageY;
	
	public Board(Canvas canvas, ArrayList<Ball> balls) {
		this.canvas = canvas;
		this.balls = balls;
		initBoard();
	}
	
	public void initBoard() {
		imageX = (int) (canvas.getWidth() / 2);
		imageY = (int) (canvas.getHeight() - 80);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.RIGHT) && imageX <canvas.getWidth() - 40) {
					imageX += 20;
					System.out.println("Rechts");
				} else if (key.getCode().equals(KeyCode.LEFT) && imageX > 20) {
					imageX -= 20;
					System.out.println("Links");
				} else if (key.getCode().equals(KeyCode.RIGHT) && imageX > canvas.getWidth() - 40 && imageX < canvas.getWidth() - 20) {
					imageX = (int) (canvas.getWidth() - 20);
				} else if (key.getCode().equals(KeyCode.LEFT) && imageX > 0 && imageX <= 20) {
					imageX = 0;
					System.out.println("Links");
				}
			}	
			
		});
		canvas.requestFocus();		
		gc = canvas.getGraphicsContext2D();
	}
	
	public void moveBalls() {
		for(Ball b : balls) {
			b.moveHorizontally();
			if(b.getX() + b.getSize() >= canvas.getWidth()) b.setSpeedX(-ballSpeed);
			else if(b.getX() <= 0) b.setSpeedX(ballSpeed);
			b.moveVertically();;
			b.incrSpeedY(0.5);
			if(b.getY() + b.getSize() > canvas.getHeight()) {
				b.setSpeedY(-20);
			}
		}
	}
	
	public void paint() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(new Image("/Stand2.png"), imageX, imageY);
		for(Ball b : balls) {
			gc.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
		}
	}
}
