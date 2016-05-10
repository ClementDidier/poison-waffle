import java.awt.Dimension;

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
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException {
		if(x > this.size.getWidth() || x < 0 || y > this.size.getHeight() || y < 0)
			throw new OutOfWaffleException("Case en dehors de la gaufre !");
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

}
