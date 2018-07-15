/**
 * ������ϵͳ������
 * ��ɽ������
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

	// ������Ҫ�����
	Image titleIcon, timeBg;
	JPanel jp1, jp2, jp3, jp4, jp5;
	// ��ʾϵͳ��ǰʱ��
	JLabel time;
	JLabel[] jp1_lab = new JLabel[8];
	// javax.swing���е�Timer���Զ�ʱ�Ĵ���Action�¼������ǿ��������������һ������
	javax.swing.Timer t;
	ImagePanel p1_imgPanel, p3_impan;
	Image p1_bg = null, p3_bg = null;
	// ��jp2��嶨����Ҫ��JLabel
	JLabel jp2_lab1, jp2_lab2;
	// ��ִ����������
	JSplitPane jsp;
	// ��Ƭ���ֿؼ�
	CardLayout p3_ct;
	// �����Ϣ��ʾ����
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
		// ��������������
		/*
		 * Index index=new Index(); //����index�߳� Thread t=new Thread(index); //�����߳�
		 * t.start();
		 */
	}

	public Windows1() {

		// ��ʼ���м����
		this.initMiddlePanel();

		// ��ʼ��״̬��
		this.initToolBar();

		// ���ô�������
		// �������
		try {
			// ����ͼ��
			titleIcon = ImageIO.read(new File("image/Windows1/title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��JFrame�л�ȡContainer
		Container ct = this.getContentPane();
		// ��JPanel����JFrame
		ct.add(jp5, "South");
		ct.add(jsp, "Center");
		// ct.add(jp2, "West");

		// ���ñ���
		this.setTitle("������ϵͳ");
		// ����ͼ��
		this.setIconImage(titleIcon);
		// ��ȡ��Ļ�Ĵ�С
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		// ���ô�С
		this.setSize(width, height - 30);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ��ʼ���м��4�����
	public void initMiddlePanel() {
		// ����м����
		jp1 = new JPanel(new BorderLayout());

		try {
			p1_bg = ImageIO.read(new File("image/Windows1/jp1_bg.jpg"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// ���ù����״Ϊ���͹��
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel = new ImagePanel(p1_bg);
		p1_imgPanel.setLayout(new GridLayout(9, 1));
		// ����JLabel
		// ����jp1�ĵ�һ��JLabel
		jp1_lab[0] = new JLabel("�� �� Ա �� Ϣ", JLabel.CENTER);
		jp1_lab[0].setIcon(new ImageIcon("image/Windows1/Admin2.png"));
		jp1_lab[0].setFont(MyTools.f6);
		jp1_lab[0].setForeground(Color.white);
		// �����һ��JLabel
		// �ø�JLabel������
		jp1_lab[0].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[0].setCursor(cursor);
		// ע�����
		jp1_lab[0].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[0]);

		// �ڶ���JLabel
		jp1_lab[1] = new JLabel("�� �� �� �� ��", JLabel.CENTER);
		jp1_lab[1].setIcon(new ImageIcon("image/Windows1/supplier.png"));
		jp1_lab[1].setFont(MyTools.f6);
		jp1_lab[1].setForeground(Color.white);
		jp1_lab[1].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[1].setCursor(cursor);
		// ע�����
		jp1_lab[1].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[1]);

		// ������JLabel
		jp1_lab[2] = new JLabel("�� �� �� �� ��", JLabel.CENTER);
		// ����ͼ��
		jp1_lab[2].setIcon(new ImageIcon("image/Windows1/seller.png"));
		// ��������
		jp1_lab[2].setFont(MyTools.f6);
		// ����������ɫ
		jp1_lab[2].setForeground(Color.white);
		jp1_lab[2].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[2].setCursor(cursor);
		// ע�����
		jp1_lab[2].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[2]);

		// ���ĸ�JLabel
		jp1_lab[3] = new JLabel("�� �� �� ��", JLabel.CENTER);
		// ����ͼ��
		jp1_lab[3].setIcon(new ImageIcon("image/Windows1/storage.png"));
		jp1_lab[3].setFont(MyTools.f6);
		jp1_lab[3].setForeground(Color.white);
		jp1_lab[3].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[3].setCursor(cursor);
		// ע�����
		jp1_lab[3].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[3]);

		// �����JLabel
		jp1_lab[4] = new JLabel("�� �� �� ��", JLabel.CENTER);
		// ����ͼ��
		jp1_lab[4].setIcon(new ImageIcon("image/Windows1/goods.png"));
		jp1_lab[4].setFont(MyTools.f6);
		jp1_lab[4].setForeground(Color.white);
		jp1_lab[4].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[4].setCursor(cursor);
		// ע�����
		jp1_lab[4].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[4]);

		// ������JLabel
		jp1_lab[5] = new JLabel("�� �� �� ��", JLabel.CENTER);
		// ����ͼ��
		jp1_lab[5].setIcon(new ImageIcon("image/Windows1/out_sto.png"));
		jp1_lab[5].setFont(MyTools.f6);
		jp1_lab[5].setForeground(Color.white);
		jp1_lab[5].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[5].setCursor(cursor);
		// ע�����
		jp1_lab[5].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[5]);

		// ���߸�JLabel
		jp1_lab[6] = new JLabel("�� �� �� ��", JLabel.CENTER);
		// ����ͼ��
		jp1_lab[6].setIcon(new ImageIcon("image/Windows1/in_stor.png"));
		jp1_lab[6].setFont(MyTools.f6);
		jp1_lab[6].setForeground(Color.white);
		jp1_lab[6].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[6].setCursor(cursor);
		// ע�����
		jp1_lab[6].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[6]);

		// �ڰ˸�JLable
		jp1_lab[7] = new JLabel("�� �� �� ��", JLabel.CENTER);
		jp1_lab[7].setIcon(new ImageIcon("image/Windows1/stock.png"));
		jp1_lab[7].setFont(MyTools.f6);
		jp1_lab[7].setForeground(Color.white);
		jp1_lab[7].setEnabled(false);
		// ����ѡ�иñ�ǩʱ�Ĺ����״
		jp1_lab[7].setCursor(cursor);
		// ע�����
		jp1_lab[7].addMouseListener(this);
		p1_imgPanel.add(jp1_lab[7]);
		jp1.add(p1_imgPanel);

		// ����jp2,jp3,jp4
		jp2 = new JPanel(new CardLayout());
		p3_ct = new CardLayout();
		jp3 = new JPanel(p3_ct);
		jp4 = new JPanel(new BorderLayout());

		// ���p3�������汳��
		try {
			p3_bg = ImageIO.read(new File("image/Windows1/jp3_bg.jpg"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		p3_impan = new ImagePanel(p3_bg);
		jp3.add(p3_impan, "0");
		// jp3.add(new JLabel("����Ա"), "1");
		// ����UserInfo����(JPanel)
		uInfo = new UserInfo(new UserModel());
		jp3.add(uInfo, "1");
		uInfo1 = new UserInfo(new SupplierModel());
		uInfo1.setLabel("����������");
		jp3.add(uInfo1, "2");
		uInfo2 = new UserInfo(new SellerModel());
		uInfo2.setLabel("����������");
		jp3.add(uInfo2, "3");
		// ���jp2
		// ��������������ͷ
		jp2_lab1 = new JLabel(new ImageIcon("image/Windows1/left_arrow.png"));
		jp2_lab2 = new JLabel(new ImageIcon("image/Windows1/right_arrow.png"));
		// ��jp2_lab1��jp2_lab2���뵽jp2
		jp2.add(jp2_lab1);
		jp2.add(jp2_lab2);

		// ��jp2��jp3���뵽jp4
		jp4.add(jp2, "West");

		jp4.add(jp3, "Center");

		// ��һ����ִ���,�ֱ���jp1,jp4
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp4);
		// ָ����ߵ����ռ���
		jsp.setDividerLocation(300);
		// �ѱ߽�����Ϊ5
		jsp.setDividerSize(5);
	}

	// ��ʼ��״̬��
	public void initToolBar() {
		// ���״̬��
		jp5 = new JPanel(new BorderLayout());
		// ����Timer,ÿ1��ʹ���ActionEvent
		t = new javax.swing.Timer(1000, this);
		// ������ʱ��
		t.start();
		time = new JLabel();
		time.setText("��ǰʱ�䣺" + Calendar.getInstance().getTime().toLocaleString() + "   ");
		time.setFont(MyTools.f3);
		// ״̬��ͼƬ
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
		// ˢ��time
		this.time.setText("��ǰʱ�䣺" + Calendar.getInstance().getTime().toLocaleString() + "   ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// �ж��û�����ĸ�JLabel
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
		// ���ĳ���û�ѡ����ĳ��JLabel���ø�JLabel������
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
		// ���ĳ���û�����뿪��ĳ��JLabel���ø�JLabel���á�
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
