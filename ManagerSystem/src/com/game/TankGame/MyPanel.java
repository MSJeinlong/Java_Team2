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

//�ҵ����
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

	// ������
	int times = 0;

	// �ؿ��������ڼ��أ�
	int stage = 1;

	// ������˵�̹����
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// ���������Ϸʱ��̹��������
	Vector<Node> nodes_tank = new Vector<Node>();

	Vector<Node> nodes_iron = new Vector<Node>();
	Vector<Node> nodes_soil = new Vector<Node>();
	Vector<Node> nodes_water = new Vector<Node>();

	// �����ϰ���
	Barriers br = null;
	Node node = null;

	// ����û�� new �����ĵ���̹��
	int NewEnemyNum = 20;

	// ���ŵ�̹������(�����Ѿ�new �����Ļ��ŵ�̹����������δ��new ������̹������)
	int LiveEnemyNum = 20;

	// ̹�����
	int num = 0;

	boolean isPause = false; // ��Ϸ�Ƿ�����ͣ״̬

	// ����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();

	// ��������ͼƬ,����ͼƬ��ɱ�ը��Ч
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	// ���췽��,��ʼ��̹��
	public MyPanel(boolean isNewGame) {

		// �ָ���¼
		this.isNewGame = isNewGame;

		Node node = null;

		Recorder.setEts(ets);
		Recorder.setEnemNum(LiveEnemyNum);

		// ���������Ϸ
		if (this.isNewGame) {

			// ������ͼ
			br = new Barriers(stage);

			// ������ҵ�̹��
			this.NewPlayer(node);

			// ��ʼ�����˵�̹��
			// ��Ϸ�������ֻ��������������̹��
			for (int i = 0; i < 4 && i < NewEnemyNum - 1; i++) {
				this.NewEnemyTank(++num, node);
				NewEnemyNum--;
			}

		} else {
			// ������Ϸ
			// ��ȡ��¼
			Recorder.readRecords();
			this.isPause = Recorder.isPause();
			// ��ȡ�ؿ���
			this.stage = Recorder.getStage();
			// ��ȡ��¼�еĵ�ͼ��Ϣ
			nodes_iron = Recorder.getNodes_iron();
			nodes_soil = Recorder.getNodes_soil();
			nodes_water = Recorder.getNodes_water();

			// ���ݼ�¼���ɵ�ͼ
			br = new Barriers(nodes_iron, nodes_soil, nodes_water);

			nodes_tank = Recorder.getNodes_tank();

			node = nodes_tank.get(0);
			this.NewPlayer(node);
			// player.life = Recorder.getMylife();
			for (int i = 1; i < nodes_tank.size(); i++) {
				// ��ȡ��һ��������Ϸʱ��̹��������
				node = nodes_tank.get(i);
				// ������Ϸ��¼��������̹��
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

		// ��ʼ��ͼƬ
		try {
			image1 = ImageIO.read(new File("Bomb/bomb3.gif"));
			image2 = ImageIO.read(new File("Bomb/bomb2.gif"));
			image3 = ImageIO.read(new File("Bomb/bomb1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g); // ���ø���Ĺ��췽�����г�ʼ��
		g.fillRect(0, 0, WIDTH, HEIGHT); // �����Ϸ���򱳾�ΪĬ����ɫ

		// ������ʾ��Ϣ
		this.showMessage(g);

		// �����ҵ�̹��
		if (player.isLive)
			player.drawTank(g);

		// ������ը��Ч
		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			if (b.life > 8) {
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			} else if (b.life > 4) {
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
				et.drawTank(g);
				// �������˵��ӵ�
				for (int j = 0; j < et.ss.size(); j++) {
					// ȡ���ӵ�
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.setColor(Color.white);
						g.fill3DRect(enemyShot.x, enemyShot.y, 4, 4, true);
					} else {
						// �������̹�˵��ڵ��������ʹ�Vector()�Ƴ�
						et.ss.remove(enemyShot);
					}
				}
			} else {// ���̹�˱����٣���ɾ����̹��
				ets.remove(et);
			}
		}

		// �������̹�˵��ӵ�
		for (int i = 0; i < player.ss.size(); i++) {
			Shot myShot = player.ss.get(i);

			// ����ÿһ���ڵ�
			if (myShot.isLive) {
				g.setColor(Color.WHITE);
				g.fill3DRect(myShot.x, myShot.y, 4, 4, true);
			} else {
				// �ڵ�����ʱɾ�����ڵ�
				player.ss.remove(myShot);
			}
		}

		// �����ϰ���
		br.drawBarrier(g);

		// ��ʾ��Ϸ��ͣ��Ϣ
		if (this.isPause) {
			System.out.println("pause");
			g.setColor(Color.RED);
			g.drawString("Pause", 200, 150);
		}

		// ������Ϸʱˢ�½���
		if (this.GameOver || this.isWin) {
			this.freshen(g);
		}
	}

	// ��Ϸ���¿�ʼʱ��ˢ����Ϸ����
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

		// ��ʾʤ������ʧ�ܵ���Ϣ
		if (isWin)
			g.drawString("Victory", 80, 40);
		else {
			g.drawString("Defeat", 80, 40);
		}

	}

	// ������ҵ�̹��
	public void NewPlayer(Node node) {

		if (this.isNewGame) {
			// ������ҵ�̹��
			player = new Player(250, HEIGHT - 30);
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
		EnemyTank et = new EnemyTank(100, 330);
		et.drawTank(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Cambria", Font.BOLD, 18));
		g.drawString(Recorder.getEnemNum() + "", 130, 340);
		Player p1 = new Player(300, 330);
		p1.drawTank(g);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMylife() + "", 330, 340);

		// ������ҵ��ܳɼ�
		g.setColor(Color.black);
		g.setFont(new Font("����", Font.BOLD, 20));
		// ��ʾ��ǰ�ؿ���
		g.drawString("��  " + stage + "  ��", 450, 30);
		g.drawString("ս��", 460, 60);
		g.drawString(Recorder.getElienemNum() + "", 480, 110);
		g.setFont(new Font("����", Font.BOLD, 15));
		g.drawString("����������", 10, 340);
		g.drawString("���̹�ˣ�", 200, 340);

		et = new EnemyTank(450, 100);
		et.drawTank(g);

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
					PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/buh.wav");
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

							// ���ɱ�ը����
							PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/kill.wav");
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
				PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/brickErase.wav");
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
				PlayVoice pv1 = new PlayVoice("/JavaProject_Image/Resourse/sounds/enemy.armor.hit.wav");
				pv1.start();
				return;
			}
		}

	}

	// ������Ϸ
	public void NewGame() {

		// ��ʼ����Ϸ��Ϣ
		this.isWin = false;
		this.GameOver = false;
		this.isPause = false;
		ets = new Vector<EnemyTank>();
		bombs = new Vector<Bomb>();
		NewEnemyNum = 20;
		LiveEnemyNum = 20;
		num = 0;
		// �����µ�ͼ
		br = new Barriers(stage);
		// ������ҵ�̹��
		this.NewPlayer(node);
		Recorder.setBarriers(br);
		Recorder.setPlayer(player);
		Recorder.setMylife(player.life);
		Recorder.setElienemNum(0);
		Recorder.setEnemNum(LiveEnemyNum);
		Recorder.setEts(ets);
		Recorder.setStage(stage);
		// ��ʼ�����˵�̹��
		for (int i = 0; i < 4 && i < NewEnemyNum - 1; i++) {
			this.NewEnemyTank(num++, node);
			NewEnemyNum -= 1;
		}

	}

	// �ж���Ϸ�Ƿ����
	public void isGameOver() {
		if (player.life <= 0) {
			this.GameOver = true;
		}
	}

	// �ж�����Ƿ�ʤ��
	public void isWin() {
		if (Recorder.getEnemNum() <= 0) {
			this.isWin = true;
			stage++;
			if (stage >= 4) {
				stage = 1;
			}
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
			System.out.println(isPause);
			PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/game.pause.wav");
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
				PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/shoot.wav");
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
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			// �������� 1
			times++;

			// ÿ��һ��ʱ�����ü�������ֵ
			if (times == 10000)
				times = 0;
			if (!this.isPause) {
				this.hitEnemyTank();// �жϵ���̹���Ƿ񱻻���

				// �����ǰ��Ϸ����ĵ���̹���������� 4�����ͼ���һ������̹��
				if (ets.size() < 4 && NewEnemyNum > 0 && times % 30 == 0) {
					this.NewEnemyTank(num++, node);
					NewEnemyNum--;
				}

				// // �ж��ҵ�̹���Ƿ񱻵��˵�̹�˻���
				if (player.isLive)
					this.hitMyTank();

				if (times % 40 == 0 && !player.isLive) {
					// �ж����̹�˵�ʣ������
					if (player.life > 0) {
						// ������̹�˵Ĳ���
						player.setX(250);
						player.setY(HEIGHT - 30);
						player.setDirect(0);
						player.isLive = true;
					}
				}

				this.repaint();

				// �ж���Ϸ�Ƿ����
				this.isGameOver();
				if (this.GameOver) {
					PlayVoice pv = new PlayVoice("/JavaProject_Image/Resourse/sounds/gameOver.wav");
					pv.start();
					JOptionPane.showMessageDialog(null, "GAME OVER", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					this.NewGame();
					// ���ſ�սǰ�ı�������
					PlayVoice pv1 = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
					pv1.start();
				}

				// �ж�����Ƿ�ʤ��
				this.isWin();
				if (this.isWin) {
					JOptionPane.showMessageDialog(null, "Great! You are winner!", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					this.NewGame();
					// ���ſ�սǰ�ı�������
					PlayVoice pv1 = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
					pv1.start();
				}

			} else {
				// this.repaint();
			}
		}
	}
}
