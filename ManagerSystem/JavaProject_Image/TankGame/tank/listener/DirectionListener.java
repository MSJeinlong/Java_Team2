package game.tank.listener;

import game.tank.MapManager;
import game.tank.entity.Tank;
import game.tank.ui.TankPanel;
import game.tank.util.Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class DirectionListener extends KeyAdapter{
	
	private int k;
	
	private int direction = 0;
	
	private TankPanel tankPanel; 
	
	private boolean complete = true;
	
	public DirectionListener(TankPanel panel){
		tankPanel = panel;
	}
	
	public void keyPressed(KeyEvent e){
	
		if(MapManager.getInstance().getGameResult() != 0)
			return;
		k = e.getKeyCode();
		
		switch(k){
		case KeyEvent.VK_UP:
			direction = 1;
			break;
			
		case KeyEvent.VK_DOWN:
			direction = 2;
			break;
			
		case KeyEvent.VK_LEFT:
			direction = 3;
			break;
			
		case KeyEvent.VK_RIGHT:
			direction = 4;
			break;
			
		}
		
		if(!complete){
			return;
		}
		
		Thread animation = new Thread(new Runnable(){
			public void run(){
				complete = false;
				Tank main = tankPanel.getScene().getMainTank();
				Utils.moveTank(main,16,direction, main.getSpeed(),tankPanel.getScene());
				complete = true;
			}	
		});
		animation.start();
		
	}

}
