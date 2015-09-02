package controllers;

import java.util.ArrayList;

import Application.Ball;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController {
	
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;
	
	private ArrayList<Ball> balls;
	private int ballSpeed = 3;
	private int startHeight = 200;
	private int ballSize = 100;

	@FXML
	public void initialize() {
		balls = new ArrayList<Ball>();
		balls.add(new Ball(0, startHeight, ballSpeed, 0, ballSize));
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
