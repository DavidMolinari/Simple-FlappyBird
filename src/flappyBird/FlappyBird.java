package flappyBird;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener {
	
	public final int WIDTH = 800, HEIGHT = 600;
	
	public static FlappyBird flappyBird;
	
	public Renderer renderer;
	
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
		
	}

}
