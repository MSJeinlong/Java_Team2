package game.tank.entity;
import game.tank.ImageManager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet extends Cell{
	
	public static final double SPEED = 200; // µ•Œª£∫œÒÀÿ/∫¡√Î
	private int status = 0; // 1-blast,0-flying
	private int direction = 0;
	private double degree = 0;
	
	public Bullet(int horizon,int vertical){
		this.horizon = horizon;
		this.vertical = vertical;
		img = ImageManager.getInstance().getBullet();
		rectangle = new Rectangle(horizon,vertical,img.getWidth(null)+3,img.getHeight(null)+3);
	}
	
	public void drawSelf(Graphics2D g2d) {  
		int width = getImg().getWidth(null);
		int height = getImg().getHeight(null);	
		g2d.rotate(degree, horizon + width/2, vertical+ height/2);  
		g2d.drawImage(getImg(),horizon,vertical, null);	
		g2d.rotate(-degree,horizon + width/2, vertical+ height/2);  
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
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
	
	
}
