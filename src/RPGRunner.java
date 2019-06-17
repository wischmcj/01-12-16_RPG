import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class RPGRunner extends JPanel {

	private static final int NUMBER_OF_PLAYERS = 10;
	
	private static final Image ARCHER = 
			Toolkit.getDefaultToolkit().getImage("ARCHER.png");
	private static final Image WARRIOR = 
			Toolkit.getDefaultToolkit().getImage("WARRIOR.jpg");
	
	public JFrame window = new JFrame("Role Playing Game");
	
	public ArrayList<RPGCharacter> players = new ArrayList<RPGCharacter>();
	
	public Timer tm = new Timer(20, new PlayGame());
	
	public RPGRunner() {
		
		setBackground(new Color(255, 255, 255));
		
		window.setBounds(100,100, RPGCharacter.MAX_SIZE,RPGCharacter.MAX_SIZE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(this);
		window.getContentPane().setLayout(null);
		window.setAlwaysOnTop(true);
		window.setVisible(true);
		
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(-1, -1, 
				window.getContentPane().getWidth() + 2,
				window.getContentPane().getHeight() - 50);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(6, 643, 117, 29);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				players.clear();
				setUpPlayers(NUMBER_OF_PLAYERS, NUMBER_OF_PLAYERS);
				tm.start();
			}
		});
		window.getContentPane().add(btnNewGame);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(577, 643, 117, 29);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		window.getContentPane().add(btnQuit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrintWriter pw = null;
				try {
					tm.stop();
					pw = new PrintWriter(new File("gamestate.txt"));
					for(RPGCharacter p : players) {p.save(pw);}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(window, "Error while saving");
				} finally {
					JOptionPane.showMessageDialog(window, "Data saved");
					tm.start();
					pw.close();
				}
				
			}
		});
		btnSave.setBounds(448, 643, 117, 29);
		window.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tm.stop();
				players.clear();
				Scanner fin = null;
				try {
					fin = new Scanner(new File("gamestate.txt"));
					while(fin.hasNextLine()) {
						RPGType rpgtype = RPGType.valueOf(fin.nextLine());
						if (rpgtype == RPGType.ARCHER) {
							Archer forAdd = new Archer("Archer");
							forAdd.load(fin);
							players.add(forAdd);
						}
						if (rpgtype == RPGType.WARRIOR) {
							Warrior forAdd = new Warrior("Warrior");
							forAdd.load(fin);
							players.add(forAdd);
						}
					}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(window, "Error while loading file: " + ex.getMessage());
				} finally {
					if(fin != null) fin.close();
					tm.start();
				}	
			}
		});
		btnLoad.setBounds(319, 643, 117, 29);
		window.getContentPane().add(btnLoad);
		
		setUpPlayers(NUMBER_OF_PLAYERS, NUMBER_OF_PLAYERS);
		
		tm.start();

	}
	
	private void setUpPlayers(int archers, int warriors) {
		
		for (int i = 0; i < archers; i++) {
			players.add(new Archer("Archer"));
		}
		for (int i = 0; i < warriors; i++) {
			players.add(new Warrior("Warrior"));
		}
		
		for (RPGCharacter player : players) {
			player.run();
		}
		
	}

	public class PlayGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) { // Looks to timer?
			for(RPGCharacter player : players) player.move();
			
			for(RPGCharacter player : players) {
				for(RPGCharacter enemy : players) {
					if (player.getRange(enemy) < 100 && !enemy.equals(player)) {
						player.fight(enemy);
					}
				}
			}
			
			for(int p = players.size() - 1; p >= 0; p--) {
				if (players.get(p).getLife() <= 0) players.remove(players.get(p));
			}
			
			window.repaint();
		}

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(RPGCharacter player : players) {
			if(player instanceof Archer) g.drawImage(ARCHER, 
					(int) player.getXPos(), (int) player.getYPos(), null);
			if(player instanceof Warrior) g.drawImage(WARRIOR, 
					(int) player.getXPos(), (int) player.getYPos(), null);
		}
	}
	
	public static void main(String[] args) {
		new RPGRunner(); 
	}
}
