package entities;

import java.awt.Color;
import java.util.Random;
import javax.swing.Timer;
import interfaces.GameInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.Vector2;

public abstract class PlayerIA extends Player implements ActionListener {

	protected Random		rand;
	protected GameInterface	game;
	protected Timer			t;

	public PlayerIA(String name, Color color) {
		super(name, color);
		this.game = null;
		this.t = null;
		this.rand = new Random();
	}

	@Override
	public void play(GameInterface g) {
		this.game = g;

		this.t = new Timer(800 + rand.nextInt(600), this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.t.stop();

		Vector2 chosenMove = this.decideMove();

		this.game.receiveMove(chosenMove);
	}

	protected abstract Vector2 decideMove();
}
