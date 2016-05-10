package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.PlayerMouse;
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
		assertEquals(g.getCurrentPlayer(), "P1");
	}
	
	@Test
	public void testChangedPlayerAfterFirstPlayed()
	{
		v = new Vector2(1,1);
		g.makeMove(v);
		assertEquals(g.getCurrentPlayer(), "P2");
	}
	
	@Test
	public void testGameIsTerminated()
	{
		v = new Vector2(0,0);
		g.makeMove(v);
		assertEquals(g.isTerminated(), true);
	}

	@Test
	public void testSetCurrentPlayer()
	{
		g.setCurrentPlayer(player2);
		assertEquals(g.getCurrentPlayer(), "P2");
	}
}
