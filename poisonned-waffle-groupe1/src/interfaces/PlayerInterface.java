package interfaces;

import java.awt.Color;
import interfaces.GameInterface;

public interface PlayerInterface {
	public void play(GameInterface g);

	public void updateBoard(BoardInterface board);

	public String getName();

	public Color getColor();
}
