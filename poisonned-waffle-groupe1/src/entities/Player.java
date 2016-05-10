package entities;
import interfaces.BoardInterface;
import interfaces.PlayerInterface;
import utilities.Vector2;

public abstract class Player implements PlayerInterface{

	protected String name;
	protected BoardInterface board;
	
	public Player(String name)
	{
		this.name = name;
	}
	
	@Override
	public abstract Vector2 play();

	@Override
	public void updateBoard(BoardInterface board) {
		// TODO Auto-generated method stub
		this.board = board;
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
