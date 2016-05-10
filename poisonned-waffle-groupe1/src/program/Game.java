package program;

import entities.Board;
import entities.Cell;
import exceptions.OutOfWaffleException;
import interfaces.BoardInterface;
import interfaces.GameInterface;
import interfaces.PlayerInterface;
import utilities.Vector2;

public class Game implements GameInterface, Runnable {

	private static final int	DEFAULT_WIDTH	= 6;
	private static final int	DEFAULT_HEIGHT	= 4;

	/**
	 * Tableau de case (gaufre)
	 */
	protected BoardInterface	board;
	/**
	 * Joueur 1 : joue le premier tour
	 */
	protected PlayerInterface	player1;
	/**
	 * Joueur 2 : joue en second
	 */
	protected PlayerInterface	player2;
	/**
	 * Joueur courant
	 */
	protected PlayerInterface	currentPlayer;

	public Game(PlayerInterface p1, PlayerInterface p2) {
		this.board = new Board(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.player1 = p1;
		this.player2 = p2;
		this.currentPlayer = this.player1;
	}

	public Game(BoardInterface board, PlayerInterface p1, PlayerInterface p2, int cp) {
		this.board = board;
		this.player1 = p1;
		this.player2 = p2;
		if (cp == 1)
			this.currentPlayer = this.player1;
		else
			this.currentPlayer = this.player2;
	}

	/**
	 * Determine si la partie est terminée
	 */
	@Override
	public boolean isTerminated() {
		try {
			return this.board.getCell(0, 1) == Cell.EATEN && this.board.getCell(1, 0) == Cell.EATEN;
		}
		catch (OutOfWaffleException e) {
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
	public void loadBoard(Board b) {
		this.board = b;
	}

	public void makeMove(Vector2 v) {
		try {
			if (this.board.getCell(v) == Cell.CLEAN) {
				try {
					for (int i = v.getX(); i < this.board.getSize().getWidth(); i++)
						for (int j = v.getY(); j < this.board.getSize().getHeight(); j++)
							this.board.setCell(i, j, Cell.EATEN);
				}
				catch (OutOfWaffleException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (!this.isTerminated()) {
			this.currentPlayer.updateBoard(this.board);
			Vector2 p = this.currentPlayer.play();
			this.makeMove(p);
		}
	}

	@Override
	public void undoMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redoMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public boolean canRedo() {
		return false;
	}

	@Override
	public BoardInterface getBoard() {
		return this.board;
	}

}
