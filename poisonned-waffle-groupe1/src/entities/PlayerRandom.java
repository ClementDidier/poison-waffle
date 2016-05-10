package entities;

import java.util.Random;

import exceptions.OutOfWaffleException;

public class PlayerRandom extends Player {

	private Random rand;
	
	public PlayerRandom(String name) {
		super(name);
		this.rand = new Random();
	}
	
	@Override
	public Cell play() {
		int x = this.rand.nextInt((int)this.board.getSize().getWidth()) + 1;
		int y = this.rand.nextInt((int)this.board.getSize().getHeight()) + 1;
		
		try 
		{
			this.board.getCell(x, y);
		} catch (OutOfWaffleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
