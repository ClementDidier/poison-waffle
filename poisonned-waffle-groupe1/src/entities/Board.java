package entities;
import java.util.ArrayList;

import exceptions.OutOfWaffleException;
import interfaces.BoardInterface;
import utilities.Vector2;

public class Board implements BoardInterface {

	private Cell[][] cells;
	private int width;
	private int height;
	
	public Board(int width, int height)
	{
		this.cells = new Cell[width][height];
		this.width = width;
		this.height = height;
		
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				this.cells[x][y] = Cell.CLEAN;
			}
		}
		
		this.cells[0][0] = Cell.POISONNED;
	}
	
	@Override
	public Cell getCell(int x, int y) throws OutOfWaffleException {
		if(x > this.width || x < 0 || y > this.height || y < 0)
			throw new OutOfWaffleException("Case (" + x + ", " + y + ") en dehors de la gaufre !");
		return this.cells[x][y];
	}
	
	@Override
	public Cell getCell(Vector2 location) throws OutOfWaffleException {
		return this.getCell(location.getX(), location.getY());
	}

	@Override
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException {
		if(x > this.width || x < 0 || y > this.height || y < 0)
			throw new OutOfWaffleException("Case (" + x + ", " + y + ") en dehors de la gaufre !");
		this.cells[x][y] = c;
	}
	
	@Override
	public void setCell(Vector2 location, Cell c) throws OutOfWaffleException {
		this.setCell(location.getX(), location.getY(), c);
	}

	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}
	
	public ArrayList<Vector2> getValidMoves()
	{
		ArrayList<Vector2> result = new ArrayList<Vector2>();
		
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				if(this.cells[x][y] == Cell.CLEAN)
				{
					result.add(new Vector2(x, y));
				}
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		String res = "Board " + this.width + "x" + this.height + '\n';
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++)
			{
				switch(this.cells[x][y]) {
					case CLEAN:
						res += 'O';
						break;
					case EATEN:
						res += 'O';
						break;
					default:
						res += 'X';
						break;
				}
			}
			res += '\n';
		}
		
		return res;
	}
	
	public BoardInterface copy() {
		Board b = new Board(this.width, this.height);
		
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++)
			{
				b.cells[x][y] = this.cells[x][y];
			}
		}
		
		return b;
	}
	
	public boolean isInBounds(int xCase, int yCase) {
		return xCase >= 0 && xCase < this.getWidth() && yCase >= 0 && yCase < this.getHeight();
	}
}
