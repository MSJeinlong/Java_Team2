package com.ivtm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import com.ivtm.Dialog.LoginDialog;

public class Index extends JWindow implements Runnable {
	// �������������ص����
	JProgressBar jpb;// ���������
	JLabel jl1;// �����ڴ���ı�����һ��ͼƬ���ϲ��ǽ�����
	int width, height;// ���ڻ�ȡ��ʾ���ֱ��ʴ�С

	// ���캯��
	public Index() {
		// ������ǩ,���ڱ�ǩ�Ϸ���һ��ͼƬ
		jl1 = new JLabel(new ImageIcon("image/index/index1.jpg"));

		// ����������
		jpb = new JProgressBar();
		// ���ý���������
		jpb.setStringPainted(true);// ��ʾ��ǰ����ֵ��Ϣ
		jpb.setIndeterminate(false);// ȷ��������ִ����ɺ����ع���
		jpb.setBorderPainted(false);// ���ý������߿���ʾ
		jpb.setBackground(Color.white);// ���ý������ı���ɫ
		jpb.setForeground(new Color(67, 205, 128));// ���ý�������ǰ��ɫ

		// ������
		this.add(jl1, BorderLayout.NORTH);
		this.add(jpb, BorderLayout.SOUTH);

		// ���ô�������
		this.setSize(465, 267);
		// ���ô�����ʾ��λ��
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 150);
		// ���ô�����ʾ
		this.setVisible(true);
	}

	public void run() {

		// ����һ�����飬��Ž�������ʾʱ��Ҫ������
		int[] progressValue = { 0, 1, 5, 8, 14, 17, 26, 42, 55, 75, 86, 98, 100 };
		for (int i = 0; i < progressValue.length; i++) {
			try {
				// ����700���룬��ִ��
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(progressValue[i]);// ȡ�������еĽ���ֵ
		}
		while (true) {
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ��������ɺ�ִ����Ӧ�Ĳ��������л��������Ĵ��ڣ�ͬʱ�رս��������ڵ�
			new LoginDialog();
			// �رս���������
			this.dispose();
			break;
		}
	}
}
