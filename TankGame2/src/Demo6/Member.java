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

//���ڼ�¼���ļ��ж�ȡ������̹��������̹�˷���
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

// ��¼��,ͬʱҲ���Լ�¼��ҵ���Ϸ����
class Recorder {
	// ��¼ÿ���ж��ٵ���
	private static int enemNum;
	// ��ҵ�̹����������
	private static int mylife = 3;
	// ��¼�ܹ������˶��ٵ���̹��
	private static int elienemNum = 0;
	// ��¼��Ϸ�Ƿ���ͣ
	private static boolean pause = false;

	// ���ļ��лظ���¼��
	private static Vector<Node> nodes = new Vector<Node>();

	// ������Ҽ�¼���ļ�
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;

	// ���ļ��ж�ȡ��Ҽ�¼
	private static FileReader fr = null;
	private static BufferedReader br = null;

	// ��ȡ����̹�ˣ����ڴ����˳�
	private static Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// ��ȡ��ҵ�̹����Ϣ
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

	// ��ȡ���ȷ���
	public static void getNodeAndEnNums() {
		try {
			fr = new FileReader("H:\\TankGame_Recorder\\recorder1.txt");
			br = new BufferedReader(fr);
			String n = "";
			// �ȶ�ȡǰ 4 ������(��Ϸ��ͣ״̬����ҳɼ�������̹����������)
			n = br.readLine();
			// �ж���Ϸ����ͣ״̬
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

			// ���Ŷ�ȡ���̹�˵�����
			n = br.readLine();
			String[] str = n.split(" ");
			System.out.println(str.length);
			Node node0 = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
			nodes.add(node0);

			// Ȼ����ѭ����ȡÿ������̹�˵�����
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
			// �����������ݵ��ļ�
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
			bw.write(enemNum + "\r\n");
			bw.write(mylife + "\r\n");

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر��ļ���
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// // ���ļ��ж�ȡ��¼
	// public static void getRecording() {
	// try {
	// fr = new FileReader("H:\\TankGame_Recorder\\recorder.txt");
	// br = new BufferedReader(fr);
	//
	// // �Ȱ��ļ��еļ�¼��ȡ��������
	// // ���ַ����ݴ�ӻ������ж�ȡ��ÿһ������
	// // �ٽ�ÿһ�е�����ͨ����ֵ����ص�����
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

// ��ը��
class Bomb {
	// ���屬ը������
	int x, y;
	// ը��������
	int life = 9;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// ������������
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

// �ڵ���
class Shot implements Runnable {
	int x; // �ڵ�������
	int y;
	int direct; // �ڵ��ķ���
	int speed = 9; // �ڵ��ٶ�
	boolean isLive = true;
	boolean isPause = false;
	// ��Ϸ����Ĵ�С�������ж��Ƿ������˱߽�
	int width;
	int height;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// �õ���Ϸ����ͣ״̬
	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// �õ���Ϸ�߽�Ĵ�С
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

			// �ڵ����ƶ�
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
			// �ڵ���ʱ��ʧ
			// System.out.println("�ڵ����� x=" + x + ", y=" + y);

			// �ж��ڵ��Ƿ�������Ե
			if (x < 0 || x > width || y < 0 || y > height) {
				this.isLive = false;
				break;
			}
		}
	}
}

// ̹����--����
class Tank {
	// ����̹��ʱ�Ĳ��յ㣬����̨��Բ��
	int x = 0; // ̹�˵ĺ�����
	int y = 0; // ̹�˵�������

	// ̹�˷���
	// 0��ʾ�� 1��ʾ�� 2 ��ʾ�� 3��
	int direct = 0;
	int Color; // ̹�˵���ɫ

	// ̹�˵��ӵ�����
	Vector<Shot> ss = new Vector<Shot>();

	// ̹�˵��ٶ�
	int speed = 2;
	boolean isLive = true; // ̹���Ƿ����
	boolean isPause = false; // ��Ϸ�Ƿ���ͣ������Ϸ��ͣ��̹��ֹͣ�˶�

	// ��Ϸ����Ĵ�С�������ж��Ƿ������˱߽�
	int width;
	int height;

	// ����һ�����������Դ�ŵ��˵�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// ����һ�����̹�ˣ������ж��Ƿ���ײ
	Player player = null;

	// ����һ���ϰ�������ж��Ƿ��������ϰ���
	Barriers br = null;

	public void setBarrier(Barriers br) {
		this.br = br;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��ȡ̹�˵����꣬����̹�˵�����
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

	// �õ���Ϸ�߽�Ĵ�С
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// �õ�MyPanel�ĵ���̹������
	public void getEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// �õ��ҵ�̹��
	public void getPlayer(Player hero) {
		this.player = hero;
	}

	// �ж��Ƿ��������ɴ�Խ���ϰ���
	public boolean isTouchBarrier() {

		// ���жϸ�̹�˵ķ���
		switch (this.direct) {
		case 0:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
			}

			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
			}
			break;

		// ̹������
		case 1:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y - 10 >= sw.y
						&& this.y - 10 < sw.y + sw.height)
					return true;
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y + 10 >= sw.y
						&& this.y + 10 < sw.y + sw.height)
					return true;
			}

			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y - 10 >= rw.y
						&& this.y - 10 < rw.y + rw.height)
					return true;
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y + 10 >= rw.y
						&& this.y + 10 < rw.y + rw.height)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y - 10 >= wt.y
						&& this.y - 10 < wt.y + wt.height)
					return true;
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y + 10 >= wt.y
						&& this.y + 10 <= wt.y + wt.height)
					return true;
			}
			break;

		// ̹������
		case 2:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
			}
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
			}
			break;
		// ̹������
		case 3:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y - 10 >= sw.y && this.y - 10 <= sw.y + 20)
					return true;
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y + 10 >= sw.y && this.y + 10 <= sw.y + 20)
					return true;
			}
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y - 10 >= rw.y && this.y - 10 <= rw.y + 10)
					return true;
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y + 10 >= rw.y && this.y + 10 <= rw.y + 10)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
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

	// �ж��Ƿ���������̹��(�������̹�˺͵���̹��)
	public boolean isTouchOtherTank() {
		switch (this.direct) {
		case 0:
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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

			// �ж��Ƿ����ҵ�̹�˷�����ײ
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
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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
			// ��̹������
			// ȡ��ÿһ������̹��
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

			// �ж��Ƿ����ҵ�̹����ײ
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

// �ҵ�̹��
class Player extends Tank {

	// ̹�˵��ڵ�
	Shot s = null;

	// ���̹�˵�����������Ĭ��Ϊ3����
	int life = 3;

	public Player(int x, int y) {
		super(x, y); // ���ø���Ĺ��췽�����г�ʼ��
		// �������̹�˵��ٶ�
		this.speed = 3;
	}

	// ����
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
		// �����ڵ��߳�
		Thread t = new Thread(s);
		t.start();
	}

	// ̹�������ƶ�
	public void moveUp() {
		y -= speed;
	}

	// ̹�������ƶ�
	public void moveRight() {
		x += speed;
	}

	// ̹�������ƶ�
	public void moveDown() {
		y += speed;
	}

	// ̹�������ƶ�
	public void moveLeft() {
		x -= speed;
	}
}

// ����̹��
class EnemyTank extends Tank implements Runnable {

	// ��������ӵ���Ӧ���ڸոմ���̹��ʱ�͸���һ���ӵ�
	int times = 0;

	// �������̹�˵�������
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
				// ˵��̹����������
				for (int i = 0; i < 30; i++) {
					if (y - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
						break;
					if (!this.isPause) {// ̹������ƽ���ƶ�ʱ����Ҫʱ���ж���Ϸ�Ƿ���ͣ��
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
				// ����
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
				// ����
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
				// ����
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

			if (this.isPause) // �ı�̹�˷����������ӵ�ʱ���ж���Ϸ�Ƿ���ͣ
				continue;

			this.times++;
			if (times % 2 == 0) {
				if (isLive) {
					if (ss.size() < 2) {
						Shot s = null;
						// û���ӵ������
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

						// �õ���Ϸ�߽����Ϣ
						s.getWidth_height(width, height);
						// �����ӵ��߳�
						Thread t = new Thread(s);
						t.start();
					}
				}
			}

			// ��̹���������һ���µķ���
			this.direct = (int) (Math.random() * 4);

			// �жϵ���̹���Ƿ�����
			if (this.isLive == false) {

				// ��̹���������˳��߳�
				// Ӧͬʱremove����̹�˵������ӵ�
				break;
			}

			// System.out.println(width + " " + height);
		}
	}
}

// �ϰ���
class Barriers {

	// ������ǽ����
	Vector<SoilWall> soilwalls = new Vector<SoilWall>();
	// ������ǽ�ϰ��Ｏ��
	Vector<IronWall> ironwalls = new Vector<IronWall>();
	// ����ˮ�Ӽ���
	Vector<Water> waters = new Vector<Water>();

	public Barriers() {

		int k = 1;
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
			Water water = waters.get(i);
			if (water.isLive)
				water.draw(g);
			else
				waters.remove(water);
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
