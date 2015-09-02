package controllers;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import Application.Ball;
import Application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController{
	
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;
	int imageX;
	int imageY;
	
	private ArrayList<Ball> balls;
	private int ballSpeed = 3;

	@FXML
	public void initialize() {
		imageX = 400;
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
		balls = new ArrayList<Ball>();
		balls.add(new Ball(100, 500, ballSpeed, 0, 100));
		gc = canvas.getGraphicsContext2D();
		startTimer();
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

	public void startTimer() {
		 new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	               moveBalls();
	               paint();
	            }
	        }.start();
	}
}
