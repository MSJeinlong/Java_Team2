package com.game.TankGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//我的面板
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

	// 计数器
	int times = 0;

	// 关卡数（即第几关）
	int stage = 1;

	// 定义敌人的坦克组
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 定义继续游戏时的坦克组坐标
	Vector<Node> nodes_tank = new Vector<Node>();

	Vector<Node> nodes_iron = new Vector<Node>();
	Vector<Node> nodes_soil = new Vector<Node>();
	Vector<Node> nodes_water = new Vector<Node>();

	// 定义障碍物
	Barriers br = null;
	Node node = null;

	// 即还没被 new 出来的敌人坦克
	int NewEnemyNum = 20;

	// 活着的坦克数量(包括已经new 出来的活着的坦克数量和尚未被new 出来的坦克数量)
	int LiveEnemyNum = 20;

	// 坦克序号
	int num = 0;

	boolean isPause = false; // 游戏是否处于暂停状态

	// 定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();

	// 定义三张图片,三张图片组成爆炸特效
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// 构造方法,初始化坦克
	public MyPanel(boolean isNewGame) {

		// 恢复记录
		this.isNewGame = isNewGame;

		Node node = null;

		Recorder.setEts(ets);
		Recorder.setEnemNum(LiveEnemyNum);

		// 如果是新游戏
		if (this.isNewGame) {

			// 创建地图
			br = new Barriers(stage);

			// 创建玩家的坦克
			this.NewPlayer(node);

			// 初始化敌人的坦克
			// 游戏界面最多只允许有四辆敌人坦克
			for (int i = 0; i < 4 && i < NewEnemyNum - 1; i++) {
				this.NewEnemyTank(++num, node);
				NewEnemyNum--;
			}

		} else {
			// 继续游戏
			// 读取记录
			Recorder.readRecords();
			this.isPause = Recorder.isPause();
			// 读取关卡数
			this.stage = Recorder.getStage();
			// 读取记录中的地图信息
			nodes_iron = Recorder.getNodes_iron();
			nodes_soil = Recorder.getNodes_soil();
			nodes_water = Recorder.getNodes_water();

			// 根据记录生成地图
			br = new Barriers(nodes_iron, nodes_soil, nodes_water);

			nodes_tank = Recorder.getNodes_tank();

			node = nodes_tank.get(0);
			this.NewPlayer(node);
			// player.life = Recorder.getMylife();
			for (int i = 1; i < nodes_tank.size(); i++) {
				// 先取出一个继续游戏时的坦克组坐标
				node = nodes_tank.get(i);
				// 根据游戏记录创建敌人坦克
				this.NewEnemyTank(i, node);
			}

			player.life = Recorder.getMylife();
			System.out.println("mylife = " + player.life);
			LiveEnemyNum = Recorder.getEnemNum();
			NewEnemyNum = LiveEnemyNum - ets.size();

			this.isNewGame = true;
		}

		Recorder.setMylife(player.life);
		Recorder.setPlayer(player);
		Recorder.setBarriers(br);
		Recorder.setStage(stage);

		// 初始化图片
		try {
			image1 = ImageIO.read(new File("Bomb/bomb3.gif"));
			image2 = ImageIO.read(new File("Bomb/bomb2.gif"));
			image3 = ImageIO.read(new File("Bomb/bomb1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g); // 调用父类的构造方法进行初始化
		g.fillRect(0, 0, WIDTH, HEIGHT); // 填充游戏区域背景为默认颜色

		// 画出提示信息
		this.showMessage(g);

		// 画出我的坦克
		if (player.isLive)
			player.drawTank(g);

		// 画出爆炸特效
		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			if (b.life > 8) {
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			} else if (b.life > 4) {
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
				et.drawTank(g);
				// 画出敌人的子弹
				for (int j = 0; j < et.ss.size(); j++) {
					// 取出子弹
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.setColor(Color.white);
						g.fill3DRect(enemyShot.x, enemyShot.y, 4, 4, true);
					} else {
						// 如果敌人坦克的炮弹已死亡就从Vector()移除
						et.ss.remove(enemyShot);
					}
				}
			} else {// 如果坦克被击毁，则删除该坦克
				ets.remove(et);
			}
		}

		// 画出玩家坦克的子弹
		for (int i = 0; i < player.ss.size(); i++) {
			Shot myShot = player.ss.get(i);

			// 画出每一颗炮弹
			if (myShot.isLive) {
				g.setColor(Color.WHITE);
				g.fill3DRect(myShot.x, myShot.y, 4, 4, true);
			} else {
				// 炮弹消亡时删除该炮弹
				player.ss.remove(myShot);
			}
		}

		// 画出障碍物
		br.drawBarrier(g);

		// 显示游戏暂停信息
		if (this.isPause) {
			System.out.println("pause");
			g.setColor(Color.RED);
			g.drawString("Pause", 200, 150);
		}

		// 重置游戏时刷新界面
		if (this.GameOver || this.isWin) {
			this.freshen(g);
		}
	}

	// 游戏重新开始时，刷新游戏界面
	public void freshen(Graphics g) {
		g.setColor(Color.black);
		for (int i = 30; i > 0; i--) {
			for (int j = 40; j > 0; j--) {
				g.fillRect(WIDTH - j * 10, HEIGHT - i * 10, 10, 10);
			}
		}
		EnemyTank et = new EnemyTank(50, 70);
		et.drawTank(g);
		// this.drawTank(50, 70, g, 0, 1);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Cambria", Font.BOLD, 20));
		int n = Recorder.getElienemNum();
		g.drawString(n + "  X " + " 100  = " + (n * 100), 80, 80);
		g.drawLine(50, 250, 350, 250);

		// 显示胜利或者失败的信息
		if (isWin)
			g.drawString("Victory", 80, 40);
		else {
			g.drawString("Defeat", 80, 40);
		}

	}

	// 创建玩家的坦克
	public void NewPlayer(Node node) {

		if (this.isNewGame) {
			// 创建玩家的坦克
			player = new Player(250, HEIGHT - 30);
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
		EnemyTank et = new EnemyTank(100, 330);
		et.drawTank(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Cambria", Font.BOLD, 18));
		g.drawString(Recorder.getEnemNum() + "", 130, 340);
		Player p1 = new Player(300, 330);
		p1.drawTank(g);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMylife() + "", 330, 340);

		// 画出玩家的总成绩
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		// 显示当前关卡数
		g.drawString("第  " + stage + "  关", 450, 30);
		g.drawString("战绩", 460, 60);
		g.drawString(Recorder.getElienemNum() + "", 480, 110);
		g.setFont(new Font("宋体", Font.BOLD, 15));
		g.drawString("敌人数量：", 10, 340);
		g.drawString("玩家坦克：", 200, 340);

		et = new EnemyTank(450, 100);
		et.drawTank(g);

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
					PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/buh.wav");
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

							// 生成爆炸声音
							PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/kill.wav");
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
				PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/brickErase.wav");
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
				PlayVoice pv1 = new PlayVoice("/JavaProject_Image/Resourse/sounds/enemy.armor.hit.wav");
				pv1.start();
				return;
			}
		}

	}

	// 重置游戏
	public void NewGame() {

		// 初始化游戏信息
		this.isWin = false;
		this.GameOver = false;
		this.isPause = false;
		ets = new Vector<EnemyTank>();
		bombs = new Vector<Bomb>();
		NewEnemyNum = 20;
		LiveEnemyNum = 20;
		num = 0;
		// 创建新地图
		br = new Barriers(stage);
		// 创建玩家的坦克
		this.NewPlayer(node);
		Recorder.setBarriers(br);
		Recorder.setPlayer(player);
		Recorder.setMylife(player.life);
		Recorder.setElienemNum(0);
		Recorder.setEnemNum(LiveEnemyNum);
		Recorder.setEts(ets);
		Recorder.setStage(stage);
		// 初始化敌人的坦克
		for (int i = 0; i < 4 && i < NewEnemyNum - 1; i++) {
			this.NewEnemyTank(num++, node);
			NewEnemyNum -= 1;
		}

	}

	// 判断游戏是否结束
	public void isGameOver() {
		if (player.life <= 0) {
			this.GameOver = true;
		}
	}

	// 判断玩家是否胜利
	public void isWin() {
		if (Recorder.getEnemNum() <= 0) {
			this.isWin = true;
			stage++;
			if (stage >= 4) {
				stage = 1;
			}
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
			System.out.println(isPause);
			PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/game.pause.wav");
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
				PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/shoot.wav");
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
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// 计数器加 1
			times++;

			// 每隔一段时间重置计数器的值
			if (times == 10000)
				times = 0;
			if (!this.isPause) {
				this.hitEnemyTank();// 判断敌人坦克是否被击中

				// 如果当前游戏界面的敌人坦克数量少于 4辆，就加入一辆敌人坦克
				if (ets.size() < 4 && NewEnemyNum > 0 && times % 30 == 0) {
					this.NewEnemyTank(num++, node);
					NewEnemyNum--;
				}

				// // 判断我的坦克是否被敌人的坦克击中
				if (player.isLive)
					this.hitMyTank();

				if (times % 40 == 0 && !player.isLive) {
					// 判断玩家坦克的剩余命数
					if (player.life > 0) {
						// 设置新坦克的参数
						player.setX(250);
						player.setY(HEIGHT - 30);
						player.setDirect(0);
						player.isLive = true;
					}
				}

				this.repaint();

				// 判断游戏是否结束
				this.isGameOver();
				if (this.GameOver) {
					PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/gameOver.wav");
					pv.start();
					JOptionPane.showMessageDialog(null, "GAME OVER", "提示", JOptionPane.INFORMATION_MESSAGE);
					this.NewGame();
					// 播放开战前的背景声音
					PlayVoice pv1 = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
					pv1.start();
				}

				// 判断玩家是否胜利
				this.isWin();
				if (this.isWin) {
					JOptionPane.showMessageDialog(null, "Great! You are winner!", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					this.NewGame();
					// 播放开战前的背景声音
					PlayVoice pv1 = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
					pv1.start();
				}

			} else {
				// this.repaint();
			}
		}
	}
}
