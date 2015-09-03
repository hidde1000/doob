package Application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Ball;
import model.Projectile;
import model.Spike;

public class Board {

	private Canvas canvas;
	private GraphicsContext gc;
	private ArrayList<Ball> balls;
	private ArrayList<Projectile> projectiles;
	private Player player;
	private int ballSpeed = 3;
	private int playerSpeed = 6;
	private int shootSpeed = 12;

	public Board(Canvas canvas, ArrayList<Ball> balls, Player player) {
		this.canvas = canvas;
		this.balls = balls;
		this.player = player;
		projectiles = new ArrayList<Projectile>();
		initBoard();
	}

	public void initBoard() {
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.RIGHT)
						&& player.getX() <= canvas.getWidth() - 40) {
					player.setSpeed(playerSpeed);
				} else if (key.getCode().equals(KeyCode.LEFT)
						&& player.getX() >= 20) {
					player.setSpeed(-playerSpeed);
				} else if (key.getCode().equals(KeyCode.RIGHT)
						&& player.getX() >= canvas.getWidth() - 40) {
					player.setSpeed(0);
				} else if (key.getCode().equals(KeyCode.LEFT)
						&& player.getX() <= 0) {
					player.setSpeed(0);
				} else if (key.getCode().equals(KeyCode.SPACE)) {
					projectiles.add(new Spike(player.getX() + player.getWidth()
							/ 2, canvas.getHeight(), shootSpeed));
				}
			}

		});

		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.RIGHT)
						|| key.getCode().equals(KeyCode.LEFT)) {
					player.setSpeed(0);
				}
			}

		});

		canvas.requestFocus();
		gc = canvas.getGraphicsContext2D();
	}

	public void moveBalls() {
		for (Ball b : balls) {
			b.moveHorizontally();
			if (b.getX() + b.getSize() >= canvas.getWidth())
				b.setSpeedX(-ballSpeed);
			else if (b.getX() <= 0)
				b.setSpeedX(ballSpeed);
			b.moveVertically();
			;
			b.incrSpeedY(0.5);
			if (b.getY() + b.getSize() > canvas.getHeight()) {
				b.setSpeedY(-20);
			}
		}
	}

	public void movePlayer() {
		player.move();
	}

	public void shoot() {
		for (Iterator<Projectile> iter = projectiles.listIterator(); iter.hasNext(); ) {
		    Projectile p = iter.next();
		    if (p.getY() <= 0) {
		        iter.remove();
		    } else {
		    	p.shoot();
		    }
		}
	}

	public void paint() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(player.getImg(), player.getX(), player.getY());
		for (Projectile b : projectiles) {
			gc.drawImage(b.getImg(), b.getX(), b.getY());
		}
		for (Ball b : balls) {
			gc.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
		}
	}
}
