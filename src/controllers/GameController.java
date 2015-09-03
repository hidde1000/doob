package controllers;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import Application.Ball;
import Application.Board;

public class GameController{
	
	@FXML
	private Pane lives1;
	@FXML
	private Pane lives2;
	@FXML
	private Label score1;
	@FXML
	private Label score2;
	
	@FXML
	private Canvas canvas;
	private Board board;
	
	private ArrayList<Ball> balls;
	private int ballSpeed = 3;
	private int startHeight = 200;
	private int ballSize = 100;

	@FXML
	public void initialize() {
		balls = new ArrayList<Ball>();
		balls.add(new Ball(0, startHeight, ballSpeed, 0, ballSize));
		board = new Board(canvas, balls);
		startTimer();
	}

	public void startTimer() {
		 new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	               board.moveBalls();
	               board.paint();
	            }
	        }.start();
	}
}
