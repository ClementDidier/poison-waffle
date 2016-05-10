package entities;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import exceptions.OutOfWaffleException;
import utilities.Vector2;

public class PlayerMouse extends Player implements MouseListener {

	private boolean clickEventRaised;
	private Vector2 clickLocation;
	
	public PlayerMouse(String name) {
		super(name);
		this.clickEventRaised = false;
		this.clickLocation = new Vector2();
	}

	@Override
	public Vector2 play() {
		System.out.println("EN ATTENTE");
		// Attente de l'utilisateur
		while(!this.clickEventRaised)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		this.clickEventRaised = false;
		System.out.println("FIN ATTENTE");
		return this.clickLocation;
	}
	
	public void setClickEvent(Vector2 clickLocation)
	{
		this.clickEventRaised = true;
		this.clickLocation = clickLocation;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int posX = e.getX();
		int posY = e.getY();
		
		int xCase = (int) posX/Cell.WIDTH;
		int yCase = (int) posY/Cell.HEIGHT;
		
		try 
		{
			if(this.board.getCell(xCase, yCase) != Cell.POISONNED && this.board.isInBounds(xCase, yCase))
			{
				this.clickLocation = new Vector2(xCase, yCase);
				this.clickEventRaised = true;
			}
		} catch (OutOfWaffleException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
