package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Player extends GameObject implements EntityA {
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	
	Game game;
	Controller controller;
	Animation anim;
	Graphics g;

	
	
	public Player(double x, double y, Textures tex, Game game, Controller controller){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		anim = new Animation(10, tex.player[0], tex.player[1], tex.player[2]);
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
	
		if(x <= -15) // boundaries so the player doesnt move out of the screen
			x = -15;
		if(x >= 960 - 100)
			x = 960 - 100;
		if(y <= 0)
			y = 0;
		if(y >= 960 - 100)
			y = 960 - 100;
		
		
		
		for(int i = 0; i < game.eb.size(); i ++){
			
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 50;
				game.setEnemyKilled(game.getEnemyKilled() + 1);
			
				
				if(Game.HEALTH == 0) {
					game.HEALTH = 300;
					game.state = Game.STATE.MENU;
				}
				
			}
		}
	
		anim.runAnimation();
	}

	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}
}
