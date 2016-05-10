package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.Board;
import entities.Cell;

public class BoardTest {
	protected Board b;

	@Before
	public void init() {
		b = new Board(2, 2);
	}

	@Test
	public void testPoisonnedCell() throws Exception {
		// Test de la case empoisonnee
		assertEquals(b.getCell(0, 0), Cell.POISONNED);
	}

	@Test
	public void testCleanCells() throws Exception {
		// Test des cases "propres"
		assertEquals(b.getCell(0, 1), Cell.CLEAN);
		assertEquals(b.getCell(1, 0), Cell.CLEAN);
		assertEquals(b.getCell(1, 1), Cell.CLEAN);
	}

	@Test
	public void testSet() throws Exception {
		b.setCell(1, 1, Cell.POISONNED);
		// Test de la case empoisonnee ajoutee
		assertEquals(b.getCell(1, 1), Cell.POISONNED);
	}

}
