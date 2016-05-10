package program;
import entities.Board;
import entities.Cell;
import exceptions.OutOfWaffleException;
import interfaces.BoardInterface;
import interfaces.GameInterface;
import interfaces.PlayerInterface;

public class Game implements GameInterface{
	
	private static final int DEFAULT_WIDTH = 6;
	private static final int DEFAULT_HEIGHT = 4;
	
	/**
	 * Tableau de case (gaufre)
	 */
	protected BoardInterface board;
	/**
	 * Joueur 1 : joue le premier tour
	 */
	protected PlayerInterface player1;
	/**
	 * Joueur 2 : joue en second
	 */
	protected PlayerInterface player2;
	/**
	 * Joueur courant
	 */
	protected PlayerInterface currentPlayer;
	
	
	public Game(PlayerInterface p1, PlayerInterface p2)
	{
		this.board = new Board(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public Game(BoardInterface board, PlayerInterface p1, PlayerInterface p2)
	{
		this.board = board;
	}
	
	/**
	 * Determine si la partie est terminée
	 */
	@Override
	public boolean isTerminated() {
		try {
			return this.board.getCell(0, 1) == Cell.EATEN && this.board.getCell(1, 0) == Cell.EATEN;
		} catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			return true;
		}
	}

	@Override
	public PlayerInterface getCurrentPlayer() {
		return this.currentPlayer;
	}

	@Override
	public void setCurrentPlayer(PlayerInterface player) {
		this.currentPlayer = player;
		
	}

	@Override
	public void loadBoard(Board board) {
		this.board = board;
	}

	@Override
	public void play(int x, int y) 
	{
		if(!this.isTerminated() /* && playPossible(x, y, board) */)
		{
			try 
			{
				for(int i = x; i < this.board.getSize().getWidth(); i++)
					for(int j = y; j < this.board.getSize().getHeight(); j++)
							this.board.setCell(i, j, Cell.EATEN);
			
			} catch (OutOfWaffleException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
