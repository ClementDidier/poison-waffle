package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {

		private static final long serialVersionUID = 1L;
    
	    private JPanel leftPanel;

	    private static final int GAP = 5;
	    private GridBagConstraints gbc;

	    public HeaderPanel() {

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
