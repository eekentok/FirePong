package pong.launch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pong.config.Config;
import pong.game.Game;

public class Menu extends JFrame {
	private int screenWidth = 480;
	private int screenHeight = 320;
	private JButton playButton, quitButton;
	private JCheckBox twoPlayerCheckBox;
	private JRadioButton isHardRadioButton, isNormalRadioButton, isEasyRadioButton;
	private JLabel labelOne, labelTwo, labelThree;
	
	public Menu(){
		createButtons();
		addActions();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(playButton)
					.addComponent(quitButton)
					.addComponent(twoPlayerCheckBox))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(labelOne)
					.addComponent(isHardRadioButton)
					.addComponent(isNormalRadioButton)
					.addComponent(isEasyRadioButton)
					.addComponent(labelTwo)
					.addComponent(labelThree))
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(playButton)
					.addComponent(labelOne))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(quitButton)
					.addComponent(isHardRadioButton))
				.addComponent(isNormalRadioButton)
				.addComponent(isEasyRadioButton)
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(twoPlayerCheckBox)
					.addComponent(labelTwo))
				.addComponent(labelThree)
				);
		
		this.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void createButtons(){
		playButton = new JButton("Play");
		quitButton = new JButton("Quit");
		twoPlayerCheckBox = new JCheckBox("Two Players?");
		isHardRadioButton = new JRadioButton("IMMORTAL!");
		isNormalRadioButton = new JRadioButton("Intermediate");
		isEasyRadioButton = new JRadioButton("Beginner");
		labelOne = new JLabel("Choose your difficulty level:");
		labelTwo = new JLabel("Player 1 Controls: W and A buttons.");
		labelThree = new JLabel("Player 2 Controls: UP and DOWN buttons.");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(isEasyRadioButton);
		buttonGroup.add(isHardRadioButton);
		buttonGroup.add(isNormalRadioButton);
	}
	
	public void addActions(){
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				Game game;
				
				if(twoPlayerCheckBox.isSelected()){
					game = new Game();
					game.setTwoPlayer(true);
				}else{
					if(isHardRadioButton.isSelected()){
						Config.DIFFICULTY = "HARD";
						Config.BALL_SPEED = 4.50;
						Config.BALL_SIZE = 15;
					}else if(isNormalRadioButton.isSelected()){
						Config.DIFFICULTY = "NORMAL";
						Config.BALL_SPEED = 3.50;
						Config.BALL_SIZE = 20;
					}else{
						Config.DIFFICULTY = "EASY";
						Config.BALL_SPEED = 2.50;
						Config.BALL_SIZE = 25;
					}
					game = new Game();
				}				
				game.start();
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
