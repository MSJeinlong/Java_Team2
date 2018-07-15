/**
 * 库存管理系统主界面
 * 完成界面设计
 */
package com.ivtm.view;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.itvm.tools.ImagePanel;
import com.itvm.tools.MyTools;
import com.model.SellerModel;
import com.model.SupplierModel;
import com.model.UserModel;

public class Windows1 extends JFrame implements ActionListener, MouseListener {

	// 定义需要的组件
	Image titleIcon, timeBg;
	JPanel jp1, jp2, jp3, jp4, jp5;
	// 显示系统当前时间
	JLabel time;
	JLabel[] jp1_lab = new JLabel[8];
	// javax.swing包中的Timer可以定时的处理Action事件，我们可以利用它来完成一下事情
	javax.swing.Timer t;
	ImagePanel p1_imgPanel, p3_impan;
	Image p1_bg = null, p3_bg = null;
	// 给jp2面板定义需要的JLabel
	JLabel jp2_lab1, jp2_lab2;
	// 拆分窗口所需组件
	JSplitPane jsp;
	// 卡片布局控件
	CardLayout p3_ct;
	// 表格信息显示部分
	UserInfo uInfo, uInfo1, uInfo2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Windows1 windows1 = new Windows1();
		// 启动进度条界面
		/*
		 * Index index=new Index(); //创建index线程 Thread t=new Thread(index); //启动线程
		 * t.start();
		 */
	}

	public Windows1() {

		// 初始化中间面板
		this.initMiddlePanel();

		// 初始化状态栏
		this.initToolBar();

		// 设置窗体属性
		// 创建组件
		try {
			// 窗体图标
			titleIcon = ImageIO.read(new File("image/Windows1/title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 从JFrame中获取Container
		Container ct = this.getContentPane();
		// 将JPanel加入JFrame
		ct.add(jp5, "South");
		ct.add(jsp, "Center");
		// ct.add(jp2, "West");

		// 设置标题
		this.setTitle("库存管理系统");
		// 设置图标
		this.setIconImage(titleIcon);
		// 获取屏幕的大小
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		// 设置大小
		this.setSize(width, height - 30);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 初始化中间的4个面板
	public void initMiddlePanel() {
		// 设计中间面板
		jp1 = new JPanel(new BorderLayout());

		try {
			p1_bg = ImageIO.read(new File("image/Windows1/jp1_bg.jpg"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// 设置光标形状为手型光标
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel = new ImagePanel(p1_bg);
		p1_imgPanel.setLayout(new GridLayout(9, 1));
		// 设置JLabel
		// 设置jp1的第一个JLabel
		jp1_lab[0] = new JLabel("管 理 员 信 息", JLabel.CENTER);
		jp1_lab[0].setIcon(new ImageIcon("image/Windows1/Admin2.png"));
		jp1_lab[0].setFont(MyTools.f6);
		jp1_lab[0].setForeground(Color.white);
		// 加入第一个JLabel
		// 让该JLabel不可用
		jp1_lab[0].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[0].setCursor(cursor);
		// 注册监听
		jp1_lab[0].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[0]);

		// 第二个JLabel
		jp1_lab[1] = new JLabel("供 货 商 管 理", JLabel.CENTER);
		jp1_lab[1].setIcon(new ImageIcon("image/Windows1/supplier.png"));
		jp1_lab[1].setFont(MyTools.f6);
		jp1_lab[1].setForeground(Color.white);
		jp1_lab[1].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[1].setCursor(cursor);
		// 注册监听
		jp1_lab[1].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[1]);

		// 第三个JLabel
		jp1_lab[2] = new JLabel("销 售 商 管 理", JLabel.CENTER);
		// 设置图标
		jp1_lab[2].setIcon(new ImageIcon("image/Windows1/seller.png"));
		// 设置字体
		jp1_lab[2].setFont(MyTools.f6);
		// 设置字体颜色
		jp1_lab[2].setForeground(Color.white);
		jp1_lab[2].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[2].setCursor(cursor);
		// 注册监听
		jp1_lab[2].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[2]);

		// 第四个JLabel
		jp1_lab[3] = new JLabel("仓 库 管 理", JLabel.CENTER);
		// 设置图标
		jp1_lab[3].setIcon(new ImageIcon("image/Windows1/storage.png"));
		jp1_lab[3].setFont(MyTools.f6);
		jp1_lab[3].setForeground(Color.white);
		jp1_lab[3].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[3].setCursor(cursor);
		// 注册监听
		jp1_lab[3].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[3]);

		// 第五个JLabel
		jp1_lab[4] = new JLabel("货 物 管 理", JLabel.CENTER);
		// 设置图标
		jp1_lab[4].setIcon(new ImageIcon("image/Windows1/goods.png"));
		jp1_lab[4].setFont(MyTools.f6);
		jp1_lab[4].setForeground(Color.white);
		jp1_lab[4].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[4].setCursor(cursor);
		// 注册监听
		jp1_lab[4].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[4]);

		// 第六个JLabel
		jp1_lab[5] = new JLabel("出 库 管 理", JLabel.CENTER);
		// 设置图标
		jp1_lab[5].setIcon(new ImageIcon("image/Windows1/out_sto.png"));
		jp1_lab[5].setFont(MyTools.f6);
		jp1_lab[5].setForeground(Color.white);
		jp1_lab[5].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[5].setCursor(cursor);
		// 注册监听
		jp1_lab[5].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[5]);

		// 第七个JLabel
		jp1_lab[6] = new JLabel("入 库 管 理", JLabel.CENTER);
		// 设置图标
		jp1_lab[6].setIcon(new ImageIcon("image/Windows1/in_stor.png"));
		jp1_lab[6].setFont(MyTools.f6);
		jp1_lab[6].setForeground(Color.white);
		jp1_lab[6].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[6].setCursor(cursor);
		// 注册监听
		jp1_lab[6].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[6]);

		// 第八个JLable
		jp1_lab[7] = new JLabel("库 存 盘 点", JLabel.CENTER);
		jp1_lab[7].setIcon(new ImageIcon("image/Windows1/stock.png"));
		jp1_lab[7].setFont(MyTools.f6);
		jp1_lab[7].setForeground(Color.white);
		jp1_lab[7].setEnabled(false);
		// 设置选中该标签时的光标形状
		jp1_lab[7].setCursor(cursor);
		// 注册监听
		jp1_lab[7].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[7]);
		jp1.add(p1_imgPanel);

		// 处理jp2,jp3,jp4
		jp2 = new JPanel(new CardLayout());
		p3_ct = new CardLayout();
		jp3 = new JPanel(p3_ct);
		jp4 = new JPanel(new BorderLayout());

		// 设计p3的主界面背景
		try {
			p3_bg = ImageIO.read(new File("image/Windows1/jp3_bg.jpg"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		p3_impan = new ImagePanel(p3_bg);
		jp3.add(p3_impan, "0");
		// jp3.add(new JLabel("管理员"), "1");
		// 创建UserInfo对象(JPanel)
		uInfo = new UserInfo(new UserModel());
		jp3.add(uInfo, "1");
		uInfo1 = new UserInfo(new SupplierModel());
		uInfo1.setLabel("供货商名称");
		jp3.add(uInfo1, "2");
		uInfo2 = new UserInfo(new SellerModel());
		uInfo2.setLabel("销售商名称");
		jp3.add(uInfo2, "3");
		// 设计jp2
		// 设置左右缩进箭头
		jp2_lab1 = new JLabel(new ImageIcon("image/Windows1/left_arrow.png"));
		jp2_lab2 = new JLabel(new ImageIcon("image/Windows1/right_arrow.png"));
		// 把jp2_lab1、jp2_lab2加入到jp2
		jp2.add(jp2_lab1);
		jp2.add(jp2_lab2);

		// 把jp2，jp3加入到jp4
		jp4.add(jp2, "West");

		jp4.add(jp3, "Center");

		// 做一个拆分窗口,分别存放jp1,jp4
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
		// 指定左边的面板占多大
		jsp.setDividerLocation(300);
		// 把边界线设为5
		jsp.setDividerSize(5);
	}

	// 初始化状态栏
	public void initToolBar() {
		// 设计状态栏
		jp5 = new JPanel(new BorderLayout());
		// 创建Timer,每1秒就触发ActionEvent
		t = new javax.swing.Timer(1000, this);
		// 启动定时器
		t.start();
		time = new JLabel();
		time.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString() + "   ");
		time.setFont(MyTools.f3);
		// 状态栏图片
		try {
			timeBg = ImageIO.read(new File("image/Windows1/time_bg.jpg"));
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 刷新time
		this.time.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString() + "   ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// 判断用户点击哪个JLabel
		if (e.getSource() == this.jp1_lab[0]) {
			this.p3_ct.show(jp3, "1");
		} else if (e.getSource() == this.jp1_lab[1]) {

			this.p3_ct.show(jp3, "2");
		} else if (e.getSource() == this.jp1_lab[2]) {

			this.p3_ct.show(jp3, "3");
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
		// 如果某个用户选中了某个JLabel，让该JLabel高亮。
		if (e.getSource() == this.jp1_lab[0]) {
			this.jp1_lab[0].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[1]) {
			this.jp1_lab[1].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[2]) {
			this.jp1_lab[2].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[3]) {
			this.jp1_lab[3].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[4]) {
			this.jp1_lab[4].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[5]) {
			this.jp1_lab[5].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[6]) {
			this.jp1_lab[6].setEnabled(true);
		} else if (e.getSource() == this.jp1_lab[7]) {
			this.jp1_lab[7].setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// 如果某个用户鼠标离开了某个JLabel，让该JLabel禁用。
		if (e.getSource() == this.jp1_lab[0]) {
			this.jp1_lab[0].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[1]) {
			this.jp1_lab[1].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[2]) {
			this.jp1_lab[2].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[3]) {
			this.jp1_lab[3].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[4]) {
			this.jp1_lab[4].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[5]) {
			this.jp1_lab[5].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[6]) {
			this.jp1_lab[6].setEnabled(false);
		} else if (e.getSource() == this.jp1_lab[7]) {
			this.jp1_lab[7].setEnabled(false);
		}
	}

}
