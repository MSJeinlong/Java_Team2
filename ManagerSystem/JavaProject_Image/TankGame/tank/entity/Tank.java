package game.tank.entity;


import game.tank.util.Consts;
import game.tank.util.Utils;
import game.tank.BulletFly;
import game.tank.ImageManager;
import game.tank.MapManager;
import game.tank.RandomMove;
import game.tank.Scene;
import game.tank.Move;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class Tank extends Cell{
	
	protected long id = 0;	
	protected int direction = 1;
	protected Bullet bullet = null;
	/** ÊÇ·ñµçÄÔ */
	protected boolean computer = false;
	protected Scene scene;
	protected Tank self;
	protected boolean alive = true;
	protected Point lastPosition;
	protected Move move;
	protected int hp = 2;
	protected int speed = 100;
	protected double degree = 0;
	
	public Tank(int horizon,int vertical,int direction,boolean computer){
		this.horizon = horizon;
		this.vertical = vertical;
		this.setDirection(direction);
		img = ImageManager.getInstance().getMainTank();
		rectangle = new Rectangle(horizon,vertical,img.getWidth(null),img.getHeight(null));
		lastPosition = new Point(horizon,vertical);
		self = this;
		type=15;
		id = Utils.getTankIdAndIncre();
		move = new RandomMove();
		this.computer = computer;
	}
	
	public void go(){
		MapManager mapMgr = MapManager.getInstance();
		this.scene = mapMgr.getScene(mapMgr.getCurrentRound());
		if(computer) {
			new Thread(new Moving(),this+"[move]").start();
			new Thread(new Fire(),this+"[fire]").start();
		}
	}
	
	public void drawSelf(Graphics2D g2d) {  
		int width = getImg().getWidth(null);
		int height = getImg().getHeight(null);	
		g2d.rotate(degree, horizon + width/2, vertical+ height/2);  
		g2d.drawImage(getImg(),horizon,vertical, null);	
		g2d.rotate(-degree,horizon + width/2, vertical+ height/2);  
	}
	
	public void undo(){
		this.setHorizon(lastPosition.x);
		this.setVertical(lastPosition.y);
		rectangle.setLocation(lastPosition);
	}
	
	class Moving implements Runnable{	
		boolean fire = false;
		public void run(){
			
			while(true) {
				if(!alive || MapManager.getInstance().getGameResult() != 0)
					break;
				move.move(self,getSpeed(),scene);
			}
		}
	}
	
	class Fire implements Runnable{

		public void run(){

			while(true){
				if(!alive  ||  MapManager.getInstance().getGameResult() != 0){
					return;
				}
				if(self.getBullet() != null){
					continue;
				}
				try {
					Bullet bullet = self.readyTofire();
					BulletFly bulletFly = new BulletFly();
					bulletFly.flyBullet(self,scene);
					Thread.sleep(1500);	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public Bullet readyTofire(){		
		int x = this.getHorizon();
		int y = this.getVertical();	
		int bulletWidth = 10;
		int bulletHeight = 10;
		int bulletDirection = direction;
		switch(bulletDirection){
			 case 1: 
				 x += Consts.CS - bulletWidth/2 ; 
				 break;
			 
			 case 2:
				 x += Consts.CS - bulletWidth/2;
				 y += Consts.CS * 2;
				 break;
			
			 case 3:
				 
				 y += Consts.CS - bulletHeight/2;
				 break;
				 
			 case 4:
				 
				 x += Consts.CS * 2;
				 y += Consts.CS - bulletHeight/2;
				 break;
		}
		bullet = new Bullet(x,y);
		bullet.setDirection(bulletDirection);
		return bullet;
		
	}
	
	public void reset(){
		this.horizon = 160;
		this.vertical = 464;
		this.hp = 1;
	}
	
	public Tank copy(){
		Tank copy = new Tank(this.getHorizon(),this.getVertical(),this.getDirection(),false);
		copy.setId(this.getId());
		return copy;
	}
	
	public void setImg(String imgUrl) {
		ImageIcon tankIcon = new ImageIcon(getClass().getResource(imgUrl));
		this.img = tankIcon.getImage();
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
		//this.setImg(getImagePools().get(direction));
		if(direction == 1){
			degree = 0;
		}else if(direction == 2){
			degree = Math.PI;
		}else if(direction == 3){
			degree = -Math.PI/2;
		}else if(direction == 4){
			degree = Math.PI/2;
		}
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public boolean isComputer() {
		return computer;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Point getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSpeed() {
		return speed;
	}

}
