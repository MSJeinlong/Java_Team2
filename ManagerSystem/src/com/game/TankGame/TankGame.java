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

public class TankGame extends JFrame implements ActionListener { // �̳�JFrame,�������ɴ���

	// ����һ����ʼ���
	MyStartPanel msp = null;

	// ����һ��Desktop�����ڴ򿪰����ĵ�
	Desktop desktop = null;

	// ����һ����Ϸ���
	MyPanel mp = null;

	// ���ÿ�ʼ�˵����
	JMenuBar jmb = null;
	JMenu jm1 = null, jm2 = null, jm3 = null;
	// ��Ϸ�˵���ѡ��
	JMenuItem jmt1 = null, jmt2 = null, jmt3 = null, jmt4 = null;
	// �����˵���ѡ��
	JMenuItem jmt5 = null;
	//�û��˵���ѡ��
	JMenuItem jmt6 = null;
	// �ж���������Ϸ���Ǹտ�ʼ����Ϸ
	int NewNum;

	public static void main(String[] args) {
		//GameClient tankGame = new GameClient();
		//����Ϊwindowsϵͳ���
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//������¼���� 
		//LoginDialog login = new LoginDialog();
		new TankGame();
	}

	public TankGame() {

		msp = new MyStartPanel();

		// �����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		jmt1 = new JMenuItem("����Ϸ(N)");
		jmt2 = new JMenuItem("�˳���Ϸ(E)");
		jmt3 = new JMenuItem("�����˳�(S)");
		jmt4 = new JMenuItem("������Ϸ(C)");

		// ���������˵�����ѡ��
		jm2 = new JMenu("����(H)");
		jmt5 = new JMenuItem("��Ϸ�淨");
		
		jm3 = new JMenu("�û�");
		jmt6 = new JMenuItem("�û���Ϣ");

		// ���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmt1.setMnemonic('N');
		jmt2.setMnemonic('E');
		jmt3.setMnemonic('S');
		jmt4.setMnemonic('C');

		// Ϊ���е�jmt���Ӱ�������
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

		// ѡ�����˵�
		jm1.add(jmt1);
		jm1.add(jmt4);
		jm1.add(jmt3);
		jm1.add(jmt2);
		jm2.add(jmt5);
		jm3.add(jmt6);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);

		// NewNum��Ϊ0
		NewNum = 0;

		// ��msp����Ϊ�߳�
		Thread t1 = new Thread(msp);
		t1.start();
		// ���˵���������Ϸ����
		this.setJMenuBar(jmb);
		this.add(msp);

		// ���ô��ڲ���
		this.setTitle("̹�˴�ս");
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
		// ���û���ͬ�ĵ��������ͬ�Ĵ���
		if (e.getActionCommand().equals("New Game")) {
			if (NewNum == 0) {
				// ����ս�����
				mp = new MyPanel(true);
				// ����mp�߳�
				Thread t = new Thread(mp);
				t.start();
				// ɾ���ɵ����
				this.remove(msp);
				// Ȼ����������
				this.add(mp);// ���ҵ�JPanel��ӵ�Frame��
				NewNum++;
				// ע�����
				this.addKeyListener(mp);
				// ��ʾ��ˢ��JFrame
				this.setVisible(true);
			} else {
				this.mp.NewGame();
			}

			// ���ſ�սǰ�ı�������
			PlayVoice pv = new PlayVoice("/JavaProject_Image/TankGame/start.wav");
			pv.start();
		} else if (e.getActionCommand().equals("exit")) {
			// �û��˳���Ϸ
			System.exit(0);
		} else if (e.getActionCommand().equals("saveExit")) {
			// �û�����ˡ������˳���
			Recorder.savaGame();
			System.exit(0);
		} else if (e.getActionCommand().equals("ContinueGame")) {
			// �û�����˼�����Ϸ
			// ����ս�����
			mp = new MyPanel(false);

			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ɾ���ɵ����
			this.remove(msp);
			// Ȼ����������
			this.add(mp);// ���ҵ�JPanel��ӵ�Frame��
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ��ˢ��JFrame
			this.setVisible(true);
		} else if (e.getActionCommand().equals("Help")) {

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				try {
					// �򿪰����ĵ�
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
