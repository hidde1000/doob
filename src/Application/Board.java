package Application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Ball;

public class Board {

	private Canvas canvas;
	private GraphicsContext gc;
	private ArrayList<Ball> balls;
	private Player player;
	private int ballSpeed = 3;
	private int playerSpeed = 6;
	
	public Board(Canvas canvas, ArrayList<Ball> balls, Player player) {
		this.canvas = canvas;
		this.balls = balls;
		this.player = player;
		initBoard();
	}
	
	public void initBoard() {
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {	
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.RIGHT) && player.getX() <= canvas.getWidth() - 40) {
					player.setSpeed(playerSpeed);
				} else if (key.getCode().equals(KeyCode.LEFT) && player.getX() >= 20) {
					player.setSpeed(-playerSpeed);
				} else if (key.getCode().equals(KeyCode.RIGHT) && player.getX() >= canvas.getWidth() - 40) {
					player.setSpeed(0);
				} else if (key.getCode().equals(KeyCode.LEFT) && player.getX() <= 0) {
					player.setSpeed(0);
				}
			}	
			
		});
		
		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.RIGHT) || key.getCode().equals(KeyCode.LEFT)) {
					player.setSpeed(0);
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
	
	public void movePlayer() {
		player.move();
	}
	
	public void paint() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(player.getImg(), player.getX(), player.getY());
		for(Ball b : balls) {
			gc.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
		}
	}
}
