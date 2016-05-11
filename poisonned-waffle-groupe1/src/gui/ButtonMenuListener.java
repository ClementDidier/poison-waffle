package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import entities.PlayerMouse;
import interfaces.GameInterface;
import interfaces.PlayerInterface;
import program.Game;

public class ButtonMenuListener implements ActionListener {

	JMenuItem	item;
	Window		window;

	public ButtonMenuListener(JMenuItem item, Window w) {
		this.item = item;
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String labelItem = this.item.getText();
		switch (labelItem) {
			case "Refaire":
				if (this.window.getGame().canRedo())
					this.window.getGame().redoMove();
				break;
			case "Annuler":
				if (this.window.getGame().canUndo())
					this.window.getGame().undoMove();
				break;
			case "Nouvelle Partie":
				this.window.askPlayersForNewGame();
				PlayerInterface player1 = new PlayerMouse("P1", new Color(120,50,50,125));
				PlayerInterface player2 = new PlayerMouse("P2", new Color(50,50,120,125));
				GameInterface g = new Game(player1, player2);
				
				this.window.setGame(g);
				break;
			case "Sauvegarder":
				this.window.getGame().save();
				break;
			case "Charger":
				this.window.setGame(Game.load());
				break;
			default:
				System.out.println("Bouton encore non implémenté");
				break;
		}
	}

}
