package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HeaderPanel extends JComponent {

	
	    public HeaderPanel() {
	
			// Configurations Panel Header
	        JPanel headerPanel = new JPanel();
	        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
	        headerPanel.setLayout(new GridLayout(1, 0));
	        
	        ImageIcon icon = new ImageIcon("./resources/logo.png");
		  	JLabel logo = new JLabel(icon);
	        headerPanel.add(logo);
	    	setSize(getWidth(), getHeight());
	    	this.setBackground(Color.RED);


	    }
	    



}