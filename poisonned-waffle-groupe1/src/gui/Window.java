package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import interfaces.GameInterface;

public class Window implements Runnable
{
	private static final String DEFAULT_NAME = "Poisonned Waffle";
	private JFrame frame;
	private JMenuBar menuBar;
	private GameInterface game;
	private static final int GAP = 5;	
	
	public Window(int width, int height, GameInterface game)
	{
		this.frame = new JFrame();
		this.frame.setName(DEFAULT_NAME);
		this.frame.setSize(width, height);
		this.menuBar = new JMenuBar();
		this.game = game;
		
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
		
		JPanel contentPanel = new JPanel(new GridLayout(2,1));
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// Configurations Panel Header
        JPanel headerPanel = new JPanel();
    	
    	headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerPanel.setLayout(new GridLayout(0, 1, GAP, GAP));
        
        ImageIcon icon = new ImageIcon("./resources/logo.png");
	  	JLabel logo = new JLabel(icon);
        headerPanel.add(logo);
    	
    	JLabel label = new JLabel("Coucou");
        headerPanel.add(label);
    
        // Configurations Panel Jeu
        JPanel gamePanel = new JPanel();
        GraphicsPanel gameGraphics = new GraphicsPanel(this.game);
        //gamePanel.setPreferredSize(new Dimension(640, 480));
        //gamePanel.add(gameGraphics);
        
        // Ajout des elements au panel principal puis affichage
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(gameGraphics, BorderLayout.SOUTH);
        
    	frame.setContentPane(contentPanel);
	}
	
	@Override
	public void run() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // this.frame.pack();
		this.frame.setVisible(true);
	}
	
}