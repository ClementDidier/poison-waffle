
public interface BoardInterface {
	public Cell getCell(int x, int y) throws OutOfWaffleException;
	public void setCell(int x, int y, Cell c) throws OutOfWaffleException;
}
