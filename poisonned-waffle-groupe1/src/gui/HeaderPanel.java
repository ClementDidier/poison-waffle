package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HeaderPanel extends JComponent {

	
	    public HeaderPanel() {
	


	    
	    }
	    
	    
	    public void paintComponent(Graphics g) {
	        Graphics2D dessin = (Graphics2D) g;

	        // On reccupere quelques infos
	        int width = getSize().width;
	        int height = getSize().height;
	        int x = 0;
	        int y = 0;
	        Point center = new Point(width/2, height/2);

	        String message;
	
	        dessin.drawString("test", 10, center.y);

	    }
	    



}