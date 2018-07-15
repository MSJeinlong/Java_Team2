package com.game.TankGame;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TankGame extends JFrame implements ActionListener { // 继承JFrame,用于生成窗体

	// 定义一个开始面板
	MyStartPanel msp = null;

	// 定义一个Desktop，用于打开帮助文档
	Desktop desktop = null;

	// 定义一个游戏面板
	MyPanel mp = null;

	// 设置开始菜单组件
	JMenuBar jmb = null;
	JMenu jm1 = null, jm2 = null, jm3 = null;
	// 游戏菜单的选项
	JMenuItem jmt1 = null, jmt2 = null, jmt3 = null, jmt4 = null;
	// 帮助菜单的选项
	JMenuItem jmt5 = null;
	//用户菜单的选项
	JMenuItem jmt6 = null;
	// 判断是重置游戏还是刚开始新游戏
	int NewNum;

	public static void main(String[] args) {
		//GameClient tankGame = new GameClient();
		//设置为windows系统风格
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//启动登录界面 
		//LoginDialog login = new LoginDialog();
		new TankGame();
	}

	public TankGame() {

		msp = new MyStartPanel();

		// 创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jmt1 = new JMenuItem("新游戏(N)");
		jmt2 = new JMenuItem("退出游戏(E)");
		jmt3 = new JMenuItem("保存退出(S)");
		jmt4 = new JMenuItem("继续游戏(C)");

		// 创建帮助菜单及其选项
		jm2 = new JMenu("帮助(H)");
		jmt5 = new JMenuItem("游戏玩法");
		
		jm3 = new JMenu("用户");
		jmt6 = new JMenuItem("用户信息");

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
		jmt6.addActionListener(this);
		jmt6.setActionCommand("UserInfo");

		// 选项加入菜单
		jm1.add(jmt1);
		jm1.add(jmt4);
		jm1.add(jmt3);
		jm1.add(jmt2);
		jm2.add(jmt5);
		jm3.add(jmt6);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);

		// NewNum置为0
		NewNum = 0;

		// 将msp设置为线程
		Thread t1 = new Thread(msp);
		t1.start();
		// 将菜单栏加入游戏窗口
		this.setJMenuBar(jmb);
		this.add(msp);

		// 设置窗口参数
		this.setTitle("坦克大战");
		Image im = null;
		try {
			im = ImageIO.read(new File("image/client/GamePanel/tankGameIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(im);
		this.setSize(600, 500);
		this.setLocation(100, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 对用户不同的点击做出不同的处理
		if (e.getActionCommand().equals("New Game")) {
			if (NewNum == 0) {
				// 创建战场面板
				mp = new MyPanel(true);
				// 启动mp线程
				Thread t = new Thread(mp);
				t.start();
				// 删除旧的面板
				this.remove(msp);
				// 然后添加新面板
				this.add(mp);// 把我的JPanel添加到Frame中
				NewNum++;
				// 注册监听
				this.addKeyListener(mp);
				// 显示，刷新JFrame
				this.setVisible(true);
			} else {
				this.mp.NewGame();
			}

			// 播放开战前的背景声音
			PlayVoice pv = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
			pv.start();
		} else if (e.getActionCommand().equals("exit")) {
			// 用户退出游戏
			System.exit(0);
		} else if (e.getActionCommand().equals("saveExit")) {
			// 用户点击了“保存退出”
			Recorder.savaGame();
			System.exit(0);
		} else if (e.getActionCommand().equals("ContinueGame")) {
			// 用户点击了继续游戏
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

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				try {
					// 打开帮助文档
					desktop.open(new File("/JavaProject_Image/help.txt"));
				} catch (IOException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		} else if(e.getActionCommand().equals("UserInfo")){
			new UserInfo();
		}
	}

}
