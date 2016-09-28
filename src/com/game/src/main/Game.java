package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Game extends Canvas implements Runnable {
	
	//frame sizes and name
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 960;
	public static final int HEIGHT = 960;
	public static final int SCALE = 1;
	public final String TITLE = "APOLLO ASCENDS";

	// running variable for state and thread to start game
	private boolean running = false;
	private Thread thread;

	//instead of having static images, loads them. ("buffering")
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // rgb = red green blue
	private BufferedImage background = null;
	private BufferedImage menu_pic = null;
	
	
	//temp
	private BufferedImage player = null;
	
	private boolean is_shooting = false;
	
	private int enemy_count = 5;
	private int enemy_killed = 0;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static int HEALTH = 100*3;
	public static int SCORE = 0;
	
	public static enum STATE {
		MENU,
		GAME
	};
	
	public static STATE state = STATE.MENU;
	
	public void init(){
		requestFocus(); //SO YOU DON'T NEED TO CLICK THE APP BEFORE INPUT
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
		background = loader.loadImage("/397989.png");
		menu_pic = loader.loadImage("/menu.jpg");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(430, 850, tex, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		c.createEnemy(enemy_count);
		
		
	}
	

	
	// starts the game, if running no need to start again
	private synchronized void start() {
		if(running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	// stops the game, shouldnt be needed but just incase of error/overflow
	private synchronized void stop() {
		if(!running)
			return;

		running = false;
		try {
			thread.join(); //joins all the threads together
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}



	//game loop that handles updates while the game is running. rendering, images etc.game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	// goes through the code then repeats, controls fps and such.
	// VERY IMPORTANT TO ALL GAMES. maxes the game out so it doesnt run super fast on good computers
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // calculates time past if fps lags.
		int update = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				update++;
				delta--;
			}	
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(update + "Ticks, Fps" + frames);
				update = 0;
				frames = 0;
			}


			//System.out.println("RUNNING");
		}
		stop();
	}

	private void tick() {
		if(state == STATE.GAME){
			p.tick();
			c.tick();
			
		}
		
		
		if(enemy_killed >= enemy_count)
		{
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		
		
	}
	//buffer strategy = handles buffering behind the scenes
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // the this. is accessed from the canvas
		if(bs == null) {

			createBufferStrategy(3); // 3 buffers. loads 2 buffers onto the screen, makes it faster.
			return;
		}

		Graphics g = bs.getDrawGraphics(); // draws out the buffers
		/////////////////////////////////////////////
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(menu_pic, 0, 0, null);
	
		
		if(state == STATE.GAME) {
			g.drawImage(background, 0, 0, null);
			p.render(g);
			c.render(g);
			
			g.setColor(Color.red);
			g.fillRect(10,10,300,30);
			
			g.setColor(Color.green);
			g.fillRect(10,10,HEALTH,30);
			
			g.setColor(Color.red);
			g.drawRect(10,10,300,30);
			
			Font fnt0 = new Font("Calibri", Font.BOLD, 25);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("Health", 15, 70);
			g.drawString("Score: " + SCORE, 15, 95);
		
		}else if (state == STATE.MENU){
			menu.render(g);
		}
		
		
		

		g.dispose();
		bs.show();

	}


	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		if(state == STATE.GAME){
			if(key == KeyEvent.VK_D){
				p.setVelX(6);
			} 	else if(key == KeyEvent.VK_A){
				p.setVelX(-6);
			} 	else if(key == KeyEvent.VK_S){
				p.setVelY(6);
			} 	else if(key == KeyEvent.VK_W){
				p.setVelY(-6);
			} 	else if(key == KeyEvent.VK_SPACE && !is_shooting){
				c.addEntity(new Bullet(p.getX()+40, p.getY()-15, tex, this));
				is_shooting = true;
			}
		}
	}
	
	
	
	public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_D){
			p.setVelX(0);
		} else if(key == KeyEvent.VK_A){
			p.setVelX(0);
		} else if(key == KeyEvent.VK_S){
			p.setVelY(0);
		} else if(key == KeyEvent.VK_W){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_SPACE){
			is_shooting = false;
		}
	}
	
	
	
	
	
	
	
	// runs the jframe game
	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	public BufferedImage getPlayer() {
		return player;
	}

	public int getEnemy_count(){
		return enemy_count;
	}
	
	public void setEnemyCount(int enemy_count){
		this.enemy_count = enemy_count;
	}
	public int getEnemyKilled(){
		return enemy_killed;
	}
	public void setEnemyKilled(int enemyKilled){
		this.enemy_killed = enemyKilled;
	}

}

