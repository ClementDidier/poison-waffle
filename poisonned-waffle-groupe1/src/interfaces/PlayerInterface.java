package interfaces;

import java.awt.Color;

import utilities.Vector2;

public interface PlayerInterface {
	public Vector2 play();

	public void updateBoard(BoardInterface board);

	public String getName();

	public Color getColor();
}
