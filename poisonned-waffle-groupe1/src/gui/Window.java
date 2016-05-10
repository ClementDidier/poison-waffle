package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		JMenu loadSave = new JMenu("Partie");

		JMenuItem redo = new JMenuItem("Refaire");
		JMenuItem undo = new JMenuItem("Annuler");

		redo.addActionListener(new ButtonMenuListener(redo, game));
		undo.addActionListener(new ButtonMenuListener(undo, game));

		redoUndo.add(redo);
		redoUndo.add(undo);

		JMenuItem save = new JMenuItem("Sauvegarder");
		JMenuItem load = new JMenuItem("Charger");
		JMenuItem newGame = new JMenuItem("Nouvelle Partie");

		loadSave.add(newGame);
		loadSave.add(load);
		loadSave.add(save);

		this.menuBar.add(loadSave);
		this.menuBar.add(redoUndo);

		this.frame.setJMenuBar(this.menuBar);

		// nombre de coups de chaque joueur
		// le tour de qui ?
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(600, 150));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setLayout(new GridLayout(1, 0));

		ImageIcon icon = new ImageIcon("./resources/logo.png");
		JLabel logo = new JLabel(icon);
		headerPanel.add(logo);

		this.turnLabel = new JLabel("Nombre de coups : " + game.getTurn());
		this.currentPlayerLabel = new JLabel("Tour du joueur : " + game.getCurrentPlayer().getName());
		headerPanel.add(turnLabel);
		headerPanel.add(currentPlayerLabel);

		// la gaufre
		this.gameGraphics = new GraphicsPanel(this.game);

		frame.getContentPane().add(headerPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(this.gameGraphics, BorderLayout.CENTER);

		/*
		 * // Ajout des elements au panel principal puis affichage contentPanel.add(headerPanel,
		 * BorderLayout.NORTH); contentPanel.add(gameGraphics, BorderLayout.CENTER);
		 * 
		 * frame.setContentPane(contentPanel);
		 */
	}

	@Override
	public void run() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(605, 608);
		this.frame.setVisible(true);
		this.frame.setResizable(false);

		this.game.addListener(this);
		this.game.doTurn();
	}

	public void notifyVictory(String player) {
		JOptionPane.showMessageDialog(frame, player + " a gagné.", "Partie terminée", 1);
	}

	private void updateHeader() {
		this.turnLabel.setText("Nombre de coups : " + this.game.getTurn());
		this.currentPlayerLabel.setText("Tour du joueur : " +  this.game.getCurrentPlayer().getName());
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
}
