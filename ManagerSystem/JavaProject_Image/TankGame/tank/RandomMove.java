package game.tank;

import game.tank.entity.Tank;
import game.tank.util.Utils;

public class RandomMove implements Move{
	
	public void move(Tank tank,int speed,Scene scene){
		int direction = (int)Math.round(Math.random()*4);
		if(direction == 0){
			direction = 4;
		}
		//if(direction != 0) {
			int step = (int)Math.round(Math.random()*96);
			Utils.moveTank(tank,step,direction,speed,scene);
		//}
	}
}
