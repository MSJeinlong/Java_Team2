package com.ivtm.Dialog;
/**
 * �û���������ʱ��������֤�û���Ϣ�ĶԻ���
 * 
 */
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JTextField;

import com.model.UserModel;

public class ForgetPwdDialog extends JDialog implements ActionListener {

	// �������
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JTextField jtf1, jtf2;

	public ForgetPwdDialog() {

		// �������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ������ǩ
		jlb1 = new JLabel("��    �ţ�");
		jlb2 = new JLabel("�ֻ����룺");
		jlb1.setIcon(new ImageIcon("image/login/account1.png"));
		jlb2.setIcon(new ImageIcon("image/login/PhoneNum1.png"));
/*		int width = 20, height = 20;
		ImageIcon image1 = new ImageIcon("image/login/account.png");
		ImageIcon image2 = new ImageIcon("image/login/PhoneNum.png");
		image1.setImage(image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb1.setIcon(image1);
		jlb1.setSize(width, height);
		jlb2.setIcon(image2);
		jlb2.setSize(width, height);*/

		// �ı���������
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);

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
		jp1.add(jtf1);
		jp2.add(jlb2);
		jp2.add(jtf2);
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
		this.setLocation(width / 2 - 200, height / 2 - 150);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":
			String uid = jtf1.getText().trim();
			String Phone = jtf2.getText().trim();
			//����û���Ϣ�Ƿ���ȷ
			if(UserModel.findPwd(uid, Phone)) {
				//������������Ի���
				new ResetPwdDialog(uid);
				this.dispose();//�ɹ��޸������رնԻ���
			} else {
				//�û���Ϣ����ȷ�������û�
				JOptionPane.showMessageDialog(this, "�˺Ż��ֻ��������", "����", JOptionPane.WARNING_MESSAGE);
			}
			break;
		case "canel":
			this.dispose();
			break;
		}
	}

}
