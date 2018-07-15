package game.tank;

import game.tank.entity.HeavyTank;
import game.tank.entity.LightTank;
import game.tank.entity.Tank;
import game.tank.util.TankType;

public class TankFactory {
	
	public static Tank buildTank(TankType type,int x,int y,int direction,boolean isComputer){
		if(type == TankType.MAIN_TANK){
			return new Tank(x,y,direction,isComputer);
		}else if(type == TankType.HEAVY_TANK){
			return new HeavyTank(x,y,direction,isComputer);
		}else if(type == TankType.LIGHT_TANK){
			return new LightTank(x,y,direction,isComputer);
		}
		System.out.println("illegal tank type");
		return null;
	}
}
