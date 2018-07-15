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
 * @author JunLong ����ϵͳ�������� ������������ 1���ҵĳɼ� 2���ҵ���ϵ�� 3���ҵĲ��񱨱� 4���ҵ���Ϸ
 */
public class MainClient extends JFrame implements ActionListener, MouseListener {

	// ������Ҫ�����
	private Image titleIcon, timeBg;
	// �˵���
	private JMenuBar jmb;
	// һ���˵�
	private JMenu jm1, jm2, jm3, jm4, jm5, jm6;
	// �����˵�
	private JMenuItem jmt1, jmt2, jmt3, jmt4, jmt5, jmt6;
	// ͼ��
	private ImageIcon jm1_icon1, jm1_icon2, jm1_icon3, jm2_icon1;
	// ������
	private JToolBar jtb;
	// ��ť
	private JButton jb1, jb2, jb3, jb4;
	// ���������JPanel
	private JPanel jp1, jp2, jp3, jp4, jp5;
	// ����ʱ����ʾ��ǩ
	private JLabel time;
	// javax.swing���е�Timer���Զ�ʱ�Ĵ���Action�¼������ǿ��������������һ������
	private Timer t;
	private ImagePanel p1_imgPanel;
	private JLabel p1_jlb1, p1_jlb2, p1_jlb3, p1_jlb4;
	// ����jp2��JLabel
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

		// �������
		try {
			// ���ô���ͼ��
			titleIcon = ImageIO.read(new File("image/client/title.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��ʼ���˵���
		//this.initMenuBar();
		/*
		 * // �������� jtb = new JToolBar(); jtb.setFloatable(false); jb1 = new JButton(new
		 * ImageIcon("image/client/Menu/info1.png")); jb2 = new JButton(new
		 * ImageIcon("image/client/Menu/changeUser.png")); jb3 = new JButton(new
		 * ImageIcon("image/client/Menu/signOut.png")); jb4 = new JButton(new
		 * ImageIcon("image/client/Menu/help.png")); // �Ѱ�ť���뵽������ jtb.add(jb1);
		 * jtb.add(jb2); jtb.add(jb3); jtb.add(jb4);
		 * 
		 * // ���������JFrame ct.add(jtb, "North");
		 */

		// �����м�����
		this.initMidPanel();

		// ����p5��壨״̬����
		this.initStatusBar();

		Container ct = this.getContentPane();
		ct.add(jp5, "South");
		ct.add(jsp1, "Center");

		// ���ô�������
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height - 33;
		this.setTitle("���˹���ϵͳ");
		this.setIconImage(titleIcon);
		this.setBounds(0, 0, w, h);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	// ��ʼ���˵���
	public void initMenuBar() {

		// ��ȡ�˵�ͼ��
		jm1_icon1 = new ImageIcon("image/client/Menu/info1.png");
		jm1_icon2 = new ImageIcon("image/client/Menu/changeUser.png");
		jm1_icon3 = new ImageIcon("image/client/Menu/signOut.png");
		jm2_icon1 = new ImageIcon("image/client/Menu/help.png");

		// ����һ���˵�
		jm1 = new JMenu("�˺Ź���");
		jm1.setFont(MyTools.f1);
		// ����jm1�Ķ����˵�
		jmt1 = new JMenuItem("�û�����", jm1_icon1);
		jmt1.setFont(MyTools.f4);
		jmt2 = new JMenuItem("�л��û�", jm1_icon2);
		jmt2.setFont(MyTools.f4);
		jmt3 = new JMenuItem("�˳�", jm1_icon3);
		jmt3.setFont(MyTools.f4);
		// ����һ���˵�jm1
		jm1.add(jmt1);
		jm1.add(jmt2);
		jm1.add(jmt3);

		jm2 = new JMenu("����");
		jm2.setFont(MyTools.f1);
		// �ı�����
		jmt4 = new JMenuItem("�ı�����", jm2_icon1);
		jmt4.setFont(MyTools.f4);
		jm2.add(jmt4);

		jmb = new JMenuBar();
		// ��һ���˵�����˵���
		jmb.add(jm1);
		jmb.add(jm2);

		// ���˵������뵽JFrame
		this.setJMenuBar(jmb);

	}

	// ��ʼ���м�Ľ������
	public void initMidPanel() {
		// ����p1���
		jp1 = new JPanel(new BorderLayout());
		Image p1_bg = null;
		try {
			p1_bg = ImageIO.read(new File("image/client/p1_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �������͹��
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel = new ImagePanel(p1_bg);
		p1_imgPanel.setLayout(new GridLayout(4, 1));
		p1_jlb1 = new JLabel("�ɼ�����", new ImageIcon("image/client/MyGrade.png"), 0);
		p1_jlb1.setFont(MyTools.f6);
		p1_jlb1.setForeground(Color.WHITE);
		p1_jlb1.setCursor(myCursor);
		p1_jlb1.addMouseListener(this);
		p1_imgPanel.add(p1_jlb1);
		//p1_jlb2 = new JLabel("ͨѶ¼", new ImageIcon("image/client/contacts.png"), 0);
	/*	p1_jlb2.setFont(MyTools.f6);
		p1_jlb2.setEnabled(false);
		p1_jlb2.setForeground(Color.WHITE);
		p1_jlb2.setCursor(myCursor);
		p1_jlb2.addMouseListener(this);*/
		//p1_imgPanel.add(p1_jlb2);
		//p1_jlb3 = new JLabel("�����˵�", new ImageIcon("image/client/finance.png"), 0);
		//p1_imgPanel.add(p1_jlb3);
/*		p1_jlb3.setFont(MyTools.f6);
		p1_jlb3.setEnabled(false);
		p1_jlb3.setForeground(Color.WHITE);
		p1_jlb3.setCursor(myCursor);
		p1_jlb3.addMouseListener(this);*/
		p1_jlb4 = new JLabel("С��Ϸ", new ImageIcon("image/client/game.png"), 0);
		p1_imgPanel.add(p1_jlb4);
		p1_jlb4.setFont(MyTools.f6);
		p1_jlb4.setEnabled(false);
		p1_jlb4.setForeground(Color.WHITE);
		p1_jlb4.setCursor(myCursor);
		p1_jlb4.addMouseListener(this);
		jp1.add(p1_imgPanel);

		// ����jp2,jp3,jp4
		jp4 = new JPanel(new BorderLayout());
		jp2 = new JPanel(new CardLayout());
		p2_lab1 = new JLabel(new ImageIcon("image/client/p2_left.png"));
		p2_lab2 = new JLabel(new ImageIcon("image/client/p2_right.png"));
		// ��p1_lab1/2���뵽jp2��
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
		//����GradeInfo����(JPanel)
		GradeInfo gInfo = new GradeInfo();
		//��jp3���뼸��JLabel
		jp3.add(gInfo, "1");
		GamePanel gamePanel = new GamePanel();
		jp3.add(gamePanel, "2");
		//jp3.add(new JLabel(new ImageIcon("image/client/p2_right.png")), "2");

		// ��jp2��jp3���뵽jp4
		jp4.add(jp2,  "West");
		jp4.add(jp3, "Center");

		// ��һ����ִ������ֱ���p1��p4
		jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
		// ָ����ߵ�����Ƕ��
		jsp1.setDividerLocation(200);
		// �ѱ߽�����Ϊ0
		jsp1.setDividerSize(0);

	}

	// ��ʼ��״̬��
	public void initStatusBar() {
		// ���״̬��
		jp5 = new JPanel(new BorderLayout());
		// ����Timer,ÿ1��ʹ���ActionEvent
		t = new Timer(1000, this);
		// ������ʱ��
		t.start();
		time = new JLabel();
		time.setText("��ǰʱ�䣺" + Calendar.getInstance().getTime().toLocaleString() + "   ");
		time.setFont(MyTools.f3);
		// ״̬��ͼƬ
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
		//�ж��û�������ĸ�����JLabel
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
		// ����û�ѡ����ĳ������JLabel�������֮
		if (e.getSource() == this.p1_jlb1) {
			this.p1_jlb1.setEnabled(true);
		}  else if (e.getSource() == this.p1_jlb4) {
			this.p1_jlb4.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// ����û�ѡ�����뿪����JLabel�����ֹ
		if (e.getSource() == this.p1_jlb1) {
			this.p1_jlb1.setEnabled(false);
		} else if (e.getSource() == this.p1_jlb4) {
			this.p1_jlb4.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.setText("��ǰʱ�䣺" + Calendar.getInstance().getTime().toLocaleString() + "   ");
	}
}
