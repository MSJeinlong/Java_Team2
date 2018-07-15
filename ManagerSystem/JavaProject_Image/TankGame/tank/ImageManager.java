package game.tank;

import game.tank.util.Consts;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	
	  private Image brick;
	  private Image steel;
	  private Image eagle;
	  private Image lake;
	  private Image tank;	  
	  private Image background;  
	  private Image edge;  
	  private Image whiteFlag;  
	  private Image boom;
	  private Image bullet;
	  private Image blink;
	  private Image blinkExp;	  
	  private Image miniTank;
	  private Image roundflag;
	  private Image mainTank;
	  private Image heavyTank;
	  private Image lightTank;
	 
	  
	  private static ImageManager instance;
	  
	  public static ImageManager getInstance(){
		  if(instance == null){
			  return new ImageManager();
		  }
		  return instance;
	  }
	  
	  private ImageManager(){
		  bullet = loadImg("image/bullet.png");
		  brick = loadImg("image/lbrick.png");
		  steel =loadImg("image/steel.png");
		  eagle = loadImg("image/eagle.png");
		  tank = loadImg("image/myTank.png");
		  background = loadImg("image/bg.png");
		  edge = loadImg("image/edge.png");	  
		  whiteFlag = loadImg("image/white_flag.png");
		  boom = loadImg("image/boom.png");
		  blink = loadImg("image/blink.png");
		  blinkExp = loadImg("image/blinkExp2.png"); 
		  miniTank = loadImg("image/mini-tank.png"); 
		  roundflag = loadImg("image/flag.png");
		  mainTank =  loadImg("image/myTank.png");
		  heavyTank =  loadImg("image/heavy_tank.png");
		  lightTank =  loadImg("image/light_tank.png");
		  lake =  loadImg("image/lake.png");
	  }

	public Image getImageByType(int type){
		Image img = null;
		switch(type) {
			case Consts.ELEMENT_TYPE_ROAD:
				img = background;
				break;
				
			case Consts.ELEMENT_TYPE_WALL:
				img = brick;
				break;
				
			case Consts.ELEMENT_TYPE_STEEL:
				img = steel;
				break;	
				
			case Consts.ELEMENT_TYPE_BASE:
				img = eagle;
				break;
				
			case Consts.ELEMENT_TYPE_EDGE:
				img = edge;
				break;
				
			case Consts.ELEMENT_TYPE_NOTHING:
		
				break;
				
			case Consts.ELEMENT_TYPE_WHITEFLAG:
				img = whiteFlag;
				break;
				
			case Consts.ELEMENT_TYPE_MINITANK:
				img = miniTank;
				break;
				
			case Consts.ELEMENT_TYPE_LAKE:
				img = lake;
				break;
		}
		return img;
	}
	

	public Image loadImg(String imgUrl){
		 ImageIcon icon = new ImageIcon(getClass().getResource(imgUrl));
		 return icon.getImage();
	}

	public Image getSteel() {
		return steel;
	}

	public void setSteel(Image steel) {
		this.steel = steel;
	}

	public Image getEagle() {
		return eagle;
	}

	public void setEagle(Image eagle) {
		this.eagle = eagle;
	}

	public Image getTank() {
		return tank;
	}

	public void setTank(Image tank) {
		this.tank = tank;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public Image getEdge() {
		return edge;
	}

	public void setEdge(Image edge) {
		this.edge = edge;
	}

	public Image getWhiteFlag() {
		return whiteFlag;
	}

	public void setWhiteFlag(Image whiteFlag) {
		this.whiteFlag = whiteFlag;
	}

	public Image getBullet() {
		return bullet;
	}


	public void setBullet(Image bullet) {
		this.bullet = bullet;
	}

	public Image getBoom() {
		return boom;
	}

	public void setBoom(Image boom) {
		this.boom = boom;
	}

	public Image getBlink() {
		return blink;
	}

	public void setBlink(Image blink) {
		this.blink = blink;
	}

	public Image getBlinkExp() {
		return blinkExp;
	}

	public void setBlinkExp(Image blinkExp) {
		this.blinkExp = blinkExp;
	}

	public Image getRoundflag() {
		return roundflag;
	}

	public Image getMainTank() {
		return mainTank;
	}

	public Image getHeavyTank() {
		return heavyTank;
	}

	public Image getLightTank() {
		return lightTank;
	}

	public Image getLake() {
		return lake;
	}


}
