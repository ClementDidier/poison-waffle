package program;

import gui.Window;
import interfaces.GameInterface;
import interfaces.PlayerInterface;

import javax.swing.SwingUtilities;

import entities.Player;
import entities.PlayerMouse;

public class Program 
{
	public static void main(String[] args)
	{
		PlayerMouse p1 = new PlayerMouse("Joueur 1");
		PlayerMouse p2 = new PlayerMouse("Joueur 2");
		GameInterface game = new Game(p1, p2);
		Window window = new Window(600, 400, game);
		SwingUtilities.invokeLater(window);
	}
}
