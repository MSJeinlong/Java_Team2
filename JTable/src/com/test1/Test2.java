package com.test1;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;

/**
 * 
 * �����ݿ���ȡ����Ϣ
 */
public class Test2 extends JFrame {

	// rowData�������������, columnNames�������
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// ����������ݿ���Ҫ�ı���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 tests = new Test2();
	}

	// ���췽��
	public Test2() {
		columnNames = new Vector();
		// ����JTable����
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");

		rowData = new Vector();
		// ����SQL Server���ݿ�
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test1";// ����Դ ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "junlong365";
		try {
			// ��������
			Class.forName(driverName);
			ct = DriverManager.getConnection(dbURL, Name, Pwd);
			ps = ct.prepareStatement("select * from stu");
			rs = ps.executeQuery();
			System.out.println("�������ݿ�ɹ�");

			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				// ��ȡ���ݿ� stu�� ������
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				//���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
		} finally {
			// �ر�
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (ct != null)
					ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// ��ʼ��JTable
		jt = new JTable(rowData, columnNames);

		// ��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jt);

		// ��jsp�ŵ�jframe
		this.add(jsp);

		this.setBounds(500, 400, 400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
