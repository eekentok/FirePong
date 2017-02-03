package pong.control;

import pong.game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener {
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_W) {
			//System.out.println("ww");
			Game.player1Paddle.setGoingUp(true); 
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player1Paddle.setGoingDown(true);
		}

		if (keyCode == KeyEvent.VK_UP) {
			Game.opponentPaddle.setGoingUp(true);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.opponentPaddle.setGoingDown(true);
		}

		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_W) {
			Game.player1Paddle.setGoingUp(false);
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player1Paddle.setGoingDown(false);
		}

		if (keyCode == KeyEvent.VK_UP) {
			Game.opponentPaddle.setGoingUp(false);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.opponentPaddle.setGoingDown(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
