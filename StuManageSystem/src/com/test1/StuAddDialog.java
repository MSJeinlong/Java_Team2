package com.test1;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener {

	// ��������Ҫ�����
	JLabel[] jlb = new JLabel[6];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[5];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	String sex = null;
	
	// owner���ĸ�����
	// title ������
	// model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public StuAddDialog(Frame owner, String title, boolean model) {
		// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);
		jlb[0] = new JLabel("ѧ�ţ�");
		jlb[1] = new JLabel("������");
		jlb[2] = new JLabel("�Ա�");
		jlb[3] = new JLabel("���䣺");
		jlb[4] = new JLabel("���᣺");
		jlb[5] = new JLabel("ϵ��");

		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		// jtf[5] = new JTextField();

		// ������ѡ��
		jrb1 = new JRadioButton("��", true);
		sex = "��";	//sexĬ��Ϊ��
		jrb2 = new JRadioButton("Ů");
		jrb1.isSelected();

		jb1 = new JButton("���");
		jb2 = new JButton("ȡ��");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ���ò���
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

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
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);

		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(box);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);

		jp3.add(jb1);
		jp3.add(jb2);

		// ע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");
		
		//JRadioButtonע�����
		//jrb1.addItemListener(this);
		jrb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb1.getText();
				System.out.println("sex:"+sex);
			}
			
		});
		
		//�����ڲ��� ActionListener
		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb2.getText();
				System.out.println("sex:"+sex);
			};
			
		});

		//System.out.println("sex:"+sex);
		// ���ò���
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// չ��
		this.setBounds(1000, 400, 300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ����������ݿ���Ҫ�ı���
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		switch (e.getActionCommand()) {
		case "confirm":
			// ����SQL Server���ݿ�
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
			String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// ����Դ ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
			String Name = "sa";
			String Pwd = "junlong365";
			try {
				// ��������
				Class.forName(driverName);
				//ע���������򣬴����Ӷ���
				ct = DriverManager.getConnection(dbURL, Name, Pwd);	
				
				//����������
				String sql = "insert into student values(?,?,?,?,?,?)";
				pstmt = ct.prepareStatement(sql);
				
				
				
				//��������ֵ
				pstmt.setString(1, jtf[0].getText());
				pstmt.setString(2, jtf[1].getText());
				pstmt.setString(3, sex);
				pstmt.setString(4, jtf[2].getText());
				pstmt.setString(5, jtf[3].getText());
				pstmt.setString(6, jtf[4].getText());
				
				//ִ�в���
				pstmt.executeUpdate();
				
				//�ر���ӶԻ���
				this.dispose();
			/*	rs = ps.executeQuery();
				System.out.println("�������ݿ�ɹ�");*/
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "canel":
			this.dispose();
			break;
		}
	}
}
