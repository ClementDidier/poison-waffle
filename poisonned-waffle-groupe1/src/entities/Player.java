package entities;
import interfaces.BoardInterface;
import interfaces.PlayerInterface;

public abstract class Player implements PlayerInterface{

	protected byte index;
	protected BoardInterface board;
	
	public Player(byte index)
	{
		this.index = index;
	}
	
	@Override
	public abstract Cell play();

	@Override
	public void updateBoard(BoardInterface board) {
		// TODO Auto-generated method stub
		this.board = board;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
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
		if (index != other.index)
			return false;
		return true;
	}

	
}
