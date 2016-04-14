package flappyBird;

import java.awt.Color;

import javax.swing.JFrame;

public class FlappyBird {
	
	public final int WIDTH = 800, HEIGHT = 600;
	
	public static FlappyBird flappyBird;
	
	public FlappyBird(){
		
		JFrame jframe = new JFrame();
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
		
	}
}
