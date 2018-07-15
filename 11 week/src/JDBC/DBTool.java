package JDBC;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBTool {
	private static final String driver;
	private static final String url;
	private static final String name;
	private static final String password;
	
	static {

		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/chi";
		name = "root";
		password = "root";
	}
	
	//�������ݿ�ķ���
	public static Connection openDatabase() {
		Connection conn = null;
		//1����������
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2���������ݿ�
		try {
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر����ݿ�
	public static void closeDatabase(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
