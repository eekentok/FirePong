package pong.entity;

import java.awt.Rectangle;

import javax.swing.JOptionPane;

import pong.config.Config;
import pong.game.Game;

public class GameBall extends Ball {
	public GameBall(int x, int y){
		this.x = x + (this.size / 2);
		this.y = y + (this.size / 2);
		
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(this.x, this.y, size, size);
		boundingBox.setBounds(this.x, this.y, this.size, this.size);
	}
	
	public void tick(Game game){
		boundingBox.setBounds(x, y, size, size);
		if (x <= 0) {
			game.p2Score++;
			game.playSound(Config.BALL_GOAL);
			x = game.getWidth() / 2;
			vx = speed / 2;
			vy = speed;

		} else if (x + size >= game.getWidth()) {
			game.p1Score++;
			game.playSound(Config.BALL_GOAL);
			x = game.getWidth() / 2;
			vx = -speed / 2;
			vy = speed;
		}
		
		wallCollide(game);
		paddleCollide(game);
		fireBallCollide(game);
		
		if (y <= 0) {
			vy = speed;
		} else if (y + size >= game.getHeight()) {
			vy = -speed;
		}

		x += vx;
		y += vy;
		
		if (game.p1Score == 10) {
			JOptionPane.showMessageDialog(null, game.player1Paddle + " won! GGWP", "Pong",
					JOptionPane.PLAIN_MESSAGE);
			game.stop();
		} else if (game.p2Score == 10) {
			JOptionPane.showMessageDialog(null, game.opponentPaddle + " won! GGWP", "Pong",
					JOptionPane.PLAIN_MESSAGE);
			game.stop();
		}
	}
	
	public void wallCollide(Game game){
		if (y == 0) {
			game.playSound(Config.BALL_HIT_WALL);
		}
		if (y + size >= game.getHeight()) {
			game.playSound(Config.BALL_HIT_WALL);
		}
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
			Game.playSound(Config.BALL_HIT_PADDLE);
		} else if (boundingBox.intersects(game.opponentPaddle.boundingBox)) {
			vx = -speed;
			Game.playSound(Config.BALL_HIT_PADDLE);
		}
	}
	
	public void fireBallCollide(Game game){
		if(boundingBox.intersects(game.fireBall.boundingBox)){
			vx = -vx;
		}
	}
}
