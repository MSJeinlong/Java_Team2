package Demo6;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//用于记录从文件中读取出来的坦克坐标与坦克方向
class Node {
	int x;
	int y;
	int direct;

	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}

// 记录类,同时也可以记录玩家的游戏设置
class Recorder {
	// 记录每关有多少敌人
	private static int enemNum;
	// 玩家的坦克生命数量
	private static int mylife = 3;
	// 记录总共消灭了多少敌人坦克
	private static int elienemNum = 0;
	// 记录游戏是否暂停
	private static boolean pause = false;

	// 从文件中回复记录点
	private static Vector<Node> nodes = new Vector<Node>();

	// 保存玩家记录到文件
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;

	// 从文件中读取玩家记录
	private static FileReader fr = null;
	private static BufferedReader br = null;

	// 获取面板的坦克，用于存盘退出
	private static Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 获取玩家的坦克信息
	private static Player player = null;

	public static void setPlayer(Player player) {
		Recorder.player = player;
	}

	public static Vector<EnemyTank> getEts() {
		return ets;
	}

	public static void setEts(Vector<EnemyTank> vv) {
		ets = vv;
	}

	// 读取进度方法
	public static void getNodeAndEnNums() {
		try {
			fr = new FileReader("H:\\TankGame_Recorder\\recorder1.txt");
			br = new BufferedReader(fr);
			String n = "";
			// 先读取前 4 行数据(游戏暂停状态，玩家成绩，敌我坦克生命数量)
			n = br.readLine();
			// 判断游戏的暂停状态
			if (Integer.parseInt(n) == 0) {
				pause = false;
			} else {
				pause = true;
			}
			n = br.readLine();
			elienemNum = Integer.parseInt(n);
			n = br.readLine();
			enemNum = Integer.parseInt(n);
			n = br.readLine();
			mylife = Integer.parseInt(n);

			// 接着读取玩家坦克的坐标
			n = br.readLine();
			String[] str = n.split(" ");
			System.out.println(str.length);
			Node node0 = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			nodes.add(node0);

			// 然后再循环读取每辆敌人坦克的坐标
			while ((n = br.readLine()) != null) {
				String[] xyz = n.split(" ");
				System.out.println(xyz.length);
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void keepRecAndEnemyTank() {
		try {
			// 创建保存数据的文件
			fw = new FileWriter("H:\\TankGame_Recorder\\recorder1.txt");
			bw = new BufferedWriter(fw);
			// 记录游戏暂停状态
			if (pause) {
				bw.write(1 + "\r\n");
			} else {
				bw.write(0 + "\r\n");
			}

			// 保存玩家的相关信息
			bw.write(elienemNum + "\r\n");
			bw.write(enemNum + "\r\n");
			bw.write(mylife + "\r\n");

			// 保存玩家坦克的坐标
			String s1 = player.getX() + " " + player.getY() + " " + player.getDirect();
			bw.write(s1 + "\r\n");
			// 保存当前活的敌人坦克的坐标和方向
			for (int i = 0; i < ets.size(); i++) {
				// 取出每一个坦克
				EnemyTank et = ets.get(i);

				if (et.isLive) {
					// 活的坦克就保存
					String recorder = et.x + " " + et.y + " " + et.direct;

					// 写入
					bw.write(recorder + "\r\n");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭文件流
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// // 从文件中读取记录
	// public static void getRecording() {
	// try {
	// fr = new FileReader("H:\\TankGame_Recorder\\recorder.txt");
	// br = new BufferedReader(fr);
	//
	// // 先把文件中的记录读取到缓冲区
	// // 用字符串暂存从缓冲区中读取的每一行数据
	// // 再将每一行的数据通过赋值给相关的属性
	// String n = "";
	// n = br.readLine();
	// elienemNum = Integer.parseInt(n);
	// n = br.readLine();
	// enemNum = Integer.parseInt(n);
	// n = br.readLine();
	// mylife = Integer.parseInt(n);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// try {
	// br.close();
	// fr.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Recorder.pause = pause;
	}

	public static int getElienemNum() {
		return elienemNum;
	}

	public static void setElienemNum(int elienemNum) {
		Recorder.elienemNum = elienemNum;
	}

	public static int getEnemNum() {
		return enemNum;
	}

	public static void setEnemNum(int enemNum) {
		Recorder.enemNum = enemNum;
	}

	public static int getMylife() {
		return mylife;
	}

	public static Vector<Node> getNodes() {
		return nodes;
	}

	public static void setMylife(int mylife) {
		Recorder.mylife = mylife;
	}

	public static void reduceEnNum() {
		enemNum -= 1;
	}

	public static void reduceMylife() {
		mylife -= 1;
	}

	public static void addElienemNum() {
		elienemNum++;
	}
}

// 爆炸类
class Bomb {
	// 定义爆炸的坐标
	int x, y;
	// 炸弹的生命
	int life = 9;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 减少生命值
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// 播放声音的类
class PlayVoice extends Thread {
	private String filename;

	public PlayVoice(String wavfile) {
		filename = wavfile;
	}

	public void run() {
		File voicefile = new File(filename);

		AudioInputStream audiois = null;
		try {
			audiois = AudioSystem.getAudioInputStream(voicefile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		AudioFormat format = audiois.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		auline.start();
		int n = 0;
		byte[] abData = new byte[1024];

		try {
			while (n != -1) {
				n = audiois.read(abData, 0, abData.length);
				if (n > 0)
					auline.write(abData, 0, n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			auline.drain();
			auline.close();
		}
	}
}

// 炮弹类
class Shot implements Runnable {
	int x; // 炮弹的坐标
	int y;
	int direct; // 炮弹的方向
	int speed = 9; // 炮弹速度
	boolean isLive = true;
	boolean isPause = false;
	// 游戏区域的大小，用于判断是否触碰到了边界
	int width;
	int height;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// 得到游戏的暂停状态
	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// 得到游戏边界的大小
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (this.isPause)
				continue;

			// 炮弹的移动
			switch (direct) {
			case 0:
				y -= speed;
				break;
			case 1:
				x += speed;
				break;
			case 2:
				y += speed;
				break;
			case 3:
				x -= speed;
				break;
			}
			// 炮弹何时消失
			// System.out.println("炮弹坐标 x=" + x + ", y=" + y);

			// 判断炮弹是否碰到边缘
			if (x < 0 || x > width || y < 0 || y > height) {
				this.isLive = false;
				break;
			}
		}
	}
}

// 坦克类--父类
class Tank {
	// 画出坦克时的参照点，即炮台的圆心
	int x = 0; // 坦克的横坐标
	int y = 0; // 坦克的纵坐标

	// 坦克方法
	// 0表示上 1表示右 2 表示下 3左
	int direct = 0;
	int Color; // 坦克的颜色

	// 坦克的子弹集合
	Vector<Shot> ss = new Vector<Shot>();

	// 坦克的速度
	int speed = 2;
	boolean isLive = true; // 坦克是否存在
	boolean isPause = false; // 游戏是否暂停，若游戏暂停，坦克停止运动

	// 游戏区域的大小，用于判断是否触碰到了边界
	int width;
	int height;

	// 定义一个向量，可以存放敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// 定义一个玩家坦克，用于判断是否碰撞
	Player player = null;

	// 定义一个障碍物，用于判断是否碰到了障碍物
	Barriers br = null;

	public void setBarrier(Barriers br) {
		this.br = br;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 获取坦克的坐标，设置坦克的坐标
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getDirect() {
		return direct;
	}

	public void setColor(int Color) {
		this.Color = Color;
	}

	public int getColor() {
		return Color;
	}

	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// 得到游戏边界的大小
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// 得到MyPanel的敌人坦克向量
	public void getEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 得到我的坦克
	public void getPlayer(Player hero) {
		this.player = hero;
	}

	// 判断是否碰到不可穿越的障碍物
	public boolean isTouchBarrier() {

		// 先判断该坦克的方向
		switch (this.direct) {
		case 0:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
			}

			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
			}
			break;

		// 坦克向右
		case 1:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y - 10 >= sw.y
						&& this.y - 10 < sw.y + sw.height)
					return true;
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y + 10 >= sw.y
						&& this.y + 10 < sw.y + sw.height)
					return true;
			}

			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y - 10 >= rw.y
						&& this.y - 10 < rw.y + rw.height)
					return true;
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y + 10 >= rw.y
						&& this.y + 10 < rw.y + rw.height)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y - 10 >= wt.y
						&& this.y - 10 < wt.y + wt.height)
					return true;
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y + 10 >= wt.y
						&& this.y + 10 <= wt.y + wt.height)
					return true;
			}
			break;

		// 坦克向下
		case 2:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
			}
			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
			}
			break;
		// 坦克向左
		case 3:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y - 10 >= sw.y && this.y - 10 <= sw.y + 20)
					return true;
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y + 10 >= sw.y && this.y + 10 <= sw.y + 20)
					return true;
			}
			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y - 10 >= rw.y && this.y - 10 <= rw.y + 10)
					return true;
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y + 10 >= rw.y && this.y + 10 <= rw.y + 10)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 15 >= wt.x && this.x - 15 <= wt.x + 20 && this.y - 10 >= wt.y && this.y - 10 <= wt.y + 20)
					return true;
				if (this.x - 15 >= wt.x && this.x - 15 <= wt.x + 20 && this.y + 10 >= wt.y && this.y + 10 <= wt.y + 20)
					return true;
			}
			break;
		}
		return false;
	}

	// 判断是否碰到其他坦克(包括玩家坦克和敌人坦克)
	public boolean isTouchOtherTank() {
		switch (this.direct) {
		case 0:
			// 该坦克向上
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 10 >= et.x - 10 && this.x - 10 <= et.x + 10 && this.y - 15 >= et.y - 15
								&& this.y - 15 <= et.y + 15) {
							return true;
						}
						if (this.x + 10 >= et.x - 10 && this.x + 10 <= et.x + 10 && this.y - 15 >= et.y - 15
								&& this.y - 15 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 10 >= et.x - 15 && this.x - 10 <= et.x + 15 && this.y - 15 >= et.y - 10
								&& this.y - 15 <= et.y + 10) {
							return true;
						}
						if (this.x + 10 >= et.x - 15 && this.x + 10 <= et.x + 15 && this.y - 15 >= et.y - 10
								&& this.y - 15 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 10 >= player.x - 10 && this.x - 10 <= player.x + 10 && this.y - 15 >= player.y - 15
							&& this.y - 15 <= player.y + 15) {
						return true;
					}
					if (this.x + 10 >= player.x - 10 && this.x + 10 <= player.x + 10 && this.y - 15 >= player.y - 15
							&& this.y - 15 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 10 >= player.x - 15 && this.x - 10 <= player.x + 15 && this.y - 15 >= player.y - 10
							&& this.y - 15 <= player.y + 10) {
						return true;
					}
					if (this.x + 10 >= player.x - 15 && this.x + 10 <= player.x + 15 && this.y - 15 >= player.y - 10
							&& this.y - 15 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 1:
			// 该坦克向右
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x + 15 >= et.x - 10 && this.x + 15 <= et.x + 10 && this.y - 10 >= et.y - 15
								&& this.y - 10 <= et.y + 15) {
							return true;
						}
						if (this.x + 15 >= et.x - 10 && this.x + 15 <= et.x + 10 && this.y + 10 >= et.y - 15
								&& this.y + 10 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x + 15 >= et.x - 15 && this.x + 15 <= et.x + 15 && this.y - 10 >= et.y - 10
								&& this.y - 10 <= et.y + 10) {
							return true;
						}
						if (this.x + 15 >= et.x - 15 && this.x + 15 <= et.x + 15 && this.y + 10 >= et.y - 10
								&& this.y + 10 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			// 判断是否与我的坦克发生碰撞
			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x + 15 >= player.x - 10 && this.x + 15 <= player.x + 10 && this.y - 10 >= player.y - 15
							&& this.y - 10 <= player.y + 15) {
						return true;
					}
					if (this.x + 15 >= player.x - 10 && this.x + 15 <= player.x + 10 && this.y + 10 >= player.y - 15
							&& this.y + 10 <= player.y + 15) {
						return true;
					}
				}

				if (player.direct == 1 || player.direct == 3) {
					if (this.x + 15 >= player.x - 15 && this.x + 15 <= player.x + 15 && this.y - 10 >= player.y - 10
							&& this.y - 10 <= player.y + 10) {
						return true;
					}
					if (this.x + 15 >= player.x - 15 && this.x + 15 <= player.x + 15 && this.y + 10 >= player.y - 10
							&& this.y + 10 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 2:
			// 该坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 10 >= et.x - 10 && this.x - 10 <= et.x + 10 && this.y + 15 >= et.y - 15
								&& this.y + 15 <= et.y + 15) {
							return true;
						}
						if (this.x + 10 >= et.x - 10 && this.x + 10 <= et.x + 10 && this.y + 15 >= et.y - 15
								&& this.y + 15 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 10 >= et.x - 15 && this.x - 10 <= et.x + 15 && this.y + 15 >= et.y - 10
								&& this.y + 15 <= et.y + 10) {
							return true;
						}
						if (this.x + 10 >= et.x - 15 && this.x + 10 <= et.x + 15 && this.y + 15 >= et.y - 10
								&& this.y + 15 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 10 >= player.x - 10 && this.x - 10 <= player.x + 10 && this.y + 15 >= player.y - 15
							&& this.y + 15 <= player.y + 15) {
						return true;
					}
					if (this.x + 10 >= player.x - 10 && this.x + 10 <= player.x + 10 && this.y + 15 >= player.y - 15
							&& this.y + 15 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 10 >= player.x - 15 && this.x - 10 <= player.x + 15 && this.y + 15 >= player.y - 10
							&& this.y + 15 <= player.y + 10) {
						return true;
					}
					if (this.x + 10 >= player.x - 15 && this.x + 10 <= player.x + 15 && this.y + 15 >= player.y - 10
							&& this.y + 15 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 3:
			// 该坦克向左
			// 取出每一个敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 15 >= et.x - 10 && this.x - 15 <= et.x + 10 && this.y - 10 >= et.y - 15
								&& this.y - 10 <= et.y + 15) {
							return true;
						}
						if (this.x - 15 >= et.x - 10 && this.x - 15 <= et.x + 10 && this.y + 10 >= et.y - 15
								&& this.y + 10 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 15 >= et.x - 15 && this.x - 15 <= et.x + 15 && this.y - 10 >= et.y - 10
								&& this.y - 10 <= et.y + 10) {
							return true;
						}
						if (this.x - 15 >= et.x - 15 && this.x - 15 <= et.x + 15 && this.y + 10 >= et.y - 10
								&& this.y + 10 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			// 判断是否与我的坦克相撞
			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 15 >= player.x - 10 && this.x - 15 <= player.x + 10 && this.y - 10 >= player.y - 15
							&& this.y - 10 <= player.y + 15) {
						return true;
					}
					if (this.x - 15 >= player.x - 10 && this.x - 15 <= player.x + 10 && this.y + 10 >= player.y - 15
							&& this.y + 10 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 15 >= player.x - 15 && this.x - 15 <= player.x + 15 && this.y - 10 >= player.y - 10
							&& this.y - 10 <= player.y + 10) {
						return true;
					}
					if (this.x - 15 >= player.x - 15 && this.x - 15 <= player.x + 15 && this.y + 10 >= player.y - 10
							&& this.y + 10 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		}

		return false;
	}
}

// 我的坦克
class Player extends Tank {

	// 坦克的炮弹
	Shot s = null;

	// 玩家坦克的生命条数，默认为3条命
	int life = 3;

	public Player(int x, int y) {
		super(x, y); // 调用父类的构造方法进行初始化
		// 设置玩家坦克的速度
		this.speed = 3;
	}

	// 开火
	public void shotEnemy() {

		switch (this.direct) {
		case 0:
			s = new Shot(x, y - 15, 0);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x + 15, y, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x, y + 15, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x - 15, y, 3);
			ss.add(s);
			break;
		}

		s.getWidth_height(width, height);
		// 启动炮弹线程
		Thread t = new Thread(s);
		t.start();
	}

	// 坦克向上移动
	public void moveUp() {
		y -= speed;
	}

	// 坦克向右移动
	public void moveRight() {
		x += speed;
	}

	// 坦克向下移动
	public void moveDown() {
		y += speed;
	}

	// 坦克向左移动
	public void moveLeft() {
		x -= speed;
	}
}

// 敌人坦克
class EnemyTank extends Tank implements Runnable {

	// 敌人添加子弹，应当在刚刚创建坦克时就给它一颗子弹
	int times = 0;

	// 定义敌人坦克的声音类
	// PlayVoice pv = new
	// PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\enemy.move.wav");

	public EnemyTank(int x, int y) {
		super(x, y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			switch (this.direct) {
			case 0:
				// 说明坦克正在向上
				for (int i = 0; i < 30; i++) {
					if (y - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
						break;
					if (!this.isPause) {// 坦克连续平滑移动时，需要时刻判断游戏是否暂停了
						y -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				break;
			case 1:
				// 向右
				for (int i = 0; i < 30; i++) {
					if (x + 15 > width || this.isTouchOtherTank() || this.isTouchBarrier())
						break;
					if (!this.isPause) {
						x += speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				// 向下
				for (int i = 0; i < 30; i++) {
					if (y + 15 > height || this.isTouchOtherTank() || this.isTouchBarrier())
						break;
					if (!this.isPause) {
						y += speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case 3:
				// 向左
				for (int i = 0; i < 30; i++) {
					if (x - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
						break;
					if (!this.isPause) {
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			}

			if (this.isPause) // 改变坦克方向或者添加子弹时，判断游戏是否暂停
				continue;

			this.times++;
			if (times % 2 == 0) {
				if (isLive) {
					if (ss.size() < 2) {
						Shot s = null;
						// 没有子弹，添加
						switch (direct) {
						case 0:
							s = new Shot(x, y - 15, 0);
							// s.getWidth_height(width, height);
							ss.add(s);
							break;
						case 1:
							s = new Shot(x + 15, y, 1);
							// s.getWidth_height(width, height);
							ss.add(s);
							break;
						case 2:
							s = new Shot(x, y + 15, 2);
							// s.getWidth_height(width, height);
							ss.add(s);
							break;
						case 3:
							s = new Shot(x - 15, y, 3);
							// s.getWidth_height(width, height);
							ss.add(s);
							break;
						}

						// 得到游戏边界的信息
						s.getWidth_height(width, height);
						// 启动子弹线程
						Thread t = new Thread(s);
						t.start();
					}
				}
			}

			// 让坦克随机产生一个新的方向
			this.direct = (int) (Math.random() * 4);

			// 判断敌人坦克是否死亡
			if (this.isLive == false) {

				// 让坦克死亡后，退出线程
				// 应同时remove掉该坦克的所有子弹
				break;
			}

			// System.out.println(width + " " + height);
		}
	}
}

// 障碍物
class Barriers {

	// 定义土墙集合
	Vector<SoilWall> soilwalls = new Vector<SoilWall>();
	// 定义铁墙障碍物集合
	Vector<IronWall> ironwalls = new Vector<IronWall>();
	// 定义水坑集合
	Vector<Water> waters = new Vector<Water>();

	public Barriers() {

		int k = 1;
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
			Water water = waters.get(i);
			if (water.isLive)
				water.draw(g);
			else
				waters.remove(water);
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
