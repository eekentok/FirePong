package pong.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import pong.control.InputHandler;
import pong.config.Config;
import pong.entity.AIPaddle;
import pong.entity.Ball;
import pong.entity.GameBall;
import pong.entity.Paddle;
import pong.entity.PlayerPaddle;
import pong.entity.FireBall;

public class Game extends Canvas implements Runnable {
	
	JFrame frame;
	public final int WIDTH = 800; 
	public final int HEIGHT = WIDTH / 16 * 9; 
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Pong Plus";
	
	InputHandler inputHandler;
	
	private Image backgroundImage = Toolkit.getDefaultToolkit().createImage(Config.BG_IMAGE);
	private Font font = new Font("Pong", Font.BOLD, 25);
	public static boolean gameRunning = false;
	public int p1Score, p2Score;
	
	public static PlayerPaddle player1Paddle;
	public static Paddle opponentPaddle;
	public static GameBall gameBall;
	public static ArrayList<FireBall> fireBalls = new ArrayList<FireBall>();
	public static FireBall fireBall;
	
	private boolean isTwoPlayer = false;
	
	public Game(){
		frame = new JFrame();
		system.out.println("*");
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);

		inputHandler = new InputHandler(this);
		gameBall = new GameBall(getWidth() / 2, getHeight() / 2);
		
		Random rand = new Random();

		if(Config.DIFFICULTY.equals("EASY")){
			for(int i = 0; i<2; i++){
				fireBall = new FireBall(rand.nextInt(800), rand.nextInt(450));
				fireBalls.add(fireBall);
			}
		}else if(Config.DIFFICULTY.equals("NORMAL")){
			for(int i = 0; i<3; i++){
				fireBall = new FireBall(rand.nextInt(800), rand.nextInt(450));
				fireBalls.add(fireBall);
			}
		}else{
			for(int i = 0; i<4; i++){
				fireBall = new FireBall(rand.nextInt(800), rand.nextInt(450));
				fireBalls.add(fireBall);
			}
		}
	}
	
	@Override
	public void run() {
		createPaddles();
		while (gameRunning) {
			tick();
			render();
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createPaddles(){
		player1Paddle = new PlayerPaddle();
		if(isTwoPlayer){
			opponentPaddle = new PlayerPaddle();
		}else{
			opponentPaddle = new AIPaddle();
		}
		player1Paddle.setX(10);
		player1Paddle.setY((this.HEIGHT / 2) - (player1Paddle.getHeight() / 2));
		opponentPaddle.setX(this.WIDTH - opponentPaddle.getWidth());
		opponentPaddle.setY((this.HEIGHT / 2) - (opponentPaddle.getHeight() / 2));
	}
	
	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	} 

	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	} 
	
	public void tick(){
		player1Paddle.tick(this);
		opponentPaddle.tick(this);
		gameBall.tick(this);
		for(FireBall fireBall : fireBalls){
			fireBall.tick(this);
		}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("" + p1Score, 200, 40);
		g.drawString("" + p2Score, getWidth() - 200, 40);

		player1Paddle.render(g);
		opponentPaddle.render(g);
		gameBall.render(g);
		for(FireBall fireBall : fireBalls){
			fireBall.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	public static void playSound(String a) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(a).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isTwoPlayer() {
		return isTwoPlayer;
	}

	public void setTwoPlayer(boolean isTwoPlayer) {
		this.isTwoPlayer = isTwoPlayer;
	}
}
