package entities;
import java.awt.Dimension;
import java.util.ArrayList;

import exceptions.OutOfWaffleException;
import interfaces.BoardInterface;
import utilities.Vector2;

public class Board implements BoardInterface {

	private Cell[][] cells;
	private Dimension size;
	
	public Board(int width, int height)
	{
		this.cells = new Cell[width][height];
		this.size = new Dimension(width, height);
		
		for(int x = 0; x < this.size.getWidth(); x++)
		{
			for(int y = 0; y < this.size.getHeight(); y++)
			{
				this.cells[x][y] = Cell.CLEAN;
			}
		}
		
		this.cells[0][0] = Cell.POISONNED;
	}
	
	@Override
	public Cell getCell(int x, int y) throws OutOfWaffleException {
		if(x > this.size.getWidth() || x < 0 || y > this.size.getHeight() || y < 0)
			throw new OutOfWaffleException("Case en dehors de la gaufre !");
		return this.cells[x][y];
	}
	
	@Override
	public Cell getCell(Vector2 location) throws OutOfWaffleException {
		return this.getCell(location.getX(), location.getY());
	}

	@Override
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException {
		if(x > this.size.getWidth() || x < 0 || y > this.size.getHeight() || y < 0)
			throw new OutOfWaffleException("Case en dehors de la gaufre !");
		this.cells[x][y] = c;
	}
	
	@Override
	public void setCell(Vector2 location, Cell c) throws OutOfWaffleException {
		this.setCell(location.getX(), location.getY(), c);
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}
	
	public ArrayList<Vector2> getValidMoves()
	{
		ArrayList<Vector2> result = new ArrayList<Vector2>();
		
		for(int x = 0; x < this.size.getWidth(); x++)
		{
			for(int y = 0; y < this.size.getHeight(); y++)
			{
				if(this.cells[x][y] == Cell.CLEAN)
				{
					result.add(new Vector2(x, y));
				}
			}
		}
		
		return result;
	}

}
