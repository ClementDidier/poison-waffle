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
		
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		int i = this.rand.nextInt(validMoves.size());
		
		return validMoves.get(i);
	}
}
