
import java.awt.*;
import java.awt.image.*;

import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CursorMaze implements MouseMotionListener {

	public static void main(String[] args) {
		CursorMaze prg = new CursorMaze();
		prg.init();
	}

	int mouseX, mouseY;
	int life = 3;

	int lastLevel;

	private BufferedImage lvl1;
	private BufferedImage lvl2;
	private BufferedImage lvl3;
	
	
	private BufferedImage fnsh;
	private BufferedImage title;
	private BufferedImage howTo;
	private BufferedImage credits;
	
	
	private JLabel titleLabel;
	private JLabel creditsLabel;
	private JLabel howToLabel;
	private JLabel finishLabel;
	private JLabel label;

	private JFrame frame;
	
	private JPanel startPanel;
	private JPanel titlePanel;
	private JPanel collisionPanel;
	private JPanel labelPanel;
	private JPanel gameoverPanel;
	private JPanel passPanel;
	private JPanel levelPanel;
	private JPanel howToPanel;
	private JPanel creditsPanel;
	private JPanel finishPanel;
	
	
	public void init() {
		
		/*
		 * Sets up the frame and panels
		 * Minimum window size: 900x700
		 * 
		 * I used GridBagLayout for layouts because
		 * that was the easiest for me to understand
		 */
		
		frame = new JFrame("Cursor Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize (1080, 720);
		frame.setPreferredSize(new Dimension(1080, 720));
		frame.setMinimumSize(new Dimension(900, 700));
		frame.setLayout(new GridBagLayout());

		startPanel = new JPanel();
		startPanel.setVisible(true);
		startPanel.setLayout(new GridBagLayout());
		
		titlePanel = new JPanel();
		titlePanel.setVisible(true);
		titlePanel.setLayout(new GridBagLayout());
		
		levelPanel = new JPanel();
		levelPanel.setVisible(true);
		levelPanel.setLayout(new GridBagLayout());
		
		howToPanel = new JPanel();
		howToPanel.setVisible(true);
		howToPanel.setLayout(new GridBagLayout());
		
		creditsPanel = new JPanel();
		creditsPanel.setVisible(true);
		creditsPanel.setLayout(new GridBagLayout());

		collisionPanel = new JPanel();
		collisionPanel.setVisible(true);
		collisionPanel.setLayout(new GridBagLayout());	

		labelPanel = new JPanel();
		labelPanel.setVisible(true);
		labelPanel.setLayout(new GridBagLayout());

		gameoverPanel = new JPanel();
		gameoverPanel.setVisible(true);
		gameoverPanel.setLayout(new GridBagLayout());

		passPanel = new JPanel();
		passPanel.setVisible(true);
		passPanel.setLayout(new GridBagLayout());

		finishPanel = new JPanel();
		finishPanel.setVisible(true);
		finishPanel.setLayout(new GridBagLayout());

		startMenu();
	}


	private void startMenu() {

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcs = new GridBagConstraints();

		frame.add(titlePanel, gbc);
		
		gbc.gridy = 1;
		frame.add(startPanel, gbc);

		gbcs.fill = GridBagConstraints.HORIZONTAL;
		
		titleLabel = new JLabel();
		titleLabel.setVisible(true);
		
		try {
			title = ImageIO.read(new File("resources/title.jpg"));
			titleLabel.setIcon(new ImageIcon(title));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		titlePanel.add(titleLabel, gbcs);
		
		gbcs.gridy = 1;
		startPanel.add(Box.createVerticalStrut(30), gbcs);
		
		JButton startGame = new JButton ("Start Game");
		gbcs.gridy = 2;
		startPanel.add(startGame, gbcs);

		gbcs.gridy = 3;
		startPanel.add(Box.createVerticalStrut(10), gbcs);

		JButton howToPlay = new JButton ("How to Play");
		gbcs.gridy = 4;
		startPanel.add(howToPlay, gbcs);

		gbcs.gridy = 5;
		startPanel.add(Box.createVerticalStrut(10), gbcs);
		
		JButton creditsButton = new JButton ("Credits");
		gbcs.gridy = 6;
		startPanel.add(creditsButton, gbcs);
		
		gbcs.gridy = 7;
		startPanel.add(Box.createVerticalStrut(20), gbcs);

		JButton exitGame = new JButton ("Exit");
		gbcs.gridy = 8;
		startPanel.add(exitGame, gbcs);

		frame.validate();
		frame.repaint();

		startGame.addActionListener(new ActionListener() {							//opens level picker screen when clicked
			public void actionPerformed(ActionEvent e) {
				startPanel.removeAll();
				frame.remove(startPanel);
				titlePanel.removeAll();
				frame.remove(titlePanel);
				
				frame.validate();
				frame.repaint();
				levelPicker();	
			}
		});

		howToPlay.addActionListener(new ActionListener() {							//opens how to play screen when clicked
			public void actionPerformed(ActionEvent e) {
				startPanel.removeAll();
				frame.remove(startPanel);
				titlePanel.removeAll();
				frame.remove(titlePanel);
				
				frame.validate();
				frame.repaint();
				
				
				
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				howToLabel = new JLabel();
				howToLabel.setVisible(true);
				
				try {
					howTo = ImageIO.read(new File("resources/howtoplay.jpg"));
					howToLabel.setIcon(new ImageIcon(howTo));
				} 
				catch (IOException ex) {
					ex.printStackTrace();
				}
				
				howToPanel.add(howToLabel, gbc);
				frame.add(howToPanel);
				frame.validate();
				frame.repaint();
				
				JButton backToStart = new JButton ("Menu");
				howToPanel.add(backToStart, gbc);
				
				frame.validate();
				frame.repaint();
				
				backToStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						howToPanel.removeAll();
						frame.remove(howToPanel);
						startMenu();
						frame.validate();
						frame.repaint();
					}
				});

			}
		});
		
		creditsButton.addActionListener(new ActionListener() {						//opens credits screen when clicked
			public void actionPerformed(ActionEvent e) {
				startPanel.removeAll();
				frame.remove(startPanel);
				titlePanel.removeAll();
				frame.remove(titlePanel);
				
				frame.validate();
				frame.repaint();
				
				
				
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				creditsLabel = new JLabel();
				creditsLabel.setVisible(true);
				
				try {
					credits = ImageIO.read(new File("resources/credits.jpg"));
					creditsLabel.setIcon(new ImageIcon(credits));
				} 
				catch (IOException ex) {
					ex.printStackTrace();
				}
				
				creditsPanel.add(creditsLabel, gbc);
				frame.add(creditsPanel);
				frame.validate();
				frame.repaint();
				
				JButton backToStart = new JButton ("Menu");
				creditsPanel.add(backToStart, gbc);
				
				frame.validate();
				frame.repaint();
				
				backToStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						creditsPanel.removeAll();
						frame.remove(creditsPanel);
						startMenu();
						frame.validate();
						frame.repaint();
					}
				});

			}
		});

		exitGame.addActionListener(new ActionListener() {							//exits the game
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	

			}
		});
	}
	private void levelPicker() {
		
		/*
		 * Buttons are added to levelPanel.
		 * Every button leads to the level on its name
		*/
		
		GridBagConstraints gbcl = new GridBagConstraints();

		frame.add(levelPanel);
		
		gbcl.fill = GridBagConstraints.HORIZONTAL;									//Evens the button width with the largest one.

		JButton level1 = new JButton ("Level 1");
		gbcl.gridy = 0;																//Declares the coordinate on grid.
		levelPanel.add(level1, gbcl);

		gbcl.gridy = 1;	
		levelPanel.add(Box.createVerticalStrut(10), gbcl);							//Simply a spacer between buttons.

		JButton level2 = new JButton ("Level 2");
		gbcl.gridy = 2;
		levelPanel.add(level2, gbcl);

		gbcl.gridy = 3;
		levelPanel.add(Box.createVerticalStrut(10), gbcl);

		JButton level3 = new JButton ("Level 3");
		gbcl.gridy = 4;
		levelPanel.add(level3, gbcl);

		gbcl.gridy = 5;
		levelPanel.add(Box.createVerticalStrut(20), gbcl);

		JButton backButton = new JButton ("Back");
		gbcl.gridy = 6;
		levelPanel.add(backButton, gbcl);

		frame.validate();
		frame.repaint();



		level1.addActionListener(new ActionListener() {								//opens level 1.
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("level 1")){
					levelPanel.removeAll();
					frame.remove(levelPanel);
					level1();


				}
			}
		});
		
		level2.addActionListener(new ActionListener() {								//opens level 2.
			public void actionPerformed(ActionEvent e) {
				levelPanel.removeAll();
				frame.remove(levelPanel);
				level2();

			}
		});

		level3.addActionListener(new ActionListener() {								//opens level 3.
			public void actionPerformed(ActionEvent e) {
				levelPanel.removeAll();
				frame.remove(levelPanel);
				level3();
			}
		});

		backButton.addActionListener(new ActionListener() {							//goes back to the start menu.
			public void actionPerformed(ActionEvent e) {
				levelPanel.removeAll();
				frame.remove(levelPanel);
				startMenu();
			}
		});

	}


	private void finish() {															//the final screen that you see when you complete all three levels. 
		frame.remove(labelPanel);
		labelPanel.removeAll();

		frame.validate();
		frame.repaint();	

		GridBagConstraints gbcf = new GridBagConstraints();
		gbcf.gridwidth = GridBagConstraints.REMAINDER;
		finishLabel = new JLabel();
		finishLabel.setVisible(true);
		
		try {
			fnsh = ImageIO.read(new File("resources/finish.gif"));
			finishLabel.setIcon(new ImageIcon(fnsh));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		finishPanel.add(finishLabel, gbcf);
		frame.add(finishPanel);
		frame.validate();
		frame.repaint();
		
		JButton backToStart = new JButton ("Menu");
		finishPanel.add(backToStart, gbcf);
		
		frame.validate();
		frame.repaint();
		
		backToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				finishPanel.removeAll();
				frame.remove(finishPanel);
				startMenu();
				frame.validate();
				frame.repaint();
			}
		});

	}
	private void collision() {														//the screen that you see when you hit a wall.
		life--;
		frame.remove(labelPanel);
		collisionPanel.removeAll();

		frame.validate();
		frame.repaint();

		GridBagConstraints gbcc = new GridBagConstraints();

		JLabel tryAgain = new JLabel("You hit a wall. Try again.");
		tryAgain.setOpaque(true);
		tryAgain.setVisible(true);
		tryAgain.setLocation(20, 40);

		JLabel remainingLives = new JLabel("Remaining lives: " + life);
		remainingLives.setOpaque(true);
		remainingLives.setVisible(true);
		remainingLives.setLocation(20, 70);

		gbcc.gridy = 0;
		collisionPanel.add(tryAgain, gbcc);

		gbcc.gridy = 1;
		collisionPanel.add(remainingLives, gbcc);

		frame.add(collisionPanel);
		collisionPanel.validate();
		collisionPanel.repaint();
		frame.validate();
		frame.repaint();

		if(life <= 0) {																//if you don't have any lives left, shows game over screen.
			gameOver();
			frame.validate();
			frame.repaint();
		}
		else {																		//if you have more than 0 lives, you go back to last played level.
			gbcc.gridy = 2;
			collisionPanel.add(Box.createVerticalStrut(50), gbcc);

			JButton backButton = new JButton ("Back");
			gbcc.fill = GridBagConstraints.HORIZONTAL;
			gbcc.gridy = 3;
			collisionPanel.add(backButton, gbcc);

			frame.validate();
			frame.repaint();

			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					frame.remove(collisionPanel);
					frame.validate();
					frame.repaint();
					try {
						Robot robot = new Robot();
						robot.mouseMove(20, 400);
					} catch (AWTException e) {
						e.printStackTrace();
					}
					switch (lastLevel) {
					case 1:
						level1();
						break;
					case 2:
						level2();
						break;
					case 3:
						level3();	
						break;
					default:
						break;
					}	
				}
			});
		}
	}
	private void gameOver() {														//the screen that you see when you have no lives left and fail.

		GridBagConstraints gbcgo = new GridBagConstraints();

		frame.remove(collisionPanel);
		collisionPanel.removeAll();

		JLabel gameOver = new JLabel("Game Over");
		gbcgo.gridx = 0;
		gbcgo.gridy = 0;
		gbcgo.weighty = 10;
		gameoverPanel.add(gameOver, gbcgo);
		gameOver.setForeground(Color.RED);

		JLabel restartLabel = new JLabel("Press the Button Below to Restart the Game From Level 1.");
		gbcgo.gridx = 0;
		gbcgo.gridy = 1;
		gbcgo.weighty = 10;
		gameoverPanel.add(restartLabel, gbcgo);

		gbcgo.gridy = 2;
		gameoverPanel.add(Box.createVerticalStrut(50), gbcgo);

		JButton restartButton = new JButton ("Restart Game");
		gbcgo.fill = GridBagConstraints.HORIZONTAL;
		gbcgo.weighty = 0.5;
		gbcgo.gridx = 0;
		gbcgo.gridy = 3;
		gameoverPanel.add(restartButton, gbcgo);

		gbcgo.gridy = 4;
		gameoverPanel.add(Box.createVerticalStrut(10), gbcgo);

		JButton exitGame = new JButton ("Exit");
		gbcgo.fill = GridBagConstraints.HORIZONTAL;
		gbcgo.weighty = 1;
		gbcgo.gridx = 0;
		gbcgo.gridy = 5;
		gbcgo.weighty = 0.5;
		gameoverPanel.add(exitGame, gbcgo);

		frame.add(gameoverPanel);

		restartButton.addActionListener(new ActionListener() {						//goes back to the beginning of level 1.
			public void actionPerformed(ActionEvent e) {
				gameoverPanel.removeAll();
				frame.remove(gameoverPanel);
				life = 3;
				level1();
				frame.validate();
				frame.repaint();	
			}
		});

		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	

			}
		});

	}
	private void levelPass() {														//when you reach the green section, you'll see this screen
		frame.remove(labelPanel);
		labelPanel.removeAll();

		GridBagConstraints gbcp = new GridBagConstraints();

		frame.validate();
		frame.repaint();	

		JLabel passLabel = new JLabel("You passed the level " + lastLevel + "!");
		passLabel.setOpaque(true);
		passLabel.setVisible(true);
		passLabel.setForeground(Color.green);

		JLabel nextLvlLabel = new JLabel("Let's move to the level " + (lastLevel+1) +"!");
		nextLvlLabel.setOpaque(true);
		nextLvlLabel.setVisible(true);

		gbcp.gridx = 0;
		gbcp.gridy = 0;
		passPanel.add(passLabel, gbcp);

		gbcp.gridx = 0;
		gbcp.gridy = 1;
		passPanel.add(nextLvlLabel, gbcp);

		frame.add(passPanel);
		passPanel.validate();
		passPanel.repaint();
		frame.validate();
		frame.repaint();

		lastLevel++;

		JButton nextLevel = new JButton ("Next Level");

		gbcp.gridx = 0;
		gbcp.gridy = 2;
		passPanel.add(nextLevel, gbcp);

		frame.validate();
		frame.repaint();

		life = 3;

		nextLevel.addActionListener(new ActionListener() {							//carries you on to the next level
			public void actionPerformed(ActionEvent evt) {
				
				passPanel.removeAll();
				frame.remove(passPanel);

				switch (lastLevel) {
				case 1:
					level1();
					break;
				case 2:
					System.out.println("lvl2");
					level2();
					break;
				case 3:
					level3();	
					break;
				case 4:
					finish();
					break;
				default:
					break;
				}	
			}
		});
	}



	
	private void level1() {

		lastLevel = 1;

		System.out.println("Life: "+life);
		JLabel remainingLives = new JLabel("Lives left: "+life);
		remainingLives.setLocation((frame.getWidth()/2)-(remainingLives.getWidth()/2), 20);
		remainingLives.setOpaque(true);
		remainingLives.setVisible(true);

		JButton backToStart = new JButton ("Menu");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		label = new JLabel();
		label.setVisible(true);

		labelPanel.removeAll();

		try {																		//gets the image file
			lvl1 = ImageIO.read(new File("resources/level1.jpg"));
			label.setIcon(new ImageIcon(lvl1));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}

		label.addMouseMotionListener(this);											//to prevent exception when mouse leaves the bounds 

		try {
			Robot robot = new Robot();
			robot.mouseMove(100, 400);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}

		frame.remove(levelPanel);
		labelPanel.add(label, gbc);
		labelPanel.add(remainingLives, gbc);
		labelPanel.add(backToStart, gbc);
		frame.add(labelPanel);
		frame.validate();
		frame.repaint();

		label.addMouseMotionListener(new MouseAdapter() {							//where the magic happens

			@Override
			public void mouseMoved(MouseEvent e) {
				frame.validate();
				frame.repaint();
				int packedInt = lvl1.getRGB(e.getX(), e.getY());					//gets rgb of the image
				Color color = new Color(packedInt, true);
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				System.out.println(mouseX +", "+ mouseY);							//these are for me to check
				System.out.println(red + ", " + green + ", " + blue + "\n");		//if these are working

				if(red <= 10 && green <= 10 && blue <= 10) {						//if wall (black) call collision
					collision();
				}
				else if(red <= 10 && green >= 250 && blue <= 10) {					//if end (green) call levelPass
					levelPass();
				}
			}
		});

		backToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				labelPanel.removeAll();
				frame.remove(labelPanel);
				life = 3;
				startMenu();
				frame.validate();
				frame.repaint();
			}
		});

		frame.validate();
		frame.repaint();

	}
	private void level2() {

		lastLevel = 2;

		
		System.out.println("Life: "+life);
		JLabel remainingLives = new JLabel("Lives left: "+life);
		remainingLives.setLocation((frame.getWidth()/2)-(remainingLives.getWidth()/2), 20);
		remainingLives.setOpaque(true);
		remainingLives.setVisible(true);

		JButton backToStart = new JButton ("Menu");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		label = new JLabel();
		label.setVisible(true);

		labelPanel.removeAll();

		try {
			lvl2 = ImageIO.read(new File("resources/level2.jpg"));
			label.setIcon(new ImageIcon(lvl2));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}

		label.addMouseMotionListener(this);

		try {
			Robot robot = new Robot();
			robot.mouseMove(100, 400);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}

		frame.remove(levelPanel);
		labelPanel.add(label, gbc);
		labelPanel.add(remainingLives, gbc);
		labelPanel.add(backToStart, gbc);
		frame.add(labelPanel);
		frame.validate();
		frame.repaint();

		label.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				frame.validate();
				frame.repaint();
				int packedInt = lvl2.getRGB(e.getX(), e.getY());
				Color color = new Color(packedInt, true);
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				System.out.println(mouseX +", "+ mouseY);
				System.out.println(red + ", " + green + ", " + blue + "\n");

				if(red <= 10 && green <= 10 && blue <= 10) {
					collision();
				}
				else if(red <= 10 && green >= 250 && blue <= 10) {
					levelPass();
				}
			}
		});

		backToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				labelPanel.removeAll();
				frame.remove(labelPanel);
				life = 3;
				startMenu();
				frame.validate();
				frame.repaint();
			}
		});

		frame.validate();
		frame.repaint();

	}
	private void level3() {
		
		lastLevel = 3;

		System.out.println("Life: "+life);
		JLabel remainingLives = new JLabel("Lives left: "+life);
		remainingLives.setLocation((frame.getWidth()/2)-(remainingLives.getWidth()/2), 20);
		remainingLives.setOpaque(true);
		remainingLives.setVisible(true);

		JButton backToStart = new JButton ("Menu");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		label = new JLabel();
		label.setVisible(true);

		labelPanel.removeAll();

		try {
			lvl3 = ImageIO.read(new File("resources/level3.jpg"));
			label.setIcon(new ImageIcon(lvl3));
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}

		label.addMouseMotionListener(this);

		try {
			Robot robot = new Robot();
			robot.mouseMove(100, 400);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}

		frame.remove(levelPanel);
		labelPanel.add(label, gbc);
		labelPanel.add(remainingLives, gbc);
		labelPanel.add(backToStart, gbc);
		frame.add(labelPanel);
		frame.validate();
		frame.repaint();

		label.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				frame.validate();
				frame.repaint();
				int packedInt = lvl3.getRGB(e.getX(), e.getY());
				Color color = new Color(packedInt, true);
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				System.out.println(mouseX +", "+ mouseY);
				System.out.println(red + ", " + green + ", " + blue + "\n");

				if(red <= 10 && green <= 10 && blue <= 10) {
					collision();
				}
				else if(red <= 10 && green >= 250 && blue <= 10) {
					finish();
				}
			}
		});

		backToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				labelPanel.removeAll();
				frame.remove(labelPanel);
				life = 3;
				startMenu();
				frame.validate();
				frame.repaint();
			}
		});

		frame.validate();
		frame.repaint();

	}


	public void mouseMoved(MouseEvent pos) {
		mouseX = pos.getX();
		mouseY = pos.getY();
	}

	
																					//I added these two because it gave me errors unless I add them.
	
	public void mouseClicked(MouseEvent pos) {

	}

	public void mouseDragged(MouseEvent e) {


	}


}


