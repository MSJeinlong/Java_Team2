package Jun_Tank;

/**
*TankGame2.0
*1������̹��
*2���ҵ�̹�˿������������ƶ�
*3�����Է����ӵ����ӵ����5����
*4�����ез�̹�ˣ��з�̹����ʧ(�б�ըЧ��)
*5�����̹�˱����к���ʾ��ըЧ��
*6����ֹ����̹���ص��˶�
*7���ж���ؿ�
*8������������Ϸʱ��ͣ���˳�
*9�����Լ�¼��ҵĳɼ�
*10�����뱳����Ч
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TankGame1 extends JFrame { // �̳�JFrame,�������ɴ���

	MyPanel mp = null;

	public static void main(String[] args) {
		TankGame1 tankGame4 = new TankGame1();
	}

	public TankGame1() {
		mp = new MyPanel();

		// ����mp�߳�
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);// ���ҵ�JPanel��ӵ�Frame��

		// ע�����
		this.addKeyListener(mp);

		this.setTitle("̹�˴�ս");
		this.setSize(600, 500);
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

// �ҵ����
class MyPanel extends JPanel implements KeyListener, Runnable {
	// ����һ���ҵ�̹��
	Hero hero = null;

	// ������˵�̹����
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	int enSize = 3; // ����̹������

	// ����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();

	// ��������ͼƬ,����ͼƬ��ɱ�ը��Ч
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// ���췽��,��ʼ��̹��
	public MyPanel() {
		hero = new Hero(100, 200);
		for (int i = 0; i < enSize; i++) {
			// ����һ�����˵�̹�˶���
			EnemyTank et = new EnemyTank((i + 1) * 50, 50);
			et.setColor(0); // ���õ���̹�˵���ɫ
			et.setDirect(2);
			// ����̹��
			Thread t = new Thread(et);
			t.start();
			// ������̹�����һ���ӵ�
			Shot s = new Shot(et.x - 10, et.y - 15, et.direct);
			et.ss.add(s);// ���ڵ��Ӹ�����̹��
			Thread t1 = new Thread(s);
			t1.start();// ��������̹�˵��ڵ��߳�
			ets.add(et); // ������̹�˼��뵽������
		}

		// ��ʼ��ͼƬ

		try {
			image1 = ImageIO.read(new File("F:\\TankGame_Picture\\bomb3.gif"));
			image2 = ImageIO.read(new File("F:\\TankGame_Picture\\bomb2.gif"));
			image3 = ImageIO.read(new File("F:\\TankGame_Picture\\bomb1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// image1 =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb3.gif"));
		// image2 =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb2.gif"));
		// image3 =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));

	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g); // ���ø���Ĺ��췽�����г�ʼ��
		g.fillRect(0, 0, 400, 300); // �����Ϸ���򱳾�ΪĬ����ɫ
		// �����ҵ�̹��
		if (hero.isLive)
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);

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
		for (int i = 0; i < hero.ss.size(); i++) {
			Shot myShot = hero.ss.get(i);

			// ����ÿһ���ڵ�
			if (myShot != null && myShot.isLive == true) {
				g.setColor(Color.WHITE);
				g.draw3DRect(myShot.x, myShot.y, 2, 2, true);
			}
			if (myShot.isLive == false) {
				// �ڵ�����ʱɾ�����ڵ�
				hero.ss.remove(myShot);
			}
		}
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

				this.hitTank(enemyShot, hero);
			}
		}
	}

	// �ж��ҵ��ӵ��ǻ��е���̹��
	public void hitEnemyTank() {
		for (int i = 0; i < hero.ss.size(); i++) {
			// ȡ���ڵ�
			Shot myShot = hero.ss.get(i);
			// �ж��ڵ��Ƿ���Ч
			if (myShot.isLive) {
				// ȡ��ÿ��̹�ˣ�����ڵ������ж�
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��̹��
					EnemyTank et = ets.get(j);

					if (et.isLive) {
						this.hitTank(myShot, et);
					}
				}
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
				et.isLive = false;// ����̹�˱�����

				// ���ɱ�ը��Ч
				Bomb b = new Bomb(et.x - 10, et.y - 15);
				bombs.add(b);
				break;
			}
		case 1:
		case 3:
			if (s.x > et.x - 15 && s.x < et.x + 15 && s.y > et.y - 10 && s.y < et.y + 10) {
				s.isLive = false; // �ڵ���ʧ
				et.isLive = false;// ����̹�˱�����

				// ���ɱ�ը��Ч
				Bomb b = new Bomb(et.x - 15, et.y - 10);
				bombs.add(b);
				break;
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

	// ���̼���
	// ��ĸ W == ���ϣ� D ==���ң�S == ���£� A == ����
	@Override
	public void keyPressed(KeyEvent e) {
		// �����ҵ�̹�˵ķ���

		if (e.getKeyCode() == KeyEvent.VK_W) {
			// ����
			this.hero.setDirect(0);
			if (hero.y - 15 > 0) { // ��ֹ̹��Խ��
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��ÿһ��̹�ˣ����ҵ�̹���Ƿ������̹�˷�����ײ
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// ����̹�����ϻ�������
					case 2:
						if (hero.y - 15 < et.y + 15 && hero.y - 15 > et.y - 15
								&& ((hero.x - 10 < et.x - 10 && hero.x - 10 > et.x + 10)
										|| (hero.x + 10 < et.x - 10 && hero.x + 10 < et.x + 10)))
							hero.isHit = true;
						break;
					case 1:
					case 3:
						if (hero.y - 15 < et.y + 10 && hero.y - 15 > et.y - 10
								&& ((hero.x - 10 < et.x - 15 && hero.x - 10 > et.x + 15)
										|| (hero.x + 10 < et.x - 15 && hero.x + 10 < et.x + 15)))
							hero.isHit = true;
						break;
					}
					if (hero.isHit)
						break;
				}
				if (!hero.isHit) {
					this.hero.moveUp();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// ����
			this.hero.setDirect(1);
			if (hero.x + 15 < 400) {
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��ÿһ��̹�ˣ����ҵ�̹���Ƿ������̹�˷�����ײ
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// ����̹�����ϻ�������
					case 2:
						if (hero.x + 15 > et.x - 10 && hero.x + 15 < et.x + 10
								&& ((hero.y - 10 > et.y - 15 && hero.y - 10 < et.y + 15)
										|| (hero.y + 10 > et.y - 15 && hero.y + 10 < et.y - 15)))
							hero.isHit = true;
						break;
					case 1:// ����̹�������������
					case 3:
						if (hero.x + 15 > et.x - 15 && hero.x + 15 < et.x + 15
								&& ((hero.y - 10 > et.y - 10 && hero.y - 10 < et.y + 10)
										|| (hero.y + 10 > et.y - 10 && hero.y + 10 < et.y - 10)))
							hero.isHit = true;
						break;
					}
					if (hero.isHit)
						break;
				}
				if (!hero.isHit)
					this.hero.moveRight();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// ����
			this.hero.setDirect(2);
			if (hero.y + 15 < 300) {
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��ÿһ��̹�ˣ����ҵ�̹���Ƿ������̹�˷�����ײ
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// ����̹�����ϻ�������
					case 2:
						if (hero.y + 15 > et.y - 15 && hero.y + 15 < et.y + 15
								&& ((hero.x - 10 > et.x - 10 && hero.x - 10 < et.x + 10)
										|| (hero.x + 10 > et.x - 10 && hero.x + 10 < et.x + 10)))
							hero.isHit = true;
						break;
					case 1:
					case 3:
						if (hero.y + 15 > et.y - 10 && hero.y + 15 < et.y + 10
								&& ((hero.x - 10 > et.x - 15 && hero.x - 10 < et.x + 15)
										|| (hero.x + 10 > et.x - 15 && hero.x + 10 < et.x + 15)))
							hero.isHit = true;
						break;
					}
					if (hero.isHit)
						break;
				}
				if (!hero.isHit)
					this.hero.moveDown();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// ����
			this.hero.setDirect(3);
			if (hero.x - 15 > 0) {
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��ÿһ��̹�ˣ����ҵ�̹���Ƿ������̹�˷�����ײ
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// ����̹�����ϻ�������
					case 2:
						if (hero.x - 15 < et.x + 10 && hero.x - 15 < et.x - 10
								&& ((hero.y - 10 > et.y - 15 && hero.y - 10 < et.y + 15)
										|| (hero.y + 10 > et.y - 15 && hero.y + 10 < et.y + 15)))
							hero.isHit = true;
						break;
					case 1:// ����̹�������������
					case 3:
						if (hero.x - 15 < et.x + 15 && hero.x - 15 > et.x - 15
								&& ((hero.y - 10 > et.y - 10 && hero.y - 10 < et.y + 10)
										|| (hero.y + 10 > et.y - 10 && hero.y + 10 < et.y + 10)))
							hero.isHit = true;
						break;
					}
					if (hero.isHit)
						break;
				}
				if (!hero.isHit)
					this.hero.moveLeft();
			}
		}

		hero.isHit = false;

		// �ж�����Ƿ���J(̹�˿���)
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// һ����෢��5ö�ڵ�
			if (hero.ss.size() < 5) {
				this.hero.shotEnemy();
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
			// �жϵ���̹���Ƿ񱻻���
			this.hitEnemyTank();

			// �ж��ҵ�̹���Ƿ񱻵��˵�̹�˻���
			this.hitMyTank();
			// System.out.println(hero.isHit);
			// �ػ����
			this.repaint();
		}
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

// �ڵ���
class Shot implements Runnable {
	int x; // �ڵ�������
	int y;
	int direct; // �ڵ��ķ���
	int speed = 2; // �ڵ��ٶ�
	boolean isLive = true;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			if (x < 0 || x > 400 || y < 0 || y > 300) {
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

	// ̹�˵��ٶ�
	int speed = 2;
	boolean isLive = true; // ̹���Ƿ����

	boolean isHit = false; // �ж��ҷ�̹���Ƿ������̹�˷�����ײ

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
}

// �ҵ�̹��
class Hero extends Tank {

	// ̹�˵��ڵ�
	Shot s = null;
	Vector<Shot> ss = new Vector<Shot>();

	public Hero(int x, int y) {
		super(x, y); // ���ø���Ĺ��췽�����г�ʼ��

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

	// ����һ��������������ŵ��˵��ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	// ��������ӵ���Ӧ���ڸոմ���̹��ʱ�͸���һ���ӵ�
	int times = 0;

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
					if (y - 15 < 0)
						break;
					y -= speed;
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
					if (x + 15 > 400)
						break;
					x += speed;
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
					if (y + 15 > 300)
						break;
					y += speed;
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
					if (x - 15 < 0)
						break;
					x -= speed;
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			}

			this.times++;
			if (times % 2 == 0) {
				if (isLive) {
					if (ss.size() < 5) {
						Shot s = null;
						// û���ӵ������
						switch (direct) {
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

		}

	}
}