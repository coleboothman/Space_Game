package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Asteroid extends GameObject implements EntityB {

	
	private Textures tex;
	Animation anim;
	private Game game;
	private Controller c;
	
	Random r = new Random();
	
	private int speed = r.nextInt(5) +1;
	
	public Asteroid(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animation(5, tex.asteroid[0], tex.asteroid[1], tex.asteroid[2]);
	}
	
	public void tick() {
		y += speed;
		if(y > (Game.HEIGHT*Game.SCALE)){
			y = -10;
			x = (r.nextInt(Game.WIDTH*Game.SCALE));
			speed = r.nextInt(2) +1;
		}
		anim.runAnimation();
		
		for(int i=0; i< game.ea.size(); i++){
			
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
				Game.SCORE += 100;
			}
		}
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
	public void setY(double y){
		this.y = y;
	}
	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x = x;
	}

}

	

