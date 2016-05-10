package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.Board;
import entities.Cell;
import entities.PlayerMouse;
import exceptions.OutOfWaffleException;
import program.Game;
import utilities.Vector2;

public class GameTest {
	protected Game g;
	protected PlayerMouse player1;
	protected PlayerMouse player2;
	protected Vector2 v;
	@Before
	public void init()
	{
		player1 = new PlayerMouse("P1");
		player2 = new PlayerMouse("P2");
		g = new Game(player1,player2);
	}
	
	@Test
	public void testFirstPlayerIsP1()
	{
		assertEquals(g.getCurrentPlayer(), player1);
	}
	
	@Test
	public void testTurnIs0()
	{
		assertEquals(g.getTurn(),0);
	}
	
	@Test
	public void testLoadBoard()
	{
		Board b = new Board(15, 48);
		g.loadBoard(b);
		assertEquals(g.getBoard(),b);
	}
	
	@Test
	public void testFirstMove()
	{
		v = new Vector2(1,1);
		g.makeMove(v);
		try {
			assertEquals(g.getBoard().getCell(v), Cell.EATEN);
		} catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGameIsTerminated()
	{
		//Ne doit pas etre termine au debut
		assertEquals(g.isTerminated(), false);
		
		v = new Vector2(0,1);
		g.makeMove(v);
		v = new Vector2(1,0);
		g.makeMove(v);
		assertEquals(g.isTerminated(), true);
	}
	
	@Test
	public void testUndo()
	{
		v = new Vector2(1,1);
		try {
			assertEquals(g.canUndo(), false);
			g.makeMove(v);
			assertEquals(g.canUndo(), true);
			assertEquals(g.getBoard().getCell(v), Cell.EATEN);
			g.undoMove();
			assertEquals(g.canUndo(), false);
			assertEquals(g.getBoard().getCell(v), Cell.CLEAN);

		} catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRedo()
	{
		v = new Vector2(1,1);
		try {
			assertEquals(g.canUndo(), false);
			assertEquals(g.canRedo(), false);
			g.makeMove(v);
			assertEquals(g.canUndo(), true);
			assertEquals(g.canRedo(), false);
			assertEquals(g.getBoard().getCell(v), Cell.EATEN);
			g.undoMove();
			assertEquals(g.canUndo(), false);
			assertEquals(g.canRedo(), true);
			assertEquals(g.getBoard().getCell(v), Cell.CLEAN);
			g.redoMove();
			assertEquals(g.canUndo(), true);
			assertEquals(g.canRedo(), false);
			assertEquals(g.getBoard().getCell(v), Cell.EATEN);
		} catch (OutOfWaffleException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
