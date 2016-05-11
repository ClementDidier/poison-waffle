package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entities.PlayerHard;
import entities.PlayerMedium;
import entities.PlayerMouse;
import entities.PlayerRandom;
import interfaces.GameInterface;
import interfaces.PlayerInterface;
import program.Game;

public class Window implements Runnable, ActionListener {
	private static final String	DEFAULT_NAME	= "Poisonned Waffle";
	private JFrame				frame;
	private JMenuBar			menuBar;
	private GameInterface		game;
	private GraphicsPanel		gameGraphics;
	private JLabel				turnLabel;
	private JLabel				currentPlayerLabel;

	public Window(int width, int height) {
		this.frame = new JFrame();
		this.frame.setName(DEFAULT_NAME);
		this.frame.setSize(width, height);
		frame.getContentPane().setLayout(new BorderLayout());
		this.menuBar = new JMenuBar();

		PlayerInterface p1 = new PlayerMouse("Humain", new Color(50, 50, 180, 80));
		PlayerInterface p2 = new PlayerRandom("IA", new Color(180, 50, 50, 80));
		this.game = new Game(p1, p2);

		JMenu redoUndo = new JMenu("Historique");
		JMenuItem redo = new JMenuItem("Refaire");
		JMenuItem undo = new JMenuItem("Annuler");
		redoUndo.add(redo);
		redoUndo.add(undo);

		JMenu loadSave = new JMenu("Partie");
		JMenuItem save = new JMenuItem("Sauvegarder");
		JMenuItem load = new JMenuItem("Charger");
		JMenuItem newGame = new JMenuItem("Nouvelle Partie");
		loadSave.add(newGame);
		loadSave.add(load);
		loadSave.add(save);

		this.menuBar.add(loadSave);
		this.menuBar.add(redoUndo);
		this.frame.setJMenuBar(this.menuBar);

		redo.addActionListener(new ButtonMenuListener(redo, this));
		undo.addActionListener(new ButtonMenuListener(undo, this));
		newGame.addActionListener(new ButtonMenuListener(newGame, this));
		load.addActionListener(new ButtonMenuListener(load, this));
		save.addActionListener(new ButtonMenuListener(save, this));

		// nombre de coups de chaque joueur
		// le tour de qui ?
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(600, 110));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		headerPanel.setLayout(new GridLayout(1, 0));

		ImageIcon icon = new ImageIcon("./resources/logo.png");
		JLabel logo = new JLabel(icon);
		headerPanel.add(logo);

		
		
		 JPanel headerRightPanel = new JPanel();
	     headerRightPanel.setBackground(Color.WHITE);
	     headerRightPanel.setLayout(new GridLayout(0, 1));
	     this.turnLabel = new JLabel("Nombre de coups: " + game.getTurn());
	     turnLabel.setBorder(BorderFactory.createEmptyBorder(30, 90, 0, 0));
	     this.currentPlayerLabel = new JLabel("Tour du joueur: " + game.getCurrentPlayer().getName());
	     currentPlayerLabel.setBorder(BorderFactory.createEmptyBorder(0, 88, 30, 0));
	     headerRightPanel.add(turnLabel, BorderLayout.CENTER); 
	     headerRightPanel.add(currentPlayerLabel, BorderLayout.CENTER); 
	        
	     headerPanel.add(headerRightPanel); 
	

		// la gaufre
		this.gameGraphics = new GraphicsPanel(this.game);

		frame.getContentPane().add(headerPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(this.gameGraphics, BorderLayout.CENTER);

		
	}

	@Override
	public void run() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(605, 568);
		this.frame.setVisible(true);
		this.frame.setResizable(false);

		this.game.addListener(this);
		this.game.doTurn();
	}
	
	public GameInterface getGame() {
		return this.game;
	}

	public void notifyVictory(String player) {
		JOptionPane.showMessageDialog(frame, player + " a gagné.", "Partie terminée", 1);
	}

	private void updateHeader() {
		this.turnLabel.setText("Nombre de coups : " + this.game.getTurn());
		this.currentPlayerLabel.setText("Tour du joueur : " + this.game.getCurrentPlayer().getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.updateHeader();
		this.gameGraphics.repaint();

		switch (e.getID()) {
			case Game.EVENT_TURN_ENDED:
				this.game.doTurn();
				break;
			case Game.EVENT_VICTORY:
				this.notifyVictory(e.getActionCommand());
				break;
			case Game.EVENT_PLAYER_TURN_START:
				this.gameGraphics.readyPlayerTurn((Color) e.getSource());
				break;
			case Game.EVENT_PLAYER_TURN_END:
				this.gameGraphics.endPlayerTurn();
				break;
			default:
				break;
		}
	}

	public void setGame(GameInterface g) {
		this.game.removeListener(this);
		this.game = g;
		this.gameGraphics.endPlayerTurn();
		this.gameGraphics.setGame(g);
		
		this.updateHeader();
		this.gameGraphics.repaint();
		
		this.game.addListener(this);
		this.game.doTurn();
	}
	
	public void askPlayersForNewGame(){
		DialogNewGame dialogNewGame = new DialogNewGame(this.frame, "Nouvelle Partie", true);
	}
	
	public Hashtable<String, Class> getListeTypeJoueur(){
		Hashtable<String, Class> list = new Hashtable<>();
		list.put("Joueur humain", PlayerMouse.class);
		list.put("IA facile", PlayerRandom.class);
		list.put("IA Moyenne", PlayerMedium.class);
		list.put("IA Difficile", PlayerHard.class);
		
		return list;
	}
}
