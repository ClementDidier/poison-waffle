package interfaces;

import java.awt.event.ActionListener;

import utilities.Vector2;

public interface GameInterface {
	public boolean isTerminated();
	
	public BoardInterface getBoard();

	public int getTurn();
	
	public void undoMove();

	public void redoMove();

	public boolean canUndo();

	public boolean canRedo();
	
	public PlayerInterface getCurrentPlayer();

	public void save();

	void clickEvent(int xCase, int yCase);

	void doTurn();

	public void addListener(ActionListener l);

	public void removeListener(ActionListener l);

	void receiveMove(Vector2 move);
}
