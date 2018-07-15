package com.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.tools.ImagePanel;
import com.tools.MyTools;

/**
 * 
 * @author JunLong 管理系统的主界面 基本功能如下 1、我的成绩 2、我的联系人 3、我的财务报表 4、我的游戏
 */
public class MainClient extends JFrame implements ActionListener, MouseListener {

	// 定义需要的组件
	private Image titleIcon, timeBg;
	// 菜单栏
	private JMenuBar jmb;
	// 一级菜单
	private JMenu jm1, jm2, jm3, jm4, jm5, jm6;
	// 二级菜单
	private JMenuItem jmt1, jmt2, jmt3, jmt4, jmt5, jmt6;
	// 图标
	private ImageIcon jm1_icon1, jm1_icon2, jm1_icon3, jm2_icon1;
	// 工具栏
	private JToolBar jtb;
	// 按钮
	private JButton jb1, jb2, jb3, jb4;
	// 定义所需的JPanel
	private JPanel jp1, jp2, jp3, jp4, jp5;
	// 定义时间显示标签
	private JLabel time;
	// javax.swing包中的Timer可以定时的处理Action事件，我们可以利用它来完成一下事情
	private Timer t;
	private ImagePanel p1_imgPanel;
	private JLabel p1_jlb1, p1_jlb2, p1_jlb3, p1_jlb4;
	// 定义jp2的JLabel
	private JLabel p2_lab1, p2_lab2;
	private JSplitPane jsp1;
	private CardLayout p3_ct;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		MainClient client = new MainClient();
	}

	public MainClient() {

		// 创建组件
		try {
			// 设置窗口图标
			titleIcon = ImageIO.read(new File("image/client/title.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 初始化菜单栏
		//this.initMenuBar();
		/*
		 * // 处理工具栏 jtb = new JToolBar(); jtb.setFloatable(false); jb1 = new JButton(new
		 * ImageIcon("image/client/Menu/info1.png")); jb2 = new JButton(new
		 * ImageIcon("image/client/Menu/changeUser.png")); jb3 = new JButton(new
		 * ImageIcon("image/client/Menu/signOut.png")); jb4 = new JButton(new
		 * ImageIcon("image/client/Menu/help.png")); // 把按钮加入到工具栏 jtb.add(jb1);
		 * jtb.add(jb2); jtb.add(jb3); jtb.add(jb4);
		 * 
		 * // 工具类加入JFrame ct.add(jtb, "North");
		 */

		// 处理中间的面板
		this.initMidPanel();

		// 处理p5面板（状态栏）
		this.initStatusBar();

		Container ct = this.getContentPane();
		ct.add(jp5, "South");
		ct.add(jsp1, "Center");

		// 设置窗体属性
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height - 33;
		this.setTitle("个人管理系统");
		this.setIconImage(titleIcon);
		this.setBounds(0, 0, w, h);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	// 初始化菜单栏
	public void initMenuBar() {

		// 获取菜单图标
		jm1_icon1 = new ImageIcon("image/client/Menu/info1.png");
		jm1_icon2 = new ImageIcon("image/client/Menu/changeUser.png");
		jm1_icon3 = new ImageIcon("image/client/Menu/signOut.png");
		jm2_icon1 = new ImageIcon("image/client/Menu/help.png");

		// 创建一级菜单
		jm1 = new JMenu("账号管理");
		jm1.setFont(MyTools.f1);
		// 创建jm1的二级菜单
		jmt1 = new JMenuItem("用户资料", jm1_icon1);
		jmt1.setFont(MyTools.f4);
		jmt2 = new JMenuItem("切换用户", jm1_icon2);
		jmt2.setFont(MyTools.f4);
		jmt3 = new JMenuItem("退出", jm1_icon3);
		jmt3.setFont(MyTools.f4);
		// 加入一级菜单jm1
		jm1.add(jmt1);
		jm1.add(jmt2);
		jm1.add(jmt3);

		jm2 = new JMenu("帮助");
		jm2.setFont(MyTools.f1);
		// 文本帮助
		jmt4 = new JMenuItem("文本帮助", jm2_icon1);
		jmt4.setFont(MyTools.f4);
		jm2.add(jmt4);

		jmb = new JMenuBar();
		// 把一级菜单加入菜单栏
		jmb.add(jm1);
		jmb.add(jm2);

		// 将菜单栏加入到JFrame
		this.setJMenuBar(jmb);

	}

	// 初始化中间的界面设计
	public void initMidPanel() {
		// 处理p1面板
		jp1 = new JPanel(new BorderLayout());
		Image p1_bg = null;
		try {
			p1_bg = ImageIO.read(new File("image/client/p1_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 设置手型光标
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel = new ImagePanel(p1_bg);
		p1_imgPanel.setLayout(new GridLayout(4, 1));
		p1_jlb1 = new JLabel("成绩管理", new ImageIcon("image/client/MyGrade.png"), 0);
		p1_jlb1.setFont(MyTools.f6);
		p1_jlb1.setForeground(Color.WHITE);
		p1_jlb1.setCursor(myCursor);
		p1_jlb1.addMouseListener(this);
		p1_imgPanel.add(p1_jlb1);
		//p1_jlb2 = new JLabel("通讯录", new ImageIcon("image/client/contacts.png"), 0);
	/*	p1_jlb2.setFont(MyTools.f6);
		p1_jlb2.setEnabled(false);
		p1_jlb2.setForeground(Color.WHITE);
		p1_jlb2.setCursor(myCursor);
		p1_jlb2.addMouseListener(this);*/
		//p1_imgPanel.add(p1_jlb2);
		//p1_jlb3 = new JLabel("财务账单", new ImageIcon("image/client/finance.png"), 0);
		//p1_imgPanel.add(p1_jlb3);
/*		p1_jlb3.setFont(MyTools.f6);
		p1_jlb3.setEnabled(false);
		p1_jlb3.setForeground(Color.WHITE);
		p1_jlb3.setCursor(myCursor);
		p1_jlb3.addMouseListener(this);*/
		p1_jlb4 = new JLabel("小游戏", new ImageIcon("image/client/game.png"), 0);
		p1_imgPanel.add(p1_jlb4);
		p1_jlb4.setFont(MyTools.f6);
		p1_jlb4.setEnabled(false);
		p1_jlb4.setForeground(Color.WHITE);
		p1_jlb4.setCursor(myCursor);
		p1_jlb4.addMouseListener(this);
		jp1.add(p1_imgPanel);

		// 处理jp2,jp3,jp4
		jp4 = new JPanel(new BorderLayout());
		jp2 = new JPanel(new CardLayout());
		p2_lab1 = new JLabel(new ImageIcon("image/client/p2_left.png"));
		p2_lab2 = new JLabel(new ImageIcon("image/client/p2_right.png"));
		// 把p1_lab1/2加入到jp2中
		jp2.add(p2_lab1, "0");
		jp2.add(p2_lab2, "1");

		Image p3_img = null;
		try {
			p3_img = ImageIO.read(new File("image/client/p3_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p3_ct = new CardLayout();
		jp3 = new JPanel(p3_ct);
		ImagePanel p3_bg = new ImagePanel(p3_img);
		jp3.add(p3_bg, "0");
		//创建GradeInfo对象(JPanel)
		GradeInfo gInfo = new GradeInfo();
		//对jp3加入几个JLabel
		jp3.add(gInfo, "1");
		GamePanel gamePanel = new GamePanel();
		jp3.add(gamePanel, "2");
		//jp3.add(new JLabel(new ImageIcon("image/client/p2_right.png")), "2");

		// 把jp2、jp3加入到jp4
		jp4.add(jp2,  "West");
		jp4.add(jp3, "Center");

		// 做一个拆分窗户，分别存放p1、p4
		jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
		// 指定左边的面板是多大
		jsp1.setDividerLocation(200);
		// 把边界线设为0
		jsp1.setDividerSize(0);

	}

	// 初始化状态栏
	public void initStatusBar() {
		// 设计状态栏
		jp5 = new JPanel(new BorderLayout());
		// 创建Timer,每1秒就触发ActionEvent
		t = new Timer(1000, this);
		// 启动定时器
		t.start();
		time = new JLabel();
		time.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString() + "   ");
		time.setFont(MyTools.f3);
		// 状态栏图片
		try {
			timeBg = ImageIO.read(new File("image/client/statusBar/time_bg.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImagePanel ip1 = new ImagePanel(timeBg);
		ip1.setLayout(new BorderLayout());
		ip1.add(time, "East");
		jp5.add(ip1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//判断用户点击了哪个操作JLabel
		if(e.getSource() == this.p1_jlb1) {
			p3_ct.show(jp3, "1");
		} else if(e.getSource() == this.p1_jlb4) {
			p3_ct.show(jp3, "2");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// 如果用户选择了某个操作JLabel，则高亮之
		if (e.getSource() == this.p1_jlb1) {
			this.p1_jlb1.setEnabled(true);
		}  else if (e.getSource() == this.p1_jlb4) {
			this.p1_jlb4.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// 如果用户选择了离开操作JLabel，则禁止
		if (e.getSource() == this.p1_jlb1) {
			this.p1_jlb1.setEnabled(false);
		} else if (e.getSource() == this.p1_jlb4) {
			this.p1_jlb4.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString() + "   ");
	}
}
