package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

//checks to see if the rectangles around the players and enemies intersect.
public class Physics {

	//checks to see if any class A runs into class B entites. ie. bullets in
	//to enemies, or player into enemies etc.
	public static boolean Collision(EntityA enta, EntityB entb){
		
		if(enta.getBounds().intersects(entb.getBounds())){
				return true;
			}
		
		return false;
	}
	
	public static boolean Collision(EntityB entb, EntityA enta){
		
		if(entb.getBounds().intersects(enta.getBounds())){
				return true;
		}
		
		return false;
	}
	
}
