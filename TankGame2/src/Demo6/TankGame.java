package Demo6;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TankGame extends JFrame implements ActionListener { // �̳�JFrame,�������ɴ���

	// ����һ����ʼ���
	MyStartPanel msp = null;

	// ����һ��Desktop�����ڴ򿪰����ĵ�
	Desktop desktop = null;

	// ����һ����Ϸ���
	MyPanel mp = null;

	// ���ÿ�ʼ�˵����
	JMenuBar jmb = null;
	JMenu jm1 = null, jm2 = null;
	// ��Ϸ�˵���ѡ��
	JMenuItem jmt1 = null, jmt2 = null, jmt3 = null, jmt4 = null;
	// �����˵���ѡ��
	JMenuItem jmt5 = null;

	public static void main(String[] args) {
		TankGame tankGame = new TankGame();
	}

	public TankGame() {

		msp = new MyStartPanel();
		// �����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		jmt1 = new JMenuItem("��ʼ����Ϸ(N)");
		jmt2 = new JMenuItem("�˳���Ϸ(E)");
		jmt3 = new JMenuItem("�����˳�(S)");
		jmt4 = new JMenuItem("������Ϸ(C)");

		// ���������˵�����ѡ��
		jm2 = new JMenu("����(H)");
		jmt5 = new JMenuItem("��Ϸ�淨");

		// ���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmt1.setMnemonic('N');
		jmt2.setMnemonic('E');
		jmt3.setMnemonic('S');
		jmt4.setMnemonic('C');

		// Ϊ���е�jmt���Ӱ�������
		jmt1.addActionListener(this);
		jmt1.setActionCommand("New Game");
		jmt2.addActionListener(this);
		jmt2.setActionCommand("exit");
		jmt3.addActionListener(this);
		jmt3.setActionCommand("saveExit");
		jmt4.addActionListener(this);
		jmt4.setActionCommand("ContinueGame");
		jmt5.addActionListener(this);
		jmt5.setActionCommand("Help");

		// ѡ�����˵�
		jm1.add(jmt1);
		jm1.add(jmt2);
		jm1.add(jmt3);
		jm1.add(jmt4);
		jm2.add(jmt5);
		jmb.add(jm1);
		jmb.add(jm2);

		Thread t1 = new Thread(msp);
		t1.start();
		this.setJMenuBar(jmb);
		this.add(msp);

		this.setTitle("̹�˴�ս");
		this.setSize(600, 500);
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ���û���ͬ�ĵ��������ͬ�Ĵ���
		if (e.getActionCommand().equals("New Game")) {
			// ����ս�����
			mp = new MyPanel(true);
			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ɾ���ɵ����
			this.remove(msp);
			// Ȼ����������
			this.add(mp);// ���ҵ�JPanel��ӵ�Frame��
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ��ˢ��JFrame
			this.setVisible(true);
		} else if (e.getActionCommand().equals("exit")) {
			// �û�������˳�
			// ���������Ϸ�ĳɼ�
			// Recorder.keepRecording();
			System.exit(0);
		} else if (e.getActionCommand().equals("saveExit")) {
			Recorder.keepRecAndEnemyTank();
			System.exit(0);
		} else if (e.getActionCommand().equals("ContinueGame")) {
			// ����ս�����
			mp = new MyPanel(false);

			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ɾ���ɵ����
			this.remove(msp);
			// Ȼ����������
			this.add(mp);// ���ҵ�JPanel��ӵ�Frame��
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ��ˢ��JFrame
			this.setVisible(true);
		} else if (e.getActionCommand().equals("Help")) {
			// ������������
			// hj = new HelpJFrame();

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				try {
					// �򿪰����ĵ�
					desktop.open(new File("F:\\JavaProject_Image\\help.txt"));
				} catch (IOException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		}
	}
}

// �����˵�����
class WinJFrame extends JFrame {

	private int width = 600;
	private int height = 500;
	private WinPanel wp = null;
	private MyPanel mp = null;

	// ���췽��
	public WinJFrame(MyPanel mp) {
		// �½�WinPanel
		wp = new WinPanel();
		this.add(wp);

		this.mp = mp;

		this.setTitle("���سɼ�����");
		this.setSize(width, height);
		this.setLocation(1000, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	// �ڲ��࣬�ɼ����
	private class WinPanel extends JPanel {

		// ���ܲ�ͬ����̹�˿����в�ͬ�ķ���
		int[] enemyType = new int[] { 100, 200, 300, 400 };
		int[] enemyNum = new int[4];
		int score = 0; // �ܷ���
		int total = 0; // ���ܵ���̹�˵�����
	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, 600, 500);
		mp.drawTank(50, 100, g, 0, 1);
	}
}

// ��ʼ����
class MyStartPanel extends JPanel implements Runnable {
	boolean isflicker = true;// ������˸

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		// ��ʾ��Ϣ
		if (isflicker) {
			g.setColor(Color.YELLOW);
			// ��Ϣ����������
			Font myfont = new Font("Cambria", Font.BOLD, 20);
			g.setFont(myfont);
			g.drawString("stage:1", 150, 150);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// ����
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			isflicker = !isflicker;
			this.repaint();
		}
	}
}

class WinPanel extends JPanel {

	// ���ܲ�ͬ����̹�˿����в�ͬ�ķ���
	int[] enemyType = new int[] { 100, 200, 300, 400 };
	int[] enemyNum = new int[4];
	int score = 0; // �ܷ���
	int total = 0; // ���ܵ���̹�˵�����
	MyPanel mp = null;

	public WinPanel(MyPanel mp) {
		this.mp = mp;

	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, 600, 500);
		mp.drawTank(50, 100, g, 1, 1);
		for (int i = 0; i < 1; i++) {
			g.drawString(" ", 80, (i + 1) * 100);
		}
	}

}

// �ҵ����
class MyPanel extends JPanel implements KeyListener, Runnable {

	// ��Ϸ����Ĵ�С
	final int WIDTH = 400;
	final int HEIGHT = 300;

	// ����һ���ҵ�̹��
	Player player = null;

	// �ж��Ǽ�����Ϸ��������Ϸ
	boolean isNewGame;

	// �ж���Ϸ�Ƿ����
	boolean GameOver = false;

	// �ж���Ϸ�Ƿ����
	boolean isWin = false;

	// ������˵�̹����
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// ���������Ϸʱ��̹��������
	Vector<Node> nodes = new Vector<Node>();

	// �����ϰ���
	Barriers br = new Barriers();

	Node node = null;

	// һ���ؿ��ܵĵ���̹��
	int enemyNum = 4;

	// ̹�����
	int num = 0;

	boolean isPause = false; // ��Ϸ�Ƿ�����ͣ״̬

	// ����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();

	// ��������ͼƬ,����ͼƬ��ɱ�ը��Ч
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// ������������
	Vector<PlayVoice> pvs = new Vector<PlayVoice>();

	PlayVoice pv = null;

	// ���췽��,��ʼ��̹��
	public MyPanel(boolean isNewGame) {

		// �ָ���¼
		this.isNewGame = isNewGame;

		Node node = null;

		Recorder.setEts(ets);
		Recorder.setEnemNum(enemyNum);

		// ���������Ϸ
		if (this.isNewGame) {
			// ������ҵ�̹��
			this.NewPlayer(node);
			Recorder.setPlayer(player);
			// ��ʼ�����˵�̹��
			for (int i = 0; i < 4 && i < enemyNum - 1; i++) {
				this.NewEnemyTank(num++, node);
				enemyNum -= 1;
			}

		} else {
			// System.out.println("������Ϸ");
			// ��ȡ��¼
			Recorder.getNodeAndEnNums();
			nodes = Recorder.getNodes();
			this.isPause = Recorder.isPause();
			node = nodes.get(0);
			this.NewPlayer(node);
			Recorder.setPlayer(player);
			for (int i = 1; i < nodes.size(); i++) {
				// ��ȡ��һ��������Ϸʱ��̹��������
				node = nodes.get(i);
				// ������Ϸ��¼��������̹��
				this.NewEnemyTank(i, node);
			}
		}

		// ��ʼ��ͼƬ
		try {
			image1 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb3.gif"));
			image2 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb2.gif"));
			image3 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ���ſ�սǰ�ı�������
		PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\TankGame\\start.wav");
		pvs.add(pv);
		pv.start();

		// // ��������̹���ƶ�������
		// pv1 = new
		// PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\enemy.move.wav");

	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g); // ���ø���Ĺ��췽�����г�ʼ��
		g.fillRect(0, 0, WIDTH, HEIGHT); // �����Ϸ���򱳾�ΪĬ����ɫ

		// ������ʾ��Ϣ
		this.showMessage(g);

		// �����ҵ�̹��
		if (player.isLive)
			this.drawTank(player.getX(), player.getY(), g, this.player.direct, 1);

		// ������ը��Ч
		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			if (b.life > 6) {
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			} else if (b.life > 3) {
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			} else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			// ��b������ֵ��С
			b.lifeDown();
			if (b.life == 0) // ��ը��Ч�������ڽ���
				bombs.remove(b); // �Ƴ�����Ч
		}

		// �������˵�̹��
		for (int i = 0; i < ets.size(); i++) {
			EnemyTank et = ets.get(i);
			if (et.isLive) { // ���̹�˴��ڣ��ͻ�����
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				// �������˵��ӵ�
				for (int j = 0; j < et.ss.size(); j++) {
					// ȡ���ӵ�
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 2, 2, true);
					} else {
						// �������̹�˵��ڵ��������ʹ�Vector()�Ƴ�
						et.ss.remove(enemyShot);
					}
				}
			} else {// ���̹�˱����٣���ɾ����̹��
				ets.remove(et);
			}
		}

		// �Ӽ���ss��ȡ��ÿ���ڵ���������
		for (int i = 0; i < player.ss.size(); i++) {
			Shot myShot = player.ss.get(i);

			// ����ÿһ���ڵ�
			if (myShot.isLive) {
				g.setColor(Color.WHITE);
				g.draw3DRect(myShot.x, myShot.y, 2, 2, true);
			} else {
				// �ڵ�����ʱɾ�����ڵ�
				player.ss.remove(myShot);
			}
		}

		// �����ϰ���
		br.drawBarrier(g);

		// ��ʾ��Ϸ��ͣ��Ϣ
		if (this.isPause) {
			g.setColor(Color.RED);
			g.drawString("Pause", 200, 150);
		}

		// ��ʾ��Ϸ����
		// if (this.GameOver) {
		// for (int i = 0; i <= 15; i++)
		// g.drawString("GAME", 200, HEIGHT - i * 10);
		// }
	}

	// ������ҵ�̹��
	public void NewPlayer(Node node) {

		if (this.isNewGame) {
			// ������ҵ�̹��
			player = new Player(200, HEIGHT - 30);
			player.setBarrier(br);
		} else {
			player = new Player(node.x, node.y);
			player.setDirect(node.direct);
			player.setBarrier(br);
		}
		// ��Ϸ�������Ϣ����̹��
		player.getPlayer(player);
		player.getEts(ets);
		player.getWidth_height(WIDTH, HEIGHT);
	}

	// �����µĵ���̹��
	public void NewEnemyTank(int num, Node node) {

		EnemyTank et = null;
		// ���������Ϸ�������´���̹��
		if (this.isNewGame) {
			et = new EnemyTank((num % 4) * 80 + 30, 20);
			et.setColor(0); // ���õ���̹�˵���ɫ
			et.setDirect(2);
		} else { // ����Ǽ�����Ϸ���͸���Node����Ϣ����̹��
			// ����һ�����˵�̹�˶���
			et = new EnemyTank(node.x, node.y);
			et.setColor(0); // ���õ���̹�˵���ɫ
			et.setDirect(node.direct);
		}
		// ����Ϸ�ı߽��С����̹��
		et.getWidth_height(WIDTH, HEIGHT);
		// ��MyPanel�ĵ���̹�����������õ���̹��
		et.getEts(ets);
		et.getPlayer(player);
		et.setBarrier(br);
		// ����Ϸ��״̬���ݸ�̹��
		et.getPause(isPause);

		// ����̹��
		Thread t = new Thread(et);
		t.start();
		// ������̹�����һ���ӵ�
		Shot s = new Shot(et.x - 10, et.y - 15, et.direct);

		// ����Ϸ�������Ϣ״̬���ݸ��ӵ�
		s.getPause(isPause);
		s.getWidth_height(WIDTH, HEIGHT);
		et.ss.add(s);// ���ڵ��Ӹ�����̹��
		Thread t1 = new Thread(s);
		t1.start();// ��������̹�˵��ڵ��߳�
		ets.add(et); // ������̹�˼��뵽������
	}

	// ������ʾ��Ϣ
	public void showMessage(Graphics g) {
		// ����������ʾ��Ϣ��̹�ˣ���̹�˲�����ս����
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Cambria", Font.BOLD, 18));
		g.drawString(Recorder.getEnemNum() + "", 100, 340);
		this.drawTank(200, 330, g, 0, 1);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMylife() + "", 220, 340);

		// ������ҵ��ܳɼ�
		g.setColor(Color.black);
		g.setFont(new Font("����", Font.BOLD, 20));
		g.drawString("�ܳɼ�", 420, 30);

		this.drawTank(420, 60, g, 0, 0);

		g.setColor(Color.black);
		g.drawString(Recorder.getElienemNum() + "", 450, 70);
	}

	// �жϵ���̹���Ƿ�����ҵ�̹��
	public void hitMyTank() {
		// ȡ��ÿһ��̹��
		for (int i = 0; i < this.ets.size(); i++) {

			// ȡ��̹��
			EnemyTank et = ets.get(i);

			// ȡ��ÿһ���ӵ�
			for (int j = 0; j < et.ss.size(); j++) {
				// ȡ���ӵ�
				Shot enemyShot = et.ss.get(j);
				// �жϵ����ӵ��Ƿ�����̹��
				this.hitTank(enemyShot, player);
				// �жϵ����ӵ��Ƿ���ϰ���
				this.hitBarrier(br, enemyShot);

				if (!player.isLive) { // ������̹�˹ҵ��ˣ�life-1, Ȼ��ֱ���˳��ڶ���ѭ��
					player.life -= 1;
					// ���ɱ�ը����
					PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\buh.wav");
					pv.start();
					Recorder.reduceMylife();
					break;
				}

				// if()
			}

			if (!player.isLive) { // ������̹�˹ҵ��ˣ� �˳���һ��ѭ��
				break;
			}
		}
	}

	// �ж��ҵ��ӵ��ǻ��е���̹��
	public void hitEnemyTank() {
		for (int i = 0; i < player.ss.size(); i++) {
			// ȡ���ڵ�
			Shot myShot = player.ss.get(i);
			// �ж��ڵ��Ƿ���Ч
			if (myShot.isLive) {
				// ȡ��ÿ��̹�ˣ�����ڵ������ж�
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��̹��
					EnemyTank et = ets.get(j);

					if (et.isLive) {
						this.hitTank(myShot, et);
						if (!et.isLive) {

							// ̹������������
							enemyNum -= 1;
							// ���ɱ�ը����
							PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\kill.wav");
							pv.start();
							Recorder.reduceEnNum(); // ̹����������
							Recorder.addElienemNum(); // ��¼��ҳɼ�
						}
					}
				}

				// �����ӵ������е��ϰ�������ж�
				this.hitBarrier(br, myShot);
			}
		}
	}

	// �ڵ��Ƿ����̹��
	public void hitTank(Shot s, Tank et) {
		// �жϵ���̹�˷���
		switch (et.direct) {
		// �������ϻ�����̹��
		case 0:
		case 2:
			if (s.x > et.x - 10 && s.x < et.x + 10 && s.y > et.y - 15 && s.y < et.y + 15) {
				s.isLive = false; // �ڵ���ʧ
				et.isLive = false;// ̹�˱�����

				// ���ɱ�ը��Ч
				Bomb b = new Bomb(et.x - 10, et.y - 15);
				bombs.add(b);

				break;
			}
		case 1:
		case 3:
			if (s.x > et.x - 15 && s.x < et.x + 15 && s.y > et.y - 10 && s.y < et.y + 10) {
				s.isLive = false; // �ڵ���ʧ
				et.isLive = false;// ̹�˱�����

				// ���ɱ�ը��Ч
				Bomb b = new Bomb(et.x - 15, et.y - 10);
				bombs.add(b);

				break;
			}
		}
	}

	// �ж��ӵ��Ƿ���ϰ���
	public void hitBarrier(Barriers br, Shot s) {
		// ���ж��ӵ�����ǽʱ����ǽ�ᱬը
		for (int i = 0; i < br.soilwalls.size(); i++) {
			// ����ǽ������ȡ��һ����ǽ����
			Barriers.SoilWall sw = br.soilwalls.get(i);
			// �ж��ӵ��Ƿ���˸���ǽ
			if (s.x >= sw.x && s.x <= sw.x + sw.width && s.y >= sw.y && s.y <= sw.y + sw.height) {
				s.isLive = false;
				sw.isLive = false;
				// ���ɱ�ը��Ч
				Bomb b = new Bomb(sw.x, sw.y);
				bombs.add(b);
				// ����������Ч
				PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\brickErase.wav");
				pv.start();
				return;
			}
		}

		// �ж��ӵ��Ƿ������ǽ
		for (int i = 0; i < br.ironwalls.size(); i++) {
			Barriers.IronWall iw = br.ironwalls.get(i);
			// ���ӵ�����ǽʱ
			if (s.x >= iw.x && s.x <= iw.x + iw.width && s.y >= iw.y && s.y <= iw.y + iw.height) {
				s.isLive = false; // �ӵ���������,����ǽ��Ȼ����
				// ���ɱ�ը��Ч
				Bomb b = new Bomb(iw.x, iw.y);
				bombs.add(b);
				// ����������Ч
				PlayVoice pv1 = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\enemy.armor.hit.wav");
				pv1.start();
				return;
			}
		}

	}

	// ����̹�˵ķ���
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		// �ж�̹�˵����ͣ����ң�����������ͬ����ɫ
		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}

		// �жϷ���
		switch (direct) {
		// ��Ͳ����
		case 0:
			// 1���Ȼ���ߵľ���
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2�������ұ߾���
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3�������м�ľ���
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4�������м����̨(Բ��)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5�������м����Ͳ��ֱ�ߣ�
			g.drawLine(x, y - 15, x, y);
			break;
		case 1: // ��Ͳ����
			// 1�������м�ľ���
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2����������ľ���
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3����������ľ���
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4���Ȼ����м����Ͳ
			g.drawLine(x, y, x + 15, y);
			// 5�������м����̨
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		case 2:// ���£������ϻ�����࣬�����޸���Ͳ����Ϊ���¼��ɣ�
				// 1���Ȼ���ߵľ���
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2�������ұ߾���
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3�������м�ľ���
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4�������м����̨(Բ��)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5�������м����Ͳ��ֱ�ߣ�
			g.drawLine(x, y + 15, x, y);
			break;
		case 3:// ����(����Ͳ���Ҳ�࣬�޸���Ͳ����Ϊ���󼴿�)
				// 1�������м�ľ���
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2����������ľ���
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3����������ľ���
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4���Ȼ����м����Ͳ
			g.drawLine(x, y, x - 15, y);
			// 5�������м����̨
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		}
	}

	public void isGameOver() {
		if (player.life <= 0) {
			this.GameOver = true;
		}
	}

	public void isWin() {
		if (Recorder.getEnemNum() <= 0) {
			this.isWin = true;
			Recorder.setEnemNum(10);
		}
	}

	// ��Ϸ��ͣʱ�������е�̹�˺��ӵ�����Ϊ��ͣ
	public void setPause() {

		// ����ҵ�̹�˼����ӵ�����Ϊ��ͣ
		for (int i = 0; i < player.ss.size(); i++) {
			Shot shot = player.ss.get(i);
			shot.getPause(isPause);
		}

		// ȡ��ÿһ������̹��
		for (int i = 0; i < this.ets.size(); i++) {
			// ȡ��̹��
			EnemyTank et = ets.get(i);
			et.getPause(isPause);
			// ȡ��ÿһ���ӵ�
			for (int j = 0; j < et.ss.size(); j++) {
				// ȡ���ӵ�
				Shot enemyShot = et.ss.get(j);
				enemyShot.getPause(isPause);
				this.hitTank(enemyShot, player);
			}
		}
	}

	// ���̼���
	// ��ĸ W == ���ϣ� D ==���ң�S == ���£� A == ����
	@Override
	public void keyPressed(KeyEvent e) {

		// �ж��Ƿ�����ͣ��
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.isPause = !this.isPause;
			PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\game.pause.wav");
			pv.start();
			this.setPause();
			Recorder.setPause(isPause);
		}

		// ���ж���Ϸ�Ƿ�����ͣ״̬
		if (this.isPause || !player.isLive)
			return; // ����Ϸ������ͣ״̬�������̹���Ѿ�����ʱֱ���˳�

		// �����ҵ�̹�˵ķ���
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// ����
			this.player.setDirect(0);
			if (player.y - 15 > 0) { // ��ֹ̹��Խ��

				if (!player.isTouchOtherTank() && !player.isTouchBarrier()) {
					this.player.moveUp();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// ����
			this.player.setDirect(1);
			if (player.x + 15 < this.WIDTH) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveRight();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// ����
			this.player.setDirect(2);
			if (player.y + 15 < this.HEIGHT) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveDown();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// ����
			this.player.setDirect(3);
			if (player.x - 15 > 0) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveLeft();
			}
		}

		// �ж�����Ƿ���J(̹�˿���)
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// һ����෢��2ö�ڵ�
			if (player.ss.size() < 2) {
				this.player.shotEnemy();

				// �������̹�˿��������
				PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\shoot.wav");
				pv.start();
			}
		}
		// �����ػ洰��
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void run() {
		// ÿ��100����ȥ�ػ�
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			if (!this.isPause) {
				this.hitEnemyTank();// �жϵ���̹���Ƿ񱻻���

				if (ets.size() < 4 && enemyNum >= 0) {
					this.NewEnemyTank(num++, node);
				}

				// // �ж��ҵ�̹���Ƿ񱻵��˵�̹�˻���
				if (player.isLive)
					this.hitMyTank();

				// ���̹�˹ҵ�֮��
				if (!player.isLive) {
					// ������100����
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// �ж����̹�˵�ʣ������
					if (player.life > 0) {
						// ������̹�˵Ĳ���
						player.setX(200);
						player.setY(HEIGHT - 30);
						player.setDirect(0);
						player.isLive = true;
					}
				}

				// �ж���Ϸ�Ƿ����
				this.isGameOver();//

				// �ж�����Ƿ�ʤ��
				this.isWin();
				if (isWin) {
					WinJFrame winJFrame = new WinJFrame(this);
					this.isWin = false;
				}
			}
			// System.out.println(hero.isHit);
			// �ػ����

			// ��������̹���ƶ�������
			// pv1.start();
			this.repaint();
		}
	}
}
