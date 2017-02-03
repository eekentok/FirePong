package pong.entity;

import java.awt.Rectangle;

import pong.game.Game;

public class PlayerPaddle extends Paddle {
	public PlayerPaddle(){
		boundingBox = new Rectangle(this.x, this.y, width, height);
		boundingBox.setBounds(this.x, this.y, width, height);
	}
	
	public void tick(Game game) {
		boundingBox.setBounds(x, y, width, height);
		if (goingUp && y > 0) {
			y -= speed;
		}
		if (goingDown && y < game.getHeight() - height) {
			y += speed;
		}
	}
}
