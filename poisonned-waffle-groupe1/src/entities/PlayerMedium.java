package entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import utilities.Vector2;

public class PlayerMedium extends Player {

	private Random rand;

	public PlayerMedium(String name, Color color) {
		super(name, color);
		this.rand = new Random();
	}

	@Override
	public Vector2 play() {
		Vector2 chosenMove = null;
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		if (!validMoves.contains(new Vector2(1, 1))) {
			if (validMoves.contains(new Vector2(1, 0)) == true && validMoves.contains(new Vector2(0, 1)) == false) {
				chosenMove = new Vector2(1, 0);
			}
			else if (validMoves.contains(new Vector2(1, 0)) == false && validMoves.contains(new Vector2(0, 1)) == true) {
				chosenMove = new Vector2(0, 1);
			}
			else {
				if (validMoves.contains(new Vector2(2, 0)) == true && validMoves.contains(new Vector2(0, 2)) == true) {
					if(rand.nextBoolean())
						chosenMove = new Vector2(2, 0);
					else
						chosenMove = new Vector2(0, 2);
				}
				else if (validMoves.contains(new Vector2(2, 0)) == true && validMoves.contains(new Vector2(0, 2)) == false) {
					chosenMove = new Vector2(2, 0);
				}
				else if (validMoves.contains(new Vector2(2, 0)) == false && validMoves.contains(new Vector2(0, 2)) == true) {
					chosenMove = new Vector2(0, 2);
				}
				else {
					if(rand.nextBoolean())
						chosenMove = new Vector2(1, 0);
					else
						chosenMove = new Vector2(0, 1);
				}
			}
		}
		else {
			chosenMove = validMoves.get(this.rand.nextInt(validMoves.size()));
		}
		System.out.println("IA en " + chosenMove);

		return chosenMove;
	}
}
