package Jun_Tank;

/**
*TankGame2.0
*1、画出坦克
*2、我的坦克可以上下左右移动
*3、可以发射子弹，子弹最多5连发
*4、击中敌方坦克，敌方坦克消失(有爆炸效果)
*5、玩家坦克被击中后，显示爆炸效果
*6、防止敌人坦克重叠运动
*7、有多个关卡
*8、可以在玩游戏时暂停和退出
*9、可以记录玩家的成绩
*10、加入背景音效
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

public class TankGame1 extends JFrame { // 继承JFrame,用于生成窗体

	MyPanel mp = null;

	public static void main(String[] args) {
		TankGame1 tankGame4 = new TankGame1();
	}

	public TankGame1() {
		mp = new MyPanel();

		// 启动mp线程
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);// 把我的JPanel添加到Frame中

		// 注册监听
		this.addKeyListener(mp);

		this.setTitle("坦克大战");
		this.setSize(600, 500);
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

// 我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {
	// 定义一个我的坦克
	Hero hero = null;

	// 定义敌人的坦克组
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	int enSize = 3; // 敌人坦克数量

	// 定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();

	// 定义三张图片,三张图片组成爆炸特效
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// 构造方法,初始化坦克
	public MyPanel() {
		hero = new Hero(100, 200);
		for (int i = 0; i < enSize; i++) {
			// 创建一辆敌人的坦克对象
			EnemyTank et = new EnemyTank((i + 1) * 50, 50);
			et.setColor(0); // 设置敌人坦克的颜色
			et.setDirect(2);
			// 启动坦克
			Thread t = new Thread(et);
			t.start();
			// 给敌人坦克添加一颗子弹
			Shot s = new Shot(et.x - 10, et.y - 15, et.direct);
			et.ss.add(s);// 将炮弹加给敌人坦克
			Thread t1 = new Thread(s);
			t1.start();// 启动敌人坦克的炮弹线程
			ets.add(et); // 将敌人坦克加入到集合了
		}

		// 初始化图片

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

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g); // 调用父类的构造方法进行初始化
		g.fillRect(0, 0, 400, 300); // 填充游戏区域背景为默认颜色
		// 画出我的坦克
		if (hero.isLive)
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);

		// 画出爆炸特效
		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			if (b.life > 6) {
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			} else if (b.life > 3) {
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			} else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			// 让b的生命值减小
			b.lifeDown();
			if (b.life == 0) // 爆炸特效生命周期结束
				bombs.remove(b); // 移除该特效
		}

		// 画出敌人的坦克
		for (int i = 0; i < ets.size(); i++) {
			EnemyTank et = ets.get(i);
			if (et.isLive) { // 如果坦克存在，就画出来
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				// 画出敌人的子弹
				for (int j = 0; j < et.ss.size(); j++) {
					// 取出子弹
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 2, 2, true);
					} else {
						// 如果敌人坦克的炮弹已死亡就从Vector()移除
						et.ss.remove(enemyShot);
					}
				}
			} else {// 如果坦克被击毁，则删除该坦克
				ets.remove(et);

			}
		}

		// 从集合ss中取出每颗炮弹，并画出
		for (int i = 0; i < hero.ss.size(); i++) {
			Shot myShot = hero.ss.get(i);

			// 画出每一颗炮弹
			if (myShot != null && myShot.isLive == true) {
				g.setColor(Color.WHITE);
				g.draw3DRect(myShot.x, myShot.y, 2, 2, true);
			}
			if (myShot.isLive == false) {
				// 炮弹消亡时删除该炮弹
				hero.ss.remove(myShot);
			}
		}
	}

	// 判断敌人坦克是否击中我的坦克
	public void hitMyTank() {
		// 取出每一辆坦克
		for (int i = 0; i < this.ets.size(); i++) {

			// 取出坦克
			EnemyTank et = ets.get(i);

			// 取出每一颗子弹
			for (int j = 0; j < et.ss.size(); j++) {
				// 取出子弹
				Shot enemyShot = et.ss.get(j);

				this.hitTank(enemyShot, hero);
			}
		}
	}

	// 判断我的子弹是击中敌人坦克
	public void hitEnemyTank() {
		for (int i = 0; i < hero.ss.size(); i++) {
			// 取出炮弹
			Shot myShot = hero.ss.get(i);
			// 判断炮弹是否有效
			if (myShot.isLive) {
				// 取出每个坦克，与该炮弹进行判断
				for (int j = 0; j < ets.size(); j++) {
					// 取出坦克
					EnemyTank et = ets.get(j);

					if (et.isLive) {
						this.hitTank(myShot, et);
					}
				}
			}
		}
	}

	// 炮弹是否击中坦克
	public void hitTank(Shot s, Tank et) {
		// 判断敌人坦克方向
		switch (et.direct) {
		// 击中向上或向下坦克
		case 0:
		case 2:
			if (s.x > et.x - 10 && s.x < et.x + 10 && s.y > et.y - 15 && s.y < et.y + 15) {
				s.isLive = false; // 炮弹消失
				et.isLive = false;// 敌人坦克被击毁

				// 生成爆炸特效
				Bomb b = new Bomb(et.x - 10, et.y - 15);
				bombs.add(b);
				break;
			}
		case 1:
		case 3:
			if (s.x > et.x - 15 && s.x < et.x + 15 && s.y > et.y - 10 && s.y < et.y + 10) {
				s.isLive = false; // 炮弹消失
				et.isLive = false;// 敌人坦克被击毁

				// 生成爆炸特效
				Bomb b = new Bomb(et.x - 15, et.y - 10);
				bombs.add(b);
				break;
			}
		}
	}

	// 画出坦克的方法
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		// 判断坦克的类型（敌我），并给定不同的颜色
		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}

		// 判断方向
		switch (direct) {
		// 炮筒向上
		case 0:
			// 1、先画左边的矩形
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2、画出右边矩形
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3、画出中间的矩形
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4、画出中间的炮台(圆形)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5、画出中间的炮筒（直线）
			g.drawLine(x, y - 15, x, y);
			break;
		case 1: // 炮筒向右
			// 1、画出中间的矩形
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2、画出上面的矩形
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3、画出下面的矩形
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4、先画出中间的炮筒
			g.drawLine(x, y, x + 15, y);
			// 5、画出中间的炮台
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		case 2:// 向下（与向上画法差不多，继续修改炮筒方向为向下即可）
				// 1、先画左边的矩形
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2、画出右边矩形
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3、画出中间的矩形
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4、画出中间的炮台(圆形)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5、画出中间的炮筒（直线）
			g.drawLine(x, y + 15, x, y);
			break;
		case 3:// 向左(与炮筒向右差不多，修改炮筒方向为向左即可)
				// 1、画出中间的矩形
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2、画出上面的矩形
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3、画出下面的矩形
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4、先画出中间的炮筒
			g.drawLine(x, y, x - 15, y);
			// 5、画出中间的炮台
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		}
	}

	// 键盘监听
	// 字母 W == 向上， D ==向右，S == 向下， A == 向左
	@Override
	public void keyPressed(KeyEvent e) {
		// 设置我的坦克的方向

		if (e.getKeyCode() == KeyEvent.VK_W) {
			// 向上
			this.hero.setDirect(0);
			if (hero.y - 15 > 0) { // 防止坦克越界
				for (int j = 0; j < ets.size(); j++) {
					// 取出每一辆坦克，看我的坦克是否与敌人坦克发生碰撞
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// 敌人坦克向上或者向下
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
			// 向右
			this.hero.setDirect(1);
			if (hero.x + 15 < 400) {
				for (int j = 0; j < ets.size(); j++) {
					// 取出每一辆坦克，看我的坦克是否与敌人坦克发生碰撞
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// 敌人坦克向上或者向下
					case 2:
						if (hero.x + 15 > et.x - 10 && hero.x + 15 < et.x + 10
								&& ((hero.y - 10 > et.y - 15 && hero.y - 10 < et.y + 15)
										|| (hero.y + 10 > et.y - 15 && hero.y + 10 < et.y - 15)))
							hero.isHit = true;
						break;
					case 1:// 敌人坦克向左或者向右
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
			// 向下
			this.hero.setDirect(2);
			if (hero.y + 15 < 300) {
				for (int j = 0; j < ets.size(); j++) {
					// 取出每一辆坦克，看我的坦克是否与敌人坦克发生碰撞
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// 敌人坦克向上或者向下
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
			// 向左
			this.hero.setDirect(3);
			if (hero.x - 15 > 0) {
				for (int j = 0; j < ets.size(); j++) {
					// 取出每一辆坦克，看我的坦克是否与敌人坦克发生碰撞
					EnemyTank et = ets.get(j);
					switch (et.direct) {
					case 0:// 敌人坦克向上或者向下
					case 2:
						if (hero.x - 15 < et.x + 10 && hero.x - 15 < et.x - 10
								&& ((hero.y - 10 > et.y - 15 && hero.y - 10 < et.y + 15)
										|| (hero.y + 10 > et.y - 15 && hero.y + 10 < et.y + 15)))
							hero.isHit = true;
						break;
					case 1:// 敌人坦克向左或者向右
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

		// 判断玩家是否按下J(坦克开火)
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// 一次最多发射5枚炮弹
			if (hero.ss.size() < 5) {
				this.hero.shotEnemy();
			}
		}
		// 必须重绘窗口
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
		// 每隔100毫秒去重绘
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			// 判断敌人坦克是否被击中
			this.hitEnemyTank();

			// 判断我的坦克是否被敌人的坦克击中
			this.hitMyTank();
			// System.out.println(hero.isHit);
			// 重绘界面
			this.repaint();
		}
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

// 炮弹类
class Shot implements Runnable {
	int x; // 炮弹的坐标
	int y;
	int direct; // 炮弹的方向
	int speed = 2; // 炮弹速度
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
			if (x < 0 || x > 400 || y < 0 || y > 300) {
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

	// 坦克的速度
	int speed = 2;
	boolean isLive = true; // 坦克是否存在

	boolean isHit = false; // 判断我方坦克是否与敌人坦克发生碰撞

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
}

// 我的坦克
class Hero extends Tank {

	// 坦克的炮弹
	Shot s = null;
	Vector<Shot> ss = new Vector<Shot>();

	public Hero(int x, int y) {
		super(x, y); // 调用父类的构造方法进行初始化

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

	// 定义一个向量，用来存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();
	// 敌人添加子弹，应当在刚刚创建坦克时就给它一颗子弹
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
				// 说明坦克正在向上
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
				// 向右
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
				// 向下
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
				// 向左
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
						// 没有子弹，添加
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

		}

	}
}