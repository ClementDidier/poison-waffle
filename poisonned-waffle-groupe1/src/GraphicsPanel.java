import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class GraphicsPanel extends JComponent
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		
		
		this.clear(batch);
		
		/* Something to draw */
	}
	
	protected void clear(Graphics2D batch)
	{
		batch.setPaint(Color.BLACK);
		batch.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
