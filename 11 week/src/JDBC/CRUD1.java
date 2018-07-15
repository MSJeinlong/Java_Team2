package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//createTable();
		insertTable();
	}
	
	//创建表操作
	private static void createTable() {
		Connection conn = FileConfig.openDatabase();
		String sql = "create table person(name varchar(10), age int)";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileConfig.closeDatabase(conn);
	}
	
	private static void insertTable() {
		Connection conn = FileConfig.openDatabase();
		String sql = "insert into person(name,age) value('hu',30)";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileConfig.closeDatabase(conn);
	}
}
