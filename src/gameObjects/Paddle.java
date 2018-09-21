package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import screen.Game;
import screen.Input;

public class Paddle extends GameObject {
	protected int width;
	protected int height;
	protected int Player;
	protected int outside; //x location of outside of paddle that will come in contact with the ball

	public Paddle(int x, int y, boolean controlled, Color color, double speed, int Player) {
		super(x, y, controlled, color, speed);
		width = 30;
		height = 100;
		this.Player = Player;
		if (Player == 0) outside = x + width;
		else outside = x;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int) x, (int) y, width, height);
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
	}

	protected void controls(Input input) {
		ySpeed = 0;
		if (input.keys[2 * Player])
			ySpeed = -speed;
		if (input.keys[2 * Player + 1])
			ySpeed = speed;
	}
	
    protected void checkBounds() {
        if (y < 0)
            y = 0;
        if (y > Game.HEIGHT - height)
            y = Game.HEIGHT - height;
    }

	public boolean inside(int x, int y) {
		return false;
	}
	
	public int outsideX() {
		return outside;
	}
	
	public int player() {
		return Player;
	}
	
	public int height() {
		return height;
	}
	
	public int width() {
		return width;
	}

}
