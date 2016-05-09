import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window implements Runnable
{
	private static final String DEFAULT_NAME = "Poisonned Waffle";
	private JFrame frame;
	private GraphicsPanel panel;
	private JMenuBar menuBar;
	
	public Window(int width, int height)
	{
		this.frame = new JFrame();
		this.frame.setName(DEFAULT_NAME);
		this.frame.setSize(width, height);
		this.menuBar = new JMenuBar();
		
		JMenu redoUndo = new JMenu("Historique");
		JMenu loadSave = new JMenu("Partie");
		
		JMenuItem redo = new JMenuItem("Refaire");
		JMenuItem undo = new JMenuItem("Annuler");
		
		redoUndo.add(redo);
		redoUndo.add(undo);
		
		JMenuItem save = new JMenuItem("Sauvegarder");
		JMenuItem load = new JMenuItem("Charger");
		JMenuItem newGame = new JMenuItem("Nouvelle Partie");
		
		loadSave.add(newGame);
		loadSave.add(load);
		loadSave.add(save);
		
		this.menuBar.add(redoUndo);
		this.menuBar.add(loadSave);
		
		this.frame.setJMenuBar(this.menuBar);
		
		
		
		this.panel = new GraphicsPanel();
		
		this.frame.setContentPane(this.panel);
	}
	
	@Override
	public void run() {
		this.frame.setVisible(true);
	}
	
}
