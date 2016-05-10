package interfaces;

import entities.Board;

public interface GameInterface {
	public boolean isTerminated();
	
	public BoardInterface getBoard();

	public int getTurn();
	
	public PlayerInterface getCurrentPlayer();

	public void save();

	public void loadBoard(Board board);

	public void undoMove();

	public void redoMove();

	public boolean canUndo();

	public boolean canRedo();
}
