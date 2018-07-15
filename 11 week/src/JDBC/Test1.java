package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
	public static void main(String[] args) {
		//1�����ù������������ݿ�
		Connection conn = DBTool.openDatabase();
		//2����һ���򵥲���
		String sql = "select * from user";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				System.out.println(id+":"+name+":"+age);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3���ر����ݿ�
		DBTool.closeDatabase(conn);
	}
}
