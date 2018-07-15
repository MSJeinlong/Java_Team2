package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

//jdbc连接数据库
public class jdbc1 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		try {
			//1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//Driver driver = new com.mysql.jdbc.Driver();
			//2、连接数据库
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chi","root","root");
			//3、做一个简单的操作
			String sql = "select * from user";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				System.out.println(id+":"+name+":"+age);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//4、关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
