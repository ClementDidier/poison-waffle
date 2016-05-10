package entities;

import interfaces.BoardInterface;
import interfaces.PlayerInterface;

public class PlayerRandom implements PlayerInterface {

	private BoardInterface board;
	
	@Override
	public Cell play() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(BoardInterface board) {
		this.board = board;
	}

}
