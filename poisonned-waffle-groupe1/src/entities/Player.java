package entities;

import java.awt.Color;

import interfaces.BoardInterface;
import interfaces.PlayerInterface;
import utilities.Vector2;

public abstract class Player implements PlayerInterface {

	protected String			name;
	protected Color			color;
	protected BoardInterface	board;

	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	@Override
	public abstract Vector2 play();

	@Override
	public void updateBoard(BoardInterface b) {
		this.board = b;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (this.name != other.name)
			return false;
		return true;
	}

}
