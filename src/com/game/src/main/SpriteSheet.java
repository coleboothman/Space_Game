package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public static BufferedImage loadSprite(String file) {
			BufferedImage sprite = null;
			
			try{
				sprite = ImageIO.read(new File("res/" + file + ".png"));
			}catch (IOException e) {
				e.printStackTrace();
			}
			return sprite;
	}
	
	
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img = image.getSubimage((col*32), (row * 32), width, height);
		return img;
	}
}
