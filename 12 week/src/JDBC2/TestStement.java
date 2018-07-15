package JDBC2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Statement���,����ִ�о�̬SQL��䲢�����������ɽ���Ķ���,CRUD
public class TestStement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//createTable();
		//insert();
		//update();
		//delete();
		List<User> list = query();
		for(User user:list) {
			System.out.println(user);
		}
	}

	private static void createTable() {
		String sql = "create table student(name varchar(10), age int)";
		Connection conn = DBTool.openDatabase();
		
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeDatabase(conn);
		}
	
	}
	
	//����
	private static void insert() {
		String sql = "insert into student(name, age) value(\"����\",20)";
		Connection conn = DBTool.openDatabase();
		
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeDatabase(conn);
		}
	}
	
	//�޸�
	private static void update() {
		String sql = "update student set name = '����', age = 31 where name = '����'";
		Connection conn = DBTool.openDatabase();
		
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeDatabase(conn);
		}
	}
	
	//�޸�
	private static void delete() {
		String sql = "delete from student where name = '����'";
		Connection conn = DBTool.openDatabase();
		
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeDatabase(conn);
		}
	}
	
	//��ѯ
	private static List<User> query() {
		String sql = "select * from user";
		Connection conn = DBTool.openDatabase();
		List<User> list = new ArrayList<>();
		User user = null;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt("age") ;
				System.out.println(name+":"+age);
				//User user = new User();
/*				user.setId(id);
				user.setName(name);
				user.setAge(age);*/
				user = new User(id, name, age);
				list.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeDatabase(conn);
		}
		return list;
	}
}
