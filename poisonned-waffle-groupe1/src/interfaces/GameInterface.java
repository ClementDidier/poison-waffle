package interfaces;

import entities.Board;

public interface GameInterface {
	public boolean isTerminated();
	
	public BoardInterface getBoard();

	public PlayerInterface getCurrentPlayer();

	public void setCurrentPlayer(PlayerInterface player);

	public void save();

	public void loadBoard(Board board);

	public void undoMove();

	public void redoMove();

	public boolean canUndo();

	public boolean canRedo();
}
