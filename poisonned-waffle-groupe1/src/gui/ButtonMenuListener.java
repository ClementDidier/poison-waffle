package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import interfaces.GameInterface;
import program.Game;

public class ButtonMenuListener implements ActionListener {

	JMenuItem		item;
	GameInterface	game;

	public ButtonMenuListener(JMenuItem item, GameInterface game) {
		this.item = item;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String labelItem = this.item.getText();
		switch (labelItem) {
			case "Refaire":
				if (this.game.canRedo())
					this.game.redoMove();
				break;
			case "Annuler":
				if (this.game.canUndo())
					this.game.undoMove();
				break;
			case "Sauvegarder":
				this.game.save();
				break;
			case "Charger":
				Game.load();
				break;
			default:
				System.out.println("bouton encore non implémenté");
				break;
		}
	}

}
