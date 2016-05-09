import javax.swing.JFrame;

public class Window implements Runnable
{
	private static final String DEFAULT_NAME = "Poisonned Waffle";
	private JFrame frame;
	private GraphicsPanel panel;
	
	public Window(int width, int height)
	{
		this.frame = new JFrame();
		this.frame.setName(DEFAULT_NAME);
		this.frame.setSize(width, height);
		
		this.panel = new GraphicsPanel();
		
		this.frame.setContentPane(this.panel);
	}
	
	@Override
	public void run() {
		this.frame.setVisible(true);
	}
	
}
