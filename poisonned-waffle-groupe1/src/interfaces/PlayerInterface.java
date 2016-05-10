package interfaces;
import entities.Board;
import entities.Cell;

public interface PlayerInterface {
	public void play(Cell cell);
	public Board getBoard();
}
