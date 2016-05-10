package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Cell {

	CLEAN("./resources/CaseSaine.png"), 
	POISONNED("./resources/CaseEmpoisonee.png"), 
	EATEN("");

	private String			path;
	private BufferedImage	apparence;

	public static final int	WIDTH	= 100;
	public static final int	HEIGHT	= 100;

	private Cell(String path) {
		this.path = path;
		if (this.path.isEmpty()) {
			this.apparence = null;
		}
		else {

			try {
				this.apparence = ImageIO.read(new File(this.path));
			}
			catch (IOException e) {

				try {
					System.err.println(this.path);
					System.err.println(new File(".").getCanonicalPath());
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String getPath() {
		return this.path;
	}

	public BufferedImage getApparence() {
		return this.apparence;
	}

}
