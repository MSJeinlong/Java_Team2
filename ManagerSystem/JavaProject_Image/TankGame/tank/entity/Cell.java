package game.tank.entity;
import game.tank.ImageManager;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Cell{
	
	protected int horizon;
	
	protected int vertical;
	
	protected Image img;
	
	protected int type;
	
	protected Rectangle rectangle;

	private int col;
	
	private int row;
	
	public Cell(){
		
	}
	
	public Cell(int horizon,int vertical,int type){
		this.horizon = horizon;
		this.vertical = vertical;
		img = ImageManager.getInstance().getImageByType(type);
		rectangle = new Rectangle(horizon,vertical,img.getWidth(null),img.getHeight(null));
		this.type = type;
	}
	
	public static void main(String[] args){
		Cell c = new Cell(1,1,1);
		System.out.println(c.getRectangle().getX());
		Rectangle r = (Rectangle)c.getRectangle().clone();
		r.setLocation(3, 3);
		System.out.println(c.getRectangle().getX());
		System.out.println("r:"+r.getX());
		//changeLocation(r,1);
		System.out.println("r:"+r.getY()+","+c.getRectangle().getY());
	}

	public void undo(){
		
	}
	
	
	public int getHorizon() {
		return horizon;
	}

	public void setHorizon(int horizon) {
		this.horizon = horizon;
		this.rectangle.x = horizon;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
		this.rectangle.y = vertical;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(String imgUrl) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imgUrl));
		this.img = icon.getImage();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
