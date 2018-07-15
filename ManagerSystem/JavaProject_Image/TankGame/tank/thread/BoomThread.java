package game.tank.thread;

import game.tank.entity.Boom;
import game.tank.Scene;

public class BoomThread extends Thread{
	
	private Boom  boom;
	private Scene scene;
	
	public BoomThread(Boom boom,Scene scene){
		this.boom = boom;
		this.scene = scene;
	}
	
	public void run(){
		scene.addBoom(boom);
		try{
			Thread.sleep(60);
		}catch(Exception e){
			e.printStackTrace();
		}
		scene.removeBoom(boom);
	}
}
