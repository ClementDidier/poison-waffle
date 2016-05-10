package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import interfaces.GameInterface;
import interfaces.PlayerInterface;

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
		frame.getContentPane().setLayout(new BorderLayout());
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
	
    	// nombre de coups de chaque joueur 
        // le tour de qui ?
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(600, 150));
    	headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new GridLayout(1, 0));
        
        ImageIcon icon = new ImageIcon("./resources/logo.png");
	  	JLabel logo = new JLabel(icon);
        headerPanel.add(logo);
        
        JLabel turn = new JLabel("Nombre de coups : " + game.getTurn());
        JLabel current = new JLabel("Tour du joueur : " + game.getCurrentPlayer().getName());
        headerPanel.add(turn); 
        headerPanel.add(current); 
        //headerPanel.add(new HeaderPanel(this.game)); 
        
        // la gaufre
        GraphicsPanel gameGraphics = new GraphicsPanel(this.game);
   
        frame.getContentPane().add(headerPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(gameGraphics, BorderLayout.CENTER);
   
        
        /*
        // Ajout des elements au panel principal puis affichage
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(gameGraphics, BorderLayout.CENTER);
        
    	frame.setContentPane(contentPanel);*/
	}
	
	@Override
	public void run() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(605, 608);
		this.frame.setVisible(true);
		this.game.setWindow(this);
		this.frame.setResizable(false);
	}
	
	public void notifyVictory(PlayerInterface player){
		JOptionPane.showMessageDialog(frame, player.getName() +" a gagné.","Partie terminée", 1);
	}
	
}