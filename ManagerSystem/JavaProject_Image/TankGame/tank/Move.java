package game.tank;

import game.tank.entity.Tank;

/**
 * ̹�˵��ƶ���ʽ��
 * ·��ѡ���ٶȵ�
 * @author 0321
 *
 */
public interface Move {
	
	void move(Tank tank,int speed,Scene scene);
}
