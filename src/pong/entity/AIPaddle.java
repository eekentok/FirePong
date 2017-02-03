package pong.entity;

import java.awt.Rectangle;

import pong.config.Config;
import pong.game.Game;

public class AIPaddle extends Paddle {

	public AIPaddle(){
		boundingBox = new Rectangle(this.x, this.y, width, height);
		boundingBox.setBounds(this.x, this.y, width, height);
		if(Config.DIFFICULTY.equals("EASY")){
			this.speed = 2.00;
		}else if(Config.DIFFICULTY.equals("NORMAL")){
			this.speed = 2.50;
		}else{
			this.speed = 3.00;
		}
		
	}
	
	public void tick(Game game) {	
		boundingBox.setBounds(this.x, this.y, width, height);
		if (game.gameBall.y <= y && y >= 0) {
			y -= speed;
		} else if (game.gameBall.y > y && y + height <= game.getHeight()) {
			y += speed;
		}
		
		if (Config.DIFFICULTY.equals("HARD")) {
			if (game.gameBall.y + (game.gameBall.size / 2) <= y - 75 && y >= 0) {				
				y -= speed;
			} else if (game.gameBall.y + (game.gameBall.size / 2)  >= y + 75 && y + (height / 2) <= game.getHeight()) {
				y += speed;
			}
		} else if (Config.DIFFICULTY.equals("EASY")) {			
			if (game.gameBall.y + (game.gameBall.size / 2) <= y - 95 && y >= 0) {
				y -= speed;
			} else if (game.gameBall.y + (game.gameBall.size / 2)  >= y + 95 && y + (height / 2) <= game.getHeight()) {
				y += speed;
			}
		} else {
			if (game.gameBall.y + (game.gameBall.size / 2) <= y - 85 && y >= 0) {
				y -= speed;
			} else if (game.gameBall.y + (game.gameBall.size / 2)  >= y + 85 && y + (height / 2) <= game.getHeight()) {
				y += speed;
			}
		}
	}
}
