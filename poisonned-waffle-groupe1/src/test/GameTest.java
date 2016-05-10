package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.PlayerMouse;
import program.Game;

public class GameTest {
	protected Game g;
	protected PlayerMouse player1;
	protected PlayerMouse player2;
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
		g.play(1, 1);
		assertEquals(g.getCurrentPlayer(), "P2");
	}
	
	@Test
	public void testGameIsTerminated()
	{
		g.play(0, 0);
		assertEquals(g.isTerminated(), true);
	}

	@Test
	public void testSetCurrentPlayer()
	{
		g.setCurrentPlayer(player2);
		assertEquals(g.getCurrentPlayer(), "P2");
	}
}
