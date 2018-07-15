package com.game.TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

//障碍物
class Barriers {

	// 定义土墙集合
	Vector<SoilWall> soilwalls = new Vector<SoilWall>();
	// 定义铁墙障碍物集合
	Vector<IronWall> ironwalls = new Vector<IronWall>();
	// 定义水坑集合
	Vector<Water> waters = new Vector<Water>();

	// 继续游戏时调用的构造方法
	public Barriers(Vector<Node> nodes_iron, Vector<Node> nodes_soil, Vector<Node> nodes_water) {
		// 根据记录生成铁墙
		for (int i = 0; i < nodes_iron.size(); i++) {
			Node node = nodes_iron.get(i);
			// System.out.println(node.x + " " + node.y);
			IronWall iw = new IronWall(node.x, node.y);
			ironwalls.add(iw);
		}

		// 根据记录生成土墙
		for (int i = 0; i < nodes_soil.size(); i++) {
			Node node = nodes_soil.get(i);
			// System.out.println(node.x + " " + node.y);
			SoilWall sw = new SoilWall(node.x, node.y);
			soilwalls.add(sw);
		}

		System.out.println("水集合的数量" + nodes_water.size());
		// 根据记录生成水坑
		for (int i = 0; i < nodes_water.size(); i++) {
			Node node = nodes_water.get(i);
			System.out.println("生成水");
			// System.out.println(node.x + " " + node.y);
			Water water = new Water(node.x, node.y);
			waters.add(water);
		}
	}

	// 新游戏或者下一关的构造方法
	public Barriers(int stage) {
		int k = 1;
		switch (stage) {
		case 1:
			// 创建土墙集合
			for (int i = 0; i < 20; i++) {
				if (i == 10)
					k = 2;
				for (int j = 0; j < 2; j++) {
					SoilWall sw = new SoilWall(50 * k + i * 10, 100 - j * 20);
					soilwalls.add(sw);
				}
			}

			k = 1;
			// 创建铁墙集合
			for (int i = 0; i < 20; i++) {
				if (i == 10)
					k = 2;
				for (int j = 0; j < 2; j++) {
					IronWall iw = new IronWall(50 * k + i * 10, 200 + j * 10);
					ironwalls.addElement(iw);
				}
			}

			k = 1;
			// 创建水坑集合
			for (int i = 0; i < 10; i++) {
				if (i == 5)
					k = 2;
				Water water = new Water(50 * k + i * 20, 150);
				waters.add(water);
			}
			break;
		case 2:
			// 创建土墙集合
			for (int i = 0; i < 26; i++) {
				if (i == 13)
					k = 2;
				for (int j = 0; j < 2; j++) {
					SoilWall sw = new SoilWall(30 * k + i * 10, 100 - j * 20);
					soilwalls.add(sw);
					sw = new SoilWall(30 * k + i * 10, 220 - j * 20);
					soilwalls.add(sw);
				}
			}

			// 创建水坑集合
			for (int i = 0; i < 10; i++) {
				Water water = new Water(80 + i * 20, 150);
				waters.add(water);
				if (i < 4) {
					System.out.println(i);
					water = new Water(240 + i * 20, 40);
					waters.add(water);
				}
			}

			// 创建铁墙集合
			for (int i = 0; i < 13; i++) {
				IronWall iw = new IronWall(30 + i * 10, 50);
				ironwalls.add(iw);
				iw = new IronWall(30 + i * 10, 240);
				ironwalls.add(iw);

				if (i < 5) {
					iw = new IronWall(340 + i * 10, 150);
					ironwalls.add(iw);
					iw = new IronWall(340 + i * 10, 160);
					ironwalls.add(iw);
				}
			}
			break;
		case 3:

			for (int i = 0; i < 7; i++) {
				switch (i) {
				case 0:
				case 1:
				case 5:
				case 6:
					for (int j = 0; j < 6; j++) {
						IronWall iw = new IronWall(165 + i * 10, 120 + j * 10);
						ironwalls.add(iw);
					}
					break;
				case 2:
				case 4:
					for (int j = 0; j < 4; j++) {
						IronWall iw = new IronWall(165 + i * 10, 130 + j * 10);
						ironwalls.add(iw);
						iw = new IronWall(220 + i * 10, 130 + j * 10);
						ironwalls.add(iw);
					}
					break;
				case 3:
					for (int j = 0; j < 6; j++) {
						IronWall iw = new IronWall(165 + i * 10, 110 + j * 10);
						ironwalls.add(iw);
					}
					break;
				}

			}

			for (int i = 0; i < 10; i++) {
				switch (i) {
				case 0:
				case 1:
				case 8:
				case 9:
					for (int j = 0; j < 5; j++) {
						SoilWall sw = new SoilWall(40 + i * 10, 120 + j * 20);
						soilwalls.add(sw);
					}
					break;
				case 2:
				case 3:
				case 6:
				case 7:
					for (int j = 0; j < 3; j++) {
						SoilWall sw = new SoilWall(40 + i * 10, 140 + j * 20);
						soilwalls.add(sw);
					}
					break;
				case 4:
				case 5:
					for (int j = 0; j < 5; j++) {
						SoilWall sw = new SoilWall(40 + i * 10, 100 + j * 20);
						soilwalls.add(sw);
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 3; j++) {
					Water water = new Water(300 + i * 20, 100 + j * 50);
					waters.add(water);
					SoilWall sw = new SoilWall(300 + i * 10, 120 + j * 50);
					soilwalls.add(sw);
				}
			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 3; j++) {
					SoilWall sw = new SoilWall(300 + i * 10, 120 + j * 50);
					soilwalls.add(sw);
				}
			}
		}
	}

	// 画出各种障碍物
	public void drawBarrier(Graphics g) {

		// 画出土墙
		for (int i = 0; i < soilwalls.size(); i++) {
			SoilWall sw = soilwalls.get(i);
			// 该土墙存在，就画出
			if (sw.isLive)
				sw.draw(g);
			// 不存在，就删除
			else
				soilwalls.remove(sw);
		}

		// 画出铁墙
		for (int i = 0; i < ironwalls.size(); i++) {
			IronWall iw = ironwalls.get(i);
			if (iw.isLive)
				iw.draw(g);
			else
				ironwalls.remove(iw);
		}

		// 画出水坑
		for (int i = 0; i < waters.size(); i++) {
			// System.out.println("画水");
			Water water = waters.get(i);
			water.draw(g);
		}
	}

	// 抽象的障碍物
	abstract class Obstacle {
		// 障碍物的坐标
		int x;
		int y;
		// 障碍物的大小
		int width;
		int height;

		// 障碍物是否存在
		boolean isLive = true;

		// 构造方法
		Obstacle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 画出障碍物的方法
		public abstract void draw(Graphics g);
	}

	// 土墙
	class SoilWall extends Obstacle {

		public SoilWall(int x, int y) {
			super(x, y);
			this.width = 10;
			this.height = 20;
		}

		// 画出土墙
		public void draw(Graphics g) {
			g.setColor(new Color(205, 133, 63));
			g.fill3DRect(x, y, width, height, true);
		}
	}

	// 铁墙
	class IronWall extends Obstacle {

		public IronWall(int x, int y) {
			super(x, y);
			this.width = 10;
			this.height = 10;
		}

		// 画出铁墙
		public void draw(Graphics g) {
			g.setColor(Color.WHITE);
			g.fill3DRect(x, y, width, height, true);
		}
	}

	// 水坑
	class Water extends Obstacle {

		public Water(int x, int y) {
			super(x, y);
			this.width = 20;
			this.height = 20;
		}

		// 画出水坑
		public void draw(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.WHITE);
			g.fillOval(x + 2, y + 2, 3, 3);
			g.fillOval(x + 13, y + 13, 3, 3);
		}
	}

}
