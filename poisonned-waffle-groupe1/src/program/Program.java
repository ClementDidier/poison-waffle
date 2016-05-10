package program;

import gui.Window;
import interfaces.GameInterface;
import interfaces.PlayerInterface;

import javax.swing.SwingUtilities;

import entities.Player;
import entities.PlayerMouse;
import entities.PlayerRandom;

public class Program 
{
	public static void main(String[] args)
	{
		PlayerMouse p1 = new PlayerMouse("Joueur 1");
		PlayerRandom p2 = new PlayerRandom("Joueur 2");
		GameInterface game = new Game(p1, p2);
		Window window = new Window(610, 455, game);
		SwingUtilities.invokeLater(window);
		game.run();
	}
}
