package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

//jdbc�������ݿ�
public class jdbc1 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		try {
			//1����������
			Class.forName("com.mysql.jdbc.Driver");
			//Driver driver = new com.mysql.jdbc.Driver();
			//2���������ݿ�
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chi","root","root");
			//3����һ���򵥵Ĳ���
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
			//4���ر�����
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
