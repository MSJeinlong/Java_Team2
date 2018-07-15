package com.test1;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author JunLong ���һ��Mini���ѧ������ϵͳ
 */
public class Test3 extends JFrame implements ActionListener {

	// ����һЩ�ؼ�
	JPanel jp1, jp2;
	JLabel jlb1;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;

	// rowData�������������, columnNames�������
	Vector rowData, columnNames;

	// ����������ݿ���Ҫ�ı���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test3 test3 = new Test3();
	}

	public Test3() {
		// �����ؼ�
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jlb1 = new JLabel("����������");

		// �Ѹ����ؼ����뵽jp1
		jp1.add(jlb1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp2 = new JPanel();

		jb2 = new JButton("���");
		jb3 = new JButton("�޸�");
		jb4 = new JButton("ɾ��");

		// Ϊ��ťע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("query");
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3.addActionListener(this);
		jb3.setActionCommand("alter");
		jb4.addActionListener(this);
		jb4.setActionCommand("delete");

		// �Ѹ�����ť���뵽jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		//����һ������ģ�Ͷ���
		StuModel sm = new StuModel();
		
		// ��ʼ��JTable
		jt = new JTable(sm);

		// ��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jt);

		// ��jp1��jsp��jp2�ŵ�jframe
		this.add(jp1, "North");
		this.add(jsp);
		this.add(jp2, "South");

		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		//�û������ѯ
		case "query":
			System.out.println("��ѯ");
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String name = this.jtf.getText().trim();
			//дһ��sql���
			String sql = "select * from stu where stuName='"+name+"'";
			//�����µ�����ģ���࣬������
			StuModel sm = new StuModel(sql);
			//����JTable
			jt.setModel(sm);
			break;
		//�û�������
		case "add":
			StuAddDialog sa = new StuAddDialog(this, "���ѧ��", true);
			//�����ٻ���µ�����ģ��
			break;
		case "alter":
			break;
		case "delete":
			break;
		}
	}
}
