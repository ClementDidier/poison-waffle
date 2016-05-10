package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.BoardInterface;
import interfaces.GameInterface;
import utilities.UndoRedoManager;

public class Window implements Runnable
{
	private static final String DEFAULT_NAME = "Poisonned Waffle";
	private JFrame frame;
	private GraphicsPanel graphicsPanel;
	private JMenuBar menuBar;
	private GameInterface game;
	private HeaderPanel headerPanel;
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
		
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0, 1, GAP, GAP));
        contentPane.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        contentPane.add(new GraphicsPanel(this.game));
        contentPane.add(new HeaderPanel());

        
		this.frame.setContentPane(contentPane);
	}
	
	@Override
	public void run() {
		this.frame.setVisible(true);
	}
	
}