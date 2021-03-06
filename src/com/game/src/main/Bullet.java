package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {

	
	
	BufferedImage image;
	
	private Textures tex;
	private Game game;
	
	Animation anim;
	
	public Bullet(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5, tex.bullet[0], tex.bullet[1], tex.bullet[2]);
	
	}
	
	public void tick(){
		y -= 10;
		
		//what happens if there is a collision
		
		anim.runAnimation();
	
	}

	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getY(){
		return y;
	}
	public double getX(){
		return x;
	}

}
