import javax.swing.SwingUtilities;

public class Program 
{
	public static void main(String[] args)
	{
		Window window = new Window(600, 400);
		SwingUtilities.invokeLater(window);
	}
}
