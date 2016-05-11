package gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogNewGame extends JDialog{
	
	Window window;

	public DialogNewGame(JFrame parent, String title, boolean modal, Window window){
	    
	    super(parent, title, modal);
	    
	    this.window = window;
	    this.setSize(200, 80);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.initComponent();
	    this.setVisible(true);
	   
	}
	
	private void initComponent(){
		JPanel content = new JPanel();
		
		JPanel panPlayer1 = new JPanel();
		
		panPlayer1.setBorder(BorderFactory.createTitledBorder("Joueur 1"));
		JLabel labelTypePlayer1 = new JLabel("Type du joueur1");
		
		panPlayer1.add(labelTypePlayer1);
		
		content.add(panPlayer1);
		
		this.getContentPane().add(content, BorderLayout.WEST);
	}
}
