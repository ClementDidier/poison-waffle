@@ -1,5 +1,6 @@
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
@@ -22,19 +23,19 @@ public class HeaderPanel extends JPanel {
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