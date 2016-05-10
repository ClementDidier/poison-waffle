package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import entities.Board;
import interfaces.GameInterface;
import utilities.UndoRedoManager;

public class ButtonMenuListener implements ActionListener {
	
	JMenuItem item;
	GameInterface game;
	
	public ButtonMenuListener(JMenuItem item, GameInterface game){
		this.item = item;
		this.game = game;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String labelItem = this.item.getText();
		switch(labelItem){
			case "Refaire":
				if(this.game.canRedo())
					this.game.redoMove();
				break;
			case "Annuler":
				if(this.game.canUndo())
					this.game.undoMove();
				break;
			default:
				System.out.println("bouton encore non implémenté");
				break;	
		}
	}

}
