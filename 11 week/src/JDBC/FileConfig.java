package JDBC;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FileConfig {
	private static String driver;
	private static String url;
	private static String name;
	private static String password;
	
	static {
		Properties pro = new Properties();
		Reader in;
		try {
			in = new FileReader("src\\connDB.properties");
			pro.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver = pro.getProperty("driver");
		url = pro.getProperty("url");
		name = pro.getProperty("name");
		password = pro.getProperty("password");
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
