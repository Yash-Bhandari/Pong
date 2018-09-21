package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import levels.level1;
import screen.Game;
import screen.Input;

public class Ball extends GameObject {
	private int radius;

	/*
	 * public static Circle createCircle(int radius, int x, int y, boolean
	 * controlled, Color color, double speed) { Circle go; go = (Circle)
	 * GameObject.repurpose(Circle.class, x, y, controlled, color, speed); if (go !=
	 * null) { go.setRadius(radius); return go; } go = new Circle(radius, x, y,
	 * controlled, color, speed); return go; }
	 */

	public Ball(int radius, int x, int y, boolean controlled, Color color, double speed, double angle) {
		super(x, y, controlled, color, speed);
		this.width = 2 * radius;
		this.height = 2 * radius;
		this.radius = radius;
		if (!controlled) {
			xSpeed = Math.cos(angle) * speed;
			ySpeed = Math.sin(angle) * speed;
		}
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.black);
		// g.drawOval((int)x-radius, (int)y-radius, radius, radius);
		g.setColor(color);
		g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
	}

	@Override
	public boolean inside(int x1, int y1) {
		double distance = Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
		System.out.println(distance);
		return distance <= radius;
	}

	@Override
	protected void controls(Input input) {
		// TODO Auto-generated method stub

	}

	protected void checkBounds() {
		if (x < width / 2 || x > Game.WIDTH - width / 2 - 1) {
			if (xSpeed < 0) level1.p2Score++;
			else level1.p1Score++;
			xSpeed = -xSpeed;
			x = 450;
			y = 350;
		}
		if (y < height / 2 || y > Game.HEIGHT - height / 2 - 1)
			ySpeed = -ySpeed;
	}
	
	public void bounceX() {
		if (xSpeed < 0) xSpeed -= 0.1;
		else xSpeed += 0.1;
		xSpeed = -xSpeed;
	}
	
	public int radius() {
		return radius;
	}

}
