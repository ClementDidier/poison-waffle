package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	    private GridBagConstraints gbc;

	    public HeaderPanel() {
	    	
	    	//setBackground(Color.);
		  	JLabel logo = new JLabel(new ImageIcon("../ressources/logo.png"));
			this.add(logo, BorderLayout.EAST);

	    }
	    

	    private JPanel getPanel() {
	        JPanel panel = new JPanel();
	        return panel;
	    }

	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(300, 200);
	    }
    


}