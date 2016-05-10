package entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import utilities.Vector2;

public class PlayerRandom extends Player {

	private Random rand;

	public PlayerRandom(String name, Color color) {
		super(name, color);
		this.rand = new Random();
	}

	@Override
	public Vector2 play() {
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		int i = this.rand.nextInt(validMoves.size());
		System.out.println("IA en " + validMoves.get(i));

		return validMoves.get(i);
	}
}
