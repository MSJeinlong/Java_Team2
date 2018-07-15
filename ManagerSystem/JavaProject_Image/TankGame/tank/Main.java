package game.tank;

import game.tank.listener.DirectionListener;
import game.tank.listener.FireMouseListener;
import game.tank.ui.TankPanel;

import java.awt.Container;


import javax.swing.JFrame;

public class Main{

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Ì¹¿Ë´óÕ½");
		TankPanel panel = new TankPanel();
		panel.setFrame(frame);
		Container contentPane = frame.getContentPane();	
		contentPane.add(panel);
		
		frame.setLocation(50, 100);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		// add listener
		FireMouseListener fireListener = new FireMouseListener(panel);
		frame.addKeyListener(new DirectionListener(panel));
		frame.addMouseListener(fireListener);
		frame.setFocusable(true); //this line is necessary
		
	}


}
