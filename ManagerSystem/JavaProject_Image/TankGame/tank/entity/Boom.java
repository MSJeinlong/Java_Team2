package game.tank.entity;
import game.tank.ImageManager;

import javax.swing.ImageIcon;

public class Boom extends Cell{
	
	public Boom(int x,int y){
		 this.horizon = x;
		 this.vertical = y;
		 img = ImageManager.getInstance().getBoom();
	}
}
