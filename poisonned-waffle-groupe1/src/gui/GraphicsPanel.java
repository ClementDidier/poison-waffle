package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import entities.Cell;
import entities.PlayerMouse;
import exceptions.OutOfWaffleException;
import interfaces.GameInterface;
import utilities.Vector2;

public class GraphicsPanel extends JComponent implements MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	private GameInterface game;
	private Vector2 offset;
	private Rectangle highlightRect;
	private int oldX, oldY;
	
	public GraphicsPanel(GameInterface game) {
		this.game = game;
		this.offset = new Vector2();
		this.game.setGraphicsPanel(this);
		this.oldX = 0;
		this.oldY = 0;
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		this.clear(batch);
		BufferedImage cell = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int x = 0; x<this.game.getBoard().getWidth(); x++)
		{
			for(int y = 0; y<this.game.getBoard().getHeight(); y++){
				Cell currentCell;
				try {
					currentCell = this.game.getBoard().getCell(x, y);
				
					if(currentCell.getApparence() != null) {
						BufferedImage currentApparence = currentCell.getApparence();
						int w = Cell.WIDTH;
						int h = Cell.HEIGHT;
						batch.drawImage(currentApparence, this.offset.getX()+x*w, this.offset.getY()+y*h, w, h, null);
					}
				} catch (OutOfWaffleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		}
		
		if(this.highlightRect != null)
		{
			batch.setPaint(new Color(50, 90, 161, 125));
			batch.fillRect(this.highlightRect.x, this.highlightRect.y, this.highlightRect.width, this.highlightRect.height);
		}
	}
	
	protected void clear(Graphics2D batch)
	{
		batch.setPaint(Color.WHITE);
		batch.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void requestListener(PlayerMouse p) {
		this.addMouseListener(p);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int posX = e.getX();
		int posY = e.getY();
		
		int xCase = (int) posX/Cell.WIDTH;
		int yCase = (int) posY/Cell.HEIGHT;
		
		if(this.game.getBoard().isInBounds(xCase, yCase))
		{
			try {
				if((xCase != this.oldX || yCase != this.oldY) && this.game.getBoard().getCell(xCase, yCase) != Cell.POISONNED)
				{
					this.oldX = xCase;
					this.oldY = yCase;
					
					this.highlightRect = new Rectangle(
							xCase * Cell.WIDTH,
							yCase * Cell.HEIGHT,
							(this.game.getBoard().getWidth() - xCase) * Cell.WIDTH,
							(this.game.getBoard().getHeight() - yCase) * Cell.HEIGHT);
					
					this.repaint();
				}
			} catch (OutOfWaffleException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
}
