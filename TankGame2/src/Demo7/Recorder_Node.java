package Demo7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//用于记录从文件中读取出来的坦克坐标与坦克方向,障碍物坐标
class Node {
	int x;
	int y;
	int direct;

	// 读取进度时，记录坦克的坐标和方向信息
	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// 读取进度时，记录障碍物的坐标
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// 记录类,同时也可以记录玩家的游戏设置
class Recorder {
	// 记录每关有多少敌人
	private static int enemyNum;
	// 玩家的坦克生命数量
	private static int mylife;
	// 记录总共消灭了多少敌人坦克
	private static int elienemNum = 0;
	// 记录游戏是否暂停
	private static boolean pause = false;

	// 记录游戏关数
	private static int stage;

	// 从文件中回复记录点
	private static Vector<Node> nodes_tank = new Vector<Node>();
	private static Vector<Node> nodes_iron = new Vector<Node>();
	private static Vector<Node> nodes_soil = new Vector<Node>();
	private static Vector<Node> nodes_water = new Vector<Node>();

	// 保存玩家记录到文件
	private static FileWriter fw = null, fw1 = null;
	private static BufferedWriter bw = null, bw1 = null;

	// 从文件中读取玩家记录
	private static FileReader fr = null, fr1 = null;
	private static BufferedReader br = null, br1 = null;

	// 获取面板的坦克，用于存盘退出
	private static Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 获取玩家的坦克信息
	private static Player player = null;

	// 获取面板的地图信息
	private static Barriers barriers = null;

	public static Vector<Node> getNodes_tank() {
		return nodes_tank;
	}

	public static void setNodes_tank(Vector<Node> nodes_tank) {
		Recorder.nodes_tank = nodes_tank;
	}

	public static Vector<Node> getNodes_iron() {
		return nodes_iron;
	}

	public static void setNodes_iron(Vector<Node> nodes_iron) {
		Recorder.nodes_iron = nodes_iron;
	}

	public static Vector<Node> getNodes_soil() {
		return nodes_soil;
	}

	public static void setNodes_soil(Vector<Node> nodes_soil) {
		Recorder.nodes_soil = nodes_soil;
	}

	public static Vector<Node> getNodes_water() {
		return nodes_water;
	}

	public static void setNodes_water(Vector<Node> nodes_water) {
		Recorder.nodes_water = nodes_water;
	}

	public static int getStage() {
		return stage;
	}

	public static void setStage(int stage) {
		Recorder.stage = stage;
	}

	public static Barriers getBarriers() {
		return barriers;
	}

	public static void setBarriers(Barriers barriers) {
		Recorder.barriers = barriers;
	}

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
	public static void readRecords() {
		try {
			fr = new FileReader("H:\\TankGame_Recorder\\recorder1.txt");
			br = new BufferedReader(fr);
			String n = "";
			// 先读取前 5 行数据(游戏暂停状态，玩家成绩，敌我坦克生命数量)
			n = br.readLine();
			// 判断游戏的暂停状态
			if (Integer.parseInt(n) == 0) {
				pause = false;
			} else {
				pause = true;
			}

			// 读取玩家的成绩
			n = br.readLine();
			elienemNum = Integer.parseInt(n);
			n = br.readLine();
			enemyNum = Integer.parseInt(n);
			n = br.readLine();
			mylife = Integer.parseInt(n);
			System.out.println("记录里的mylife = " + mylife);

			// 读取游戏关数
			n = br.readLine();
			stage = Integer.parseInt(n);

			// 接着读取玩家坦克的坐标
			n = br.readLine();
			String[] str = n.split(" ");
			Node node0 = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			nodes_tank.add(node0);

			// 然后再循环读取每辆敌人坦克的坐标
			while ((n = br.readLine()) != null) {
				String[] xyz = n.split(" ");
				// System.out.println(xyz.length);
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes_tank.add(node);
			}

			// 读取地图信息
			fr1 = new FileReader("H:\\TankGame_Recorder\\recorder2.txt");
			br1 = new BufferedReader(fr1);

			// 按照保存各种地图障碍物的先后顺序，依次读取
			// 读取顺序----铁墙
			while ((n = br1.readLine()) != null && !(n.equals(" "))) {
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]));
				nodes_iron.add(node);
			}

			// 土墙
			// System.out.println("土墙");
			while ((n = br1.readLine()) != null && !(n.equals(" "))) {
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]));
				nodes_soil.add(node);
			}

			while ((n = br1.readLine()) != null) {
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]));
				nodes_water.add(node);
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

	public static void savaGame() {
		try {
			// 创建保存数据的文件1
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
			bw.write(enemyNum + "\r\n");
			bw.write(mylife + "\r\n");

			// 保存关数
			bw.write(stage + "\r\n");

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

			// 创建保存数据的文件2,用于保存地图
			fw1 = new FileWriter("H:\\TankGame_Recorder\\recorder2.txt");
			bw1 = new BufferedWriter(fw1);

			// 保存当前地图
			// 保存铁墙
			for (int i = 0; i < barriers.ironwalls.size(); i++) {
				Barriers.IronWall iw = barriers.ironwalls.get(i);
				if (iw.isLive) {
					// 保存障碍物的坐标
					String re = iw.x + " " + iw.y;
					bw1.write(re + "\r\n");
				}
			}

			// 下一行写入一个空字符,以区分不同的障碍物坐标
			bw1.write(" \r\n");

			// 保存土墙
			for (int i = 0; i < barriers.soilwalls.size(); i++) {
				Barriers.SoilWall sw = barriers.soilwalls.get(i);
				if (sw.isLive) {
					String re = sw.x + " " + sw.y;
					bw1.write(re + "\r\n");
				}
			}

			bw1.write(" \r\n");

			// 保存水坑
			for (int i = 0; i < barriers.waters.size(); i++) {
				Barriers.Water wt = barriers.waters.get(i);
				if (wt.isLive) {
					String re = wt.x + " " + wt.y;
					bw1.write(re + "\r\n");
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
				bw1.close();
				fw1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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
		return enemyNum;
	}

	public static void setEnemNum(int enemNum) {
		Recorder.enemyNum = enemNum;
	}

	public static int getMylife() {
		return mylife;
	}

	public static void setMylife(int mylife) {
		Recorder.mylife = mylife;
	}

	public static void reduceEnNum() {
		enemyNum -= 1;
	}

	public static void reduceMylife() {
		mylife -= 1;
	}

	public static void addElienemNum() {
		elienemNum++;
	}
}