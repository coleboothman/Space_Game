package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 125, 500, 300, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 125, 600, 300, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 125, 700, 300, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		
		
		Font fnt0 = new Font("Calibri", Font.BOLD, 150);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("APOLLO", 100, 300);
		g.drawString("ASCENDS", 200,400);
		
		Font fnt1 = new Font("Calibri", Font.BOLD, 40);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 110, playButton.y + 35);
		g.drawString("Help", helpButton.x + 110, helpButton.y + 35);
		g.drawString("Quit", quitButton.x + 110, quitButton.y + 35);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	
	}
}
