package interfaces;
import entities.Board;

public interface GameInterface {
	public boolean isTerminated();
	public PlayerInterface getCurrentPlayer();
	public void setCurrentPlayer(PlayerInterface player);
	public void save();
	public void loadBoard(Board board);
	public void play(int x, int y);
	public void undoMove();
	public void redoMove();
	public void canUndo();
	public void canRedo();
}
