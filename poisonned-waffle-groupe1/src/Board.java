
public class Board implements BoardInterface {

	private Cell[][] cells;
	private int width;
	private int height;
	
	public Board(int width, int height)
	{
		this.cells = new Cell[width][height];
		this.height = height;
		this.width = width;
		
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
			throw new OutOfWaffleException("Case en dehors de la gaufre !");
		return this.cells[x][y];
	}

	@Override
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException {
		if(x > this.width || x < 0 || y > this.height || y < 0)
			throw new OutOfWaffleException("Case en dehors de la gaufre !");
		// TODO Auto-generated method stub
		
	}

}
