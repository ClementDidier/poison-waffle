package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entities.Board;
import entities.Cell;
import entities.PlayerMouse;
import exceptions.OutOfWaffleException;
import interfaces.BoardInterface;
import interfaces.GameInterface;
import interfaces.PlayerInterface;
import utilities.UndoRedoManager;
import utilities.Vector2;

public class Game implements GameInterface {

	public static final int						DEFAULT_WIDTH			= 6;
	public static final int						DEFAULT_HEIGHT			= 4;
	public static final int						EVENT_TURN_ENDED		= 0;
	public static final int						EVENT_VICTORY			= 1;
	public static final int						EVENT_PLAYER_TURN_END	= 2;
	public static final int						EVENT_PLAYER_TURN_START	= 3;

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
	private ArrayList<ActionListener>			listeners;

	public Game(PlayerInterface p1, PlayerInterface p2) {
		this.board = new Board(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.players = new ArrayList<PlayerInterface>();
		this.players.add(p1);
		this.players.add(p2);
		this.currentTurn = 0;
		this.history = new UndoRedoManager<BoardInterface>();
		this.listeners = new ArrayList<ActionListener>();
	}

	public Game(BoardInterface board, ArrayList<PlayerInterface> players, int turns,
			UndoRedoManager<BoardInterface> history) {
		this.board = board;
		this.players = players;
		this.currentTurn = turns;
		this.history = history;
		this.listeners = new ArrayList<ActionListener>();
	}

	@Override
	public BoardInterface getBoard() {
		return this.board;
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
	public void undoMove() {
		BoardInterface b = this.history.undo(this.board);
		this.board = b;
		this.currentTurn--;
		this.raiseEvent(new ActionEvent(this, EVENT_TURN_ENDED, null));
	}

	@Override
	public void redoMove() {
		BoardInterface b = this.history.redo(this.board);
		this.board = b;
		this.currentTurn++;
		this.raiseEvent(new ActionEvent(this, EVENT_TURN_ENDED, null));
	}

	@Override
	public boolean canUndo() {
		return this.history.canUndo();
	}

	@Override
	public boolean canRedo() {
		return this.history.canRedo();
	}

	public void makeMove(Vector2 v) {
		this.history.add(this.board.copy());

		try {
			if (this.board.getCell(v) == Cell.CLEAN) {
				for (int i = v.getX(); i < this.board.getWidth(); i++)
					for (int j = v.getY(); j < this.board.getHeight(); j++)
						this.board.setCell(i, j, Cell.EATEN);
				this.currentTurn++;
				this.raiseEvent(new ActionEvent(this, EVENT_TURN_ENDED, null));
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

		try {
			FileOutputStream fstream = new FileOutputStream("save.txt");

			ObjectOutputStream out = new ObjectOutputStream(fstream);
			out.writeObject(jsonBoard);
			out.writeObject(jsonPlayers);
			out.writeObject(jsonTurns);
			out.writeObject(jsonHistory);
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Game load() {
		try {
			FileInputStream fstream = new FileInputStream("save.txt");

			ObjectInputStream in = new ObjectInputStream(fstream);
			String jsonBoard = (String) in.readObject();
			String jsonPlayers = (String) in.readObject();
			String jsonTurns = (String) in.readObject();
			String jsonHistory = (String) in.readObject();
			in.close();

			Gson gson = new Gson();
			BoardInterface b = gson.fromJson(jsonBoard, BoardInterface.class);
			ArrayList<PlayerInterface> p = gson.fromJson(jsonPlayers,
					new TypeToken<ArrayList<PlayerInterface>>() {}.getType());
			int t = gson.fromJson(jsonTurns, int.class);
			UndoRedoManager<BoardInterface> h = gson.fromJson(jsonHistory,
					new TypeToken<UndoRedoManager<BoardInterface>>() {}.getType());

			return new Game(b, p, t, h);
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void clickEvent(int xCase, int yCase) {
		if (!this.isTerminated()) {
			if (this.getCurrentPlayer().getClass() == PlayerMouse.class) {
				try {
					if (this.board.getCell(xCase, yCase) != Cell.POISONNED && this.board.isInBounds(xCase, yCase)) {
						this.raiseEvent(new ActionEvent(this, EVENT_PLAYER_TURN_END, null));
						this.makeMove(new Vector2(xCase, yCase));
					}
				}
				catch (OutOfWaffleException e1) {
					System.err.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void doTurn() {
		if (this.isTerminated()) {
			this.raiseEvent(new ActionEvent(this, EVENT_VICTORY, this.players.get((this.currentTurn-1) % this.players.size()).getName()));
		}

		if (this.getCurrentPlayer().getClass() != PlayerMouse.class) {
			this.getCurrentPlayer().updateBoard(this.board.copy());
			Vector2 move = this.getCurrentPlayer().play();
			this.makeMove(move);
		}
		else {
			this.raiseEvent(new ActionEvent(this.getCurrentPlayer().getColor(), EVENT_PLAYER_TURN_START, null));
		}
	}

	public void addListener(ActionListener l) {
		this.listeners.add(l);
	}

	public void raiseEvent(ActionEvent e) {
		for (ActionListener l : listeners) {
			l.actionPerformed(e);
		}
	}
}
