package com.test;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=school";// ����Դ ��������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "junlong365";
		try {
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(dbURL, Name, Pwd);
			System.out.println("�������ݿ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
		}
	}

}