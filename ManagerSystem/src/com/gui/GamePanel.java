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
 *	����һ��������ʾ��Ϸ�����
 */
public class GamePanel extends JPanel  implements  MouseListener {

	//�������
	private JLabel lab1, lab2;
	//private JButton jb1;
	
	//���췽��
	public GamePanel() {
		//�������
		lab1 = new JLabel("��ʾ�������Ϸ���ɿ�ʼ��Ϸ", new ImageIcon("image/client/GamePanel/prompt.png"), 0);
		lab2 = new JLabel("̹�˴�ս", new ImageIcon("image/client/GamePanel/TankGame.png"), 0);
		//jb1 = new JButton("̹�˴�ս", new ImageIcon("image/client/GamePanel/TankGame.png"));
		// �������͹��
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
		//�����û�ѡ�����Ϸ����Ϸ
		if(e.getSource() == this.lab2) {
			new TankGame();		//����̹�˴�ս
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
