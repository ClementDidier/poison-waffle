package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Cell{

	CLEAN("../resources/CaseSaine.png"),
	POISONNED("../resources/CaseEmpoisonee.png"),
	EATEN("");
	
	private String path;
	private BufferedImage apparence;
	
	private Cell(String path){
		this.path = path;
		this.apparence = null;
		try{
			this.apparence = ImageIO.read(new File(this.path));
		}catch(IOException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getPath(){
		return this.path;
	}
	
	public BufferedImage getApparence(){
		return this.apparence;
	}
	
}