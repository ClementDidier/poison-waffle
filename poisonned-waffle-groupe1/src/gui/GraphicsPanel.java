package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import entities.Cell;
import exceptions.OutOfWaffleException;
import interfaces.GameInterface;
import utilities.Vector2;

public class GraphicsPanel extends JComponent
{
	private static final long serialVersionUID = 1L;
	private GameInterface game;
	private Vector2 offset;
	
	public GraphicsPanel(GameInterface game) {
		this.game = game;
		this.offset = new Vector2();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		this.clear(batch);
		BufferedImage cell = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int x = 0; x<this.game.getBoard().getSize().getWidth(); x++)
		{
			for(int y = 0; y<this.game.getBoard().getSize().getHeight(); y++){
				Cell currentCell;
				try {
					currentCell = this.game.getBoard().getCell(x, y);
				
					if(!currentCell.equals(Cell.EATEN)) {
						BufferedImage currentApparence = currentCell.getApparence();
						batch.drawImage(currentApparence, this.offset.getX(), this.offset.getY(), currentApparence.getWidth(), currentApparence.getHeight(), null);
					}
				} catch (OutOfWaffleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		}
		/* Something to draw */
	}
	
	protected void clear(Graphics2D batch)
	{
		batch.setPaint(Color.BLACK);
		batch.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
