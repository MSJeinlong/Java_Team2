package game.tank;

import game.tank.entity.Tank;

/**
 * 坦克的移动方式：
 * 路线选择，速度等
 * @author 0321
 *
 */
public interface Move {
	
	void move(Tank tank,int speed,Scene scene);
}
