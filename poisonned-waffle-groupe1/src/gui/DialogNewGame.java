package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogNewGame extends JDialog{

	public DialogNewGame(JFrame parent, String title, boolean modal){
	    
	    super(parent, title, modal);
	    
	    this.setSize(200, 80);
	    
	    this.setLocationRelativeTo(null);
	    
	    this.setResizable(false);
	    
	    this.setVisible(true);
	    
	}
}
