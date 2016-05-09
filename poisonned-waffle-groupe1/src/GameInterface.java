
public interface GameInterface {
	public boolean isTerminated();
	public Player getCurrentPlayer();
	public void setCurrentPlayer();
	public void save();
	public Game load();
	public Board loadBoard(Board board);
	public void play(int x, int y);
}
