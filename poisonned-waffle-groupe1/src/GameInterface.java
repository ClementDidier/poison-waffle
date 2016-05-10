
public interface GameInterface {
	public boolean isTerminated();
	public PlayerInterface getCurrentPlayer();
	public void setCurrentPlayer(PlayerInterface player);
	public void save();
	public Game load();
	public void loadBoard(Board board);
	public void play(int x, int y);
}
