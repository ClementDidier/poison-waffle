package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.Format;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaces.PlayerInterface;
import utilities.JNumberTextField;

public class DialogNewGame extends JDialog{
	
	Window window;

	public DialogNewGame(JFrame parent, String title, boolean modal, Window window){
	    
	    super(parent, title, modal);
	    
	    this.window = window;
	    this.setSize(600, 300);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.initComponent();
	    this.setVisible(true);
	   
	}
	
	private void initComponent(){
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(3, 2));
		//===========Joueur 1 =========//
		JPanel panPlayer1 = new JPanel();
		panPlayer1.setLayout(new GridLayout(2, 2));
		
		panPlayer1.setBorder(BorderFactory.createTitledBorder("Joueur 1"));
		
		JLabel labelTypePlayer1 = new JLabel("Type du joueur1");
		
		Hashtable<String, Class> listTypePlayer = this.window.getListTypePlayer();
		Set<String> listLabelTypePlayer = listTypePlayer.keySet();
		
		JComboBox<String> comboBoxPlayer1 = new JComboBox<>();
		for(String s:listLabelTypePlayer){
			comboBoxPlayer1.addItem(s);
		}
		
		JLabel nameTypePlayer1 = new JLabel("Nom du joueur 1");
		JTextField textFieldPlayer1 = new JTextField();
		
		panPlayer1.add(labelTypePlayer1);
		panPlayer1.add(comboBoxPlayer1);
		panPlayer1.add(nameTypePlayer1);
		panPlayer1.add(textFieldPlayer1);
		
		//========= Joueur 2 =========//
		JPanel panPlayer2 = new JPanel();
		panPlayer2.setLayout(new GridLayout(2, 2));
		panPlayer2.setBorder(BorderFactory.createTitledBorder("Joueur 2"));
		JLabel labelTypePlayer2 = new JLabel("Type du joueur2");
		
		JComboBox<String> comboBoxPlayer2 = new JComboBox<>();
		for(String s:listLabelTypePlayer){
			comboBoxPlayer2.addItem(s);
		}
		
		JLabel nameTypePlayer2 = new JLabel("Nom du joueur 2");
		JTextField textFieldPlayer2 = new JTextField();
		
		panPlayer2.add(labelTypePlayer2);
		panPlayer2.add(comboBoxPlayer2);
		panPlayer2.add(nameTypePlayer2);
		panPlayer2.add(textFieldPlayer2);
		
		content.add(panPlayer1);
		content.add(panPlayer2);
		
		
		//Taille de la carte
		JPanel panelBoardSize  = new JPanel();
		panelBoardSize.setBorder(BorderFactory.createTitledBorder("Taille de la carte"));
		JNumberTextField numberTextFieldWidth = new JNumberTextField();
		
		JLabel labelWidth = new JLabel("Largeur: ");
		
		panelBoardSize.add(labelWidth);
		panelBoardSize.add(numberTextFieldWidth);
		content.add(panelBoardSize, BorderLayout.SOUTH);
		
		this.getContentPane().add(content, BorderLayout.WEST);
		
		
	}
}
