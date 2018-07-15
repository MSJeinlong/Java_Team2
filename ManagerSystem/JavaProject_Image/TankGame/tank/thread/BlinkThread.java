package game.tank.thread;

import game.tank.entity.Blink;
import game.tank.ImageManager;
import game.tank.Scene;
import game.tank.entity.Tank;

import java.awt.Rectangle;
import java.util.List;

public class BlinkThread extends Thread{
	
	private Scene scene;
	
	public BlinkThread(Scene scene){
		this.scene = scene;
	}
	public void run(){
		List<Tank> tankList = scene.getTankList();
		List<Tank> waitedTanks = scene.getWaitedTanks();
		if(waitedTanks == null || waitedTanks.size()==0){
			return;
		}
		Tank newTank = waitedTanks.get(0);
		Blink blink = new Blink(newTank.getHorizon(),newTank.getVertical());
		scene.addBlink(blink);
		for(int i=0;i<8;i++){
			if(i%2==0){
				blink.setImg(ImageManager.getInstance().getBlinkExp());
			}else{
				blink.setImg(ImageManager.getInstance().getBlink());
			}
			try {
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		scene.removeBlink(blink);
		// new! check collide
		Rectangle rec = new Rectangle(204,16,32,32);
		boolean collide = false;
		while(true) {
			for(Tank t:scene.getTankList()){
				if(t.isAlive() == true && rec.intersects(t.getRectangle())){
					System.out.println("blink lag");
					collide = true;
					return;// break?
				}
			}
			if(collide == false){
				break;
			}
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//
		System.out.println("join to battle");
		waitedTanks.remove(0);
		tankList.add(newTank);  
		//activate 
		newTank.go();
	}
}

