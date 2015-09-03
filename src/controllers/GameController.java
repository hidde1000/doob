package controllers;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Ball;
import Application.Board;
import Application.Player;

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
	private Player player;
	private int ballSpeed = 3;
	private int startHeight = 200;
	private int ballSize = 100;
	private int playerHeight = 80;
	private int playerWidth = 40;

		
	JASPER HOU OP KIJK FACEBOOK KOM NAAR MOLA KIJK OP FACEBOOK WE HEBBEN NIEUWE REPO MET MAVEN
	
	@FXML
	public void initialize() {
		balls = new ArrayList<Ball>();
		balls.add(new Ball(0, startHeight, ballSpeed, 0, ballSize));
		player = new Player(canvas.getWidth() / 2, canvas.getHeight() - playerHeight, playerWidth, playerHeight, 0);
		board = new Board(canvas, balls, player);
		startTimer();
	}

	public void startTimer() {
		 new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	               board.moveBalls();
	               board.movePlayer();
	               board.shoot();
	               board.paint();
	            }
	        }.start();
	}
}
