package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
	/*
		public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 125, 500, 300, 50);
		public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 125, 600, 300, 50);
		public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 125, 700, 300, 50);
	*/	
		//play button
		if(mx >= Game.WIDTH / 2 - 125 && mx <= Game.WIDTH /2 + 170 ) {
			
			if(my >= 500 && my <= 550){
				//pressed play button
				Game.state = Game.STATE.GAME;
			}
		}
		//quit button
		if(mx >= Game.WIDTH / 2 - 125 && mx <= Game.WIDTH /2 + 170 ) {
			
			if(my >= 700 && my <= 750){
				//pressed play button
				System.exit(1);
			}
		}
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
