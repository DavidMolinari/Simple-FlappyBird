package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;import java.awt.peer.KeyboardFocusManagerPeer;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener
{
	
	public final int WIDTH = 1200, HEIGHT = 600;
	
	public static FlappyBird flappyBird;
	
	public Renderer renderer;
	public Random rand;
	
	public Rectangle minou;
	public int ticks, yMotion, score;
	
	public boolean gameOver, started = false;

	
	// Colletion de rectangles pour les colonnes.
	public ArrayList<Rectangle> columns;
	
	
	public FlappyBird()
	{
		
		
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		
		
		renderer = new Renderer();
		rand = new Random();
		
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");

		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setResizable(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		minou = new Rectangle(WIDTH / 2 -10, HEIGHT / 2 -10, 20, 20);
		columns = new ArrayList<Rectangle>();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);


		timer.start();
	}
	public static void main(String[] args)
	{
		flappyBird = new FlappyBird();
		
	}
	
	
	public void jump()
	{
		if (gameOver)
		{
			minou = new Rectangle(WIDTH / 2 -10, HEIGHT / 2 -10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;
			
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			gameOver = false;
		}
		
		if (!started)	
		{
			started = true;
		} else if (!gameOver)
		{
			if(yMotion > 0) 
			{
				yMotion = 0;
			}
			yMotion -= 8;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int speed = 10;
		ticks++;
		
		
		if (started)
		{
		for (int i = 0; i < columns.size(); i++) 
		{
			Rectangle column = columns.get(i);
			column.x -= speed;
			
		}
		
		if (ticks % 2 == 0 && yMotion < 15)
		{
			yMotion += 2;
		}
		for (int i = 0; i < columns.size(); i++) 
		{
			Rectangle column = columns.get(i);
			if (column.x + column.width < 0 )
			{
				columns.remove(column);
				//INFINITEMEH
				if(column.y == 0){
					addColumn(false);
				}
			}
		}
		
		minou.y += yMotion;
		
		
		for(Rectangle column : columns)
		{
			
			if ( column.y == 0 && minou.x + minou.width / 2 > column.x + column.width / 2 - 10 && minou.x + minou.width / 2 < column.x + column.width / 2 + 10)
			{
				score++;
			}
			if (column.intersects(minou))
			{
				gameOver = true;
				if (minou.x <= column.x )
				{
					minou.x = column.x - minou.width;
				}
				else {
					if (column.y != 0) {
						minou.y = column.y - minou.height;
					} else if(minou.y < column.height)
					{
						minou.y = column.height;
					}
				}
				
				minou.x = column.x - minou.width; // pour que le minou reste coincé sur une colonne
			}
		}
		
		if (minou.y > HEIGHT - 120 || minou.y < 0){
			
			gameOver = true;
		}
		//if (gameOver) minou.y = HEIGHT - 120 - minou.height;
		if (minou.y + yMotion >= HEIGHT - 120 ) minou.y = HEIGHT - 120 - minou.height;

		}
			
			
		renderer.repaint();

	}
	
	public void addColumn( boolean start)
	{
		int space = 300; // Espace entre les colonnes
		int width = 100;
		int height = 50 + rand.nextInt(300);
		
		if(start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else 
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x,0, width, HEIGHT - height - space));
		}
	
	}
	
	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	public void repaint(Graphics g) 
	{
		// TODO Auto-generated method stub
		
		//Background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Le personnage Minou
		g.setColor(Color.red);
		g.fillRect(minou.x, minou.y, minou.width, minou.height);
		
		
		// Le sol
		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);
		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);
		
		for(Rectangle column : columns)
		{
			paintColumn(g, column);
		}
		
		

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 100));
		if (!started){
			g.drawString("MEH START ", 250, HEIGHT / 2 - 50);
		}
		
		g.setColor(Color.red);
		g.setFont(new Font("Arial", 1, 100));
		if (gameOver){
			g.drawString("GAME OVER ! ", 250, HEIGHT / 2 - 50);
		}
		
		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), 75, 150);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		jump();		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
