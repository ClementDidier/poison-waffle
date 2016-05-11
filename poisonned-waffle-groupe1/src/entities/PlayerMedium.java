package entities;

import java.awt.Color;
import java.util.ArrayList;
import utilities.Vector2;

public class PlayerMedium extends PlayerIA {

	public PlayerMedium(String name, Color color) {
		super(name, color);
	}

	@Override
	protected Vector2 decideMove() {
		Vector2 chosenMove = null;
		ArrayList<Vector2> validMoves = this.board.getValidMoves();
		boolean needChoice = !validMoves.contains(new Vector2(1, 1));
		if (needChoice) {
			boolean lignePresente = validMoves.contains(new Vector2(1, 0));
			boolean colonnePresente = validMoves.contains(new Vector2(0, 1));
			if (lignePresente == true && colonnePresente == false) {
				chosenMove = new Vector2(1, 0);
			}
			else if (lignePresente == false && colonnePresente == true) {
				chosenMove = new Vector2(0, 1);
			}
			else {
				boolean ligneEtendue = validMoves.contains(new Vector2(2, 0));
				boolean colonneEtendue = validMoves.contains(new Vector2(0, 2));
				if (ligneEtendue == true && colonneEtendue == true) {
					if (this.rand.nextBoolean())
						chosenMove = new Vector2(2, 0);
					else
						chosenMove = new Vector2(0, 2);
				}
				else if (ligneEtendue == true && colonneEtendue == false) {
					chosenMove = new Vector2(2, 0);
				}
				else if (ligneEtendue == false && colonneEtendue == true) {
					chosenMove = new Vector2(0, 2);
				}
				else {
					if (this.rand.nextBoolean())
						chosenMove = new Vector2(1, 0);
					else
						chosenMove = new Vector2(0, 1);
				}
			}
		}
		else {
			chosenMove = validMoves.get(this.rand.nextInt(validMoves.size()));
		}
		
		return chosenMove;
	}
}
