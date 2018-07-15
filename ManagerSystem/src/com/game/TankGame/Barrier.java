package com.game.TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

//�ϰ���
class Barriers {

	// ������ǽ����
	Vector<SoilWall> soilwalls = new Vector<SoilWall>();
	// ������ǽ�ϰ��Ｏ��
	Vector<IronWall> ironwalls = new Vector<IronWall>();
	// ����ˮ�Ӽ���
	Vector<Water> waters = new Vector<Water>();

	// ������Ϸʱ���õĹ��췽��
	public Barriers(Vector<Node> nodes_iron, Vector<Node> nodes_soil, Vector<Node> nodes_water) {
		// ���ݼ�¼������ǽ
		for (int i = 0; i < nodes_iron.size(); i++) {
			Node node = nodes_iron.get(i);
			// System.out.println(node.x + " " + node.y);
			IronWall iw = new IronWall(node.x, node.y);
			ironwalls.add(iw);
		}

		// ���ݼ�¼������ǽ
		for (int i = 0; i < nodes_soil.size(); i++) {
			Node node = nodes_soil.get(i);
			// System.out.println(node.x + " " + node.y);
			SoilWall sw = new SoilWall(node.x, node.y);
			soilwalls.add(sw);
		}

		System.out.println("ˮ���ϵ�����" + nodes_water.size());
		// ���ݼ�¼����ˮ��
		for (int i = 0; i < nodes_water.size(); i++) {
			Node node = nodes_water.get(i);
			System.out.println("����ˮ");
			// System.out.println(node.x + " " + node.y);
			Water water = new Water(node.x, node.y);
			waters.add(water);
		}
	}

	// ����Ϸ������һ�صĹ��췽��
	public Barriers(int stage) {
		int k = 1;
		switch (stage) {
		case 1:
			// ������ǽ����
			for (int i = 0; i < 20; i++) {
				if (i == 10)
					k = 2;
				for (int j = 0; j < 2; j++) {
					SoilWall sw = new SoilWall(50 * k + i * 10, 100 - j * 20);
					soilwalls.add(sw);
				}
			}

			k = 1;
			// ������ǽ����
			for (int i = 0; i < 20; i++) {
				if (i == 10)
					k = 2;
				for (int j = 0; j < 2; j++) {
					IronWall iw = new IronWall(50 * k + i * 10, 200 + j * 10);
					ironwalls.addElement(iw);
				}
			}

			k = 1;
			// ����ˮ�Ӽ���
			for (int i = 0; i < 10; i++) {
				if (i == 5)
					k = 2;
				Water water = new Water(50 * k + i * 20, 150);
				waters.add(water);
			}
			break;
		case 2:
			// ������ǽ����
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

			// ����ˮ�Ӽ���
			for (int i = 0; i < 10; i++) {
				Water water = new Water(80 + i * 20, 150);
				waters.add(water);
				if (i < 4) {
					System.out.println(i);
					water = new Water(240 + i * 20, 40);
					waters.add(water);
				}
			}

			// ������ǽ����
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

	// ���������ϰ���
	public void drawBarrier(Graphics g) {

		// ������ǽ
		for (int i = 0; i < soilwalls.size(); i++) {
			SoilWall sw = soilwalls.get(i);
			// ����ǽ���ڣ��ͻ���
			if (sw.isLive)
				sw.draw(g);
			// �����ڣ���ɾ��
			else
				soilwalls.remove(sw);
		}

		// ������ǽ
		for (int i = 0; i < ironwalls.size(); i++) {
			IronWall iw = ironwalls.get(i);
			if (iw.isLive)
				iw.draw(g);
			else
				ironwalls.remove(iw);
		}

		// ����ˮ��
		for (int i = 0; i < waters.size(); i++) {
			// System.out.println("��ˮ");
			Water water = waters.get(i);
			water.draw(g);
		}
	}

	// ������ϰ���
	abstract class Obstacle {
		// �ϰ��������
		int x;
		int y;
		// �ϰ���Ĵ�С
		int width;
		int height;

		// �ϰ����Ƿ����
		boolean isLive = true;

		// ���췽��
		Obstacle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// �����ϰ���ķ���
		public abstract void draw(Graphics g);
	}

	// ��ǽ
	class SoilWall extends Obstacle {

		public SoilWall(int x, int y) {
			super(x, y);
			this.width = 10;
			this.height = 20;
		}

		// ������ǽ
		public void draw(Graphics g) {
			g.setColor(new Color(205, 133, 63));
			g.fill3DRect(x, y, width, height, true);
		}
	}

	// ��ǽ
	class IronWall extends Obstacle {

		public IronWall(int x, int y) {
			super(x, y);
			this.width = 10;
			this.height = 10;
		}

		// ������ǽ
		public void draw(Graphics g) {
			g.setColor(Color.WHITE);
			g.fill3DRect(x, y, width, height, true);
		}
	}

	// ˮ��
	class Water extends Obstacle {

		public Water(int x, int y) {
			super(x, y);
			this.width = 20;
			this.height = 20;
		}

		// ����ˮ��
		public void draw(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.WHITE);
			g.fillOval(x + 2, y + 2, 3, 3);
			g.fillOval(x + 13, y + 13, 3, 3);
		}
	}

}
