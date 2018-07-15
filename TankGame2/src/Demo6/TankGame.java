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

public class TankGame extends JFrame implements ActionListener { // 继承JFrame,用于生成窗体

	// 定义一个开始面板
	MyStartPanel msp = null;

	// 定义一个Desktop，用于打开帮助文档
	Desktop desktop = null;

	// 定义一个游戏面板
	MyPanel mp = null;

	// 设置开始菜单组件
	JMenuBar jmb = null;
	JMenu jm1 = null, jm2 = null;
	// 游戏菜单的选项
	JMenuItem jmt1 = null, jmt2 = null, jmt3 = null, jmt4 = null;
	// 帮助菜单的选项
	JMenuItem jmt5 = null;

	public static void main(String[] args) {
		TankGame tankGame = new TankGame();
	}

	public TankGame() {

		msp = new MyStartPanel();
		// 创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jmt1 = new JMenuItem("开始新游戏(N)");
		jmt2 = new JMenuItem("退出游戏(E)");
		jmt3 = new JMenuItem("保存退出(S)");
		jmt4 = new JMenuItem("继续游戏(C)");

		// 创建帮助菜单及其选项
		jm2 = new JMenu("帮助(H)");
		jmt5 = new JMenuItem("游戏玩法");

		// 设置快捷方式
		jm1.setMnemonic('G');
		jmt1.setMnemonic('N');
		jmt2.setMnemonic('E');
		jmt3.setMnemonic('S');
		jmt4.setMnemonic('C');

		// 为所有的jmt增加按键监听
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

		// 选项加入菜单
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
			mp = new MyPanel(true);
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
		} else if (e.getActionCommand().equals("exit")) {
			// 用户点击了退出
			// 保存玩家游戏的成绩
			// Recorder.keepRecording();
			System.exit(0);
		} else if (e.getActionCommand().equals("saveExit")) {
			Recorder.keepRecAndEnemyTank();
			System.exit(0);
		} else if (e.getActionCommand().equals("ContinueGame")) {
			// 创建战场面板
			mp = new MyPanel(false);

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
		} else if (e.getActionCommand().equals("Help")) {
			// 弹出帮助窗口
			// hj = new HelpJFrame();

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				try {
					// 打开帮助文档
					desktop.open(new File("F:\\JavaProject_Image\\help.txt"));
				} catch (IOException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		}
	}
}

// 帮助菜单窗口
class WinJFrame extends JFrame {

	private int width = 600;
	private int height = 500;
	private WinPanel wp = null;
	private MyPanel mp = null;

	// 构造方法
	public WinJFrame(MyPanel mp) {
		// 新建WinPanel
		wp = new WinPanel();
		this.add(wp);

		this.mp = mp;

		this.setTitle("本关成绩汇总");
		this.setSize(width, height);
		this.setLocation(1000, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	// 内部类，成绩面板
	private class WinPanel extends JPanel {

		// 击败不同敌人坦克可以有不同的分数
		int[] enemyType = new int[] { 100, 200, 300, 400 };
		int[] enemyNum = new int[4];
		int score = 0; // 总分数
		int total = 0; // 击败敌人坦克的总数
	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, 600, 500);
		mp.drawTank(50, 100, g, 0, 1);
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

class WinPanel extends JPanel {

	// 击败不同敌人坦克可以有不同的分数
	int[] enemyType = new int[] { 100, 200, 300, 400 };
	int[] enemyNum = new int[4];
	int score = 0; // 总分数
	int total = 0; // 击败敌人坦克的总数
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

// 我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {

	// 游戏区域的大小
	final int WIDTH = 400;
	final int HEIGHT = 300;

	// 定义一个我的坦克
	Player player = null;

	// 判断是继续游戏还是新游戏
	boolean isNewGame;

	// 判断游戏是否结束
	boolean GameOver = false;

	// 判断游戏是否结束
	boolean isWin = false;

	// 定义敌人的坦克组
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 定义继续游戏时的坦克组坐标
	Vector<Node> nodes = new Vector<Node>();

	// 定义障碍物
	Barriers br = new Barriers();

	Node node = null;

	// 一个关卡总的敌人坦克
	int enemyNum = 4;

	// 坦克序号
	int num = 0;

	boolean isPause = false; // 游戏是否处于暂停状态

	// 定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();

	// 定义三张图片,三张图片组成爆炸特效
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// 定义声音集合
	Vector<PlayVoice> pvs = new Vector<PlayVoice>();

	PlayVoice pv = null;

	// 构造方法,初始化坦克
	public MyPanel(boolean isNewGame) {

		// 恢复记录
		this.isNewGame = isNewGame;

		Node node = null;

		Recorder.setEts(ets);
		Recorder.setEnemNum(enemyNum);

		// 如果是新游戏
		if (this.isNewGame) {
			// 创建玩家的坦克
			this.NewPlayer(node);
			Recorder.setPlayer(player);
			// 初始化敌人的坦克
			for (int i = 0; i < 4 && i < enemyNum - 1; i++) {
				this.NewEnemyTank(num++, node);
				enemyNum -= 1;
			}

		} else {
			// System.out.println("继续游戏");
			// 读取记录
			Recorder.getNodeAndEnNums();
			nodes = Recorder.getNodes();
			this.isPause = Recorder.isPause();
			node = nodes.get(0);
			this.NewPlayer(node);
			Recorder.setPlayer(player);
			for (int i = 1; i < nodes.size(); i++) {
				// 先取出一个继续游戏时的坦克组坐标
				node = nodes.get(i);
				// 根据游戏记录创建敌人坦克
				this.NewEnemyTank(i, node);
			}
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

		// 播放开战前的背景声音
		PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\TankGame\\start.wav");
		pvs.add(pv);
		pv.start();

		// // 创建敌人坦克移动的声音
		// pv1 = new
		// PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\enemy.move.wav");

	}

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g); // 调用父类的构造方法进行初始化
		g.fillRect(0, 0, WIDTH, HEIGHT); // 填充游戏区域背景为默认颜色

		// 画出提示信息
		this.showMessage(g);

		// 画出我的坦克
		if (player.isLive)
			this.drawTank(player.getX(), player.getY(), g, this.player.direct, 1);

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
		for (int i = 0; i < player.ss.size(); i++) {
			Shot myShot = player.ss.get(i);

			// 画出每一颗炮弹
			if (myShot.isLive) {
				g.setColor(Color.WHITE);
				g.draw3DRect(myShot.x, myShot.y, 2, 2, true);
			} else {
				// 炮弹消亡时删除该炮弹
				player.ss.remove(myShot);
			}
		}

		// 画出障碍物
		br.drawBarrier(g);

		// 显示游戏暂停信息
		if (this.isPause) {
			g.setColor(Color.RED);
			g.drawString("Pause", 200, 150);
		}

		// 提示游戏结束
		// if (this.GameOver) {
		// for (int i = 0; i <= 15; i++)
		// g.drawString("GAME", 200, HEIGHT - i * 10);
		// }
	}

	// 创建玩家的坦克
	public void NewPlayer(Node node) {

		if (this.isNewGame) {
			// 创建玩家的坦克
			player = new Player(200, HEIGHT - 30);
			player.setBarrier(br);
		} else {
			player = new Player(node.x, node.y);
			player.setDirect(node.direct);
			player.setBarrier(br);
		}
		// 游戏的相关信息传给坦克
		player.getPlayer(player);
		player.getEts(ets);
		player.getWidth_height(WIDTH, HEIGHT);
	}

	// 创建新的敌人坦克
	public void NewEnemyTank(int num, Node node) {

		EnemyTank et = null;
		// 如果是新游戏，就如下创建坦克
		if (this.isNewGame) {
			et = new EnemyTank((num % 4) * 80 + 30, 20);
			et.setColor(0); // 设置敌人坦克的颜色
			et.setDirect(2);
		} else { // 如果是继续游戏，就根据Node的信息创建坦克
			// 创建一辆敌人的坦克对象
			et = new EnemyTank(node.x, node.y);
			et.setColor(0); // 设置敌人坦克的颜色
			et.setDirect(node.direct);
		}
		// 将游戏的边界大小传给坦克
		et.getWidth_height(WIDTH, HEIGHT);
		// 将MyPanel的敌人坦克向量交给该敌人坦克
		et.getEts(ets);
		et.getPlayer(player);
		et.setBarrier(br);
		// 将游戏的状态传递给坦克
		et.getPause(isPause);

		// 启动坦克
		Thread t = new Thread(et);
		t.start();
		// 给敌人坦克添加一颗子弹
		Shot s = new Shot(et.x - 10, et.y - 15, et.direct);

		// 将游戏的相关信息状态传递给子弹
		s.getPause(isPause);
		s.getWidth_height(WIDTH, HEIGHT);
		et.ss.add(s);// 将炮弹加给敌人坦克
		Thread t1 = new Thread(s);
		t1.start();// 启动敌人坦克的炮弹线程
		ets.add(et); // 将敌人坦克加入到集合了
	}

	// 画出提示信息
	public void showMessage(Graphics g) {
		// 画出用于提示信息的坦克（该坦克不参与战斗）
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Cambria", Font.BOLD, 18));
		g.drawString(Recorder.getEnemNum() + "", 100, 340);
		this.drawTank(200, 330, g, 0, 1);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMylife() + "", 220, 340);

		// 画出玩家的总成绩
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString("总成绩", 420, 30);

		this.drawTank(420, 60, g, 0, 0);

		g.setColor(Color.black);
		g.drawString(Recorder.getElienemNum() + "", 450, 70);
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
				// 判断敌人子弹是否打到玩家坦克
				this.hitTank(enemyShot, player);
				// 判断敌人子弹是否打到障碍物
				this.hitBarrier(br, enemyShot);

				if (!player.isLive) { // 如果玩家坦克挂掉了，life-1, 然后直接退出第二重循环
					player.life -= 1;
					// 生成爆炸声音
					PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\buh.wav");
					pv.start();
					Recorder.reduceMylife();
					break;
				}

				// if()
			}

			if (!player.isLive) { // 如果玩家坦克挂掉了， 退出第一重循环
				break;
			}
		}
	}

	// 判断我的子弹是击中敌人坦克
	public void hitEnemyTank() {
		for (int i = 0; i < player.ss.size(); i++) {
			// 取出炮弹
			Shot myShot = player.ss.get(i);
			// 判断炮弹是否有效
			if (myShot.isLive) {
				// 取出每个坦克，与该炮弹进行判断
				for (int j = 0; j < ets.size(); j++) {
					// 取出坦克
					EnemyTank et = ets.get(j);

					if (et.isLive) {
						this.hitTank(myShot, et);
						if (!et.isLive) {

							// 坦克总数量减少
							enemyNum -= 1;
							// 生成爆炸声音
							PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\kill.wav");
							pv.start();
							Recorder.reduceEnNum(); // 坦克数量减少
							Recorder.addElienemNum(); // 记录玩家成绩
						}
					}
				}

				// 将该子弹与所有的障碍物进行判断
				this.hitBarrier(br, myShot);
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

	// 判断子弹是否打到障碍物
	public void hitBarrier(Barriers br, Shot s) {
		// 先判断子弹打到土墙时，土墙会爆炸
		for (int i = 0; i < br.soilwalls.size(); i++) {
			// 从土墙集合中取出一个土墙对象
			Barriers.SoilWall sw = br.soilwalls.get(i);
			// 判断子弹是否打到了该土墙
			if (s.x >= sw.x && s.x <= sw.x + sw.width && s.y >= sw.y && s.y <= sw.y + sw.height) {
				s.isLive = false;
				sw.isLive = false;
				// 生成爆炸特效
				Bomb b = new Bomb(sw.x, sw.y);
				bombs.add(b);
				// 产生声音特效
				PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\brickErase.wav");
				pv.start();
				return;
			}
		}

		// 判断子弹是否打到了铁墙
		for (int i = 0; i < br.ironwalls.size(); i++) {
			Barriers.IronWall iw = br.ironwalls.get(i);
			// 但子弹打到铁墙时
			if (s.x >= iw.x && s.x <= iw.x + iw.width && s.y >= iw.y && s.y <= iw.y + iw.height) {
				s.isLive = false; // 子弹将不存在,但铁墙仍然存在
				// 生成爆炸特效
				Bomb b = new Bomb(iw.x, iw.y);
				bombs.add(b);
				// 产生声音特效
				PlayVoice pv1 = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\enemy.armor.hit.wav");
				pv1.start();
				return;
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

	// 游戏暂停时，将所有的坦克和子弹设置为暂停
	public void setPause() {

		// 将玩家的坦克及其子弹设置为暂停
		for (int i = 0; i < player.ss.size(); i++) {
			Shot shot = player.ss.get(i);
			shot.getPause(isPause);
		}

		// 取出每一辆敌人坦克
		for (int i = 0; i < this.ets.size(); i++) {
			// 取出坦克
			EnemyTank et = ets.get(i);
			et.getPause(isPause);
			// 取出每一颗子弹
			for (int j = 0; j < et.ss.size(); j++) {
				// 取出子弹
				Shot enemyShot = et.ss.get(j);
				enemyShot.getPause(isPause);
				this.hitTank(enemyShot, player);
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
			PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\game.pause.wav");
			pv.start();
			this.setPause();
			Recorder.setPause(isPause);
		}

		// 先判断游戏是否处于暂停状态
		if (this.isPause || !player.isLive)
			return; // 当游戏处于暂停状态或者玩家坦克已经挂了时直接退出

		// 设置我的坦克的方向
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// 向上
			this.player.setDirect(0);
			if (player.y - 15 > 0) { // 防止坦克越界

				if (!player.isTouchOtherTank() && !player.isTouchBarrier()) {
					this.player.moveUp();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// 向右
			this.player.setDirect(1);
			if (player.x + 15 < this.WIDTH) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveRight();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// 向下
			this.player.setDirect(2);
			if (player.y + 15 < this.HEIGHT) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveDown();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// 向左
			this.player.setDirect(3);
			if (player.x - 15 > 0) {

				if (!player.isTouchOtherTank() && !player.isTouchBarrier())
					this.player.moveLeft();
			}
		}

		// 判断玩家是否按下J(坦克开火)
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// 一次最多发射2枚炮弹
			if (player.ss.size() < 2) {
				this.player.shotEnemy();

				// 产生玩家坦克开火的声音
				PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\Resourse\\sounds\\shoot.wav");
				pv.start();
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

			if (!this.isPause) {
				this.hitEnemyTank();// 判断敌人坦克是否被击中

				if (ets.size() < 4 && enemyNum >= 0) {
					this.NewEnemyTank(num++, node);
				}

				// // 判断我的坦克是否被敌人的坦克击中
				if (player.isLive)
					this.hitMyTank();

				// 玩家坦克挂掉之后
				if (!player.isLive) {
					// 先休眠100毫秒
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 判断玩家坦克的剩余命数
					if (player.life > 0) {
						// 设置新坦克的参数
						player.setX(200);
						player.setY(HEIGHT - 30);
						player.setDirect(0);
						player.isLive = true;
					}
				}

				// 判断游戏是否结束
				this.isGameOver();//

				// 判断玩家是否胜利
				this.isWin();
				if (isWin) {
					WinJFrame winJFrame = new WinJFrame(this);
					this.isWin = false;
				}
			}
			// System.out.println(hero.isHit);
			// 重绘界面

			// 启动敌人坦克移动的声音
			// pv1.start();
			this.repaint();
		}
	}
}
