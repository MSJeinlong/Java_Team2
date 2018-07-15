package com.game.TankGame;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * 
 * @author JunLong ��������ĶԻ��� �û����һ����뻷��ʱ������û���Ϣ��֤ͨ����������������
 */
public class ResetPwdDialog extends JDialog implements ActionListener {

	// �������
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JPasswordField jpf1, jpf2;
	private String uid;

	// ͨ�����λ���û�id
	public ResetPwdDialog(String uid) {

		this.uid = uid;

		// �������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ������ǩ
		jlb1 = new JLabel("�������룺");
		jlb2 = new JLabel("ȷ�����룺");
		jlb1.setIcon(new ImageIcon("image/login/password3.png"));
		jlb2.setIcon(new ImageIcon("image/login/password3.png"));
/*		int width = 20, height = 20;
		ImageIcon image1 = new ImageIcon("image/login/password1.png");
		ImageIcon image2 = new ImageIcon("image/login/password1.png");
		image1.setImage(image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb1.setIcon(image1);
		jlb1.setSize(width, height);
		jlb2.setIcon(image2);
		jlb2.setSize(width, height);*/

		// �ı���������
		jpf1 = new JPasswordField(15);
		jpf2 = new JPasswordField(15);

		// ������ť
		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");

		// Ϊ������ťע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// ���ò��ֹ�����
		this.setLayout(new GridLayout(3, 1));
		// jp2.setLayout(new GridLayout(2,1));

		// �����������
		jp1.add(jlb1);
		jp1.add(jpf1);
		jp2.add(jlb2);
		jp2.add(jpf2);
		jp3.add(jb1);
		jp3.add(jb2);

		// �����嵽JFrame
		// this.add(jlb3);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// ���ô�������
		this.setTitle("�һ�����");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 170, height / 2 - 130);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":
			// �ȼ�����������������Ƿ�һ��
			// ��ȡ����������������
			String pwd1 = new String(jpf1.getPassword());
			String pwd2 = new String(jpf2.getPassword());
			// ����������������һ��
			if (pwd1.equals(pwd2)) {
				// ���÷�����������
				UserModel.resetPasswd(uid, pwd1);
				// ��ʾ�û���������ɹ�
				JOptionPane.showMessageDialog(this, "��������ɹ���", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();// �˳�
			} else {
				// ���벻һ�£���ʾ�û�
				JOptionPane.showMessageDialog(this, "������������벻һ�£�", "����", JOptionPane.WARNING_MESSAGE);
			}
			break;
		case "canel":
			// �û����ȡ��ʱѯ���û��Ƿ����Ҫ�����޸�
			int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ���������޸ģ�����δ�޸ģ���", "�����޸�", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
			//�û�����ǣ����������޸�
			if (n == 0) {
				this.dispose();// �˳�
			}
			break;
		}
	}
}
