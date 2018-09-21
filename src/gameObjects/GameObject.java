package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import screen.Game;
import screen.Input;

public abstract class GameObject {

    public static GameObject[] recycle = new GameObject[20];
    public static int index = 0;

    protected double x;
    protected double y;
    protected double angle;
    protected int mouseX;
    protected int mouseY;
    protected int width;
    protected int height;
    protected double speed;
    protected double xSpeed;
    protected double ySpeed;
    protected boolean controlled;
    protected Color color;

    public boolean inHandler = false;
    public boolean inUse;
    
    public abstract void render(Graphics g);

    // called by subclasses when repurposing a recycled GameObject
    public static GameObject repurpose(Class c, int x, int y, boolean controlled, Color color, double speed) {
        int recycleIndex = findObject(c);
        if (recycleIndex != -1) {
            GameObject go = recycle[recycleIndex];
            if (index > 0) {
                recycle[recycleIndex] = recycle[--index];
                recycle[index] = null;
            }
            go.x = x;
            go.y = y;
            go.controlled = controlled;
            go.color = color;
            go.speed = speed;
            go.inUse = true;
            return go;
        }
        return null;
    }

    // searches recycle for object of Class c and returns its index, or -1 if none
    // are found
    public static int findObject(Class c) {
        for (int i = 0; i < index; i++) {
            if (recycle[i].getClass() == c) {
                return i;
            }
        }
        return -1;

    }

    public GameObject(int x, int y, boolean controlled, Color color, double speed) {
        this.x = x;
        this.y = y;
        this.controlled = controlled;
        this.color = color;
        this.speed = speed;
        inUse = true;
    }

    public void update(Input input) {
        if (controlled) {
            controls(input);
        } else {
        	ai();
        }
        move();
        checkBounds();
    }
    
    protected void move() {
        x += xSpeed;
        y += ySpeed;
    }

    // returns true if x, y is inside object
    public abstract boolean inside(int x, int y);

    protected double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    protected abstract void controls(Input input);
    
    protected void ai() {
    	
    }

    protected void checkBounds() {
        if (x < width / 2)
            x = width / 2;
        if (x > Game.WIDTH - width / 2 - 1)
            x = Game.WIDTH - width / 2 - 1;
        if (y < height / 2)
            y = height / 2;
        if (y > Game.HEIGHT - height / 2 - 1)
            y = Game.HEIGHT - height / 2 - 1;
    }

    public void discard() {
        x = -500;
        y = -500;
        speed = 0;
        xSpeed = 0;
        ySpeed = 0;
        inUse = false;
        GameObject.recycle(this);
    }

    public static void recycle(GameObject go) {
        if (index >= recycle.length)
            resize();
        recycle[index++] = go;
    }

    private static void resize() {
        GameObject[] temp = new GameObject[recycle.length];
        for (int i = 0; i < recycle.length; i++) {
            temp[i] = recycle[i];
        }
        recycle = new GameObject[temp.length * 2];
        for (int i = 0; i < temp.length; i++) {
            recycle[i] = temp[i];
        }
    }

    public boolean inBounds() {
        if (x < width / 2)
            return false;
        if (x > Game.WIDTH - width / 2 - 1)
            return false;
        if (y < height / 2)
            return false;
        if (y > Game.HEIGHT - height / 2 - 1)
            return false;
        return true;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    // returns angle from center of object to mouse cursor in radians
    public double getAngle() {
        return angle;
    }

    public Color getColor() {
        return color;
    }

    public boolean isControlled() {
        return controlled;
    }

    // returns true if GameObject is not in recycle
    public boolean inUse() {
        return inUse;
    }
}
