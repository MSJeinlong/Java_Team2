package com.test3;

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
 * @author JunLong 
 * ���һ��Mini���ѧ������ϵͳ 
 * 1����ѯ 
 * 2����� 
 * 3��ɾ�� 
 * 4���޸�
 * �����ݵĴ�������ļ�IOʵ��
 */
public class StuManage extends JFrame implements ActionListener {

	// ����һЩ�ؼ�
	JPanel jp1, jp2;
	JLabel jlb1;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	String sql = null;

	// rowData�������������, columnNames�������
	Vector rowData, columnNames;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test3 = new StuManage();
	}

	public StuManage() {
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

		// ����һ������ģ�Ͷ���
		sm = new StuModel();
		String[] paras = new String[0];
		sm.queryStu("select * from student", paras);

		// ��ʼ��JTable
		jt = new JTable(sm);

		// ��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jt);

		// ��jp1��jsp��jp2�ŵ�jframe
		this.add(jp1, "North");
		this.add(jsp);
		this.add(jp2, "South");

		this.setBounds(700, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] paras2 = new String[0];
		switch (e.getActionCommand()) {

		// �û������ѯ
		case "query":
			System.out.println("��ѯ");
			// ��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String name = this.jtf.getText().trim();
			// дһ��sql���
			String sql = "select * from student where stuName like '"+name+"%'";
			String[] paras = {name};
			// �����µ�����ģ���࣬������
			sm = new StuModel();
			sm.queryStu(sql, paras);
			// ����JTable
			jt.setModel(sm);
			break;

		// �û�������
		case "add":
			StuAddDialog sa = new StuAddDialog(this, "���ѧ��", true);
			// �����ٻ���µ�����ģ��
			sm = new StuModel();
			sm.queryStu("select * from student", paras2);
			// ����JTable
			jt.setModel(sm);
			break;

		case "alter":
			// �û�ϣ���޸�
			System.out.println("�û�ϣ���޸�");
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// �û�ûѡ���κ�һ��ʱ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}

			// ��ʾ�޸ĶԻ���
			new StuUpdateDialog(this, "�޸�ѧ����Ϣ", true, sm, rowNum);
			// �����ٻ���µ�����ģ��
			sm = new StuModel();
			sm.queryStu("select * from student", paras2);
			// ����JTable
			jt.setModel(sm);
			break;

		// �û�ϣ��ɾ��ĳ����¼
		case "delete":
			// 1���õ���ѧ����id
			// getSelectedRow�᷵���û��������
			// ������û�һ�ж�û��ѡ�У��ͻ᷵��-1
			int rowNum1 = this.jt.getSelectedRow();
			if (rowNum1 == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			// �õ�ѧ�����
			String stuId = (String) sm.getValueAt(rowNum1, 0);

			// ����һ��sql���
			sql = "delete from student where stuId = ?";
			String[] paras1 = { stuId };
			// StuModel temp = new StuModel();
			StuModel.updateStu(sql, paras1);
			// �������ݿ�
			sm = new StuModel();		
			sm.queryStu("select * from student", paras2);
			jt.setModel(sm);
			break;
		}
	}
}
