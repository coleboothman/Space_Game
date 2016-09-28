package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Textures {

	
	Random r = new Random();

	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] asteroid = new BufferedImage[3];
	
	
	public Textures(Game game){
		
	getTextures();

	}
	
	public void getTextures(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
		player[0] = loader.loadImage("/pengu.png");
		player[1] = loader.loadImage("/pengu2.png");
		player[2] = loader.loadImage("/pengu3.png");
		
		enemy[0] = loader.loadImage("/enemy.png");
		enemy[1] = loader.loadImage("/enemy2.png");
		enemy[2] = loader.loadImage("/enemy3.png");
		
		asteroid[0] = loader.loadImage("/asteroids.png");
		asteroid[1] = loader.loadImage("/asteroids2.png");
		asteroid[2] = loader.loadImage("/asteroids3.png");
		
		bullet[0] = loader.loadImage("/bullet.png");
		bullet[1] = loader.loadImage("/bullet2.png");
		bullet[2] = loader.loadImage("/bullet3.png");
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
}

	
