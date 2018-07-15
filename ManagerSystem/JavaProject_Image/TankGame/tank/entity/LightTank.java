package game.tank.entity;
import game.tank.ImageManager;
public class LightTank extends Tank{
	
	private int hp = 1;
	private int speed = 100;
	
	public LightTank(int horizon,int vertical,int direction,boolean computer){
		super(horizon,vertical,direction,computer);
		img = ImageManager.getInstance().getLightTank();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
