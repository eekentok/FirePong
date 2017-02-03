package pong.entity;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import pong.config.Config;
import pong.game.Game;

public class FireBall extends Ball {
	public FireBall(int x, int y){
		this.size = 45;
		this.x = x + (this.size / 2);
		this.y = y + (this.size / 2);
		
		vx = speed;
		vy = speed;
		
		ball = new ImageIcon(Config.FIRE_BALL);
		boundingBox = new Rectangle(this.x, this.y, this.size, this.size);
		boundingBox.setBounds(this.x, this.y, this.size, this.size);
	}
	
	public void tick(Game game){
		boundingBox.setBounds(x, y, size, size);
		if (x <= 0) {
			vx = speed / 2;
			vy = speed;

		} else if (x + size >= game.getWidth()) {
			vx = -speed / 2;
			vy = speed;
		}
		paddleCollide(game);
		ballCollide(game);
		if (y <= 0) {
			vy = speed;
		} else if (y + size >= game.getHeight()) {
			vy = -speed;
		}

		x += vx;
		y += vy;
	}
	
	public void paddleCollide(Game game){
		if (boundingBox.intersects(game.player1Paddle.boundingBox)) {
			if(game.player1Paddle.goingDown){
				if(vy < 0){
					vx = speed;
					vy = 1.3 * speed;
				}else{
					vy = speed;
					vx = 1.3 * speed;
				}
			}else{
				if(vy < 0){
					vy = -speed;
					vx = 1.3 * speed;
				}else{
					vx = speed;
					vy = 1.3 * speed;
				}
			}
		} else if (boundingBox.intersects(game.opponentPaddle.boundingBox)) {
			vx = -speed;
		}
	}
	
	public void ballCollide(Game game){
		if(boundingBox.intersects(game.gameBall.boundingBox)){
			
		}
	}
}
