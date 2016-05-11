package entities;

import java.awt.Color;
import java.util.ArrayList;
import utilities.Vector2;

public class PlayerRandom extends PlayerIA {

	public PlayerRandom(String name, Color color) {
		super(name, color);
	}

	@Override
	protected Vector2 decideMove() {
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		int i = this.rand.nextInt(validMoves.size());
		System.out.println("IA en " + validMoves.get(i));
		
		return validMoves.get(i);
	}
}
