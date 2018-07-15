package Demo3;

/**
*TankGame2.0
*1、画出坦克
*2、我的坦克可以上下左右移动
*3、可以发射子弹，子弹最多5连发
*4、击中敌方坦克，敌方坦克消失(有爆炸效果)
*5、玩家坦克被击中后，显示爆炸效果
*6、防止敌人坦克重叠运动
* 6.1决定把判断是否碰撞的函数写在tank类里
*7、有多个关卡
*7.1做一个开始的Panel，它是一个空的
*7.2做一个闪烁效果
*8、可以在玩游戏时暂停和退出
*8.1玩家可以按下空格键实现暂停
*9、可以记录玩家的成绩(x)
*9.1用文件流
*9.2单写一个类
*10、加入背景音效(x)
 */

import java.awt.Color;
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

public class TankGame3 extends JFrame implements ActionListener { // 继承JFrame,用于生成窗体

	MyPanel mp = null;
	// 定义一个开始面板
	MyStartPanel msp = null;

	// 设置开始菜单组件
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmt1 = null;

	public static void main(String[] args) {
		TankGame3 tankGame4 = new TankGame3();
	}

	public TankGame3() {

		msp = new MyStartPanel();
		// 创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		// 设置快捷方式
		jm1.setMnemonic('G');
		jmt1 = new JMenuItem("开始新游戏(N)");

		// 为jmt1增加按键监听
		jmt1.addActionListener(this);
		jmt1.setActionCommand("New Game");
		jm1.add(jmt1);
		jmb.add(jm1);

		jm1.add(jmt1);
		jmb.add(jm1);

		Thread t1 = new Thread(msp);
		t1.start();
		this.setJMenuBar(jmb);
		this.add(msp);

		this.setTitle("坦克大战");
		this.setSize(600, 500);
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 对用户不同的点击做出不同的处理
		if (e.getActionCommand().equals("New Game")) {
			// 创建战场面板
			mp = new MyPanel();
			// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 删除旧的面板
			this.remove(msp);
			// 然后添加新面板
			this.add(mp);// 把我的JPanel添加到Frame中
			// 注册监听
			this.addKeyListener(mp);
			// 显示，刷新JFrame
			this.setVisible(true);
		}
	}
}

// 开始界面
class MyStartPanel extends JPanel implements Runnable {
	boolean isflicker = true;// 控制闪烁

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		// 提示信息
		if (isflicker) {
			g.setColor(Color.YELLOW);
			// 信息的字体设置
			Font myfont = new Font("Cambria", Font.BOLD, 20);
			g.setFont(myfont);
			g.drawString("stage:1", 150, 150);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 休眠
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

// 我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {
	// 定义一个我的坦克
	Hero hero = null;

	// 定义敌人的坦克组
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	int enSize = 3; // 敌人坦克数量

	boolean isPause = false; // 游戏是否处于暂停状态

	// 定义纪录类
	Recorter recorter = null;

	// 定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();

	// 定义三张图片,三张图片组成爆炸特效
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// 构造方法,初始化坦克
	public MyPanel() {
		hero = new Hero(100, 200);

		recorter = new Recorter();

		// 初始化敌人的坦克
		for (int i = 0; i < enSize; i++) {
			// 创建一辆敌人的坦克对象
			EnemyTank et = new EnemyTank((i + 1) * 50, 50);
			et.setColor(0); // 设置敌人坦克的颜色
			et.setDirect(2);
			// 将MyPanel的敌人坦克向量交给该敌人坦克
			et.getEts(ets);
			et.getHero(hero);
			// 将游戏的状态传递给坦克
			et.getPause(isPause);

			// 启动坦克
			Thread t = new Thread(et);
			t.start();
			// 给敌人坦克添加一颗子弹
			Shot s = new Shot(et.x - 10, et.y - 15, et.direct);

			// 将游戏的状态传递给子弹
			s.getPause(isPause);
			et.ss.add(s);// 将炮弹加给敌人坦克
			Thread t1 = new Thread(s);
			t1.start();// 启动敌人坦克的炮弹线程
			ets.add(et); // 将敌人坦克加入到集合了
		}

		// 初始化图片

		try {
			image1 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb3.gif"));
			image2 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb2.gif"));
			image3 = ImageIO.read(new File("F:\\Java_Picture\\TankGame\\bomb1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g); // 调用父类的构造方法进行初始化
		g.fillRect(0, 0, 400, 300); // 填充游戏区域背景为默认颜色

		// 画出提示信息
		this.showMessage(g);

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
				if (et.isPause) {
					g.drawString("Tank is Pause", 100, 200);
				}
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

		if (this.isPause) {
			g.setColor(Color.RED);
			g.drawString("Pause", 200, 150);
		}
	}

	// 画出提示信息
	public void showMessage(Graphics g) {
		// 画出用于提示信息的坦克（该坦克不参与战斗）
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Cambria", Font.BOLD, 18));
		g.drawString(recorter.getEnemNum() + "", 100, 340);
		this.drawTank(200, 330, g, 0, 1);
		g.setColor(Color.BLACK);
		g.drawString(recorter.getMylife() + "", 220, 340);

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

				if (!hero.isLive) { // 如果玩家坦克挂掉了，mylife-1, 然后直接退出第二重循环
					recorter.reduceMylife();
					break;
				}
			}

			if (!hero.isLive) { // 如果玩家坦克挂掉了， 退出第一重循环
				break;
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
						if (!et.isLive)
							recorter.reduceEnNum(); // 坦克数量减少
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
				et.isLive = false;// 坦克被击毁

				// 生成爆炸特效
				Bomb b = new Bomb(et.x - 10, et.y - 15);
				bombs.add(b);
				break;
			}
		case 1:
		case 3:
			if (s.x > et.x - 15 && s.x < et.x + 15 && s.y > et.y - 10 && s.y < et.y + 10) {
				s.isLive = false; // 炮弹消失
				et.isLive = false;// 坦克被击毁

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

	// 游戏暂停时，将所有的坦克和子弹设置为暂停
	public void setPause() {
		// 取出每一辆坦克
		for (int i = 0; i < this.ets.size(); i++) {
			// 取出坦克
			EnemyTank et = ets.get(i);
			et.getPause(isPause);
			// 取出每一颗子弹
			for (int j = 0; j < et.ss.size(); j++) {
				// 取出子弹
				Shot enemyShot = et.ss.get(j);
				enemyShot.getPause(isPause);
				this.hitTank(enemyShot, hero);
			}
		}
	}

	// 键盘监听
	// 字母 W == 向上， D ==向右，S == 向下， A == 向左
	@Override
	public void keyPressed(KeyEvent e) {

		// 判断是否按下暂停键
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.isPause = !this.isPause;
			this.setPause();
		}

		// 先判断游戏是否处于暂停状态
		if (this.isPause)
			return;

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
			if (!this.isPause) {
				this.hitEnemyTank();

				// 判断我的坦克是否被敌人的坦克击中
				// if (hero.isLive)
				// this.hitMyTank();
			}
			// System.out.println(hero.isHit);
			// 重绘界面
			this.repaint();
		}
	}
}

// 记录类,同时也可以记录玩家的游戏设置
class Recorter {
	// 记录每关有多少敌人
	private static int enemNum = 20;
	// 玩家的坦克生命数量
	private static int mylife = 3;

	public static int getEnemNum() {
		return enemNum;
	}

	public static void setEnemNum(int enemNum) {
		Recorter.enemNum = enemNum;
	}

	public static int getMylife() {
		return mylife;
	}

	public static void setMylife(int mylife) {
		Recorter.mylife = mylife;
	}

	public static void reduceEnNum() {
		enemNum -= 1;
	}

	public static void reduceMylife() {
		mylife -= 1;
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
	boolean isPause = false;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
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
	boolean isPause = false; // 游戏是否暂停，若游戏暂停，坦克停止运动

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

	public void getPause(boolean pause) {
		this.isPause = pause;
	}
}

// 我的坦克
class Hero extends Tank {

	// 坦克的炮弹
	Shot s = null;
	Vector<Shot> ss = new Vector<Shot>();

	// 定义一个向量，可以存放敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

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

	// 定义一个向量，可以存放敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	Hero hero = null;

	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// 得到MyPanel的敌人坦克向量
	public void getEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 得到我的坦克
	public void getHero(Hero hero) {
		this.hero = hero;
	}

	public boolean isTouchOtherEnemy() {
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

			if (hero.direct == 0 || hero.direct == 2) {
				if (this.x - 10 >= hero.x - 10 && this.x - 10 <= hero.x + 10 && this.y - 15 >= hero.y - 15
						&& this.y - 15 <= hero.y + 15) {
					return true;
				}
				if (this.x + 10 >= hero.x - 10 && this.x + 10 <= hero.x + 10 && this.y - 15 >= hero.y - 15
						&& this.y - 15 <= hero.y + 15) {
					return true;
				}
			}
			if (hero.direct == 1 || hero.direct == 3) {
				if (this.x - 10 >= hero.x - 15 && this.x - 10 <= hero.x + 15 && this.y - 15 >= hero.y - 10
						&& this.y - 15 <= hero.y + 10) {
					return true;
				}
				if (this.x + 10 >= hero.x - 15 && this.x + 10 <= hero.x + 15 && this.y - 15 >= hero.y - 10
						&& this.y - 15 <= hero.y + 10) {
					return true;
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
			if (hero.direct == 0 || hero.direct == 2) {
				if (this.x + 15 >= hero.x - 10 && this.x + 15 <= hero.x + 10 && this.y - 10 >= hero.y - 15
						&& this.y - 10 <= hero.y + 15) {
					return true;
				}
				if (this.x + 15 >= hero.x - 10 && this.x + 15 <= hero.x + 10 && this.y + 10 >= hero.y - 15
						&& this.y + 10 <= hero.y + 15) {
					return true;
				}
			}

			if (hero.direct == 1 || hero.direct == 3) {
				if (this.x + 15 >= hero.x - 15 && this.x + 15 <= hero.x + 15 && this.y - 10 >= hero.y - 10
						&& this.y - 10 <= hero.y + 10) {
					return true;
				}
				if (this.x + 15 >= hero.x - 15 && this.x + 15 <= hero.x + 15 && this.y + 10 >= hero.y - 10
						&& this.y + 10 <= hero.y + 10) {
					return true;
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

			if (hero.direct == 0 || hero.direct == 2) {
				if (this.x - 10 >= hero.x - 10 && this.x - 10 <= hero.x + 10 && this.y + 15 >= hero.y - 15
						&& this.y + 15 <= hero.y + 15) {
					return true;
				}
				if (this.x + 10 >= hero.x - 10 && this.x + 10 <= hero.x + 10 && this.y + 15 >= hero.y - 15
						&& this.y + 15 <= hero.y + 15) {
					return true;
				}
			}
			if (hero.direct == 1 || hero.direct == 3) {
				if (this.x - 10 >= hero.x - 15 && this.x - 10 <= hero.x + 15 && this.y + 15 >= hero.y - 10
						&& this.y + 15 <= hero.y + 10) {
					return true;
				}
				if (this.x + 10 >= hero.x - 15 && this.x + 10 <= hero.x + 15 && this.y + 15 >= hero.y - 10
						&& this.y + 15 <= hero.y + 10) {
					return true;
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
			if (hero.direct == 0 || hero.direct == 2) {
				if (this.x - 15 >= hero.x - 10 && this.x - 15 <= hero.x + 10 && this.y - 10 >= hero.y - 15
						&& this.y - 10 <= hero.y + 15) {
					return true;
				}
				if (this.x - 15 >= hero.x - 10 && this.x - 15 <= hero.x + 10 && this.y + 10 >= hero.y - 15
						&& this.y + 10 <= hero.y + 15) {
					return true;
				}
			}
			if (hero.direct == 1 || hero.direct == 3) {
				if (this.x - 15 >= hero.x - 15 && this.x - 15 <= hero.x + 15 && this.y - 10 >= hero.y - 10
						&& this.y - 10 <= hero.y + 10) {
					return true;
				}
				if (this.x - 15 >= hero.x - 15 && this.x - 15 <= hero.x + 15 && this.y + 10 >= hero.y - 10
						&& this.y + 10 <= hero.y + 10) {
					return true;
				}
			}
			break;
		}

		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			switch (this.direct) {
			case 0:
				// 说明坦克正在向上
				for (int i = 0; i < 30; i++) {
					if (y - 15 < 0 || this.isTouchOtherEnemy())
						break;
					if (!this.isPause) // 坦克连续平滑移动时，需要时刻判断游戏是否暂停了
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
					if (x + 15 > 400 || this.isTouchOtherEnemy())
						break;
					if (!this.isPause)
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
					if (y + 15 > 300 || this.isTouchOtherEnemy())
						break;
					if (!this.isPause)
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
					if (x - 15 < 0 || this.isTouchOtherEnemy())
						break;
					if (!this.isPause)
						x -= speed;
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