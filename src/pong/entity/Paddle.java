package pong.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import pong.config.Config;
import pong.game.Game;
import pong.util.Renderable;

public class Paddle implements Renderable {
	protected int x;
	protected int y;
	protected int width = Config.PADDLE_WIDTH;
	protected int height = Config.PADDLE_HEIGHT;
	protected double speed = Config.PADDLE_SPEED;
	protected boolean goingUp = false;
	protected boolean goingDown = false;
	
	Rectangle boundingBox;
	ImageIcon paddle = new ImageIcon(Config.PONG_PADDLE);
	
	public void render(Graphics g) {
		g.drawImage(paddle.getImage(), x, y, width, height, null);

	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public boolean isGoingDown() {
		return goingDown;
	}

	public void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
	}

	@Override
	public void tick(Game game) {
		// TODO Auto-generated method stub
		
	}
	
}
