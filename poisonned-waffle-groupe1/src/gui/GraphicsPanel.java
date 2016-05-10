package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import entities.Cell;
import exceptions.OutOfWaffleException;
import interfaces.GameInterface;
import utilities.Vector2;

public class GraphicsPanel extends JComponent implements MouseMotionListener, MouseListener {
	private static final long	serialVersionUID	= 1L;
	private GameInterface		game;
	private Vector2				offset;
	private Vector2				cursorPos;
	private Color highlightColor;

	public GraphicsPanel(GameInterface game) {
		this.game = game;
		this.offset = new Vector2();
		this.cursorPos = null;
		this.highlightColor = null;
	}
	
	public void setGame(GameInterface g) {
		this.game = g;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		this.clear(batch);

		for (int x = 0; x < this.game.getBoard().getWidth(); x++) {
			for (int y = 0; y < this.game.getBoard().getHeight(); y++) {
				Cell currentCell;
				try {
					currentCell = this.game.getBoard().getCell(x, y);

					if (currentCell.getApparence() != null) {
						BufferedImage currentApparence = currentCell.getApparence();
						int w = Cell.WIDTH;
						int h = Cell.HEIGHT;
						batch.drawImage(currentApparence, this.offset.getX() + x * w, this.offset.getY() + y * h, w, h,
								null);

						if (this.cursorPos != null && x >= this.cursorPos.getX() && y >= this.cursorPos.getY()) {
							batch.setPaint(highlightColor);
							batch.fillRect(this.offset.getX() + x * w, this.offset.getY() + y * h, w, h);
						}
					}
				}
				catch (OutOfWaffleException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readyPlayerTurn(Color color)
	{
		this.cursorPos = null;
		this.highlightColor = color;
		this.repaint();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	public void endPlayerTurn() {
		this.removeMouseMotionListener(this);
		this.removeMouseListener(this);
		this.cursorPos = null;
		this.repaint();
	}

	protected void clear(Graphics2D batch) {
		batch.setPaint(Color.WHITE);
		batch.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int posX = e.getX();
		int posY = e.getY();

		int xCase = (int) posX / Cell.WIDTH;
		int yCase = (int) posY / Cell.HEIGHT;
		Vector2 v = new Vector2(xCase, yCase);

		try {
			if (this.game.getBoard().isInBounds(v) && this.game.getBoard().getCell(v) != Cell.POISONNED) {
				if (v == null || v != this.cursorPos) {
					this.cursorPos = v;
					this.repaint();
				}
			}
		}
		catch (OutOfWaffleException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int posX = e.getX();
		int posY = e.getY();

		int xCase = (int) posX / Cell.WIDTH;
		int yCase = (int) posY / Cell.HEIGHT;

		this.game.clickEvent(xCase, yCase);
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
