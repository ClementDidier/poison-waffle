package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	        r = new Random();
	        setOpaque(true);
	        setBackground(getRandomColor());
	        setLayout(new BorderLayout(GAP, GAP));      
	        JPanel footerPanel = getPanel();
	        footerPanel.setLayout(new GridBagLayout());
	        gbc = new GridBagConstraints();
	        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	        gbc.insets = new Insets(GAP, GAP, GAP, GAP);
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.weightx = 0.8;
	        gbc.weighty = 1.0;
	        leftPanel = getPanel();
	        footerPanel.add(leftPanel, gbc);
	        gbc.gridx = 1;
	        gbc.weightx = 0.2;
	        JLabel rightLabel = new JLabel("Right Label", JLabel.CENTER);
	        footerPanel.add(rightLabel);

	        JLabel centerLabel = new JLabel("Main Panel", JLabel.CENTER);
	        add(centerLabel, BorderLayout.CENTER);
	        add(footerPanel, BorderLayout.PAGE_END);
	    }

	    private JPanel getPanel() {
	        JPanel panel = new JPanel();
	        panel.setOpaque(true);
	        panel.setBackground(getRandomColor());

	        return panel;
	    }

	    private Color getRandomColor() {
	        return new Color(r.nextFloat(), r.nextFloat(),
	                            r.nextFloat(), r.nextFloat());
	    }

	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(300, 200);
	    }
    


}
