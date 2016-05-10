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

import interfaces.GameInterface;

public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameInterface game;
	
	    public HeaderPanel(GameInterface game) {
			this.game = game;
	    }
	    
	    
	    public void paintComponent(Graphics g) {
	        Graphics2D dessin = (Graphics2D) g;

	       

	    }
	    



}