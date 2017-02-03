package pong.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import pong.config.Config;
import pong.game.Game;
import pong.util.Renderable;

public class Ball implements Renderable {
	protected int x;
	protected int y;
	protected int size = Config.BALL_SIZE;
	protected double speed = Config.BALL_SPEED;
	protected double vx;
	protected double vy;
	Rectangle boundingBox;	
	ImageIcon ball = new ImageIcon(Config.PONG_BALL);
	
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public void render(Graphics g) {
		g.drawImage(ball.getImage(), x, y, size, size, null);
	}

	@Override
	public void tick(Game game) {
		// TODO Auto-generated method stub
		
	}
}
