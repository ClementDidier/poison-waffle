package program;

import java.util.ArrayList;

import com.google.gson.Gson;

import entities.Board;
import entities.Cell;
import entities.PlayerMouse;
import exceptions.OutOfWaffleException;
import gui.GraphicsPanel;
import gui.Window;
import interfaces.BoardInterface;
import interfaces.GameInterface;
import interfaces.PlayerInterface;
import utilities.UndoRedoManager;
import utilities.Vector2;

public class Game implements GameInterface {

	private static final int					DEFAULT_WIDTH	= 6;
	private static final int					DEFAULT_HEIGHT	= 4;

	/**
	 * Tableau de case (gaufre)
	 */
	protected BoardInterface					board;
	/**
	 * Liste des joueurs, les joueurs jouent dans l'ordre de la liste
	 */
	protected ArrayList<PlayerInterface>		players;
	/**
	 * Tour courant, débute à 0
	 */
	protected int								currentTurn;
	/**
	 * Historique des coups
	 */
	protected UndoRedoManager<BoardInterface>	history;
	protected GraphicsPanel						graphicsPanel;
	private Window								window;

	public Game(PlayerInterface p1, PlayerInterface p2) {
		this.board = new Board(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.players = new ArrayList<PlayerInterface>();
		this.players.add(p1);
		this.players.add(p2);
		this.currentTurn = 0;
		this.graphicsPanel = null;
		this.history = new UndoRedoManager<BoardInterface>();
	}

	public Game(BoardInterface board, ArrayList<PlayerInterface> players, int turns,
			UndoRedoManager<BoardInterface> history) {
		this.board = board;
		this.players = players;
		this.currentTurn = turns;
		this.graphicsPanel = null;
		this.history = history;
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
	public int getTurn() {
		return this.currentTurn;
	}

	@Override
	public PlayerInterface getCurrentPlayer() {
		return this.players.get(this.currentTurn % this.players.size());
	}

	@Override
	public void loadBoard(Board b) {
		this.board = b;
	}

	public void makeMove(Vector2 v) {
		this.history.add(this.board.copy());

		try {
			if (this.board.getCell(v) == Cell.CLEAN) {
				for (int i = v.getX(); i < this.board.getWidth(); i++)
					for (int j = v.getY(); j < this.board.getHeight(); j++)
						this.board.setCell(i, j, Cell.EATEN);
			}
		}
		catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		Gson gson = new Gson();
		String jsonBoard = gson.toJson(this.board);
		String jsonPlayers = gson.toJson(this.players);
		String jsonTurns = gson.toJson(this.currentTurn);
		String jsonHistory = gson.toJson(this.history);
	}

	public void run() {
		while (!this.isTerminated()) {
			this.getCurrentPlayer().updateBoard(this.board.copy());
			Vector2 p = this.getCurrentPlayer().play();
			this.makeMove(p);
			this.currentTurn++;
			this.graphicsPanel.repaint();
		}
		this.currentTurn--;
		this.window.notifyVictory(this.getCurrentPlayer());
	}

	@Override
	public void undoMove() {
		BoardInterface b = this.history.undo(this.board);
		this.board = b;
		this.currentTurn--;
		this.graphicsPanel.repaint();
	}

	@Override
	public void redoMove() {
		BoardInterface b = this.history.redo(this.board);
		this.board = b;
		this.currentTurn++;
		this.graphicsPanel.repaint();
	}

	@Override
	public boolean canUndo() {
		return this.history.canUndo();
	}

	@Override
	public boolean canRedo() {
		return this.history.canRedo();
	}

	@Override
	public BoardInterface getBoard() {
		return this.board;
	}

	@Override
	public void setGraphicsPanel(GraphicsPanel gp) {
		this.graphicsPanel = gp;
		for (PlayerInterface p : this.players) {
			if (p.getClass() == PlayerMouse.class) {
				this.graphicsPanel.requestListener((PlayerMouse) p);
			}
		}
	}

	@Override
	public void setWindow(Window w) {
		this.window = w;
	}
}
