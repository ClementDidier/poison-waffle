package entities;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utilities.Vector2;

public class PlayerMouse extends Player implements MouseListener{

	private boolean clickEventRaised;
	private Vector2 clickLocation;
	
	public PlayerMouse(String name) {
		super(name);
		this.clickEventRaised = false;
		this.clickLocation = new Vector2();
	}

	@Override
	public Vector2 play() {
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
		
		return this.clickLocation;
	}
	
	public void setClickEvent(Vector2 clickLocation)
	{
		this.clickEventRaised = true;
		this.clickLocation = clickLocation;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		 
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
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
