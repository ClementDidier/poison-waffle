package program;

import gui.Window;
import javax.swing.SwingUtilities;

public class Program {
	public static void main(String[] args) {
		Window window = new Window(610, 455);
		SwingUtilities.invokeLater(window);
	}
}
