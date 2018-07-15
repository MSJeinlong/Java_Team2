package game.tank;

import game.tank.entity.Blink;
import game.tank.entity.Boom;
import game.tank.entity.Cell;
import game.tank.entity.Tank;
import game.tank.util.Consts;

import java.util.ArrayList;
import java.util.List;
/**
 * 每一关的场景
 * @author Administrator
 *
 */
public class Scene {
	
	private int[][] map;
	
	private List<Cell> mapElements;
	/** 战场上的坦克 */
	private List<Tank> tankList;
	
	private List<Boom> booms;
	/** 等待中的坦克 */
	private List<Tank> waitedTanks;
	
	private List<Blink> blinkList;
	
	public Scene(){
		
	}
	
	public Scene(int round){
		map = new int[32][32];
		int[][] m = MapManager.getInstance().getAllMaps()[round-1];
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				this.map[i][j] = m[i][j];
			}
		}
		tankList = new ArrayList<Tank>();
		waitedTanks = new ArrayList<Tank>();
		mapElements = new ArrayList<Cell>();
		booms = new ArrayList<Boom>();
		blinkList = new ArrayList<Blink>();
		int type = 0;
		//地图元素入队列
		for(int i=0;i<map.length;i++) {
			for(int j=0; j< map[0].length;j++){
				type = map[i][j];
				if(type == 0 || type == 5){
					continue;
				}
				Cell cell = this.genMapElement(type, j, i);
				cell.setCol(j);
				cell.setRow(i);
				mapElements.add(cell);
			}
		}
	}
	
	private Cell genMapElement(int type,int col,int row){
		Cell element = new Cell();
		switch(type){
			case Consts.ELEMENT_TYPE_WALL:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_WALL);
				break;
				
			case Consts.ELEMENT_TYPE_STEEL:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_STEEL);
				break;
				
			case Consts.ELEMENT_TYPE_BASE:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_BASE);
				break;
				
			case Consts.ELEMENT_TYPE_EDGE:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_EDGE);
				break;
				
			case Consts.ELEMENT_TYPE_WHITEFLAG:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_WHITEFLAG);
				break;
				
			case Consts.ELEMENT_TYPE_LAKE:
				element = new Cell(col*Consts.CS,row*Consts.CS,Consts.ELEMENT_TYPE_LAKE);
				break;
		}
		return element;
	}
	
	public Tank getMainTank() {
		for(Tank tank: tankList){
			if(tank.isComputer() == false){
				return tank;
			}
		}
		System.out.println("Main tank not found!");
		return null;
	}

	public List<Tank> getTankList() {
		return tankList;
	}

	public void addTank(Tank t){
		tankList.add(t);
	}
	
	public void addTankList(List<Tank> list){
		tankList.addAll(list);
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public List<Cell> getMapElements() {
		return mapElements;
	}

	public void setMapElements(List<Cell> mapElements) {
		this.mapElements = mapElements;
	}

	public List<Boom> getBooms() {
		return booms;
	}

	public void addBoom(Boom b) {
		booms.add(b);
	}
	
	public void removeBoom(Boom b) {
		int index = booms.indexOf(b);
		if(index >= 0){
			booms.remove(index);
		}
	}

	public List<Tank> getWaitedTanks() {
		return waitedTanks;
	}
	
	public void addWaitedTank(Tank t){
		waitedTanks.add(t);
	}
	
	public void addWaitedTankList(List<Tank> list){
		waitedTanks.addAll(list);
	}
	
	public void addBlink(Blink b){
		blinkList.add(b);
	}
	
	public void removeBlink(Blink b){
		int index = blinkList.indexOf(b);
		if(index >= 0){
			blinkList.remove(index);
		}
	}

	public List<Blink> getBlinkList() {
		return blinkList;
	}
}
