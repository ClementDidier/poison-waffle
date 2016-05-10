package entities;

import java.util.ArrayList;
import java.util.Random;
import utilities.Vector2;

public class PlayerRandom extends Player {

	private Random rand;
	
	public PlayerRandom(String name) {
		super(name);
		this.rand = new Random();
	}
	
	@Override
	public Vector2 play() {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		int i = this.rand.nextInt(validMoves.size());
		System.out.println("IA en " + validMoves.get(i));
		
		return validMoves.get(i);
	}
}
