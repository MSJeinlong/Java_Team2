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
	
	//连接数据库的方法
	public static Connection openDatabase() {
		Connection conn = null;
		//1、加载驱动
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、连接数据库
		try {
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据库
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
