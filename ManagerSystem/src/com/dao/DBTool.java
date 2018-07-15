package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTool {
	private static String driver;
	private static String url;
	private static String name;
	private static String password;

	static {

		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/chi";
		name = "root";
		password = "root";
	}

	// �������ݿ�ķ���
	public static Connection open() {
		Connection conn = null;
		// 1����������
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2���������ݿ�
		try {
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// �ر����ݿ�
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
