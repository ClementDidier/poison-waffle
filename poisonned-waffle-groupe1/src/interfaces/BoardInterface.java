package interfaces;
import java.awt.Dimension;
import java.util.ArrayList;

import entities.Cell;
import exceptions.OutOfWaffleException;
import utilities.Vector2;

public interface BoardInterface {
	public Dimension getSize();
	public Cell getCell(int x, int y) throws OutOfWaffleException;
	public Cell getCell(Vector2 location) throws OutOfWaffleException;
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException;
	public void setCell(Vector2 location, Cell c) throws OutOfWaffleException;
	public ArrayList<Vector2> getValidMoves();
}
