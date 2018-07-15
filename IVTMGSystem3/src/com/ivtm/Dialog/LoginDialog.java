package com.ivtm.Dialog;

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
import javax.swing.JTextField;

import com.itvm.tools.MyTools;
import com.ivtm.view.ForgetPwdDialog;
import com.ivtm.view.Windows1;
import com.model.UserModel;

public class LoginDialog extends JDialog implements ActionListener {

	// �������
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JTextField jtf;
	private JPasswordField jpf;
	int width, height;

	public LoginDialog() {

		// �������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ������ǩ
		jlb1 = new JLabel("�˺ţ�");
		jlb1.setIcon(new ImageIcon("image/login/account1.png"));
		jlb2 = new JLabel("���룺");
		jlb2.setIcon(new ImageIcon("image/login/password3.png"));
		jlb1.setFont(MyTools.f1);
		jlb2.setFont(MyTools.f1);
		/*
		 * ImageIcon image1 = new ImageIcon("image/login/account.png"; ImageIcon image2
		 * = new ImageIcon("image/login/password1.png"); //����JLabel�ķ������Զ���image�Ĵ�С jlb1
		 * = MyTools.setImage(jlb1, image1, 20, 20); jlb2 = MyTools.setImage(jlb2,
		 * image2, 20, 20);
		 */

		// �ı���������
		jtf = new JTextField(15);
		jpf = new JPasswordField(15);

		// ������ť
		jb1 = new JButton("��¼");
		jb2 = new JButton("ע��");
		jb3 = new JButton("��������");

		// Ϊ������ťע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("Login");
		jb2.addActionListener(this);
		jb2.setActionCommand("SignUp");
		jb3.addActionListener(this);
		jb3.setActionCommand("Forget");

		// ���ò��ֹ�����
		this.setLayout(new GridLayout(3, 1));
		// jp2.setLayout(new GridLayout(2,1));

		// �����������
		jp1.add(jlb1);
		jp1.add(jtf);
		jp2.add(jlb2);
		jp2.add(jpf);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);

		// �����嵽JFrame
		// this.add(jlb3);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// ���ô�������
		this.setTitle("������ϵͳ");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 150);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ���û���ͬ�ĵ��������ͬ�ķ�Ӧ
		// System.out.println("�����");
		switch (e.getActionCommand()) {
		// ��¼
		case "Login":
			UserModel um = new UserModel();
			um.readData();
			String uid = this.jtf.getText().trim();
			String pwd = new String(this.jpf.getPassword());
			pwd.trim();
			// ����ɹ���¼
			if (um.checkUser(uid, pwd)) {
				new Windows1(); // new������
				this.dispose(); // �رյ�¼����
			} else {
				// ��¼ʧ��
				JOptionPane.showMessageDialog(this, "��¼ʧ��!\nԭ���˺Ż��������", "����", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// ע��
		case "SignUp":
			System.out.println("ע��");
			// ����ע�����
			new UserAddDialog(null, "�û�ע��", true, "SignUp");
			break;
		// ��������
		case "Forget":
			new ForgetPwdDialog();
			break;
		}
	}
}
