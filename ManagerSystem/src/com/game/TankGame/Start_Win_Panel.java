package com.game.TankGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

//开始界面
class MyStartPanel extends JPanel implements Runnable {
	boolean isflicker = true;// 控制闪烁

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);

		// 提示信息
		if (isflicker) {
			g.setColor(Color.YELLOW);
			// 信息的字体设置
			Font myfont = new Font("Cambria", Font.BOLD, 20);
			g.setFont(myfont);
			g.drawString("stage:1", 150, 150);
		}

		Font myfont = new Font("宋体", Font.BOLD, 25);
		g.setFont(myfont);
		g.setColor(Color.RED);
		g.drawString("坦克大战", 150, 100);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 休眠
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
