package Demo7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//���ڼ�¼���ļ��ж�ȡ������̹��������̹�˷���,�ϰ�������
class Node {
	int x;
	int y;
	int direct;

	// ��ȡ����ʱ����¼̹�˵�����ͷ�����Ϣ
	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// ��ȡ����ʱ����¼�ϰ��������
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// ��¼��,ͬʱҲ���Լ�¼��ҵ���Ϸ����
class Recorder {
	// ��¼ÿ���ж��ٵ���
	private static int enemyNum;
	// ��ҵ�̹����������
	private static int mylife;
	// ��¼�ܹ������˶��ٵ���̹��
	private static int elienemNum = 0;
	// ��¼��Ϸ�Ƿ���ͣ
	private static boolean pause = false;

	// ��¼��Ϸ����
	private static int stage;

	// ���ļ��лظ���¼��
	private static Vector<Node> nodes_tank = new Vector<Node>();
	private static Vector<Node> nodes_iron = new Vector<Node>();
	private static Vector<Node> nodes_soil = new Vector<Node>();
	private static Vector<Node> nodes_water = new Vector<Node>();

	// ������Ҽ�¼���ļ�
	private static FileWriter fw = null, fw1 = null;
	private static BufferedWriter bw = null, bw1 = null;

	// ���ļ��ж�ȡ��Ҽ�¼
	private static FileReader fr = null, fr1 = null;
	private static BufferedReader br = null, br1 = null;

	// ��ȡ����̹�ˣ����ڴ����˳�
	private static Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// ��ȡ��ҵ�̹����Ϣ
	private static Player player = null;

	// ��ȡ���ĵ�ͼ��Ϣ
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

	// ��ȡ���ȷ���
	public static void readRecords() {
		try {
			fr = new FileReader("H:\\TankGame_Recorder\\recorder1.txt");
			br = new BufferedReader(fr);
			String n = "";
			// �ȶ�ȡǰ 5 ������(��Ϸ��ͣ״̬����ҳɼ�������̹����������)
			n = br.readLine();
			// �ж���Ϸ����ͣ״̬
			if (Integer.parseInt(n) == 0) {
				pause = false;
			} else {
				pause = true;
			}

			// ��ȡ��ҵĳɼ�
			n = br.readLine();
			elienemNum = Integer.parseInt(n);
			n = br.readLine();
			enemyNum = Integer.parseInt(n);
			n = br.readLine();
			mylife = Integer.parseInt(n);
			System.out.println("��¼���mylife = " + mylife);

			// ��ȡ��Ϸ����
			n = br.readLine();
			stage = Integer.parseInt(n);

			// ���Ŷ�ȡ���̹�˵�����
			n = br.readLine();
			String[] str = n.split(" ");
			Node node0 = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			nodes_tank.add(node0);

			// Ȼ����ѭ����ȡÿ������̹�˵�����
			while ((n = br.readLine()) != null) {
				String[] xyz = n.split(" ");
				// System.out.println(xyz.length);
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes_tank.add(node);
			}

			// ��ȡ��ͼ��Ϣ
			fr1 = new FileReader("H:\\TankGame_Recorder\\recorder2.txt");
			br1 = new BufferedReader(fr1);

			// ���ձ�����ֵ�ͼ�ϰ�����Ⱥ�˳�����ζ�ȡ
			// ��ȡ˳��----��ǽ
			while ((n = br1.readLine()) != null && !(n.equals(" "))) {
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]));
				nodes_iron.add(node);
			}

			// ��ǽ
			// System.out.println("��ǽ");
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
			// �����������ݵ��ļ�1
			fw = new FileWriter("H:\\TankGame_Recorder\\recorder1.txt");
			bw = new BufferedWriter(fw);
			// ��¼��Ϸ��ͣ״̬
			if (pause) {
				bw.write(1 + "\r\n");
			} else {
				bw.write(0 + "\r\n");
			}

			// ������ҵ������Ϣ
			bw.write(elienemNum + "\r\n");
			bw.write(enemyNum + "\r\n");
			bw.write(mylife + "\r\n");

			// �������
			bw.write(stage + "\r\n");

			// �������̹�˵�����
			String s1 = player.getX() + " " + player.getY() + " " + player.getDirect();
			bw.write(s1 + "\r\n");
			// ���浱ǰ��ĵ���̹�˵�����ͷ���
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��ÿһ��̹��
				EnemyTank et = ets.get(i);

				if (et.isLive) {
					// ���̹�˾ͱ���
					String recorder = et.x + " " + et.y + " " + et.direct;

					// д��
					bw.write(recorder + "\r\n");
				}
			}

			// �����������ݵ��ļ�2,���ڱ����ͼ
			fw1 = new FileWriter("H:\\TankGame_Recorder\\recorder2.txt");
			bw1 = new BufferedWriter(fw1);

			// ���浱ǰ��ͼ
			// ������ǽ
			for (int i = 0; i < barriers.ironwalls.size(); i++) {
				Barriers.IronWall iw = barriers.ironwalls.get(i);
				if (iw.isLive) {
					// �����ϰ��������
					String re = iw.x + " " + iw.y;
					bw1.write(re + "\r\n");
				}
			}

			// ��һ��д��һ�����ַ�,�����ֲ�ͬ���ϰ�������
			bw1.write(" \r\n");

			// ������ǽ
			for (int i = 0; i < barriers.soilwalls.size(); i++) {
				Barriers.SoilWall sw = barriers.soilwalls.get(i);
				if (sw.isLive) {
					String re = sw.x + " " + sw.y;
					bw1.write(re + "\r\n");
				}
			}

			bw1.write(" \r\n");

			// ����ˮ��
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
			// �ر��ļ���
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