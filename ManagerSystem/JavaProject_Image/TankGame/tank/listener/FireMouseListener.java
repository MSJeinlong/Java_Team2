package game.tank.listener;


import game.tank.entity.Bullet;
import game.tank.BulletFly;
import game.tank.MapManager;
import game.tank.entity.Tank;
import game.tank.ui.TankPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FireMouseListener extends MouseAdapter{

	 private TankPanel tankPanel;
	 
	 private static Tank tank;
	 
	 public FireMouseListener(TankPanel tankPanel) {
		 this.tankPanel = tankPanel;
		 tank = tankPanel.getScene().getMainTank();
	 }
	
	 public void mouseClicked(MouseEvent e){	
		 if( MapManager.getInstance().getGameResult() != 0){
				return;
		 }
		 if(tank.getBullet() != null){
			 System.out.println("bullet still fly");
			 return;
		 }
		 Bullet bullet = tank.readyTofire();
		 //必须另起线程！
		 new Thread(){
			 public void run(){
				 BulletFly bulletFly = new BulletFly();
				 bulletFly.flyBullet(tank,tankPanel.getScene());
			 }
		 }.start();
	}
	 
	
	 
	public TankPanel getTankPanel() {
		return tankPanel;
	}

	public void setTankPanel(TankPanel tankPanel) {
		this.tankPanel = tankPanel;
	}

	public static void setTank(Tank t) {
		tank = t;
	}
	 
	
}
