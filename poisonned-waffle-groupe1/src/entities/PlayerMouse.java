package entities;

import utilities.Vector2;

public class PlayerMouse extends Player {

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
}
