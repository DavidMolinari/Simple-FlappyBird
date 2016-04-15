package flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener {
	
	public final int WIDTH = 800, HEIGHT = 600;
	
	public static FlappyBird flappyBird;
	
	public Renderer renderer;
	
	public Rectangle minou;
	
	public FlappyBird(){
		
		
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		
		
		jframe.setTitle("Flappy Bird");
		renderer = new Renderer();
		jframe.add(renderer);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		minou = new Rectangle(WIDTH / 2 -10, HEIGHT / 2 -10, 20, 20);
		timer.start();
	}
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		renderer.repaint();
	}
	
	
	public void repaint(Graphics g) {
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
		
		
		
		
	}

}
