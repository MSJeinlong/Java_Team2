package com.game.TankGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

//��ʼ����
class MyStartPanel extends JPanel implements Runnable {
	boolean isflicker = true;// ������˸

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		// ��ʾ��Ϣ
		if (isflicker) {
			g.setColor(Color.YELLOW);
			// ��Ϣ����������
			Font myfont = new Font("Cambria", Font.BOLD, 20);
			g.setFont(myfont);
			g.drawString("stage:1", 150, 150);
		}

		Font myfont = new Font("����", Font.BOLD, 25);
		g.setFont(myfont);
		g.setColor(Color.RED);
		g.drawString("̹�˴�ս", 150, 100);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// ����
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
