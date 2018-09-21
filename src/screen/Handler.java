package screen;

import java.awt.Graphics;
import java.util.LinkedList;

import gameObjects.Ball;
import gameObjects.GameObject;
import gameObjects.Paddle;

public class Handler {

    public LinkedList<GameObject> objects;
    public Paddle p1;
    public Paddle p2;
    public Ball ball;
    private Input input;

    public Handler(Input input) {
        objects = new LinkedList<GameObject>();
        this.input = input;
    }

    public void add(GameObject go) {
        if (!go.inHandler) {
            objects.add(go);
            go.inHandler = true;
        }
        if (go instanceof Paddle) {
            Paddle a = (Paddle)go;
            if (a.player() == 0)
            	p1 = a;
            if (a.player() == 1)
            	p2 = a;
        }
        if (go instanceof Ball) {
        	ball = (Ball)go;	
        }
    }

    public void updateObjects() {

        for (GameObject go : objects) {
            if (go.inUse())
                go.update(input);
        }
        if (ball != null && p1 != null && p2 != null) collision();
    }

    public void renderObjects(Graphics g) {
        for (GameObject go : objects) {
            go.render(g);
        }
    }

    public void clear() {
        objects.clear();
    }

    private void collision() {
    	if (ball.getX() - ball.radius() < p1.outsideX() && ball.getY() > p1.getY() && ball.getY() < p1.getY()+p1.height()) {
    		ball.bounceX();
    	}
    	if (ball.getX() + ball.radius() > p2.outsideX() && ball.getY() > p2.getY() && ball.getY() < p2.getY()+p2.height()) {
    		ball.bounceX();
    	}
    }
}
