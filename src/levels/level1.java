package levels;

import java.awt.Color;
import java.awt.Graphics;

import gameObjects.Ball;
import gameObjects.Paddle;
import screen.Handler;
import screen.Input;

public class level1 {
	public static int p1Score = 0;
	public static int p2Score = 0;

	public static void start(Handler handler, Input input) {
		handler.add(new Paddle(50, 200, true, Color.white, 5, 0));
		handler.add(new Paddle(820, 100, true, Color.WHITE, 5, 1));
		handler.add(new Ball(10, 450, 350, false, Color.white, 5.0, Math.random() * Math.PI/2 * ((int)Math.random()-1)));
	}

	// draws score
	public static void render(Graphics g) {
		g.drawString("" + p1Score, 100, 40);
		g.drawString("" + p2Score, 750, 40);
	}
}
