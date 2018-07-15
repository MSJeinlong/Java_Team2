package com.gui;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.TankGame.TankGame;
import com.tools.MyTools;

/**
 * 
 * @author JunLong
 *	这是一个用于显示游戏的面板
 */
public class GamePanel extends JPanel  implements  MouseListener {

	//定义组件
	private JLabel lab1, lab2;
	//private JButton jb1;
	
	//构造方法
	public GamePanel() {
		//创建组件
		lab1 = new JLabel("提示：点击游戏即可开始游戏", new ImageIcon("image/client/GamePanel/prompt.png"), 0);
		lab2 = new JLabel("坦克大战", new ImageIcon("image/client/GamePanel/TankGame.png"), 0);
		//jb1 = new JButton("坦克大战", new ImageIcon("image/client/GamePanel/TankGame.png"));
		// 设置手型光标
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		lab1.setFont(MyTools.f3);
		lab1.setCursor(cursor);
		lab1.addMouseListener(this);
		lab2.setFont(MyTools.f3);
		lab2.setCursor(cursor);
		lab2.setEnabled(false);
		lab2.addMouseListener(this);
		
		this.setLayout(new GridLayout(5, 1));
		this.add(lab1);
		this.add(lab2);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//根据用户选择的游戏打开游戏
		if(e.getSource() == this.lab2) {
			new TankGame();		//启动坦克大战
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
		if(e.getSource() == this.lab2) {
			this.lab2.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.lab2) {
			this.lab2.setEnabled(false);
		}
	}

}
