package test;

import org.junit.Before;
import org.junit.Test;

import entities.Board;
import entities.PlayerMouse;

public class PlayerMouseTest {
	protected PlayerMouse p1;
	
	@Before
	public void init()
	{
		p1 = new PlayerMouse("P1");
	}
	
	@Test
	public void testPlayerUpdatedBoard()
	{
		Board b = new Board(2, 2);
		p1.updateBoard(b);
		//TODO: tests ?
	}
}
