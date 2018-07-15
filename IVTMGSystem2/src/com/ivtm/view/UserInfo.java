package com.ivtm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.demo2.UserAddDialog;
import com.demo2.UserModel;
import com.itvm.tools.MyTools;

public class UserInfo extends JPanel implements ActionListener {

	// ������Ҫ�����
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JLabel jp1_lab1, jp3_lab;
	private JTextField jp1_jtf;
	private JButton jp1_jb1, jp4_jb1, jp4_jb2, jp4_jb3, jp4_jb4;
	// �����������ʾ��Ϣ��JTable
	private JTable jtb;
	// ���췽��
	private JScrollPane jsp;
	private int counts; // ��¼����
	UserModel um;

	public UserInfo() {

		// ��������jp1
		// ������Ҫ�����
		jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1_lab1 = new JLabel("���������Ա����");
		jp1_jtf = new JTextField(20);
		jp1_lab1.setFont(MyTools.f1);
		jp1_jb1 = new JButton("��ѯ");
		jp1_jb1.setFont(MyTools.f3);
		// �����Ǽ��뵽jp1
		jp1.add(jp1_lab1);
		jp1.add(jp1_jtf);
		jp1.add(jp1_jb1);

		// �����м��jp2
		um = new UserModel();
		counts = um.query("");
		jtb = new JTable();
		jtb.setModel(um);
		jp2 = new JPanel(new BorderLayout());
		jsp = new JScrollPane(jtb);
		jp2.add(jsp);

		// �����ϲ���jp5
		jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3_lab = new JLabel("�ܹ��� " + counts + " ����¼");
		jp3_lab.setFont(MyTools.f1);
		jp3.add(jp3_lab);
		jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp4_jb1 = new JButton("��ϸ��Ϣ");
		jp4_jb2 = new JButton("���");
		jp4_jb3 = new JButton("�޸�");
		jp4_jb4 = new JButton("ɾ��");
		jp4_jb1.setFont(MyTools.f3);
		jp4_jb2.setFont(MyTools.f3);
		jp4_jb3.setFont(MyTools.f3);
		jp4_jb4.setFont(MyTools.f3);
		jp4.add(jp4_jb1);
		jp4.add(jp4_jb2);
		jp4.add(jp4_jb3);
		jp4.add(jp4_jb4);
		jp5 = new JPanel(new BorderLayout());

		// Ϊ��ťע�����
		jp1_jb1.addActionListener(this);
		jp1_jb1.setActionCommand("query");
		jp4_jb1.addActionListener(this);
		jp4_jb1.setActionCommand("all");
		jp4_jb2.addActionListener(this);
		jp4_jb2.setActionCommand("add");
		jp4_jb3.addActionListener(this);
		jp4_jb3.setActionCommand("alter");
		jp4_jb4.addActionListener(this);
		jp4_jb4.setActionCommand("delete");

		// ΪJTextField��Ӽ����س����Ĺ���
		// �½�һ�������ڲ���
		jp1_jtf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) // ���س���ִ����Ӧ����;
				{
					// �����ڲ�������ⲿ��ķ���
					String name = UserInfo.this.jp1_jtf.getText().trim();
					UserInfo.this.query(name); // �û�����ؼ��ֺ󣬰���Enter�����ɽ��в�ѯ
				}
			}
		});

		jp5.add(jp3, "West");
		jp5.add(jp4, "East");

		// ���ܵ�JPanel����Ϊ�߽粼��
		this.setLayout(new BorderLayout());
		// ��jp1���뵽�ܵ�JPanel
		this.add(jp1, "North");
		this.add(jp2, "Center");
		this.add(jp5, "South");
		// this.setBackground(Color.pink);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ���û������ɾ�Ĳ�Ȳ�ͬ�İ�ť�����¼�����
		switch (e.getActionCommand()) {
		// ��ѯ
		case "query":
			// ���ò�ѯ
			String name = this.jp1_jtf.getText().trim();
			this.query(name);
			break;
		// ���
		case "add":
			UserAddDialog ua = new UserAddDialog(null, "���", true, "add");
			// ������ӶԻ���
			um.updateDialog(null, 0);
			// ��������ģ��
			// model = new UserModel();
			um = MyTools.newTclass(um.getClass());
			// model.query("");
			this.query("");
			// ����JTable
			jtb.setModel(um);
			break;
		// �޸�
		case "alter":
			int rowNum = this.jtb.getSelectedRow();
			if (rowNum == -1) {
				// �û�ûѡ���κ�һ��ʱ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "���棡", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// ��ʾ�޸ĶԻ���
			um.updateDialog(um, rowNum);
			// ��������ģ��
			um = new UserModel();
			// ͨ����ѯ����JTable
			this.query("");
			// ����JTable
			jtb.setModel(um);
			break;
		// ɾ��
		case "delete":
			int rowNum1 = this.jtb.getSelectedRow();
			if (rowNum1 == -1) {
				// �û�ûѡ���κ�һ��ʱ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "���棡", JOptionPane.WARNING_MESSAGE);
				return;
			} else { // �û�ѡ��������һ��ʱ
				// ɾ��ǰ���ٴ������û���ȷ��
				int n = JOptionPane.showConfirmDialog(null, "����Ҫ����ɾ��һ�У�ȷ��ɾ����", "ɾ������", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
				if (n == 0) { // n == 0����ʾ�û������"��"��
					UserModel.update(null, rowNum1);
					// ������ʾ��Ϣ
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					// ��������ģ��
					um = new UserModel();
					// model = MyTools.newTclass(model.getClass());
					// model.query("");
					this.query("");
					// ����JTable
					jtb.setModel(um);
				}
			}
			break;
		case "all":
			// ���ò�ѯ
			this.query("");
			break;
		}
	}

	// ����ѯ���ܽ�һ������
	public void query(String name) {

		// �����µ�User����ģ��
		um = new UserModel();
		// um = MyTools.newTclass(um.getClass());
		// ִ�в�ѯ����
		counts = um.query(name);
		// ����JTable
		jtb.setModel(um);
		// ���¼�¼��ʾ����
		jp3_lab.setText("�ܹ��� " + counts + " ����¼");
	}

	public void setLabel(String text) {
		jp1_lab1.setText("������" + text);
	}
}
