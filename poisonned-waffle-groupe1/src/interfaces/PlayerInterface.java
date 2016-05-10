package interfaces;
import entities.Cell;

public interface PlayerInterface {
	public Cell play();
	public void updateBoard(BoardInterface board);
}
