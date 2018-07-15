package com.ivtm.Dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.model.UserModel;

/**
 * 
 * @author JunLong ���User�Ľ��� ʵ�����User�Ĺ���
 *         UserAddDialog��̳���JDialog����ʵ����ActionListener�ӿ�
 */
public class UserAddDialog extends JDialog implements ActionListener {

	// ��������Ҫ�����
	JLabel[] jlb = new JLabel[8];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[7];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	String sex = null;
	String action;

	// owner���ĸ�����
	// title ������
	// model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public UserAddDialog(Frame owner, String title, boolean model, String action) {
		// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);

		// ����action
		this.action = action; // �����ж���ע���û���������û�

		jlb[0] = new JLabel("�˺ţ�");
		jlb[1] = new JLabel("���룺");
		jlb[2] = new JLabel("������");
		jlb[3] = new JLabel("�Ա�");
		jlb[4] = new JLabel("���䣺");
		jlb[5] = new JLabel("�ֻ����룺");
		jlb[6] = new JLabel("�������䣺");

		// �����ע�ᣬ��Ҫȷ������
		if (action.equals("SignUp")) {
			jlb[7] = new JLabel("ȷ�����룺");
			jtf[6] = new JTextField();
		}

		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField(3);
		jtf[3] = new JTextField(11);
		jtf[4] = new JTextField();
		jtf[5] = new JTextField();

		// ������ѡ��
		jrb1 = new JRadioButton("��", true);
		sex = "��"; // sexĬ��Ϊ��
		jrb2 = new JRadioButton("Ů");

		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ���ò���
		if (action.equals("add")) {
			jp1.setLayout(new GridLayout(7, 1));
			jp2.setLayout(new GridLayout(7, 1));
		} else {
			jp1.setLayout(new GridLayout(8, 1));
			jp2.setLayout(new GridLayout(8, 1));
		}

		// ��Ϊ��ѡ��������ͬʱѡ�����мӵ�һ��������ԴﵽЧ��
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(2));
		box.add(jrb1);
		box.add(jrb2);

		// ������
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		// �����ע���û�����ô��Ҫ����ȷ��������һ��
		if (action.equals("SignUp")) {
			jp1.add(jlb[7]);
		}
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);
		jp1.add(jlb[6]);

		jp2.add(jtf[0]);
		// �����ע���û�����ô��Ҫ��������
		if (action.equals("SignUp")) {
			jp2.add(jtf[6]);
		}
		jp2.add(jtf[1]);
		jp2.add(jtf[2]);
		jp2.add(box);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);
		jp2.add(jtf[5]);

		jp3.add(jb1);
		jp3.add(jb2);

		// ע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// �û�ѡ��ͬ���Ա�ѡ��ʱ��sexҲ���Ÿı�
		jrb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// �û�ѡ���е�ѡ��
				sex = jrb1.getText();
				// System.out.println("sex:"+sex);
			}

		});

		// �����ڲ��� ActionListener
		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// �û�ѡ��Ů��ѡ��
				sex = jrb2.getText();
				// System.out.println("sex:"+sex);
			};

		});

		// ���ò���
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// չ��
		this.setBounds(1000, 400, 300, 220);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �û����ȷ���������ʱ
		switch (e.getActionCommand()) {
		// �û�������Ϣ���ȷ����ťʱ
		case "confirm":
			String mess = null;
			if (action.equals("add")) {
				mess = "���";
			} else {
				mess = "ע��";
				UserModel.readData();// ��User���ݶ�ȡ��������ֹ����ظ�
			}
			String[] paras = { jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), sex, jtf[3].getText(),
					jtf[4].getText(), jtf[5].getText() };
			// ִ����Ӳ���
			UserModel um = new UserModel();
			int res = um.add(paras);

			switch (res) {
			case 1:
				JOptionPane.showMessageDialog(this, mess + "�ɹ�!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				// �ر���ӽ���
				this.dispose();
				break;
			case -1:
				// // ��������Ի���
				JOptionPane.showMessageDialog(this, mess + "ʧ��!\nԭ��������һ�������ϢΪ�գ�", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this, mess + "ʧ��!\nԭ����������", "����", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane.showMessageDialog(this, mess + "ʧ��!\nԭ���ֻ���������", "����", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane.showMessageDialog(this, mess + "ʧ��!\nԭ�򣺵�����������", "����", JOptionPane.WARNING_MESSAGE);
				break;
			case -5:
				JOptionPane.showMessageDialog(this, mess + "ʧ��!\nԭ���û��˺��ظ���", "����", JOptionPane.WARNING_MESSAGE);
				break;
			}
			break;
		// �û�������Ϣ���ȡ����ťʱ
		case "canel":
			this.dispose(); // �رմ���
			break;
		}
	}
}
